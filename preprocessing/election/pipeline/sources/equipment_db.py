"""Equipment database loader."""

import pandas as pd
import re
from typing import Dict, Type, Optional

from ..core import DataNode, register_node


@register_node
class EquipmentDBNode(DataNode):
    """Load equipment database with manufacturer/model details."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        equipment_path = self.data_dir / 'data/CSE416-S01 Equipment Data.csv'
        df = pd.read_csv(equipment_path, dtype=str, low_memory=False)

        # Deduplicate rows with the same Manufacturer and Model Name
        df = self._deduplicate_equipment(df)

        # Calculate derived columns
        df['age'] = df.apply(self.get_age, axis=1)
        df['error_rate'] = df.apply(self.get_error_rate, axis=1)
        df['discontinued'] = df.apply(self.get_discontinued, axis=1)

        df['reliability'] = df.apply(self.get_reliability, axis=1)
        df['quality'] = df.apply(self.get_equipment_quality, axis=1)

        # Load external quality scores and merge if available
        df = self._merge_external_quality_scores(df)

        # Format error_rate
        # df['error_rate'] = df['error_rate'].apply(self.format_rate)

        # Cast age to nullable int
        df['age'] = df['age'].astype('Int64')

        # Cast discontinued to bool
        df['discontinued'] = df['discontinued'].astype(bool)

        # Rename columns
        df = df.rename(columns={
            'Manufacturer': 'make',
            'Equipment Type': 'description',
            'Model Name': 'model',
            'OS': 'operating_system',
            'Certification Level': 'cert',
            'Scanning Rate': 'scan_rate'
        })

        # Select final columns
        output_columns = [
            'make', 'description', 'model', 'age', 'operating_system',
            'cert', 'scan_rate', 'error_rate', 'reliability', 'quality', 'discontinued'
        ]

        # Filter columns that exist
        final_columns = [c for c in output_columns if c in df.columns]

        return df[final_columns]

    @staticmethod
    def _deduplicate_equipment(df: pd.DataFrame) -> pd.DataFrame:
        """Deduplicate rows with the same Manufacturer and Model Name.

        For rows with identical Manufacturer and Model Name:
        - For each column, choose the non-empty value if one exists
        - If both rows have values, keep the first one
        - If both are empty, keep empty

        Args:
            df: DataFrame with equipment data

        Returns:
            DataFrame with deduplicated rows
        """
        # Group by Manufacturer and Model Name
        grouped = df.groupby(['Manufacturer', 'Model Name'], as_index=False)

        def merge_group(group):
            """Merge a group of duplicate rows by choosing non-empty values."""
            if len(group) == 1:
                return group.iloc[0]

            # Start with the first row
            result = group.iloc[0].copy()

            # For each column, check if we should use a value from another row
            for col in group.columns:
                if col in ['Manufacturer', 'Model Name']:
                    continue  # Skip grouping columns

                # Find the first non-empty value across all rows
                for idx in range(len(group)):
                    val = group.iloc[idx][col]
                    # Check if value is not empty/NaN
                    if pd.notna(val) and str(val).strip() and str(val).lower() != 'nan':
                        result[col] = val
                        break

            return result

        # Apply the merge function to each group
        deduplicated = grouped.apply(merge_group, include_groups=False).reset_index(drop=True)

        return deduplicated

    def _merge_external_quality_scores(self, df: pd.DataFrame) -> pd.DataFrame:
        """Load quality and reliability scores from equipment_with_quality_scores.csv and merge if available.

        Matches on Manufacturer and Model Name. If a match is found, uses the external
        Reliability Rate and Quality Score values instead of the calculated ones.
        When duplicates arise from the merge, keep the row from equipment_with_quality_scores.csv.

        Args:
            df: DataFrame with equipment data and calculated quality/reliability scores

        Returns:
            DataFrame with quality and reliability scores updated from external file where available
        """
        external_path = self.data_dir / 'data/equipment_with_quality_scores.csv'
        try:
            external_df = pd.read_csv(external_path, dtype=str, low_memory=False)

            # Ensure scores are numeric
            external_df['Quality Score'] = pd.to_numeric(external_df['Quality Score'], errors='coerce')
            external_df['Reliability Rate'] = pd.to_numeric(external_df['Reliability Rate'], errors='coerce')

            # Mark external rows so we can prioritize them in case of duplicates
            external_df['_from_external'] = True

            # Perform left merge to preserve all rows from df
            # Match on Manufacturer and Model Name
            merged = df.merge(
                external_df[['Manufacturer', 'Model Name', 'Quality Score', 'Reliability Rate', '_from_external']],
                on=['Manufacturer', 'Model Name'],
                how='left'
            )

            # Fill _from_external with False for rows without external match
            merged['_from_external'] = merged['_from_external'].fillna(False)

            # Update quality score where external value is available (not null)
            if 'Quality Score' in merged.columns:
                merged.loc[merged['Quality Score'].notna(), 'quality'] = \
                    merged.loc[merged['Quality Score'].notna(), 'Quality Score']
                merged = merged.drop(columns=['Quality Score'])

            # Update reliability score where external value is available (not null)
            if 'Reliability Rate' in merged.columns:
                merged.loc[merged['Reliability Rate'].notna(), 'reliability'] = \
                    merged.loc[merged['Reliability Rate'].notna(), 'Reliability Rate']
                merged = merged.drop(columns=['Reliability Rate'])

            # Deduplicate by (Manufacturer, Model Name), choosing the row with most filled columns
            # First, count non-null values in each row (excluding the _from_external flag)
            merged['_null_count'] = merged.iloc[:, merged.columns != '_from_external'].isna().sum(axis=1)

            # Sort by: (1) _from_external (True first), (2) _null_count (fewest nulls first)
            # This ensures external rows are preferred, then rows with more data, then first occurrence
            merged = merged.sort_values(
                by=['_from_external', '_null_count'],
                ascending=[False, True]
            )

            # Keep only the first (best) occurrence of each (Manufacturer, Model Name)
            merged = merged.drop_duplicates(subset=['Manufacturer', 'Model Name'], keep='first')

            # Clean up temporary columns
            merged = merged.drop(columns=['_from_external', '_null_count'])

            return merged
        except FileNotFoundError:
            # If external file doesn't exist, return original dataframe
            return df
        except Exception as e:
            # Log error but continue with calculated values
            print(f"Warning: Could not load external quality/reliability scores: {e}")
            return df

    # @staticmethod
    # def format_rate(val: float) -> str:
    #     if pd.isna(val) or val <= 0:
    #         return ""
    #     denom = int(round(1.0 / val))
    #     return f"1/{denom:,}"

    @staticmethod
    def get_age(row: pd.Series) -> float:
        first_manufactured = str(row.get('First Manufactured', ''))
        if first_manufactured and first_manufactured.lower() != 'nan':
            match = re.search(r'\d{4}', first_manufactured)
            if match:
                year = int(match.group(0))
                return 2025 - year
        return float('nan')

    @staticmethod
    def get_error_rate(row: pd.Series) -> str:
        cert_level = str(row.get('Certification Level', '')).lower()
        if 'vvsg' in cert_level:
            if '2.0' in cert_level:
                # VVSG 2.0 target is 1 in 1,670,000
                return '<=1/1,670,000'
            # VVSG 1.0 (2005) target is 1 in 500,000
            return '<=1/500,000'
        if '2002' in cert_level or 'fec' in cert_level:
            # FEC 2002 standards
            return '<=1/1,000,000'
        if '1990' in cert_level:
            return '<=1/100,000'
        # Default for unknown/uncertified
        return ''

    @staticmethod
    def get_discontinued(row: pd.Series) -> bool:
        discontinued_val = str(row.get('Discontinued', ''))
        if discontinued_val and discontinued_val.lower() != 'nan' and discontinued_val.upper() != 'FALSE':
            return True
        return False

    @staticmethod
    def get_last_manufacture_year(row: pd.Series) -> Optional[int]:
        """Extract last manufacture year, falling back to first manufacture + 10 years."""
        last_manufactured = str(row.get('Last Manufactured', ''))
        if last_manufactured and last_manufactured.lower() != 'nan':
            match = re.search(r'\d{4}', last_manufactured)
            if match:
                return int(match.group(0))

        # Fallback to first manufactured + 10 years
        first_manufactured = str(row.get('First Manufactured', ''))
        if first_manufactured and first_manufactured.lower() != 'nan':
            match = re.search(r'\d{4}', first_manufactured)
            if match:
                year = int(match.group(0))
                return year + 10

        return None

    @staticmethod
    def get_cert_year(row: pd.Series) -> Optional[int]:
        """Extract certification year from Certification Level field."""
        cert_level = str(row.get('Certification Level', ''))
        if cert_level and cert_level.lower() != 'nan':
            if 'VVSG' in cert_level and '2.0' in cert_level:
                return 2025
            else:
                match = re.search(r'20\d{2}', cert_level)
                if match:
                    return int(match.group(0))
        return None

    @staticmethod
    def get_reliability(row: pd.Series) -> float:
        """Calculate reliability based on certification year, last manufacture year, and discontinuation status.

        - Certification year: normalized to 0-1 range using bounds 2002-2025
        - Last manufacture year: normalized to 0-1 range using bounds 1985-2020
        - Discontinuation: multiplies final score by 0.7 if discontinued
        - Fallback: returns 0.5 if no data available
        """
        cert_year = EquipmentDBNode.get_cert_year(row)
        last_mfg_year = EquipmentDBNode.get_last_manufacture_year(row)
        is_discontinued = row.get('discontinued', False)

        # If no data available, return fallback value
        if cert_year is None and last_mfg_year is None:
            return 0.5

        # Normalization bounds from actual data
        cert_min, cert_max = 2002, 2025
        mfg_min, mfg_max = 1985, 2020

        score_components = []

        # Normalize and add certification year component
        if cert_year is not None:
            norm_cert = (cert_year - cert_min) / (cert_max - cert_min)
            norm_cert = max(0.0, min(1.0, norm_cert))
            score_components.append(norm_cert)

        # Normalize and add manufacture year component
        if last_mfg_year is not None:
            norm_mfg = (last_mfg_year - mfg_min) / (mfg_max - mfg_min)
            norm_mfg = max(0.0, min(1.0, norm_mfg))
            score_components.append(norm_mfg)

        # Average the available components
        if score_components:
            reliability_score = sum(score_components) / len(score_components)
        else:
            reliability_score = 0.5

        # Apply discontinuation penalty
        if is_discontinued:
            reliability_score *= 0.7

        return round(reliability_score, 2)

    @staticmethod
    def get_equipment_quality(row: pd.Series) -> float:
        """Calculate equipment quality based on reliability, age, and security risks.

        - Reliability (50% weight): Use the calculated reliability metric as base
        - Age (25% weight): Normalize age inversely so newer equipment scores higher.
          Age bounds: 9-40 years (observed min-max from actual data)
        - Security Risks (25% weight): Check if Security Risks field is non-empty.
          Apply 0.75 multiplier if security risks present.
        - Fallback: Returns 0.5 if reliability not available
        """
        # Get reliability score (already calculated and available in row)
        reliability = row.get('reliability', 0.5)
        if pd.isna(reliability):
            reliability = 0.5

        # Get age and normalize it inversely (newer = higher score)
        # Observed age range: 9-40 years
        age = row.get('age')
        if pd.isna(age):
            # If age is NaN, use neutral weight (0.5)
            norm_age = 0.5
        else:
            # Invert age: newer equipment (low age) gets higher score
            # norm_age = 1 - (age - age_min) / (age_max - age_min)
            # With bounds 10-40, a 10-year-old gets 1.0, a 40-year-old gets 0.0
            age_min, age_max = 10, 40
            norm_age = 1.0 - ((age - age_min) / (age_max - age_min))
            norm_age = max(0.0, min(1.0, norm_age))

        # Get security risks indicator
        security_risks = str(row.get('Security Risks', ''))
        has_risks = False
        if security_risks and security_risks.lower() != 'nan':
            has_risks = True

        # Calculate weighted quality score
        # 50% reliability, 25% age, 25% security risk presence
        quality_score = (reliability * 0.6) + (norm_age * 0.3) + ((1.0 if not has_risks else 0.0) * 0.10)

        return round(quality_score, 2)

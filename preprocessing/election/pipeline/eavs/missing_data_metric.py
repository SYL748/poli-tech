"""EAVS Missing Data Metric Node."""

import pandas as pd
from typing import Dict, Type, List

from ..core import DataNode, register_node
from ..sources import EAVS2024RawCSVNode, EAVSCodebookNode
from .regions import RegionsNode


@register_node
class EAVSMissingDataNode(DataNode):
    """
    Calculate data quality metric for EAVS data.

    Metric is on a scale from 0 to 1.
    1.0 = fully complete data, 0.0 = completely missing data.
    Focuses on sections first (A-F).
    Weighted average of data quality based on section importance.
    Uses raw EAVS CSV data to distinguish valid skip (-77) from missing/data not available.
    """

    dependencies = [EAVS2024RawCSVNode, EAVSCodebookNode, RegionsNode]
    output_filename = "EAVS_Missing_Data_Metric.csv"

    # Weights based on usage frequency in other nodes
    SECTION_WEIGHTS = {
        'A': 0.30,  # Registration
        'B': 0.05,  # UOCAVA
        'C': 0.20,  # Mail Ballots
        'D': 0.00,  # Polling Places (unused)
        'E': 0.15,  # Provisional
        'F': 0.30,  # Participation/Equipment
    }

    # Values that count as missing
    MISSING_VALUES = ['Data not available', 'DATA NOT AVAILABLE']
    # Note: -77 is 'Valid skip' and is treated as a valid response (not missing)
    # NaN and empty strings are treated as missing
    # Negative integers (except -77) are treated as missing

    def _is_missing(self, x) -> bool:
        """
        Check if a value should be considered missing.

        Missing values are:
        - NaN/None
        - Empty string
        - Values in MISSING_VALUES list
        - Negative integers (except -77 which is valid skip)
        """
        if pd.isna(x):
            return True
        if x == '':
            return True
        if x in self.MISSING_VALUES:
            return True
        # Check for negative integers except -77
        try:
            if isinstance(x, (int, float)) and x < 0 and x != -77:
                return True
        except (TypeError, ValueError):
            pass
        return False

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        raw_df = inputs[EAVS2024RawCSVNode]
        codebook = inputs[EAVSCodebookNode]
        regions = inputs[RegionsNode]

        # 1. Identify numeric columns per section from codebook
        numeric_cols = codebook[codebook['Format'] == 'Numeric']['VariableName'].tolist()

        section_cols: Dict[str, List[str]] = {k: [] for k in self.SECTION_WEIGHTS.keys()}

        for col in numeric_cols:
            if col not in raw_df.columns:
                continue

            section = col[0].upper()
            if section in section_cols:
                section_cols[section].append(col)

        # 2. Calculate data quality per section per state
        df = raw_df.copy()

        results = []

        # Group by state_id
        grouped = df.groupby('state_id')

        for state_id, group in grouped:
            state_result = {}

            for section, cols in section_cols.items():
                if not cols:
                    state_result[f'section_{section}_quality'] = 1.0
                    continue

                section_data = group[cols]
                total_cells = section_data.size

                # Count complete (non-missing) cells
                missing_count = 0
                for col in cols:
                    for value in section_data[col]:
                        if self._is_missing(value):
                            missing_count += 1

                # Quality score: proportion of complete cells (1.0 = all complete, 0.0 = all missing)
                quality_rate = 1.0 - (missing_count / total_cells if total_cells > 0 else 0.0)
                state_result[f'section_{section}_quality'] = quality_rate

            results.append(state_result)

        result_df = pd.DataFrame(results)
        result_df['state_id'] = [state_id for state_id, _ in grouped]
        result_df = result_df.set_index('state_id')

        # 3. Calculate weighted average for overall data quality score
        weighted_sum = 0.0
        total_weight = 0.0

        for section, weight in self.SECTION_WEIGHTS.items():
            col_name = f'section_{section}_quality'
            weighted_sum += result_df[col_name] * weight
            total_weight += weight

        if total_weight > 0:
            result_df['data_quality_score'] = (weighted_sum / total_weight).apply(lambda x: f'{x:.2f}')
        else:
            result_df['data_quality_score'] = '0.00'

        # Return only the data_quality_score column along with the index
        return result_df[['data_quality_score']]


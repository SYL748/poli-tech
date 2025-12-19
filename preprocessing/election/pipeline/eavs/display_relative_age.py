"""Display relative age of voting equipment by state."""

import pandas as pd
import csv
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class DisplayRelativeAgeNode(DataNode):
    """Calculate average age of voting equipment by state using verified voting data."""

    dependencies = []
    output_filename = "Display_Relative_Age_of_Voting_Equipment.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        # Load verified voting machines data
        csv_path = self.data_dir / 'data/verified_voting/verifier-machines.csv'

        # Use csv.DictReader to correctly parse the CSV
        with open(csv_path) as f:
            reader = csv.DictReader(f)
            rows = list(reader)

        df = pd.DataFrame(rows)

        # Extract state FIPS code (leftmost 2 characters from FIPS code field)
        df['state_id'] = df['FIPS code'].astype(str).str[:2]

        # Convert 'First Year in Use' to numeric, coercing errors to NaN
        df['year_int'] = pd.to_numeric(df['First Year in Use'], errors='coerce')

        # Handle data entry errors: invert negative years
        df['year_int'] = df['year_int'].apply(lambda x: -x if pd.notna(x) and x < 0 else x)

        # Filter out rows with NaN year values using .notna()
        df_valid = df[df['year_int'].notna()].copy()

        # Calculate age (2024 - first year in use)
        df_valid['age'] = 2024 - df_valid['year_int'].astype(int)

        # Group by state_id and calculate simple average age per state
        result_rows = []

        # Get all unique state_ids from the data
        if len(df_valid) > 0:
            for state_id in sorted(df_valid['state_id'].unique()):
                state_data = df_valid[df_valid['state_id'] == state_id]
                avg_age = state_data['age'].mean()

                # Round to nearest integer
                avg_age_int = int(round(avg_age)) if pd.notna(avg_age) else 0

                result_rows.append({
                    'state_id': state_id,
                    'average_age_of_equipment': avg_age_int
                })

        # Create result dataframe with state_id as index
        if len(result_rows) > 0:
            result = pd.DataFrame(result_rows).set_index('state_id')
        else:
            # Return empty dataframe with correct structure
            result = pd.DataFrame(columns=['average_age_of_equipment'])
            result.index.name = 'state_id'

        return result


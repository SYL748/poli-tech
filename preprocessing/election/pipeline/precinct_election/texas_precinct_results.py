"""Texas precinct-level presidential election results loader."""

import pandas as pd
import numpy as np
from pathlib import Path
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import TexasVTDResultsNode
from .cvap_precinct_texas import CVAPPrecinctTexasNode


@register_node
class TexasPrecinctResultsNode(DataNode):
    """Load and process Texas 2024 precinct-level presidential election results.

    Extracts presidential race results from the Texas VTD shapefile/data,
    and computes Democratic and Republican vote percentages.

    Joins with CVAPPrecinctTexasNode to include demographic percentages for each precinct.

    Output has two rows per precinct - one for Democratic and one for Republican.

    Output columns:
    - county: County name
    - precinct_name: Precinct name
    - party: Political party (Democratic or Republican)
    - vote_percent: Percentage of votes for this party in this precinct
    - white_pct: Percentage of White CVAP (from CVAPPrecinctTexasNode)
    - hispanic_pct: Percentage of Hispanic CVAP (from CVAPPrecinctTexasNode)
    - african_american_pct: Percentage of African American CVAP (from CVAPPrecinctTexasNode)
    """

    dependencies = [TexasVTDResultsNode, CVAPPrecinctTexasNode]
    output_filename = "Texas_Precinct_Results.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        vtd_data = inputs[TexasVTDResultsNode]
        cvap_precinct = inputs[CVAPPrecinctTexasNode]

        # vtd_data is a GeoDataFrame, but we only need the attributes
        df = pd.DataFrame(vtd_data.drop(columns='geometry'))

        # Columns from README:
        # G24PREDHAR: Harris (D) President
        # G24PRERTRU: Trump (R) President
        # G24PREGSTE: Stein (G)
        # G24PRELOLI: Oliver (L)
        # G24PREOWRI: Write-In

        # Calculate total votes for presidential race
        pres_cols = ['G24PREDHAR', 'G24PRERTRU', 'G24PREGSTE', 'G24PRELOLI', 'G24PREOWRI']

        # Ensure columns exist (handle potential case sensitivity or missing cols)
        available_cols = [c for c in pres_cols if c in df.columns]

        # Convert to numeric
        for col in available_cols:
            df[col] = pd.to_numeric(df[col], errors='coerce').fillna(0)

        df['total_votes'] = df[available_cols].sum(axis=1)

        # Calculate percentages
        # Handle division by zero
        df['dem_percent'] = np.where(
            df['total_votes'] > 0,
            (df['G24PREDHAR'] / df['total_votes'] * 100).round(2),
            0
        )

        df['rep_percent'] = np.where(
            df['total_votes'] > 0,
            (df['G24PRERTRU'] / df['total_votes'] * 100).round(2),
            0
        )

        # Prepare for merge
        # We use precinct_id which we standardized in TexasVTDResultsNode

        # Merge with CVAP data
        merged = df.merge(
            cvap_precinct[['precinct_id', 'white_pct', 'hispanic_pct', 'african_american_pct']],
            on='precinct_id',
            how='left'
        )

        # For unmatched precincts, use county-level averages from CVAP
        # Calculate county averages from the CVAP data
        county_avg = cvap_precinct.groupby('county_fips').agg({
            'white_pct': 'mean',
            'hispanic_pct': 'mean',
            'african_american_pct': 'mean'
        }).reset_index()
        county_avg.columns = ['county_fips', 'white_pct_avg', 'hispanic_pct_avg',
                             'african_american_pct_avg']

        merged = merged.merge(county_avg, on='county_fips', how='left')

        # Fill missing demographic data with county averages
        for demo in ['white', 'hispanic', 'african_american']:
            col = f'{demo}_pct'
            avg_col = f'{demo}_pct_avg'
            merged[col] = merged[col].fillna(merged[avg_col])

        # Round CVAP percentages
        for demo in ['white', 'hispanic', 'african_american']:
            col = f'{demo}_pct'
            merged[col] = merged[col].round(2)

        # Create result rows
        results = []

        for _, row in merged.iterrows():
            # Common data
            base_data = {
                'county': row['County'],
                'county_fips': row['county_fips'],
                'precinct_name': row['precinct_name'],
                'precinct_id': row['precinct_id'],
                'total_votes': int(row['total_votes']),
                'white_pct': row['white_pct'],
                'hispanic_pct': row['hispanic_pct'],
                'african_american_pct': row['african_american_pct']
            }

            # Democratic row
            dem_data = base_data.copy()
            dem_data['party'] = 'Democratic'
            dem_data['vote_percent'] = row['dem_percent']
            dem_data['votes'] = int(row['G24PREDHAR'])
            results.append(dem_data)

            # Republican row
            rep_data = base_data.copy()
            rep_data['party'] = 'Republican'
            rep_data['vote_percent'] = row['rep_percent']
            rep_data['votes'] = int(row['G24PRERTRU'])
            results.append(rep_data)

        results_df = pd.DataFrame(results)
        print(f"    Processed {len(results_df)} result rows for Texas")

        # Export to JSON
        json_dir = self.data_dir / 'output/json'
        json_dir.mkdir(parents=True, exist_ok=True)
        json_path = json_dir / 'Texas_Precinct_Results.json'
        results_df.to_json(json_path, orient='records', indent=2)
        print(f"    Exported Texas precinct results to {json_path}")

        return results_df


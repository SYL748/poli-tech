"""Alabama precinct-level presidential election results loader."""

import pandas as pd
import numpy as np
from pathlib import Path
from typing import Dict, Type

from ..core import DataNode, register_node
from .cvap_precinct import CVAPPrecinctNode


@register_node
class AlabamaPrecinctResultsNode(DataNode):
    """Load and process Alabama 2024 precinct-level presidential election results.

    Reads all .xls files from data/2024_election/alabama/, extracts presidential
    race results per precinct, and computes Democratic and Republican vote percentages.

    Joins with CVAPPrecinctNode to include demographic percentages for each precinct.

    Output has two rows per precinct - one for Democratic and one for Republican.

    Output columns:
    - county: County name
    - precinct_name: Precinct name from election data
    - party: Political party (Democratic or Republican)
    - vote_percent: Percentage of votes for this party in this precinct
    - white_pct: Percentage of White CVAP (from CVAPPrecinctNode)
    - hispanic_pct: Percentage of Hispanic CVAP (from CVAPPrecinctNode)
    - african_american_pct: Percentage of African American CVAP (from CVAPPrecinctNode)
    """

    dependencies = [CVAPPrecinctNode]
    output_filename = "Alabama_Precinct_Results.csv"

    # Alabama county FIPS codes mapping
    # Source: https://www.census.gov/library/reference/code-lists/ansi.html
    COUNTY_FIPS = {
        'Autauga': '01001', 'Baldwin': '01003', 'Barbour': '01005', 'Bibb': '01007',
        'Blount': '01009', 'Bullock': '01011', 'Butler': '01013', 'Calhoun': '01015',
        'Chambers': '01017', 'Cherokee': '01019', 'Chilton': '01021', 'Choctaw': '01023',
        'Clarke': '01025', 'Clay': '01027', 'Cleburne': '01029', 'Coffee': '01031',
        'Colbert': '01033', 'Conecuh': '01035', 'Coosa': '01037', 'Covington': '01039',
        'Crenshaw': '01041', 'Cullman': '01043', 'Dale': '01045', 'Dallas': '01047',
        'DeKalb': '01049', 'Elmore': '01051', 'Escambia': '01053', 'Etowah': '01055',
        'Fayette': '01057', 'Franklin': '01059', 'Geneva': '01061', 'Greene': '01063',
        'Hale': '01065', 'Henry': '01067', 'Houston': '01069', 'Jackson': '01071',
        'Jefferson': '01073', 'Lamar': '01075', 'Lauderdale': '01077', 'Lawrence': '01079',
        'Lee': '01081', 'Limestone': '01083', 'Lowndes': '01085', 'Macon': '01087',
        'Madison': '01089', 'Marengo': '01091', 'Marion': '01093', 'Marshall': '01095',
        'Mobile': '01097', 'Monroe': '01099', 'Montgomery': '01101', 'Morgan': '01103',
        'Perry': '01105', 'Pickens': '01107', 'Pike': '01109', 'Randolph': '01111',
        'Russell': '01113', 'Shelby': '01117', 'StClair': '01115', 'Sumter': '01119',
        'Talladega': '01121', 'Tallapoosa': '01123', 'Tuscaloosa': '01125', 'Walker': '01127',
        'Washington': '01129', 'Wilcox': '01131', 'Winston': '01133',
        # Handle variations in naming
        'St. Clair': '01115', 'Saint Clair': '01115',
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        cvap_precinct = inputs[CVAPPrecinctNode]

        # Directory containing Alabama election XLS files
        election_dir = self.data_dir / 'data/2024_election/alabama'

        all_results = []

        # Process each county XLS file
        for xls_file in sorted(election_dir.glob('2024-General-*.xls')):
            # Extract county name from filename (e.g., "2024-General-Autauga.xls" -> "Autauga")
            county_name = xls_file.stem.replace('2024-General-', '')
            county_fips = self.COUNTY_FIPS.get(county_name)

            if not county_fips:
                print(f"    Warning: Unknown county '{county_name}', skipping")
                continue

            try:
                # Read the Excel file
                df = pd.read_excel(xls_file, sheet_name='Precinct Results')

                # Find presidential race rows
                pres_mask = df['Contest Title'].str.contains(
                    'PRESIDENT AND VICE PRESIDENT',
                    case=False,
                    na=False
                )
                pres_df = df[pres_mask].copy()

                if pres_df.empty:
                    print(f"    Warning: No presidential race found in {xls_file.name}")
                    continue

                # Get Democratic and Republican candidate rows
                dem_row = pres_df[pres_df['Party'] == 'DEM']
                rep_row = pres_df[pres_df['Party'] == 'REP']

                if dem_row.empty or rep_row.empty:
                    print(f"    Warning: Missing DEM or REP in {xls_file.name}")
                    continue

                # Get precinct columns (exclude metadata columns)
                metadata_cols = ['Contest Title', 'Party', 'Candidate']
                precinct_cols = [col for col in df.columns if col not in metadata_cols]

                # Extract votes for each precinct
                for precinct_col in precinct_cols:
                    # Skip ABSENTEE and PROVISIONAL for now (aggregate separately if needed)
                    if precinct_col in ['ABSENTEE', 'PROVISIONAL']:
                        continue

                    dem_votes = dem_row[precinct_col].values[0]
                    rep_votes = rep_row[precinct_col].values[0]

                    # Get all votes for this precinct in presidential race
                    total_votes = pres_df[precinct_col].sum()

                    # Skip precincts with no votes
                    if pd.isna(total_votes) or total_votes == 0:
                        continue

                    # Handle NaN values
                    dem_votes = 0 if pd.isna(dem_votes) else dem_votes
                    rep_votes = 0 if pd.isna(rep_votes) else rep_votes

                    # Calculate percentages
                    dem_percent = (dem_votes / total_votes * 100) if total_votes > 0 else 0
                    rep_percent = (rep_votes / total_votes * 100) if total_votes > 0 else 0

                    all_results.append({
                        'county': county_name,
                        'county_fips': county_fips,
                        'precinct_name': precinct_col,
                        'dem_votes': int(dem_votes),
                        'rep_votes': int(rep_votes),
                        'total_votes': int(total_votes),
                        'dem_percent': round(dem_percent, 2),
                        'rep_percent': round(rep_percent, 2),
                    })

            except Exception as e:
                print(f"    Error processing {xls_file.name}: {e}")
                continue

        # Create results DataFrame
        results_df = pd.DataFrame(all_results)
        print(f"    Loaded {len(results_df)} precinct results from {len(results_df['county'].unique())} counties")

        # Merge with CVAP precinct data
        # First, try to match precincts by county and name
        # The CVAP precinct data has precinct_id (VTD GEOID) and precinct_name

        # Normalize precinct names for matching
        results_df['precinct_name_normalized'] = results_df['precinct_name'].str.upper().str.strip()
        cvap_precinct['precinct_name_normalized'] = cvap_precinct['precinct_name'].str.upper().str.strip()

        # Merge on county_fips and normalized precinct name
        merged = results_df.merge(
            cvap_precinct[['county_fips', 'precinct_name_normalized', 'precinct_id',
                          'white_pct', 'hispanic_pct', 'african_american_pct']],
            on=['county_fips', 'precinct_name_normalized'],
            how='left'
        )

        # For unmatched precincts, use county-level averages from CVAP
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

        # Round CVAP percentages to 2 decimals
        for demo in ['white', 'hispanic', 'african_american']:
            col = f'{demo}_pct'
            merged[col] = merged[col].round(2)

        # Drop helper columns
        merged = merged.drop(columns=['precinct_name_normalized',
                                      'white_pct_avg', 'hispanic_pct_avg',
                                      'african_american_pct_avg'])

        # Create two rows per precinct (one for Democratic, one for Republican)
        dem_rows = merged.copy()
        dem_rows['party'] = 'Democratic'
        dem_rows['vote_percent'] = dem_rows['dem_percent']

        rep_rows = merged.copy()
        rep_rows['party'] = 'Republican'
        rep_rows['vote_percent'] = rep_rows['rep_percent']

        # Combine and select output columns
        result = pd.concat([dem_rows, rep_rows], ignore_index=True)

        # Reorder columns
        output_cols = [
            'county', 'county_fips', 'precinct_name', 'party', 'vote_percent',
            'white_pct', 'hispanic_pct', 'african_american_pct'
        ]

        # Ensure all columns exist
        for col in output_cols:
            if col not in result.columns:
                result[col] = None

        result = result[output_cols].copy()

        # Sort by county, precinct, then party
        result = result.sort_values(['county', 'precinct_name', 'party']).reset_index(drop=True)

        # Export to JSON
        json_dir = self.data_dir / 'output/json'
        json_dir.mkdir(parents=True, exist_ok=True)
        json_path = json_dir / 'Alabama_Precinct_Results.json'
        result.to_json(json_path, orient='records', indent=2)
        print(f"    Exported Alabama precinct results to {json_path}")

        return result


"""CVAP demographic data aggregated to Texas precinct level using spatial intersection."""

import pandas as pd
import geopandas as gpd
import numpy as np
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import CVAPBlockGroupTexasNode, TexasBlockGroupsNode, TexasVTDResultsNode


@register_node
class CVAPPrecinctTexasNode(DataNode):
    """Aggregate CVAP block group demographic data to Texas voting precinct level.

    Uses geopandas spatial overlay to compute the intersection between block groups
    and voting districts. CVAP demographic data is then aggregated to precinct level
    weighted by the intersection area.

    Output columns:
    - precinct_id: Unique precinct identifier
    - county_fips: 5-digit county FIPS code
    - precinct_name: Human-readable precinct name
    - white_pct: Percentage of White Alone CVAP
    - hispanic_pct: Percentage of Hispanic or Latino CVAP
    - african_american_pct: Percentage of Black or African American Alone CVAP
    - total_cvap: Total CVAP population in precinct
    """

    dependencies = [CVAPBlockGroupTexasNode, TexasBlockGroupsNode, TexasVTDResultsNode]
    output_filename = None

    # Demographic groups to analyze (maps CVAP lntitle to output column names)
    DEMOGRAPHIC_GROUPS = {
        'White Alone': 'white',
        'Hispanic or Latino': 'hispanic',
        'Black or African American Alone': 'african_american',
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        cvap_df = inputs[CVAPBlockGroupTexasNode]
        block_groups_gdf = inputs[TexasBlockGroupsNode]
        voting_districts_gdf = inputs[TexasVTDResultsNode]

        # Convert to equal-area projection for accurate area calculations (Texas Statewide Mapping System)
        # EPSG:3081 (NAD83 / Texas Centric Albers Equal Area) or EPSG:32139 (Texas State Plane)
        # Using EPSG:3081 for equal area properties suitable for statewide analysis
        equal_area_crs = 'EPSG:3081'

        block_groups_proj = block_groups_gdf.to_crs(equal_area_crs)
        voting_districts_proj = voting_districts_gdf.to_crs(equal_area_crs)

        # Calculate original block group areas
        block_groups_proj['bg_area'] = block_groups_proj.geometry.area

        # Compute spatial overlay (intersection) between block groups and voting districts
        print("    Computing spatial intersection between block groups and voting districts...")
        # Keep only necessary columns to reduce memory usage
        intersection = gpd.overlay(
            block_groups_proj[['block_group_fips', 'county_fips', 'bg_area', 'geometry']],
            voting_districts_proj[['precinct_id', 'precinct_name', 'geometry']],
            how='intersection'
        )

        # Calculate intersection area and weight (fraction of block group in precinct)
        intersection['intersection_area'] = intersection.geometry.area
        intersection['area_weight'] = intersection['intersection_area'] / intersection['bg_area']

        # Prepare CVAP data: pivot to get demographic values per block group
        # Filter for relevant demographic groups and Total
        demo_filter = list(self.DEMOGRAPHIC_GROUPS.keys()) + ['Total']
        cvap_filtered = cvap_df[cvap_df['lntitle'].isin(demo_filter)].copy()
        cvap_filtered['cvap_est'] = pd.to_numeric(cvap_filtered['cvap_est'], errors='coerce').fillna(0)

        # Pivot to wide format: one row per block group with columns for each demographic
        cvap_pivot = cvap_filtered.pivot_table(
            index='block_group_fips',
            columns='lntitle',
            values='cvap_est',
            aggfunc='first'
        ).reset_index()
        cvap_pivot.columns.name = None

        # Rename demographic columns
        rename_map = {demo: f'{short}_cvap' for demo, short in self.DEMOGRAPHIC_GROUPS.items()}
        rename_map['Total'] = 'total_cvap'
        cvap_pivot = cvap_pivot.rename(columns=rename_map)

        # Merge CVAP data with intersection geometries
        intersection_with_cvap = intersection.merge(
            cvap_pivot,
            on='block_group_fips',
            how='left'
        )

        # Weight CVAP values by area fraction
        cvap_columns = [f'{short}_cvap' for short in self.DEMOGRAPHIC_GROUPS.values()] + ['total_cvap']
        for col in cvap_columns:
            if col in intersection_with_cvap.columns:
                intersection_with_cvap[f'weighted_{col}'] = (
                    intersection_with_cvap[col].fillna(0) * intersection_with_cvap['area_weight']
                )

        # Aggregate weighted CVAP values to precinct level
        weighted_cols = [f'weighted_{col}' for col in cvap_columns]
        precinct_cvap = intersection_with_cvap.groupby(['precinct_id', 'precinct_name']).agg({
            'county_fips': 'first',
            **{col: 'sum' for col in weighted_cols}
        }).reset_index()

        # Rename weighted columns back
        precinct_cvap = precinct_cvap.rename(columns={
            f'weighted_{col}': col for col in cvap_columns
        })

        # Calculate demographic percentages
        for short in self.DEMOGRAPHIC_GROUPS.values():
            cvap_col = f'{short}_cvap'
            pct_col = f'{short}_pct'
            precinct_cvap[pct_col] = np.where(
                precinct_cvap['total_cvap'] > 0,
                (precinct_cvap[cvap_col] / precinct_cvap['total_cvap'] * 100).round(2),
                0
            )

        # Select output columns
        output_cols = [
            'precinct_id', 'county_fips', 'precinct_name', 'total_cvap',
            'white_pct', 'hispanic_pct', 'african_american_pct'
        ]
        result = precinct_cvap[output_cols].copy()

        print(f"    Aggregated CVAP data for {len(result)} precincts")
        return result


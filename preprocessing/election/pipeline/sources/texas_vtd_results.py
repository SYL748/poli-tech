"""Texas VTD shapefile and election results loader."""

import pandas as pd
import geopandas as gpd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class TexasVTDResultsNode(DataNode):
    """Load Texas VTD shapefile with embedded election results.

    Loads the shapefile containing both VTD boundaries and 2024 general election results.

    Relevant columns from README:
    - UNIQUE_ID: Unique ID for each precinct
    - COUNTYFP: County FIP identifier
    - County: County Name
    - TX_VTD: Texas Voting Tabulation District ID
    - G24PREDHAR: Harris (D) President
    - G24PRERTRU: Trump (R) President
    """

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> gpd.GeoDataFrame:
        shapefile_path = self.data_dir / 'data/preclearance/tx_2024_gen_all_tx_vtd.shp'
        gdf = gpd.read_file(shapefile_path)

        # Ensure consistent CRS (use EPSG:4326 for lat/lon)
        if gdf.crs is None:
            gdf = gdf.set_crs('EPSG:4326')
        else:
            gdf = gdf.to_crs('EPSG:4326')

        # Standardize column names for downstream use
        # Create a precinct_id that is unique state-wide.
        # The README says UNIQUE_ID is unique, but let's also construct one from County + VTD if needed.
        # Looking at Alabama implementation, precinct_id was GEOID20.
        # Here we can use UNIQUE_ID or construct a FIPS-like ID if available.
        # The shapefile has COUNTYFP and TX_VTD.

        # Ensure COUNTYFP is 3 digits (it might be numeric in shapefile)
        # If it's already string, pad it. If numeric, convert and pad.
        # Assuming it's the 3 digit county code.

        # Let's inspect the data types if we could, but we can't run code yet.
        # We'll assume standard shapefile behavior.

        gdf['county_fips'] = '48' + gdf['COUNTYFP'].astype(str).str.zfill(3)

        # Construct a precinct_id.
        # TX_VTD might be unique within county.
        gdf['precinct_id'] = gdf['UNIQUE_ID']
        gdf['precinct_name'] = gdf['TX_VTD'] # Use VTD ID as name for now, or maybe 'County' + 'TX_VTD'

        return gdf


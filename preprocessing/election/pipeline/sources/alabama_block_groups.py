"""Alabama block group shapefile loader."""

import pandas as pd
import geopandas as gpd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class AlabamaBlockGroupsNode(DataNode):
    """Load Alabama block group shapefile.

    Loads the Census TIGER/Line block group shapefile for Alabama (FIPS 01).
    The shapefile contains geometry for each block group which can be used
    for spatial joins with voting district boundaries.
    """

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> gpd.GeoDataFrame:
        shapefile_path = self.data_dir / 'data/block_groups/tl_2025_01_bg.shp'
        gdf = gpd.read_file(shapefile_path)

        # Ensure consistent CRS (use EPSG:4326 for lat/lon)
        if gdf.crs is None:
            gdf = gdf.set_crs('EPSG:4326')
        else:
            gdf = gdf.to_crs('EPSG:4326')

        # The GEOID field contains the 12-digit block group FIPS code
        # Format: STATEFP (2) + COUNTYFP (3) + TRACTCE (6) + BLKGRPCE (1)
        gdf['block_group_fips'] = gdf['GEOID']
        gdf['county_fips'] = gdf['STATEFP'] + gdf['COUNTYFP']

        return gdf


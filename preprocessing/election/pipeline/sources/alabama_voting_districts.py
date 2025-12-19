"""Alabama voting district shapefile loader."""

import pandas as pd
import geopandas as gpd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class AlabamaVotingDistrictsNode(DataNode):
    """Load Alabama voting district (VTD) shapefile.

    Loads the Census TIGER/Line voting district shapefile for Alabama (FIPS 01).
    VTDs represent voting precincts/districts used for elections.
    """

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> gpd.GeoDataFrame:
        shapefile_path = self.data_dir / 'data/voting_districts/tl_2020_01_vtd20.shp'
        gdf = gpd.read_file(shapefile_path)

        # Ensure consistent CRS (use EPSG:4326 for lat/lon)
        if gdf.crs is None:
            gdf = gdf.set_crs('EPSG:4326')
        else:
            gdf = gdf.to_crs('EPSG:4326')

        # VTD shapefile fields:
        # STATEFP20: State FIPS code
        # COUNTYFP20: County FIPS code
        # VTDST20: VTD code within county
        # GEOID20: Full VTD identifier (state + county + vtd)
        # NAME20: VTD name
        # NAMELSAD20: VTD name with legal/statistical area description

        gdf['precinct_id'] = gdf['GEOID20']
        gdf['county_fips'] = gdf['STATEFP20'] + gdf['COUNTYFP20']
        gdf['precinct_name'] = gdf['NAME20']

        return gdf


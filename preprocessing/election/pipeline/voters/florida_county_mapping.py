"""Florida county code to region ID mapping."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import FloridaCountyCodesNode
from ..eavs import RegionsNode
@register_node
class FloridaCountyMappingNode(DataNode):
    """Create mapping from Florida county codes to region IDs (intermediate node)."""
    dependencies = [RegionsNode, FloridaCountyCodesNode]
    output_filename = None
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        regions = inputs[RegionsNode]
        fl_county_codes = inputs[FloridaCountyCodesNode]
        df_fl_regions = regions[regions['state_id'] == '12'].copy().set_index('geo_id')
        df_fl_regions['county_code'] = df_fl_regions['name'].map(
            fl_county_codes.set_index('County Name')['County Code'])
        fl_county_to_region = pd.Series(df_fl_regions.index, name='geo_id', index=df_fl_regions['county_code'])
        result = fl_county_to_region.to_frame().reset_index()
        result.columns = ['county_code', 'geo_id']
        return result

"""Regions (jurisdictions) extraction from EAVS data."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import EAVS2024Node
@register_node
class RegionsNode(DataNode):
    """Extract regions (jurisdictions) from EAVS data."""
    dependencies = [EAVS2024Node]
    output_filename = "EAVS_Region.csv"
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = df[['Jurisdiction_Name', 'state_id', 'FIPSCode']].copy()
        regions = regions.rename_axis('id').rename(columns={
            'Jurisdiction_Name': 'name',
            'FIPSCode': 'geo_id'
        })
        regions['name'] = regions['name'].apply(lambda x: x.title() if isinstance(x, str) else x)
        regions['geo_id'] = regions['geo_id'].apply(lambda x: x[:5] if x and len(str(x)) >= 5 else x)
        return regions

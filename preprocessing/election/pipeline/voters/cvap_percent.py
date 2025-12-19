"""CVAP percentage calculation by state."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import CVAPCountyNode, EAVS2024Node
from ..eavs import RegionsNode
@register_node
class CVAPPercentNode(DataNode):
    """Calculate CVAP percentage by state."""
    dependencies = [CVAPCountyNode, RegionsNode, EAVS2024Node]
    output_filename = "Political_Party_State_Percent.csv"
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        cvap_county = inputs[CVAPCountyNode]
        regions = inputs[RegionsNode]
        df = inputs[EAVS2024Node]
        cvap_wide = cvap_county[['geoname', 'geoid', 'lntitle', 'cvap_est']].pivot_table(
            index=['geoname', 'geoid'], columns='lntitle', values='cvap_est', aggfunc='first'
        ).reset_index()
        cvap_wide.columns.name = None
        cvap_wide['county'] = cvap_wide['geoname'].str.split(',').str[0].str.strip()
        cvap_wide['state'] = cvap_wide['geoname'].str.split(',').str[1].str.strip()
        cvap_wide['geoid'] = cvap_wide['geoid'].apply(lambda x: str(x)[-5:] if pd.notna(x) else None)
        regions_reset = regions.reset_index().rename(columns={'id': 'region_id'})
        cvap_regions = regions_reset.merge(cvap_wide[['geoid', 'county', 'state', 'Total']],
            left_on='geo_id', right_on='geoid', how='left')
        reg_df = df['A1a'].rename_axis('region_id').rename('reg').reset_index()
        reg_df['reg'] = pd.to_numeric(reg_df['reg'], errors='coerce').fillna(0).astype(int)
        county_regions = cvap_regions[cvap_regions['name'].str.endswith('County')]
        cvap_eligible = county_regions.merge(reg_df, on='region_id', how='left')
        cvap_eligible = cvap_eligible.groupby('state').agg({'state_id': 'first', 'Total': 'sum', 'reg': 'sum'}).reset_index()
        cvap_eligible['CVAP_Percent'] = ((cvap_eligible['reg'] / cvap_eligible['Total']) * 100).round(2)
        result = cvap_eligible.set_index('state_id').rename(columns={'state': 'name'})
        return result[['name', 'CVAP_Percent']]

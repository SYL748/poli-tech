"""Florida voter registration summary by county."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import FloridaVoterFilesNode, CVAPCountyNode
from .florida_county_mapping import FloridaCountyMappingNode
from ..eavs import RegionsNode
from ...util import calculate_percentage
from pathlib import Path


@register_node
class FloridaVoterSummaryNode(DataNode):
    """Aggregate Florida voter registration by county."""
    dependencies = [FloridaVoterFilesNode, FloridaCountyMappingNode, CVAPCountyNode, RegionsNode]
    output_filename = "fl_voter_summary.csv"
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        fl_voter_df = inputs[FloridaVoterFilesNode]
        fl_county_mapping = inputs[FloridaCountyMappingNode]
        cvap_df = inputs[CVAPCountyNode]
        regions_df = inputs[RegionsNode]

        county_to_geo = fl_county_mapping.set_index('county_code')['geo_id'].to_dict()
        fl_voter_df['geo_id'] = fl_voter_df['County Code'].map(county_to_geo)
        fl_voter_summary = pd.DataFrame()
        fl_voter_summary['total_num_registered_voters'] = fl_voter_df.groupby('geo_id').size()
        fl_voter_summary['total_num_republican'] = fl_voter_df[fl_voter_df['Party Affiliation'] == 'REP'].groupby('geo_id').size()
        fl_voter_summary['total_num_democratic'] = fl_voter_df[fl_voter_df['Party Affiliation'] == 'DEM'].groupby('geo_id').size()
        fl_voter_summary['total_num_unaffiliated'] = fl_voter_df[fl_voter_df['Party Affiliation'] == 'NPA'].groupby('geo_id').size()
        fl_voter_summary = fl_voter_summary.fillna(0).astype(int)
        fl_voter_summary.index.name = 'geo_id'

        # Add EAVS region_id
        # Filter regions for Florida only to ensure unique geo_id mapping
        fl_regions = regions_df[regions_df['state_id'] == '12'].drop_duplicates(subset=['geo_id'])
        geo_to_region_map = pd.Series(fl_regions.index, index=fl_regions['geo_id'])
        fl_voter_summary['region_id'] = fl_voter_summary.index.map(geo_to_region_map)

        # Add CVAP data
        cvap_df = cvap_df[cvap_df['lntitle'] == 'Total'].copy()
        cvap_df['geoid'] = cvap_df['geoid'].apply(lambda x: x.split('US')[1] if 'US' in str(x) else x)
        cvap_data = cvap_df.set_index('geoid')['cvap_est']
        fl_voter_summary['total_cvap'] = fl_voter_summary.index.map(cvap_data)

        # Calculate registered voter percentage
        fl_voter_summary['registered_voter%'] = calculate_percentage(
            fl_voter_summary,
            'total_num_registered_voters',
            'total_cvap',
            clip_at_100=True
        )

        # Export to JSON as well
        # output_dir = Path('output/json')
        # output_dir.mkdir(parents=True, exist_ok=True)
        # fl_voter_summary.reset_index().to_json(output_dir / "fl_voter_summary.json", orient='records', indent=2)

        return fl_voter_summary

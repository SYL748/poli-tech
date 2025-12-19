"""Florida voter registration list processing."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import FloridaVoterFilesNode
from .florida_county_mapping import FloridaCountyMappingNode
from pathlib import Path

@register_node
class FloridaVoterListNode(DataNode):
    """Process Florida voter registration list."""
    dependencies = [FloridaVoterFilesNode, FloridaCountyMappingNode]
    output_filename = "fl_voter_list.csv"
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        fl_voter_df = inputs[FloridaVoterFilesNode]
        fl_county_mapping = inputs[FloridaCountyMappingNode]
        county_to_geo = fl_county_mapping.set_index('county_code')['geo_id'].to_dict()
        fl_voter_df['geo_id'] = fl_voter_df['County Code'].map(county_to_geo)
        fl_voter_df['party'] = fl_voter_df['Party Affiliation'].map({'DEM': 'Democrat', 'REP': 'Republican'}).fillna('Other')
        fl_voter_df['name_full'] = fl_voter_df[['Name First', 'Name Middle', 'Name Last']].apply(
            lambda x: ' '.join([str(x['Name First']) if pd.notna(x['Name First']) else '',
                str(x['Name Middle']) if pd.notna(x['Name Middle']) else '',
                str(x['Name Last']) if pd.notna(x['Name Last']) else '']).strip(), axis=1)

        processed_df = fl_voter_df[['geo_id', 'name_full', 'party']]

        # Custom export logic for JSON splitting
        # output_dir = Path('output/json')
        # output_dir.mkdir(parents=True, exist_ok=True)

        # Group by county (geo_id)
        # for geo_id, county_df in processed_df.groupby('geo_id'):
        #     # 1. All voters
        #     self._save_json(county_df, output_dir / f"fl_voters_{geo_id}_all.json")
        #
        #     # 2. Democrat only
        #     dem_df = county_df[county_df['party'] == 'Democrat']
        #     self._save_json(dem_df, output_dir / f"fl_voters_{geo_id}_dem.json")
        #
        #     # 3. Republican only
        #     rep_df = county_df[county_df['party'] == 'Republican']
        #     self._save_json(rep_df, output_dir / f"fl_voters_{geo_id}_rep.json")

        return processed_df

    def _save_json(self, df: pd.DataFrame, path: Path):
        """Helper to save DataFrame to JSON records."""
        if not df.empty:
            df.to_json(path, orient='records', indent=2)

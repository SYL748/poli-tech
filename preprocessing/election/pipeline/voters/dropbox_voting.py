"""Dropbox voting statistics for bubble chart."""
import pandas as pd
import numpy as np
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from ..eavs import RegionsNode
from election.util import calculate_percentage

@register_node
class DropboxVotingNode(DataNode):
    """Calculate dropbox voting statistics for bubble chart."""
    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "Dropbox_Voting_BubbleChart.csv"

    dropbox_cols = {
        'C6a': 'dropbox_ballots',
        'C8a': 'mail_ballots',
        'F1a': 'total_ballots'
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        voting_df = df[list(self.dropbox_cols.keys())].copy().rename_axis('region_id')
        for col in self.dropbox_cols.keys():
            voting_df[col] = pd.to_numeric(voting_df[col], errors='coerce').fillna(0)
        voting_df = voting_df.rename(columns=self.dropbox_cols)
        regions_reset = regions.reset_index().rename(columns={'id': 'region_id'})
        voting_df_reset = voting_df.reset_index()
        all_regions = regions_reset.merge(voting_df_reset, on='region_id', how='left').fillna(0)
        all_regions['percent_dropbox'] = calculate_percentage(all_regions, 'dropbox_ballots', 'total_ballots')
        all_regions['percent_mail'] = calculate_percentage(all_regions, 'mail_ballots', 'total_ballots')
        return all_regions.set_index('region_id')

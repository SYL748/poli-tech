"""Early voting statistics by state."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from .regions import RegionsNode
@register_node
class EarlyVotingNode(DataNode):
    """Calculate early voting statistics by state."""
    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "State_Early_Voting.csv"
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        early_voting_df = df[['F1a', 'F1c', 'F1d', 'F1f', 'E1a', 'C6a']].copy()
        early_voting_df = early_voting_df.rename_axis('region_id').fillna(0).astype(int)
        early_voting_df = early_voting_df.rename(columns={
            'F1a': 'total_votes_cast', 'F1c': 'uocava_accepted', 'F1d': 'mail_accepted',
            'F1f': 'in_person_early', 'E1a': 'provisional_counted', 'C6a': 'dropbox_accepted'
        })
        early_voting_df = early_voting_df.mask(early_voting_df < 0, 0)
        vote_by_mail = df['F1g'].fillna(0).astype(int).mask(lambda x: x < 0, 0)
        early_voting_df['mail_accepted'] = early_voting_df['mail_accepted'] + vote_by_mail
        early_voting_df = early_voting_df.join(regions['state_id'], on='region_id', how='left')
        early_voting_df = early_voting_df.groupby('state_id').sum()
        early_voting_df['total_early'] = (early_voting_df['in_person_early'] + early_voting_df['mail_accepted'] +
            early_voting_df['dropbox_accepted'] + early_voting_df['uocava_accepted'])
        return early_voting_df

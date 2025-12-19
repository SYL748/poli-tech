"""Mail and dropbox voting statistics by state."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node, StateVotingRightsNode
from election.util import calculate_percentage


@register_node
class MailDropboxNode(DataNode):
    """Calculate mail and dropbox voting statistics by state with voting rights information."""

    dependencies = [EAVS2024Node, StateVotingRightsNode]
    output_filename = "State_Mail_and_Dropbox.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]

        dropbox_df = df[['state_id', 'A1a', 'C6a', 'C8a', 'F1a']].copy().rename(columns={
            'A1a': 'total_registered_voter',
            'C6a': 'dropbox_votes',
            'C8a': 'total_mail_ballot',
            'F1a': 'total_votes_cast',
        })

        numeric_cols = ['dropbox_votes', 'total_mail_ballot', 'total_votes_cast', 'total_registered_voter']
        for col in numeric_cols:
            dropbox_df[col] = pd.to_numeric(dropbox_df[col], errors='coerce').fillna(0)
        dropbox_df[numeric_cols] = dropbox_df[numeric_cols].mask(dropbox_df[numeric_cols] < 0, 0)
        dropbox_df = dropbox_df.groupby('state_id').sum().astype(int)
        dropbox_df['percent_dropbox'] = calculate_percentage(dropbox_df, 'dropbox_votes', 'total_votes_cast')
        dropbox_df['percent_mail_ballot'] = calculate_percentage(dropbox_df, 'total_mail_ballot', 'total_votes_cast')
        dropbox_df['turnout_rate'] = calculate_percentage(dropbox_df, 'total_votes_cast', 'total_registered_voter')

        voting_rights = inputs[StateVotingRightsNode]
        dropbox_df = dropbox_df.join(voting_rights, how='left')

        return dropbox_df

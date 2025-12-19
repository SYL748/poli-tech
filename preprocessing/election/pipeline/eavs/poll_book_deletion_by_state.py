"""Poll book deletion statistics by state."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from .regions import RegionsNode


@register_node
class PollBookDeletionByStateNode(DataNode):
    """Calculate poll book deletion statistics by state."""

    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "Poll_Book_Deletion_By_State.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        deletion_cols = ['A12b', 'A12c', 'A12d', 'A12e', 'A12f', 'A12g', 'A12h']
        deletion_df = df[deletion_cols].copy().rename_axis('region_id')
        for col in deletion_cols:
            deletion_df[col] = pd.to_numeric(deletion_df[col], errors='coerce').fillna(0)
        deletion_df = deletion_df.rename(columns={
            'A12b': 'moved', 'A12c': 'death', 'A12d': 'felony', 'A12e': 'fail_response',
            'A12f': 'incompetent_to_vote', 'A12g': 'voter_request', 'A12h': 'duplicate_record'
        })
        deletion_df = deletion_df.join(regions['state_id'], on='region_id', how='left')
        return deletion_df.groupby('state_id').sum().astype(int)


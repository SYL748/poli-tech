"""Poll book deletion statistics by state."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from .regions import RegionsNode
from election.util import calculate_percentage


@register_node
class PollBookDeletionByRegionNode(DataNode):
    """Calculate poll book deletion statistics by state."""

    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "Poll_Book_Deletion_By_Region.csv"

    pollbook_cols = {
        'A1a': 'total_registered_voters',
        'A12a': 'total_pollbook_deletion',
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        deletion_df = (df[list(self.pollbook_cols.keys())]
                       .copy()
                       .rename_axis('region_id')
                       .rename(columns=self.pollbook_cols)
                       )
        for col in self.pollbook_cols.values():
            deletion_df[col] = pd.to_numeric(deletion_df[col], errors='coerce').fillna(0)

        deletion_df['deletion%'] = calculate_percentage(deletion_df, 'total_pollbook_deletion', 'total_registered_voters')
        deletion_df = deletion_df.join(regions['state_id'], on='region_id', how='left')

        return deletion_df[['total_pollbook_deletion', 'deletion%']]


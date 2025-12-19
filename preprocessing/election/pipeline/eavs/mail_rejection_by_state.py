"""Mail ballot rejection statistics by state."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .mail_rejection_by_region import MailRejectionByRegionNode
from .regions import RegionsNode


@register_node
class MailRejectionByStateNode(DataNode):
    """Calculate mail ballot rejection statistics by state."""

    dependencies = [MailRejectionByRegionNode, RegionsNode]
    output_filename = "Mail_Ballot_Rejection_By_State.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        rejection_df = inputs[MailRejectionByRegionNode].copy()
        regions = inputs[RegionsNode]

        # Join with regions to get state_id
        rejection_df = rejection_df.join(regions[['state_id']], how='left')

        # Get all columns except state_id and rejection% for aggregation
        agg_cols = [col for col in rejection_df.columns if col not in ['state_id', 'rejection%']]

        # Group by state and sum the rejection counts
        grouped_df = rejection_df.groupby('state_id')[agg_cols].sum()

        return grouped_df.astype(int)


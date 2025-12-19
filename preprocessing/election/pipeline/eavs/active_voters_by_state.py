"""Active voters by state node."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .regions import RegionsNode
from .active_voters_by_region import ActiveVotersByRegionNode
from election.util import calculate_percentage


@register_node
class ActiveVotersByStateNode(DataNode):
    """Calculate active and inactive voters by state."""

    dependencies = [ActiveVotersByRegionNode, RegionsNode]
    output_filename = "Active_Voters_by_State.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        active_by_region = inputs[ActiveVotersByRegionNode]
        regions = inputs[RegionsNode]

        # Join with regions to get state_id
        df = active_by_region.join(regions['state_id'], on='region_id', how='left')

        # Group by state_id and sum counts
        # We also don't need the percentages
        cols_to_sum = [
            'total_registered_voters',
            'active_registered_voters',
            'inactive_registered_voters'
        ]

        result = df.groupby('state_id')[cols_to_sum].sum()

        return result


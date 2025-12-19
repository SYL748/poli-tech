"""Provisional ballots by state aggregation."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .provisional_by_region import ProvisionalByRegionNode
from .regions import RegionsNode


@register_node
class ProvisionalByStateNode(DataNode):
    """Aggregate provisional ballots by state."""

    dependencies = [ProvisionalByRegionNode, RegionsNode]
    output_filename = "Provisional_Category_By_State.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        provisional = inputs[ProvisionalByRegionNode]
        regions = inputs[RegionsNode]
        provisional_data = provisional.drop(columns=['Total_Provisional'], errors='ignore')
        provisional_with_state = provisional_data.join(regions['state_id'], on='region_id', how='left')
        state_data = provisional_with_state.groupby('state_id').sum().astype(int)
        if 'Other' in state_data.columns:
            state_data = state_data.rename(columns={'Other': 'Other Reasons'})
        return state_data


import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class StateVotingRightsNode(DataNode):
    """Load state-level voting rights data."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        path = self.data_dir / 'data/state_voting_rights.csv'
        voting_rights = pd.read_csv(path, dtype={'state_id': str})
        voting_rights = voting_rights.set_index('state_id')
        return voting_rights

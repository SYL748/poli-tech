"""CVAP state-level data loader."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class CVAPStateNode(DataNode):
    """Load state-level CVAP data."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        cvap_path = self.data_dir / 'data/CVAP_ACS/State.csv'
        cvap_state_long = pd.read_csv(cvap_path, encoding='windows-1252', low_memory=False)
        cvap_state = cvap_state_long[['geoname', 'lntitle', 'geoid', 'cvap_est']].pivot_table(
            index=['geoname', 'geoid'],
            columns='lntitle',
            values='cvap_est',
            aggfunc='first'
        ).reset_index()

        # Flatten column names if needed (pivot creates multi-level columns)
        cvap_state.columns.name = None
        cvap_state['geoid'] = cvap_state['geoid'].apply(lambda x: str(x)[-2:])
        cvap_state = cvap_state.rename(columns={'geoid': 'state_id'}).set_index('state_id')

        return cvap_state

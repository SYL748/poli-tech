"""State-wide registration and turnout statistics node."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node, CVAPStateNode
from .regions import RegionsNode
from election.util import calculate_percentage


@register_node
class RegistrationTurnoutNode(DataNode):
    """Calculate voter registration and turnout statistics by state."""

    dependencies = [EAVS2024Node, RegionsNode, CVAPStateNode]
    output_filename = "Registration_Data_for_Opt_In_and_Opt_Out.csv"

    turnout_cols = {
        'A1a': 'total_registered_voters',
        'A1b': 'active_registered_voters',
        'F1a': 'total_votes_cast'
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        cvap_state = inputs[CVAPStateNode]
        turnout_df = (df[list(self.turnout_cols.keys())]
                      .copy()
                      .rename_axis('region_id')
                      .rename(columns=self.turnout_cols)
                      .fillna(0)
                      .astype(int)
                      .join(regions['state_id'], on='region_id', how='left')
                      .groupby('state_id')
                      .sum()
                      )
        turnout_df['total_cvap'] = cvap_state['Total']
        turnout_df['registration_rate'] = calculate_percentage(turnout_df, 'total_registered_voters', 'total_cvap', clip_at_100=True)
        turnout_df['turnout_rate'] = calculate_percentage(turnout_df, 'total_votes_cast', 'total_cvap', clip_at_100=True)

        return turnout_df

"""State-level equipment totals by type."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from .regions import RegionsNode


@register_node
class StateEquipmentTotalNode(DataNode):
    """Calculate state-level equipment totals by type."""

    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "State_Equipment_Total.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        state_equipment_totals = regions[['state_id']].copy().rename_axis('region_id')
        state_equipment_totals['DRE_No_VVPAT'] = df[['F3c_1', 'F3c_2', 'F3c_3']].apply(pd.to_numeric, errors='coerce').sum(axis=1)
        state_equipment_totals['DRE_With_VVPAT'] = df[['F4c_1', 'F4c_2', 'F4c_3']].apply(pd.to_numeric, errors='coerce').sum(axis=1)
        state_equipment_totals['Ballot_Marking_Device'] = df[['F5c_1', 'F5c_2', 'F5c_3']].apply(pd.to_numeric, errors='coerce').sum(axis=1)
        state_equipment_totals['Scanner'] = df[['F6c_1', 'F6c_2', 'F6c_3']].apply(pd.to_numeric, errors='coerce').sum(axis=1)
        result = state_equipment_totals.groupby('state_id').sum().astype(int)
        return result


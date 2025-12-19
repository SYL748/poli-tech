"""Equipment types by region."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .equipment_melted import EquipmentMeltedNode


@register_node
class EquipmentTypesByRegionNode(DataNode):
    """Generate equipment type summary."""

    dependencies = [EquipmentMeltedNode]
    output_filename = "Display_Type_of_Voting_Equipment_by_Region.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        equipment_melted = inputs[EquipmentMeltedNode]
        equipment_types_by_region = equipment_melted.groupby(['region_id', 'equipment_type']).size().unstack(fill_value=0)
        equipment_types_by_region['Type_of_Equipment_Used'] = equipment_types_by_region.apply(
            lambda row: ','.join(row.index[row > 0]), axis=1)
        result = equipment_types_by_region[['Type_of_Equipment_Used']]
        return result


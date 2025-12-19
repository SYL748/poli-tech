"""Equipment overview with specifications."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EquipmentDBNode
from .equipment_by_state import EquipmentByStateNode


@register_node
class EquipmentOverviewNode(DataNode):
    """Create equipment overview with specifications."""

    dependencies = [EquipmentDBNode, EquipmentByStateNode]
    output_filename = "Voting_Equipment_Overview.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        equipment_db = inputs[EquipmentDBNode]
        equipment_by_state = inputs[EquipmentByStateNode]

        # Calculate total quantity deployed across all states
        # EquipmentByStateNode output has columns:
        # state_id, Manufacturer, Model Name, quantity, Equipment Type, Age, OS, Certification Level, Scanning Rate, Error Rate, Reliability

        # We need to group by Manufacturer and Model Name to get total quantity
        # Note: EquipmentByStateNode output columns might have been renamed in its process method.
        # Let's check EquipmentByStateNode implementation or assume standard names based on previous context.
        # Based on read_file output of EquipmentByStateNode:
        # result = equipment_by_state[['state_id', 'Manufacturer', 'Model Name', 'number_deployed', ...]].rename(columns={'number_deployed': 'quantity'})

        # So we group by Manufacturer and Model Name
        quantity_df = equipment_by_state.groupby(['Manufacturer', 'Model Name'])['quantity'].sum().reset_index()

        # Merge quantity into equipment_db
        # equipment_db has columns: make, description, model, age, operating_system, cert, scan_rate, error_rate, reliability, discontinued

        # We need to match Manufacturer (make) and Model Name (model)
        # Rename quantity_df columns to match equipment_db for merging
        quantity_df = quantity_df.rename(columns={'Manufacturer': 'make', 'Model Name': 'model'})

        # Merge
        result = equipment_db.merge(quantity_df, on=['make', 'model'], how='left')

        # Fill NaN quantity with 0 and cast to int
        result['quantity'] = result['quantity'].fillna(0).astype(int)

        # Select final columns
        # The user wants "Voting_Equipment_Overview" output.
        # The previous implementation returned: Manufacturer, Model Name, Equipment Type, Age, OS, Certification Level, Scanning Rate, Error Rate, Reliability
        # The user said "modify the EquipmentOverviewNode to match the modified EquipmentDBNode we just did... where the only thing we need is to have a 'quantity' field"

        # So we should return the columns from EquipmentDBNode + quantity.
        # EquipmentDBNode columns: make, description, model, age, operating_system, cert, scan_rate, error_rate, reliability, discontinued

        return result

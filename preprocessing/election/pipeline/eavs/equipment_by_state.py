"""Equipment summary by state."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .equipment_melted import EquipmentMeltedNode
from .equipment_parsed import EquipmentParsedNode
from ..sources import EquipmentDBNode
from .regions import RegionsNode


@register_node
class EquipmentByStateNode(DataNode):
    """Aggregate equipment summary by state."""

    dependencies = [EquipmentMeltedNode, EquipmentParsedNode, EquipmentDBNode, RegionsNode]
    output_filename = "Equipment_Summary_by_State.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        equipment_melted = inputs[EquipmentMeltedNode]
        equipment_parsed = inputs[EquipmentParsedNode]
        equipment_db = inputs[EquipmentDBNode]
        regions = inputs[RegionsNode]

        # Join melted data with parsed make/model info
        # equipment_parsed has index 'make_model' and columns 'make', 'model'
        equipment_with_parsed = equipment_melted.merge(
            equipment_parsed, left_on='make_model', right_index=True, how='left')

        # Join with state info
        equipment_with_state = equipment_with_parsed.merge(
            regions[['state_id']].rename_axis('region_id'), on='region_id', how='left')

        # Group by state and clean make/model
        # We use the parsed 'make' and 'model' here
        equipment_by_state = equipment_with_state.groupby(
            ['state_id', 'make', 'model', 'equipment_type']).agg({'number_deployed': 'sum'}).reset_index()

        # Merge with EquipmentDBNode to get specs
        # EquipmentDBNode columns: make, description, model, age, operating_system, cert, scan_rate, error_rate, reliability, discontinued
        # Set equipment_db index to (make, model) to avoid duplicates from many-to-many merge
        equipment_db_indexed = equipment_db.set_index(['make', 'model'])
        equipment_by_state = equipment_by_state.merge(
            equipment_db_indexed, left_on=['make', 'model'], right_index=True, how='left')

        # Deduplicate by (state_id, make, model) since equipment_type is not in the final output
        # For duplicates, sum the quantities and keep the first occurrence of other columns
        equipment_by_state = equipment_by_state.sort_values(['state_id', 'make', 'model'])

        # Group by the unique key columns and aggregate
        equipment_by_state = equipment_by_state.groupby(
            ['state_id', 'make', 'model'], as_index=False, sort=False
        ).agg({
            'number_deployed': 'sum',
            'equipment_type': 'first',
            'description': 'first',
            'age': 'first',
            'operating_system': 'first',
            'cert': 'first',
            'scan_rate': 'first',
            'error_rate': 'first',
            'reliability': 'first',
            'quality': 'first',
            'discontinued': 'first'
        })

        # Rename columns to match expected output format
        result = equipment_by_state.rename(columns={
            'number_deployed': 'quantity',
            'make': 'Manufacturer',
            'model': 'Model Name',
            'description': 'Equipment Type',
            'age': 'Age',
            'operating_system': 'OS',
            'cert': 'Certification Level',
            'scan_rate': 'Scanning Rate',
            'error_rate': 'Error Rate',
            'reliability': 'Reliability',
            'quality': 'Quality'
        })

        return result[['state_id', 'Manufacturer', 'Model Name', 'quantity',
                       'Equipment Type', 'Age', 'OS', 'Certification Level', 'Scanning Rate', 'Error Rate',
                       'Reliability', 'Quality'
                       ]]

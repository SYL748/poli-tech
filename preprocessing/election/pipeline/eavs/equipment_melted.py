"""Equipment data extraction and reshaping."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node


@register_node
class EquipmentMeltedNode(DataNode):
    """Extract and reshape equipment data (intermediate node)."""

    dependencies = [EAVS2024Node]
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        equipment_df = df[
            ['F3a', 'F3b_1', 'F3b_1other', 'F3c_1', 'F3b_2', 'F3b_2other', 'F3c_2', 'F3b_3', 'F3b_3other', 'F3c_3',
             'F4a', 'F4b_1', 'F4b_1other', 'F4c_1', 'F4b_2', 'F4b_2other', 'F4c_2', 'F4b_3', 'F4b_3other', 'F4c_3',
             'F5a', 'F5b_1', 'F5b_1other', 'F5c_1', 'F5b_2', 'F5b_2other', 'F5c_2', 'F5b_3', 'F5b_3other', 'F5c_3',
             'F6a', 'F6b_1', 'F6b_1other', 'F6c_1', 'F6b_2', 'F6b_2other', 'F6c_2', 'F6b_3', 'F6b_3other', 'F6c_3']
        ].copy().rename_axis('region_id')
        equipment_parts = []
        for equip_type, prefix in [('DRE no VVPAT', 'F3'), ('DRE with VVPAT', 'F4'),
                                   ('Ballot Marking Device', 'F5'), ('Scanner', 'F6')]:
            type_df = equipment_df[[f'{prefix}a', f'{prefix}b_1', f'{prefix}b_1other', f'{prefix}c_1',
                                   f'{prefix}b_2', f'{prefix}b_2other', f'{prefix}c_2',
                                   f'{prefix}b_3', f'{prefix}b_3other', f'{prefix}c_3']].copy()
            type_df = type_df[type_df[f'{prefix}a'] == 'Yes']
            for slot in ['1', '2', '3']:
                slot_df = type_df[[f'{prefix}b_{slot}', f'{prefix}b_{slot}other', f'{prefix}c_{slot}']].copy()
                slot_df = slot_df.reset_index()
                slot_df.columns = ['region_id', 'Fb_x', 'Fb_xother', 'Fc_x']
                slot_df['equipment_type'] = equip_type
                equipment_parts.append(slot_df)
        equipment_melted = pd.concat(equipment_parts, ignore_index=True)
        equipment_melted = equipment_melted.dropna(subset=['Fb_x'])
        other_mask = equipment_melted['Fb_x'] == 'Other (use text box to describe)'
        if other_mask.any():
            for idx in equipment_melted[other_mask].index:
                other_value = equipment_melted.loc[idx, 'Fb_xother']
                equipment_melted.loc[idx, 'Fb_x'] = f"{other_value}" if pd.notna(other_value) else 'Other (unspecified)'
        equipment_melted = equipment_melted.drop(columns=['Fb_xother'])
        equipment_melted = equipment_melted.rename(columns={'Fb_x': 'make_model', 'Fc_x': 'number_deployed'})
        equipment_melted['number_deployed'] = pd.to_numeric(equipment_melted['number_deployed'], errors='coerce').fillna(0).astype(int)
        equipment_melted = equipment_melted.sort_values(['region_id', 'equipment_type', 'make_model']).reset_index(drop=True)
        return equipment_melted


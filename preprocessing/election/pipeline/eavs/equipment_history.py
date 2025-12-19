"""Equipment deployment history across years."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node, EAVSHistoricalNode
from .regions import RegionsNode


@register_node
class EquipmentHistoryNode(DataNode):
    """Create equipment deployment history across years."""

    dependencies = [EAVS2024Node, EAVSHistoricalNode, RegionsNode]
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df_2024 = inputs[EAVS2024Node]
        df_historical = inputs[EAVSHistoricalNode]
        regions = inputs[RegionsNode]
        totals_2024 = self._process_year(df_2024, regions, 2024, is_2024=True)
        all_years = [totals_2024]
        for year in [2022, 2020, 2018, 2016]:
            year_df = df_historical[df_historical['year'] == year]
            if not year_df.empty:
                year_totals = self._process_year(year_df, regions, year, is_2024=False)
                all_years.append(year_totals)
        result = all_years[0]
        for year_totals in all_years[1:]:
            result = result.join(year_totals, how='outer')
        return result.fillna(0).astype(int)

    def _process_year(self, df, regions, year, is_2024=False):
        if is_2024:
            state_map = df[['state_id']].copy().rename_axis('region_id')
            state_map['DRE_No_VVPAT'] = df[['F3c_1', 'F3c_2', 'F3c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            state_map['DRE_With_VVPAT'] = df[['F4c_1', 'F4c_2', 'F4c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            state_map['Ballot_Marking_Device'] = df[['F5c_1', 'F5c_2', 'F5c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            state_map['Scanner'] = df[['F6c_1', 'F6c_2', 'F6c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            totals = state_map.groupby('state_id').sum().astype(int)
        elif year == 2016:
            state_map = df[['state_id']].copy()
            converted = df[['F7a_Number', 'F7b_Number', 'F7c_Number', 'F7d_NumBooths']].replace(
                ['-999999: Data Not Available', '-888888: Not Applicable'], None
            ).apply(pd.to_numeric, errors='coerce').fillna(0).astype(int)
            state_map['DRE_No_VVPAT'] = converted['F7a_Number']
            state_map['DRE_With_VVPAT'] = converted['F7b_Number']
            state_map['Ballot_Marking_Device'] = converted['F7c_Number']
            state_map['Scanner'] = converted['F7d_NumBooths']
            totals = state_map.groupby('state_id').sum().astype(int)
        else:
            state_map = df[['state_id']].copy()
            state_map['DRE_No_VVPAT'] = df[['F5c_1', 'F5c_2', 'F5c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            state_map['DRE_With_VVPAT'] = df[['F6c_1', 'F6c_2', 'F6c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            state_map['Ballot_Marking_Device'] = df[['F7c_1', 'F7c_2', 'F7c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            state_map['Scanner'] = df[['F8c_1', 'F8c_2', 'F8c_3']].apply(pd.to_numeric, errors='coerce').fillna(0).sum(axis=1)
            totals = state_map.groupby('state_id').sum().astype(int)
        return totals.add_suffix(f'_{year}')


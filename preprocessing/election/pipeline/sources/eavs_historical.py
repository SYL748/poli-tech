"""Historical EAVS data loader (2016-2022)."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .state_fips import StateFIPSNode


@register_node
class EAVSHistoricalNode(DataNode):
    """Load historical EAVS data (2016-2022)."""

    dependencies = [StateFIPSNode]
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        state_mappings = inputs[StateFIPSNode]
        state_code_map = state_mappings.set_index('State_Full')['FIPS'].to_dict()
        state_full_map = state_mappings.set_index('State_Abbr')['State_Full'].to_dict()
        dfs = []
        try:
            path_2022 = self.data_dir / 'data/EAVS/2022_EAVS_for_Public_Release_nolabel_V1.1_CSV.csv'
            df_2022 = pd.read_csv(path_2022, low_memory=False)
            df_2022['state_id'] = df_2022['State_Full'].map(state_code_map)
            df_2022 = df_2022.dropna(subset=['state_id']).replace([-77, -88, -99], None)
            df_2022['year'] = 2022
            dfs.append(df_2022)
        except Exception as e:
            print(f"    Warning: Could not load 2022 data: {e}")
        try:
            path_2020 = self.data_dir / 'data/EAVS/2020_EAVS_for_Public_Release_nolabel_V1.2_CSV.csv'
            df_2020 = pd.read_csv(path_2020, low_memory=False)
            df_2020['state_id'] = df_2020['State_Full'].map(state_code_map)
            df_2020 = df_2020.dropna(subset=['state_id']).replace([-77, -88, -99], None)
            df_2020['year'] = 2020
            dfs.append(df_2020)
        except Exception as e:
            print(f"    Warning: Could not load 2020 data: {e}")
        try:
            path_2018 = self.data_dir / 'data/EAVS/EAVS_2018_for_Public_Release_Updates3.csv'
            df_2018 = pd.read_csv(path_2018, low_memory=False)
            df_2018['state_id'] = df_2018['State_Full'].map(state_code_map)
            df_2018 = df_2018.dropna(subset=['state_id']).replace([-77, -88, -99], None)
            df_2018['year'] = 2018
            dfs.append(df_2018)
        except Exception as e:
            print(f"    Warning: Could not load 2018 data: {e}")
        try:
            path_2016 = self.data_dir / 'data/EAVS/EAVS_2016_for_Public_Release_nolabel_V1.1_CSV/EAVS_2016_Final_Data_for_Public_Release_nolabel_V1.1_CSV.csv'
            df_2016 = pd.read_csv(path_2016, dtype={'FIPSCode': str, 'FIPS_2Digit': str}, encoding='windows-1252', low_memory=False)
            df_2016['state_id'] = df_2016['State'].map(lambda x: state_code_map.get(state_full_map.get(x, None), None))
            df_2016 = df_2016.dropna(subset=['state_id'])
            df_2016['year'] = 2016
            dfs.append(df_2016)
        except Exception as e:
            print(f"    Warning: Could not load 2016 data: {e}")
        if not dfs:
            raise RuntimeError("No historical EAVS data could be loaded")
        return pd.concat(dfs, ignore_index=True)


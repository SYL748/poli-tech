"""2024 EAVS raw data loader."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .state_fips import StateFIPSNode


@register_node
class EAVS2024RawNode(DataNode):
    """Load 2024 EAVS data from Excel file without replacing special values."""

    dependencies = [StateFIPSNode]
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        state_mappings = inputs[StateFIPSNode]
        state_code_map = state_mappings.set_index('State_Full')['FIPS'].to_dict()
        eavs_path = self.data_dir / 'data/EAVS/2024_EAVS_for_Public_Release_V1_xlsx.xlsx'
        print(f"    Loading EAVS Excel file (13MB, may take 30-60 seconds)...")
        df = pd.read_excel(eavs_path, dtype={'FIPSCode': str}, engine='openpyxl')
        print(f"    Loaded {len(df)} rows")
        df['state_id'] = df['State_Full'].map(state_code_map)
        df = df.dropna(subset=['state_id'])
        df = df.reset_index(drop=True)
        df.index += 1
        return df


@register_node
class EAVS2024RawCSVNode(DataNode):
    """Load 2024 EAVS data from CSV file without replacing special values."""

    dependencies = [StateFIPSNode]
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        state_mappings = inputs[StateFIPSNode]
        state_code_map = state_mappings.set_index('State_Full')['FIPS'].to_dict()
        eavs_path = self.data_dir / 'data/EAVS/2024_EAVS_for_Public_Release_nolabel_V1_csv/2024_EAVS_for_Public_Release_nolabel_V1.csv'
        print(f"    Loading EAVS CSV file...")
        df = pd.read_csv(eavs_path, low_memory=False)
        print(f"    Loaded {len(df)} rows")
        df['state_id'] = df['State_Full'].map(state_code_map)
        df = df.dropna(subset=['state_id'])
        df = df.reset_index(drop=True)
        df.index += 1
        return df

"""State FIPS code mappings loader."""
import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
@register_node
class StateFIPSNode(DataNode):
    """Load state FIPS code mappings."""
    dependencies = []
    output_filename = None
    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        state_to_fips_path = self.data_dir / 'data/state_to_fips.csv'
        df_fips = pd.read_csv(state_to_fips_path, dtype={'FIPS': str}, low_memory=False)
        state_abbr_path = self.data_dir / 'data/state_abbr_to_state_full.csv'
        df_abbr = pd.read_csv(state_abbr_path, low_memory=False)
        return df_fips.merge(df_abbr, on='State_Full', how='left')

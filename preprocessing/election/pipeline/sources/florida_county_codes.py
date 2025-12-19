"""Florida county codes loader."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class FloridaCountyCodesNode(DataNode):
    """Load Florida county code mappings."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        fl_codes_path = self.data_dir / 'data/fl_county_codes.csv'
        return pd.read_csv(fl_codes_path, low_memory=False)


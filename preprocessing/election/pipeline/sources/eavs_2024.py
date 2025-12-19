"""2024 EAVS data loader."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .eavs_2024_raw import EAVS2024RawNode


@register_node
class EAVS2024Node(DataNode):
    """Load 2024 EAVS data from Excel file."""

    dependencies = [EAVS2024RawNode]
    output_filename = None
    SPECIAL_VALUES = ['Does not apply', 'DOES NOT APPLY', 'Data not available', 'Valid skip', 'VALID SKIP']

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024RawNode].copy()
        df = df.replace(self.SPECIAL_VALUES, None)
        return df

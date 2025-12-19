"""EAVS codebook loader."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class EAVSCodebookNode(DataNode):
    """Load EAVS codebook for variable labels."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        codebook_path = self.data_dir / 'data/EAVS/2024_EAVS_Codebook.csv'
        return pd.read_csv(codebook_path, low_memory=False)


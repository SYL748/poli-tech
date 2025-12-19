"""CVAP county-level data loader."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class CVAPCountyNode(DataNode):
    """Load county-level CVAP data."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        cvap_path = self.data_dir / 'data/CVAP_ACS/County.csv'
        return pd.read_csv(cvap_path, encoding='windows-1252', low_memory=False)


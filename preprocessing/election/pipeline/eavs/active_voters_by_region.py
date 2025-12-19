"""Active voters by region node."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from .regions import RegionsNode
from election.util import calculate_percentage


@register_node
class ActiveVotersByRegionNode(DataNode):
    """Calculate active and inactive voters by region."""

    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "Active_Voters_by_Region.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]

        # Extract relevant columns
        # A1a: Total registered voters
        # A1b: Active registered voters
        # A1c: Inactive registered voters
        cols = {
            'A1a': 'total_registered_voters',
            'A1b': 'active_registered_voters',
            'A1c': 'inactive_registered_voters'
        }

        # Join with regions to get region_id
        # The EAVS2024Node output has the same index as RegionsNode if we align them correctly
        # But let's be safe and use the index from EAVS2024Node which should match RegionsNode's source

        # RegionsNode output is indexed by 'id' which corresponds to the row number (1-based)
        # EAVS2024Node output is also 1-based indexed

        result = df[list(cols.keys())].copy()
        result = result.rename(columns=cols)
        result.index.name = 'region_id'

        # Convert to numeric, coercing errors to NaN (though EAVS loader handles special values)
        for col in result.columns:
            result[col] = pd.to_numeric(result[col], errors='coerce').fillna(0)

        # Calculate active percentage
        result['active_percentage'] = calculate_percentage(
            result,
            'active_registered_voters',
            'total_registered_voters',
            clip_at_100=True
        )

        return result


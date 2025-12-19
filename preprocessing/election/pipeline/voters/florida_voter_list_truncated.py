"""Truncated Florida voter registration list node.

This node depends on `FloridaVoterListNode` and samples up to `SAMPLE_N`
rows for each unique `geo_id`. Sampling is deterministic and controlled by
`SEED`. The node will raise a ValueError if any `geo_id` values are missing.
"""

import pandas as pd
from typing import Dict, Type
from ..core import DataNode, register_node
from .florida_voter_list import FloridaVoterListNode


@register_node
class FloridaVoterListTruncatedNode(DataNode):
    """Return a deterministically sampled subset (per-geo) of Florida voters.

    Class-level constants:
        SAMPLE_N: maximum number of rows to keep per `geo_id`.
        SEED: random seed for deterministic sampling.
    """

    dependencies = [FloridaVoterListNode]
    output_filename = "fl_voter_list_truncated.csv"

    # Defaults — can be overridden in tests or by changing the class
    SAMPLE_N: int = 10000
    SEED: int = 42

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        fl_df = inputs[FloridaVoterListNode]

        # Basic validation
        if 'geo_id' not in fl_df.columns:
            raise ValueError("FloridaVoterListNode output is missing required column 'geo_id'")

        n_missing = int(fl_df['geo_id'].isnull().sum())
        if n_missing:
            raise ValueError(f"Found {n_missing} rows with missing 'geo_id' in FloridaVoterListNode output; aborting.")

        # Group by geo_id and sample up to SAMPLE_N rows per group.
        # Use a fixed seed for reproducibility.
        def _sample_group(g: pd.DataFrame) -> pd.DataFrame:
            k = min(len(g), self.SAMPLE_N)
            if k == len(g):
                return g
            return g.sample(n=k, random_state=self.SEED)

        sampled = fl_df.groupby('geo_id', group_keys=False).apply(_sample_group).reset_index(drop=True)
        return sampled


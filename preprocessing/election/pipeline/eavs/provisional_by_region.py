"""Provisional ballots by region processing."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from .regions import RegionsNode


@register_node
class ProvisionalByRegionNode(DataNode):
    """Process provisional ballots by region with totals."""

    dependencies = [EAVS2024Node, RegionsNode]
    output_filename = "Provisional_By_Region.csv"

    provisional_cols = {
        'E2a': 'Voter_Not_on_List',
        'E2b': 'Voter_Lacked_ID',
        'E2c': 'Election_Official_Challenged_Eligibility',
        'E2d': 'Another_Person_Challenged_Eligibility',
        'E2e': 'Voter_Not_Resident',
        'E2f': 'Voter_Registration_Not_Updated',
        'E2g': 'Voter_Did_Not_Surrender_Mail_Ballot',
        'E2h': 'Judge_Extended_Voting_Hours',
        'E2i': 'Voter_Used_SDR',
        'E2j': 'Other',
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        totals = df['E1a'].rename_axis('region_id').rename('Total_Provisional')
        totals = pd.to_numeric(totals, errors='coerce').fillna(0).astype(int)
        reason_cols = list(self.provisional_cols.keys())[:-1]
        result = df[reason_cols].copy()
        for col in reason_cols:
            result[col] = pd.to_numeric(result[col], errors='coerce').fillna(0)
        other_cols = ['E2j', 'E2k', 'E2l']
        other_df = df[other_cols].copy()
        for col in other_cols:
            other_df[col] = pd.to_numeric(other_df[col], errors='coerce').fillna(0)
        result['E2j'] = other_df.sum(axis=1)
        result = (result
                  .rename(columns=self.provisional_cols)
                  .rename_axis('region_id')
                  .astype(int)
                  .join(totals, on='region_id', how='left'))
        return result

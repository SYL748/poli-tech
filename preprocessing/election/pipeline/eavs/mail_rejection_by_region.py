"""Mail ballot rejection statistics by region."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import EAVS2024Node
from election.util import calculate_percentage


@register_node
class MailRejectionByRegionNode(DataNode):
    """Calculate mail ballot rejection statistics by region."""

    dependencies = [EAVS2024Node]
    output_filename = "Mail_Ballot_Rejection_By_Region.csv"

    REJECTION_RENAME = {
        'C8a': 'total_mail_ballot', 'C9a': 'total_rejection', 'C9b': 'late',
        'C9c': 'missing_voter_signature', 'C9d': 'missing_witness_signature',
        'C9e': 'non_matching_voter_signature', 'C9f': 'unofficial_envelope',
        'C9g': 'ballot_missing_from_envelope', 'C9h': 'no_secrecy_envelope',
        'C9i': 'multiple_ballots_in_one_envelope', 'C9j': 'envelope_not_sealed',
        'C9k': 'no_postmark', 'C9l': 'no_resident_address_on_envelope',
        'C9m': 'voter_deceased', 'C9n': 'voter_already_voted',
        'C9o': 'missing_documentation', 'C9p': 'voter_not_eligible', 'C9q': 'no_ballot_application'
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        df = inputs[EAVS2024Node]
        rejection_cols = list(self.REJECTION_RENAME.keys())
        rejection_df = df[rejection_cols].copy().rename_axis('region_id')
        for col in rejection_cols:
            rejection_df[col] = pd.to_numeric(rejection_df[col], errors='coerce').fillna(0)
        rejection_df = rejection_df.rename(columns=self.REJECTION_RENAME)
        rejection_df = rejection_df.astype(int)

        # Calculate rejection percentage using util, with ternary to handle zero denominators per row
        rejection_pct = calculate_percentage(rejection_df, 'total_rejection', 'total_mail_ballot')
        rejection_df['rejection%'] = rejection_pct.where(rejection_df['total_mail_ballot'] > 0, 0.0)

        return rejection_df


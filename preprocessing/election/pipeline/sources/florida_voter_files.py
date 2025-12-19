"""Florida voter registration files loader."""

import pandas as pd
import glob
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class FloridaVoterFilesNode(DataNode):
    """Load Florida voter registration files."""

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        fl_voter_columns = [
            'County Code', 'Voter ID', 'Name Last', 'Name Suffix', 'Name First', 'Name Middle',
            'Requested public records exemption', 'Residence Address Line 1', 'Residence Address Line 2',
            'Residence City (USPS)', 'Residence State', 'Residence Zipcode', 'Mailing Address Line 1',
            'Mailing Address Line 2', 'Mailing Address Line 3', 'Mailing City', 'Mailing State',
            'Mailing Zipcode', 'Mailing Country', 'Gender', 'Race', 'Birth Date', 'Registration Date',
            'Party Affiliation', 'Precinct', 'Precinct Group', 'Precinct Split', 'Precinct Suffix',
            'Voter Status', 'Congressional District', 'House District', 'Senate District',
            'County Commission District', 'School Board District', 'Daytime Area Code',
            'Daytime Phone Number', 'Daytime Phone Extension', 'Email Address',
        ]
        pattern = str(self.data_dir / 'data/Florida Department of State Statewide Voter Registration and Voting History Extract File/20251014_VoterDetail/*.txt')
        fl_voter_files = glob.glob(pattern)
        if not fl_voter_files:
            raise FileNotFoundError(f"No Florida voter files found at: {pattern}")
        print(f"    Loading {len(fl_voter_files)} Florida voter files...")
        dfs = []
        for file in fl_voter_files:
            df = pd.read_csv(file, sep='\t', header=None, names=fl_voter_columns, dtype=str)
            dfs.append(df)
        result = pd.concat(dfs, ignore_index=True)
        print(f"    Loaded {len(result):,} voter records")
        return result


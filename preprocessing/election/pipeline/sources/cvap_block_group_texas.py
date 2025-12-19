"""CVAP block group-level data loader for Texas."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node


@register_node
class CVAPBlockGroupTexasNode(DataNode):
    """Load block group-level CVAP data for Texas.

    Loads demographic data from BlockGr.csv filtered for Texas (FIPS 48).
    Contains demographic categories via 'lntitle' field including:
    - White Alone
    - Hispanic or Latino
    - Black or African American Alone
    """

    dependencies = []
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        cvap_path = self.data_dir / 'data/CVAP_ACS/BlockGr.csv'
        # Using latin-1 encoding as seen in other CVAP nodes
        df = pd.read_csv(cvap_path, encoding='windows-1252', low_memory=False)

        # Extract FIPS state code from geoid (format: 1500000US01XXXXXXXXX)
        # Block group geoid has 12 digits after state prefix
        df['state_fips'] = df['geoid'].str.extract(r'1500000US(\d{2})')

        # Filter for Texas (FIPS 48)
        df = df[df['state_fips'] == '48'].copy()

        # Extract block group FIPS (12 digits: 2 state + 3 county + 6 tract + 1 block group)
        df['block_group_fips'] = df['geoid'].str.extract(r'1500000US(\d{12})')

        # Extract county FIPS (5 digits: state + county)
        df['county_fips'] = df['geoid'].str.extract(r'1500000US(\d{5})')

        return df


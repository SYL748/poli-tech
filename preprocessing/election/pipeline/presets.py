"""
Pipeline Presets

Defines common node groupings for typical processing workflows.
Uses class references for type safety.
"""

from typing import List, Type, Optional

from .core import DataNode
from .eavs import (
    RegionsNode,
    ProvisionalByRegionNode,
    ProvisionalByStateNode,
    EquipmentOverviewNode,
    EquipmentByStateNode,
    StateEquipmentTotalNode,
    EquipmentHistoryNode,
    EquipmentTypesByRegionNode,
    EarlyVotingNode,
    MailRejectionByStateNode,
    MailRejectionByRegionNode,
    PollBookDeletionByStateNode,
    PollBookDeletionByRegionNode,
    MailDropboxNode,
    ActiveVotersByRegionNode,
    ActiveVotersByStateNode,
)
from .voters import (
    CVAPPercentNode,
    DropboxVotingNode,
    FloridaVoterListNode,
    FloridaVoterSummaryNode,
)


# EAVS-focused preset: regions, provisional ballots, equipment, voting stats
EAVS_PRESET: List[Type[DataNode]] = [
    RegionsNode,
    ProvisionalByRegionNode,
    ProvisionalByStateNode,
    EquipmentOverviewNode,
    EquipmentByStateNode,
    StateEquipmentTotalNode,
    EquipmentHistoryNode,
    EquipmentTypesByRegionNode,
    EarlyVotingNode,
    MailRejectionByStateNode,
    MailRejectionByRegionNode,
    PollBookDeletionByStateNode,
    PollBookDeletionByRegionNode,
    MailDropboxNode,
    ActiveVotersByRegionNode,
    ActiveVotersByStateNode,
]

# Voter registration preset: Florida voters, CVAP
VOTER_REGISTRATION_PRESET: List[Type[DataNode]] = [
    RegionsNode,
    FloridaVoterListNode,
    FloridaVoterSummaryNode,
    CVAPPercentNode,
]

# Election results preset: dropbox voting, CVAP
ELECTION_PRESET: List[Type[DataNode]] = [
    RegionsNode,
    DropboxVotingNode,
    CVAPPercentNode,
]

# Run all exportable nodes
ALL_PRESET: Optional[List[Type[DataNode]]] = None  # Special value: run all nodes with output_filename set

"""
Election Data Pipeline

Graph-based pipeline for processing election data with declarative dependencies.
Each DataNode represents a processing unit that produces a DataFrame, which can
be consumed by dependent nodes or exported to CSV.

Uses class-based registry for type safety and IDE autocomplete.

Usage:
    from election.pipeline import Pipeline, RegionsNode
    from election.pipeline.eavs import ProvisionalByStateNode

    pipeline = Pipeline(output_dir='./output')
    pipeline.run([RegionsNode, ProvisionalByStateNode])
    pipeline.run_node(RegionsNode)
    pipeline.list_nodes()
"""

from .core import DataNode, register_node, Pipeline, NODE_REGISTRY

# Import all node modules to register them
from . import sources
from . import eavs
from . import voters

# Import presets
from .presets import EAVS_PRESET, VOTER_REGISTRATION_PRESET, ELECTION_PRESET, ALL_PRESET

# Re-export commonly used node classes for convenience
from .sources import (
    EAVSCodebookNode,
    EAVS2024Node,
    EAVSHistoricalNode,
    EquipmentDBNode,
    CVAPCountyNode,
    CVAPStateNode,
    CVAPBlockGroupNode,
    FloridaCountyCodesNode,
    FloridaVoterFilesNode,
    StateFIPSNode,
    AlabamaBlockGroupsNode,
    AlabamaVotingDistrictsNode,
)

from .eavs import (
    RegionsNode,
    ProvisionalByRegionNode,
    ProvisionalByStateNode,
    EquipmentMeltedNode,
    EquipmentParsedNode,
    EquipmentOverviewNode,
    EquipmentByStateNode,
    StateEquipmentTotalNode,
    EquipmentTypesByRegionNode,
    EquipmentHistoryNode,
    EarlyVotingNode,
    MailRejectionByStateNode,
    MailRejectionByRegionNode,
    PollBookDeletionByStateNode,
    MailDropboxNode,
)

from .voters import (
    CVAPPercentNode,
    DropboxVotingNode,
    FloridaCountyMappingNode,
    FloridaVoterListNode,
    FloridaVoterSummaryNode,
    FloridaVoterListTruncatedNode,
)

from .precinct_election import (
    CVAPPrecinctNode,
    AlabamaPrecinctResultsNode,
    PrecinctDemographicRegressionNode,
)

__all__ = [
    # Core infrastructure
    'DataNode',
    'register_node',
    'Pipeline',
    'NODE_REGISTRY',

    # Presets
    'EAVS_PRESET',
    'VOTER_REGISTRATION_PRESET',
    'ELECTION_PRESET',
    'ALL_PRESET',

    # Source nodes
    'StateFIPSNode',
    'EAVS2024Node',
    'EAVSCodebookNode',
    'EAVSHistoricalNode',
    'EquipmentDBNode',
    'CVAPCountyNode',
    'CVAPStateNode',
    'CVAPBlockGroupNode',
    'FloridaCountyCodesNode',
    'FloridaVoterFilesNode',
    'AlabamaBlockGroupsNode',
    'AlabamaVotingDistrictsNode',

    # EAVS nodes
    'RegionsNode',
    'ProvisionalByRegionNode',
    'ProvisionalByStateNode',
    'EquipmentMeltedNode',
    'EquipmentParsedNode',
    'EquipmentOverviewNode',
    'EquipmentByStateNode',
    'StateEquipmentTotalNode',
    'EquipmentTypesByRegionNode',
    'EquipmentHistoryNode',
    'EarlyVotingNode',
    'MailRejectionByStateNode',
    'MailRejectionByRegionNode',
    'PollBookDeletionByStateNode',
    'MailDropboxNode',

    # Voter nodes
    'CVAPPercentNode',
    'DropboxVotingNode',
    'FloridaCountyMappingNode',
    'FloridaVoterListNode',
    'FloridaVoterSummaryNode',
    'FloridaVoterListTruncatedNode',

    # Precinct election nodes
    'CVAPPrecinctNode',
    'AlabamaPrecinctResultsNode',
    'PrecinctDemographicRegressionNode',
]


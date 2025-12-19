"""
Election Data Preprocessing Package

Graph-based pipeline for processing election data with declarative dependencies.
Each DataNode represents a processing unit that produces a DataFrame, which can
be consumed by dependent nodes or exported to CSV.

Uses class-based registry for type safety and IDE autocomplete.

Package Structure:
- pipeline: Core pipeline infrastructure with nodes for EAVS, CVAP, and voter data
  - sources/: Data loader nodes (no dependencies)
  - eavs/: EAVS processing nodes
  - voters/: Voter registration and CVAP nodes

Usage:
    from election import Pipeline, EAVS_PRESET, RegionsNode

    pipeline = Pipeline(output_dir='./output')
    pipeline.run(EAVS_PRESET)
    pipeline.run_node(RegionsNode)
    pipeline.list_nodes()
"""

# Pipeline infrastructure
from .pipeline import (
    Pipeline,
    DataNode,
    register_node,
    NODE_REGISTRY,
    EAVS_PRESET,
    VOTER_REGISTRATION_PRESET,
    ELECTION_PRESET,
    ALL_PRESET,
)

# Re-export all node classes for convenience
from .pipeline import (
    # Source nodes
    StateFIPSNode,
    EAVS2024Node,
    EAVSCodebookNode,
    EAVSHistoricalNode,
    EquipmentDBNode,
    CVAPCountyNode,
    CVAPStateNode,
    CVAPBlockGroupNode,
    FloridaCountyCodesNode,
    FloridaVoterFilesNode,
    AlabamaBlockGroupsNode,
    AlabamaVotingDistrictsNode,

    # EAVS nodes
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

    # Voter nodes
    CVAPPercentNode,
    DropboxVotingNode,
    FloridaCountyMappingNode,
    FloridaVoterListNode,
    FloridaVoterSummaryNode,

    # Precinct election nodes
    CVAPPrecinctNode,
    AlabamaPrecinctResultsNode,
    PrecinctDemographicRegressionNode,
)

__all__ = [
    # Core infrastructure
    'Pipeline',
    'DataNode',
    'register_node',
    'NODE_REGISTRY',

    # Presets
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

    # Precinct election nodes
    'CVAPPrecinctNode',
    'AlabamaPrecinctResultsNode',
    'PrecinctDemographicRegressionNode',
]

__version__ = '4.0.0'


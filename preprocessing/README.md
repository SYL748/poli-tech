# Election Data Preprocessing Pipeline

A Python pipeline for processing election data from EAVS (Election Administration and Voting Survey), CVAP (Citizen Voting Age Population), and state voter registration files.

## Features

- **Graph-based pipeline architecture**: Declarative dependencies with automatic topological execution
- **Class-based node registry**: Type-safe node references with IDE autocomplete
- **Modular design**: Each data node in its own file for easy maintenance
- **CSV exports**: Automatic export of processed data to CSV files

## Installation

```bash
# Clone the repository
git clone <repository-url>
cd election-preprocessing

# Create virtual environment
python -m venv .venv
source .venv/bin/activate  # On Windows: .venv\Scripts\activate

# Install dependencies
pip install -r requirements.txt
```

### Data Directory 

**Required Data Files:**
- EAVS survey data (2016-2024) from [EAC](https://www.eac.gov/research-and-data/datasets-codebooks-and-surveys) (included)
- CVAP data from [Census Bureau](https://www.census.gov/programs-surveys/decennial-census/about/voting-rights/cvap.html) (included)
- Florida voter files from [Florida Division of Elections](https://dos.myflorida.com/elections/) (**not** included in repository due to size; download manually)

## Quick Start

### Command Line

```bash
# List all available nodes
python main.py --list-nodes

# Run a preset
python main.py --preset eavs      # EAVS processing nodes
python main.py --preset voter     # Voter registration nodes
python main.py --preset election  # Election analysis nodes
python main.py --preset all       # All exportable nodes

# Run a single node (with dependencies)
python main.py --node RegionsNode
python main.py --node ProvisionalByStateNode

# Specify output directory
python main.py --preset eavs --output-dir ./my_output
```

### Python API

```python
from election import Pipeline, RegionsNode, EAVS_PRESET

# Create pipeline
pipeline = Pipeline(output_dir='./output', data_dir='.')

# Run a single node
result = pipeline.run_node(RegionsNode)
print(result.head())

# Run a preset
pipeline.run(EAVS_PRESET)

# Run specific nodes
from election import ProvisionalByStateNode, EquipmentOverviewNode
pipeline.run([ProvisionalByStateNode, EquipmentOverviewNode])
```

## Available Nodes

### Source Nodes (Data Loaders)
| Node | Description |
|------|-------------|
| `StateFIPSNode` | State FIPS code mappings |
| `EAVS2024Node` | 2024 EAVS survey data |
| `EAVSHistoricalNode` | Historical EAVS data (2016-2022) |
| `CVAPCountyNode` | County-level CVAP data |
| `FloridaVoterFilesNode` | Florida voter registration files |

### EAVS Processing Nodes
| Node | Output File | Description |
|------|-------------|-------------|
| `RegionsNode` | `EAVS_Region.csv` | Jurisdictions/regions |
| `ProvisionalByRegionNode` | `Provisional_By_Region.csv` | Provisional ballots by region |
| `ProvisionalByStateNode` | `Provisional_Category_By_State.csv` | Provisional ballots by state |
| `EquipmentOverviewNode` | `Voting_Equipment_Overview.csv` | Voting equipment details |
| `EquipmentByStateNode` | `Equipment_Summary_by_State.csv` | Equipment by state |
| `EarlyVotingNode` | `State_Early_Voting.csv` | Early voting statistics |
| `MailRejectionByStateNode` | `Mail_Ballot_Rejection_By_State.csv` | Mail ballot rejections |
| `PollBookDeletionNode` | `Poll_Book_Deletion_By_State.csv` | Voter roll deletions |

### Voter/CVAP Nodes
| Node | Output File | Description |
|------|-------------|-------------|
| `CVAPPercentNode` | `Political_Party_State_Percent.csv` | Registration as % of CVAP |
| `DropboxVotingNode` | `Dropbox_Voting_BubbleChart.csv` | Dropbox voting statistics |
| `FloridaVoterListNode` | `fl_voter_list.csv` | Florida voter list |
| `FloridaVoterSummaryNode` | `fl_voter_summary.csv` | Florida voter summary by county |

## Creating Custom Nodes

```python
from election.pipeline.core import DataNode, register_node
from election.pipeline.sources import EAVS2024Node
from election.pipeline.eavs import RegionsNode

@register_node
class MyCustomNode(DataNode):
    """Custom processing node."""
    
    dependencies = [EAVS2024Node, RegionsNode]  # Class references
    output_filename = "my_output.csv"  # Set to None for intermediate nodes
    
    def process(self, inputs):
        eavs_data = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        
        # Your processing logic here
        result = eavs_data.merge(regions, ...)
        
        return result
```

## Data Sources

- **EAVS**: [Election Administration and Voting Survey](https://www.eac.gov/research-and-data/datasets-codebooks-and-surveys)
- **CVAP**: [Citizen Voting Age Population](https://www.census.gov/programs-surveys/decennial-census/about/voting-rights/cvap.html)
- **Florida Voter Files**: [Florida Division of Elections](https://dos.myflorida.com/elections/)

## Presets

| Preset | Description | Nodes |
|--------|-------------|-------|
| `eavs` | EAVS processing | Regions, provisional ballots, equipment, voting stats |
| `voter` | Voter registration | Florida voters, CVAP percentages |
| `election` | Election analysis | Dropbox voting, CVAP analysis |
| `all` | All exportable nodes | Everything with `output_filename` set |

## Data Folder
Contains all csv files that will be seeded using flyaway migration in the backend, but file `19_fl_voter_list` is way too big to seed using backend, so a script is included (`../MagicServer/script-to-load-fl-voters/load_19_fl_voter.sh`) to minimize the amount of time (~2min) needed to migrat this data into the database
PS: Run that script only after the springboot initialization
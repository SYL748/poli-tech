"""Equipment make/model parsing."""

import pandas as pd
from typing import Dict, Type

from ..core import DataNode, register_node
from .equipment_melted import EquipmentMeltedNode


@register_node
class EquipmentParsedNode(DataNode):
    """Parse make/model from equipment strings (intermediate node)."""

    dependencies = [EquipmentMeltedNode]
    output_filename = None

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        equipment_melted = inputs[EquipmentMeltedNode]
        unique_makes = equipment_melted['make_model'].unique()
        equipment_map = pd.DataFrame(index=unique_makes).rename_axis('make_model')
        equipment_map[['make', 'model']] = equipment_map.index.to_series().apply(self._separate_make_model)
        return equipment_map

    @staticmethod
    def _separate_make_model(make_model_str):
        if make_model_str in ['ImageCast Precinct/ICP (Dominion)']:
            make, model = 'Dominion', 'ImageCast Precinct'
        elif make_model_str in ['ImageCast Precint2/ICP2', 'ImageCast Precinct with Integrated BMD (Dominion)',
                                'DOMINION IMAGECAST PRINCINCT2/V5.17.17.1']:
            make = 'Dominion'
            model = 'ImageCast Precinct 2'
        elif make_model_str in ['ImageCast Central/ICC (Dominion)', 'DOMINION ICC CANNON DR-G1130/V5.17.17.1 COTS',
                                'DOMINION ICC INTERSCAN HIPRO 821/V5.17.17.1 COTS']:
            make, model = 'Dominion', 'ImageCast Central'
        elif make_model_str in ['AutoMARK (Dominion)', 'AUTOMARK (DOMINION)', 'AutoMARK (ES&S)']:
            make, model = 'Dominion', 'AutoMARK'
        elif make_model_str in ['ImageCast X/ICX BMD (Dominion)', 'ImageCast X/Samsung Galaxy Tab Pro (Dominion)']:
            make, model = 'Dominion', 'ImageCast X'
        elif make_model_str in ['ImageCast X/ICX DRE (Dominion)', 'DOMINION IMAGECAST X/V5.17.17.1']:
            make, model = 'Dominion', 'ImageCast X as DRE'
        elif make_model_str in ['ImageCast Evolution/ICE (Dominion)']:
            make, model = 'Dominion', 'ImageCast Evolution'
        elif make_model_str in ['ExpressVote (ES&S)', 'EXPRESS VOTE', 'EXPRESS VOTE ESS', 'ES&S EXPRESSVOTE KIOSK',
                                'EXPRESSVOTES AND AUTOMARKS',
                                'EXPRESS VOTE BALLOT MARKING KIOSKS USED FOR ADA VOTING.',
                                'EXPRESS VOTE TABULATOR V2.1', 'ESS', 'ExpressVote Tabulator (ES&S)']:
            make = 'ES&S'
            model = 'ExpressVote'
        elif make_model_str in ['ES&S EXPRESSVOTE XL', 'ExpressVoteXL', 'ExpressVoteXL (ES&S)']:
            make = 'ES&S'
            model = 'ExpressVote XL'
        elif make_model_str in ['M650 (ES&S)']:
            make = 'ES&S'
            model = 'M650'
        elif make_model_str in ['M100 (ES&S)']:
            make = 'ES&S'
            model = 'M100'
        elif make_model_str in ['iVotronic (ES&S)']:
            make = 'ES&S'
            model = 'iVotronic'
        elif make_model_str in ['EXPRESS TOUCH ES&S', 'ExpressTouch (ES&S)']:
            make = 'ES&S'
            model = 'ExpressTouch'
        elif make_model_str in ['DS 200 EXPRESS VOTE', 'DS200 (ES&S)', 'DS 200 TABULATOR', 'DS200 ES&S', 'DS-200',
                                'DS200']:
            make = 'ES&S'
            model = 'DS200'
        elif make_model_str in ['DS300 (ES&S)']:
            make = 'ES&S'
            model = 'DS300'
        elif make_model_str in ['DS450 (ES&S)']:
            make = 'ES&S'
            model = 'DS450'
        elif make_model_str in ['DS850 (ES&S)']:
            make = 'ES&S'
            model = 'DS850'
        elif make_model_str in ['DS950 (ES&S)']:
            make = 'ES&S'
            model = 'DS950'
        elif make_model_str in ['SLD-21V-237-B1R', 'AVALUE TECHNOLOGY INC SLD-21V-237-B1R']:
            make = 'Avalue'
            model = 'SLD-21V-237-B1R'
        elif make_model_str in ['INTERSCAN HIPRO SCANNER WITH DEMOCACY SUITE 5.10A ']:
            make = 'Dominion'
            model = 'Democracy Suite 5.10-A'
        elif make_model_str in ['HART VERACITY', 'Veracity (Hart)']:
            make = 'Hart InterCivic'
            model = 'Veracity'
        elif make_model_str in ['HART INTERCIVIC']:
            make = 'Hart'
            model = 'InterCivic'
        elif make_model_str in ['VERITY VOTING 2.5', 'HART VERITY 2.5', 'HART INTERCIVIC 2.5',
                                'HART INTERCIVIC VERITY 2.5', 'HART DUOS VEERITY 2.5']:
            make = 'Hart InterCivic'
            model = 'Verity Voting 2.5'
        elif make_model_str in ['Verity Central (Hart)', 'BallotNow (Hart)']:
            make = 'Hart InterCivic'
            model = 'Verity Central'
        elif make_model_str in ['HART-VERITY SCANNER', 'Verity Scan (Hart)', 'VERITY SCAN']:
            make = 'Hart InterCivic'
            model = 'Verity Scan'
        elif make_model_str in ['HART INTERCIVIC VERITY 2.7']:
            make, model = 'Hart InterCivic', 'Verity 2.7'
        elif make_model_str in ['Verity Touch DRE (Hart)']:
            make, model = 'Hart InterCivic', 'Verity Touch'
        elif make_model_str in ['Verity Touch Writer (Hart)']:
            make, model = 'Hart InterCivic', 'Verity Touch Writer'
        elif make_model_str in ['HARP VERITY', 'DUO STANDALONE', 'DUO STAND ALONE', 'HART-VERITY DUO',
                                'HART INTERCIVIC VERITY DUO', 'VERITY DUO STANDALONE', 'DUO STAND ALONE (HART)',
                                'HART VERITY DUO', 'VERITY TOUCH DUO', 'VERITY DUO (HART)',
                                'HART VERITY DUO (BALLOT MARKERS), CONTROLLERS, SCANNERS',
                                'HART VERITY DUO VERSION 2.5', 'HART VERITY DUO STANDALONE WITH ACCESS', 'HART DUO',
                                'VERITY DUO']:
            make = 'Hart InterCivic'
            model = 'Verity Duo'
        elif make_model_str in ['VERITY DUO GO']:
            make, model = 'Hart InterCivic', 'Verity Duo Go'
        elif make_model_str in ['VERITY CONTROLLER (HART)']:
            make, model = 'Hart InterCivic', 'Verity Controller'
        elif make_model_str in ['eSlate (Hart)']:
            make, model = 'Hart InterCivic', 'eSlate'
        elif make_model_str in ['eScan (Hart)']:
            make, model = 'Hart InterCivic', 'eScan'
        elif make_model_str.startswith('CANON'):
            make = 'Canon'
            model = make_model_str.replace('CANON', '').strip()
        elif make_model_str in ['VOTING WORKS']:
            make = 'VotingWorks'
            model = 'Unknown'
        elif make_model_str.startswith('VOTINGWORKS'):
            make = 'VotingWorks'
            model = make_model_str.replace('VOTINGWORKS', '').strip()
        elif make_model_str in ['INTERSCAN HI-PRO 8X1']:
            make = 'InterScan'
            model = 'HiPro 8x1'
        elif make_model_str in ['ClearAccess (Clear Ballot)', 'CLEAR BALLOT ACCESS UNIT']:
            make = 'Clear Ballot'
            model = 'ClearAccess'
        elif make_model_str in ['ClearCount (Clear Ballot)']:
            make = 'Clear Ballot'
            model = 'ClearCount'
        elif make_model_str in ['CLEAR BALLOT CAST UNIT', 'ClearCast (Clear Ballot)']:
            make = 'Clear Ballot'
            model = 'ClearCast'
        elif make_model_str in ['ClearMark (Clear Ballot)']:
            make, model = 'Clear Ballot', 'ClearMark'
        elif make_model_str.startswith('KODAK'):
            make = 'Kodak'
            model = make_model_str.replace('KODAK', '').strip()
        elif make_model_str in ['FUJITSU SCANNERS CENTRAL COUNTY FI-7900']:
            make = 'Fujitsu'
            model = 'fi-7900'
        elif make_model_str in ['OpenElect OVI (Unisyn)']:
            make, model = 'Unisyn', 'OpenElect OVI'
        elif make_model_str in ['OpenElect FVT (Unisyn)', 'UNISYN FREEDOM VOTE TABLET',
                                'UNISYN FREEDOM VOTE 8033-9200']:
            make, model = 'Unisyn', 'Freedom Vote Tablet (FVT)'
        elif make_model_str in ['OpenElect Freedom Vote Scan (Unisyn)', 'UNISYN FREEDOM VOTE SCANNER']:
            make, model = 'Unisyn', 'Freedom Vote Scan (FVS)'
        elif make_model_str in ['UNISYN OPENELECT VOTING OPTICAL', 'OpenElect Voting Optical Scan/OVO (Unisyn)']:
            make, model = 'Unisyn', 'OpenElect OVO'
        elif make_model_str in ['OpenElect Voting Central Scan/OVCS (Unisyn)']:
            make, model = 'Unisyn', 'OpenElect (OVCS)'
        elif make_model_str in ['VSAP Ballot Marking Device (Los Angeles County)']:
            make, model = 'Smartmatic/Los Angeles County', 'VSAP BMD'
        elif make_model_str in ['VSAP IBML (Los Angeles County)', 'IBML (Los Angeles County)']:
            make, model = 'Smartmatic/Los Angeles County', 'IBML'
        elif make_model_str in ['Inspire Ballot Marking System (IVS)']:
            make, model = 'IVS', 'Inspire Ballot Marking Device'
        elif make_model_str in ['DIEBOLD TSX', 'AccuVote-TSX (Premier)']:
            make, model = 'Premier Election Solutions (Diebold)', 'AccuVote TSX'
        elif make_model_str in ['UNISYN ACCUVOTE']:
            make, model = 'Unisyn', 'AccuVote'
        elif make_model_str in ['DIEBOLD TOUCHSCREEN', 'GBS TSX']:
            make, model = 'Premier Election Solutions (Diebold)', 'TSX'
        elif make_model_str in ['ACCUVOTE/A,B,C,D-1.96.13, DOMINION', 'AccuVote-OS (Premier)', 'DIEBOLD OPTICAL SCAN',
                                'GBS OS']:
            make = 'Premier Election Solutions (Diebold)'
            model = 'AccuVote OS'
        elif make_model_str in ['MICROVOTE EMS 4.41 VOTING SYSTEM']:
            make, model = 'MicroVote', 'EMS'
        elif make_model_str in ['Chatsworth ACP (MicroVotes)']:
            make, model = 'MicroVote', 'Chatsworth ACP'
        elif make_model_str in ['MICROVOTE INFINITY VP-1 WITH VVPAT', ]:
            make, model = 'MicroVote', 'Infinity'
        elif make_model_str in ['Advantage (Sequoia)']:
            make, model = 'Sequoia Voting Systems', 'AV'
        elif make_model_str in ['OmniBallot Tablet (Democracy Live)']:
            make, model = 'Democracy Live', 'OmniBallot Tablet'
        else:
            print(f'Warning: Unknown make/model combination: {make_model_str}')
            make = model = 'Unknown'

        # additional post-processing
        if len(model) > 0 and model[0] == '-':
            model = model[1:].strip()

        return pd.Series({'make': make, 'model': model})

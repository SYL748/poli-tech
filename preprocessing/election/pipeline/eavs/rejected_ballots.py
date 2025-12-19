"""Rejected ballots vs equipment quality analysis."""

import pandas as pd
import matplotlib.pyplot as plt
from typing import Dict, Type
from pathlib import Path

from ..core import DataNode, register_node
from ..sources import EAVS2024Node, EquipmentDBNode
from .regions import RegionsNode
from .equipment_melted import EquipmentMeltedNode
from .equipment_parsed import EquipmentParsedNode
from ...util import calculate_percentage


@register_node
class RejectedBallotsNode(DataNode):
    """Analyze rejected ballots vs equipment quality."""

    dependencies = [EAVS2024Node, RegionsNode, EquipmentMeltedNode, EquipmentParsedNode, EquipmentDBNode]
    output_filename = "VotingEquipmentQuality_RejectionBubbleChart.csv"

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        eavs_df = inputs[EAVS2024Node]
        regions = inputs[RegionsNode]
        equipment_melted = inputs[EquipmentMeltedNode]
        equipment_parsed = inputs[EquipmentParsedNode]
        equipment_db = inputs[EquipmentDBNode]

        # 1. Calculate rejected ballots and total ballots per region (EAVS)
        # Fields:
        # C9a: Total Mail Ballots Rejected
        # E1d: Provisional Ballots Rejected
        # B24a: UOCAVA Ballots Rejected

        # Total ballots denominator components:
        # C8a: Total Mail Ballots Counted
        # F1c: Early voting (in person)
        # F1d: Election day
        # E1b: Provisional Ballots Counted

        # Extract columns
        cols_to_extract = ['C9a', 'E1d', 'B24a', 'C8a', 'F1c', 'F1d', 'E1b', 'F1a']
        # Note: B24a is UOCAVA rejected.

        # We need to handle -88, -99, etc. (missing values).
        # Helper to clean numeric columns
        def clean_numeric(series):
            return pd.to_numeric(series, errors='coerce').fillna(0).apply(lambda x: x if x >= 0 else 0)

        df = eavs_df.copy()
        # Use index as region_id if not present
        if 'region_id' not in df.columns:
            df.index.name = 'region_id'
            df = df.reset_index()

        # Join with regions to get region_name
        # RegionsNode output columns: name, state_id, geo_id. Index is 'id' (region_id).
        # We need 'name' from regions, which corresponds to 'region_name' in our output.
        df = df.merge(regions[['name']], left_on='region_id', right_index=True, how='left')
        df = df.rename(columns={'name': 'region_name'})

        # Calculate rejected counts
        df['mail_rejected'] = clean_numeric(df['C9a'])
        df['provisional_rejected'] = clean_numeric(df['E1d'])
        df['uocava_rejected'] = clean_numeric(df['B24a'])
        df['total_rejected_ballots'] = df['mail_rejected'] + df['provisional_rejected'] + df['uocava_rejected']

        # Calculate counted ballots (denominator components)
        df['absentee_ballots_counted'] = clean_numeric(df['C8a'])
        df['early_in_person_ballots'] = clean_numeric(df['F1c'])
        df['election_day_ballots'] = clean_numeric(df['F1d'])
        df['provisional_ballots_counted'] = clean_numeric(df['E1b'])
        df['total_participation'] = clean_numeric(df['F1a'])

        # Total ballots
        # df['total_ballots'] = (df['absentee_ballots_counted'] +
        #                        df['early_in_person_ballots'] +
        #                        df['election_day_ballots'] +
        #                        df['provisional_ballots_counted'])
        df['total_ballots'] = df['total_participation']

        # Percent rejected
        df['percent_rejected_ballots'] = calculate_percentage(df, 'total_rejected_ballots', 'total_ballots')

        # 2. Calculate Equipment Quality Score per region
        # We need to link regions to equipment.
        # Use EquipmentMeltedNode -> EquipmentParsedNode -> EquipmentDBNode

        # Join melted with parsed
        equip_joined = equipment_melted.merge(
            equipment_parsed, left_on='make_model', right_index=True, how='left'
        )

        # Join with EquipmentDB to get quality score
        # EquipmentDBNode calculates 'reliability' which serves as the quality score (0-1).
        equip_joined = equip_joined.merge(
            equipment_db[['make', 'model', 'reliability']],
            on=['make', 'model'],
            how='left'
        )

        # Calculate weighted average quality per region
        # Weight by 'number_deployed'

        def calculate_weighted_quality(group):
            total_deployed = group['number_deployed'].sum()
            if total_deployed == 0:
                return 0.0

            # Fill missing reliability with 0
            group['reliability'] = group['reliability'].fillna(0)

            weighted_sum = (group['reliability'] * group['number_deployed']).sum()
            return round(weighted_sum / total_deployed, 2)

        quality_by_region = equip_joined.groupby('region_id').apply(calculate_weighted_quality, include_groups=False).reset_index(name='equipment_quality_score')

        # 3. Merge everything
        final_df = df[['region_id', 'region_name',
                       'mail_rejected', 'provisional_rejected', 'uocava_rejected', 'total_rejected_ballots',
                       'absentee_ballots_counted', 'early_in_person_ballots', 'election_day_ballots',
                       'provisional_ballots_counted', 'total_ballots', 'percent_rejected_ballots']]

        final_df = final_df.merge(quality_by_region, on='region_id', how='left')

        # Fill NaN quality with 0 (or maybe average?)
        final_df['equipment_quality_score'] = final_df['equipment_quality_score'].fillna(0)

        # Format as string to ensure 2 decimal places and avoid floating point errors
        final_df['equipment_quality_score'] = final_df['equipment_quality_score'].apply(lambda x: f"{x:.2f}")

        # Reorder columns as requested
        output_cols = [
            'region_id', 'region_name', 'equipment_quality_score',
            'mail_rejected', 'provisional_rejected', 'uocava_rejected', 'total_rejected_ballots',
            'absentee_ballots_counted', 'early_in_person_ballots', 'election_day_ballots',
            'provisional_ballots_counted', 'total_ballots', 'percent_rejected_ballots'
        ]

        result_df = final_df[output_cols].set_index('region_id')

        # 4. Create bubble chart visualization
        # Convert quality score back to float for plotting
        plot_df = final_df.copy()
        plot_df['equipment_quality_score_float'] = plot_df['equipment_quality_score'].astype(float)
        plot_df = plot_df.dropna(subset=['equipment_quality_score_float', 'percent_rejected_ballots'])

        fig, ax = plt.subplots(figsize=(12, 8))

        # Create scatter plot with bubble size based on total ballots
        scatter = ax.scatter(
            plot_df['equipment_quality_score_float'],
            plot_df['percent_rejected_ballots'],
            s=plot_df['total_ballots'] / 100,  # Scale down for reasonable bubble sizes
            alpha=0.6,
            c=plot_df['percent_rejected_ballots'],
            cmap='RdYlGn_r',  # Red-Yellow-Green reversed (red for high rejection)
            edgecolors='black',
            linewidth=0.5
        )

        ax.set_xlabel('Equipment Quality Score', fontsize=12, fontweight='bold')
        ax.set_ylabel('Rejection Percentage (%)', fontsize=12, fontweight='bold')
        ax.set_title('Voting Equipment Quality vs Ballot Rejection Rate by County/Region', fontsize=14, fontweight='bold')
        ax.grid(True, alpha=0.3)

        # Add colorbar
        cbar = plt.colorbar(scatter, ax=ax)
        cbar.set_label('Rejection Percentage (%)', fontsize=11)

        plt.tight_layout()

        # Save the plot
        output_dir = Path(__file__).parent.parent.parent.parent / 'output' / 'plots'
        output_dir.mkdir(parents=True, exist_ok=True)
        plot_path = output_dir / 'equipment_quality_vs_rejection_bubble.png'
        plt.savefig(plot_path, dpi=300, bbox_inches='tight')
        plt.close()

        return result_df

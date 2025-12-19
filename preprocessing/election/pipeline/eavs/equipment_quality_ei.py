"""Ecological inference analysis of equipment quality by demographics."""

import pandas as pd
import numpy as np
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import CVAPCountyNode
from .regions import RegionsNode
from .rejected_ballots import RejectedBallotsNode


@register_node
class EquipmentQualityEINode(DataNode):
    """Ecological inference analysis of equipment quality by demographic groups using PyEI.

    Uses King's Ecological Inference (TwoByTwoEI) with the king99_pareto_modification model,
    which is based on King (1997) with improvements for numerical stability.

    This node analyzes the relationship between voting equipment quality scores (0-1)
    and demographic composition to estimate equipment quality distributions per demographic group.
    """

    dependencies = [RejectedBallotsNode, CVAPCountyNode, RegionsNode]
    output_filename = "Equipment_Quality_EI_KDE.csv"

    # Demographic groups to analyze (only "Alone" categories, excluding aggregates and multi-race)
    # Maps CVAP category names to shorter output names
    DEMOGRAPHIC_GROUPS = {
        'White Alone': 'White',
        'Hispanic or Latino': 'Hispanic',
        'Black or African American Alone': 'African American',
    }

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        from pyei import TwoByTwoEI
        from scipy import stats
        import matplotlib.pyplot as plt

        rejected_df = inputs[RejectedBallotsNode].reset_index()
        cvap_df = inputs[CVAPCountyNode]
        regions = inputs[RegionsNode]

        # Parse geoid to get FIPS code (5 digits) from CVAP data
        cvap_df['fips'] = cvap_df['geoid'].str.extract(r'(\d{5})$')

        # Get geo_id and state_id from regions
        regions_with_fips = regions.reset_index().rename(columns={'id': 'region_id'})

        # Join rejected_df with regions to get geo_id (FIPS) and state_id
        data_with_fips = rejected_df.merge(
            regions_with_fips[['region_id', 'geo_id', 'state_id']],
            on='region_id',
            how='left'
        )

        # Convert equipment_quality_score to float (it's stored as string with 2 decimal places)
        data_with_fips['equipment_quality'] = pd.to_numeric(
            data_with_fips['equipment_quality_score'], errors='coerce'
        ).fillna(0)

        # Use total_ballots as precinct population (consistent with rejected ballots EI)
        data_with_fips['precinct_pop'] = data_with_fips['total_ballots']

        # Filter out regions with no ballots or invalid equipment quality
        data_with_fips = data_with_fips[
            (data_with_fips['precinct_pop'] > 0) &
            (data_with_fips['equipment_quality'] >= 0) &
            (data_with_fips['equipment_quality'] <= 1)
        ]

        # Pivot CVAP data to get demographic proportions per county
        cvap_filtered = cvap_df[cvap_df['lntitle'].isin(self.DEMOGRAPHIC_GROUPS.keys())]

        # Get total CVAP for each county
        cvap_totals = cvap_df[cvap_df['lntitle'] == 'Total'][['fips', 'cvap_est']].copy()
        cvap_totals = cvap_totals.rename(columns={'cvap_est': 'total_cvap'})
        cvap_totals['total_cvap'] = pd.to_numeric(cvap_totals['total_cvap'], errors='coerce')

        # Merge totals with demographic data
        cvap_with_totals = cvap_filtered.merge(cvap_totals, on='fips', how='left')
        cvap_with_totals['cvap_est'] = pd.to_numeric(cvap_with_totals['cvap_est'], errors='coerce')
        cvap_with_totals['proportion'] = (
            cvap_with_totals['cvap_est'] / cvap_with_totals['total_cvap']
        ).fillna(0)

        # Generate KDE data for each state and demographic group using PyEI
        kde_results = []
        # Ecological inference with only Florida for now
        # To run all states, use: states = data_with_fips['state_id'].dropna().unique()
        # states = ['12', '48']
        states = ['48']

        # Create plots directory for EI visualizations
        output_dir = self.data_dir / 'output'
        plots_dir = output_dir / 'plots' / 'equipment_quality_ei'
        plots_dir.mkdir(parents=True, exist_ok=True)

        for state_id in states:
            state_data = data_with_fips[data_with_fips['state_id'] == state_id].copy()

            if len(state_data) < 5:
                continue

            for demo, demo_short in self.DEMOGRAPHIC_GROUPS.items():
                # Get demographic proportions for this state's counties
                state_cvap = cvap_with_totals[
                    (cvap_with_totals['lntitle'] == demo) &
                    (cvap_with_totals['fips'].isin(state_data['geo_id']))
                ][['fips', 'proportion']].copy()

                # Merge with equipment quality data
                analysis_df = state_data.merge(
                    state_cvap,
                    left_on='geo_id',
                    right_on='fips',
                    how='inner'
                )

                if len(analysis_df) < 5:
                    continue

                # Prepare data for PyEI
                group_fraction = analysis_df['proportion'].values
                votes_fraction = analysis_df['equipment_quality'].values
                precinct_pops = analysis_df['precinct_pop'].values.astype(float)

                # Filter valid data
                valid_mask = (
                    (group_fraction >= 0) & (group_fraction <= 1) &
                    (votes_fraction >= 0) & (votes_fraction <= 1) &
                    (precinct_pops > 0) &
                    np.isfinite(group_fraction) &
                    np.isfinite(votes_fraction) &
                    np.isfinite(precinct_pops)
                )

                if valid_mask.sum() < 5:
                    continue

                group_fraction = group_fraction[valid_mask]
                votes_fraction = votes_fraction[valid_mask]
                precinct_pops = precinct_pops[valid_mask]

                try:
                    # Run King's Ecological Inference using TwoByTwoEI
                    # king99_pareto_modification is a numerically stable variant of King (1997)
                    # It uses a truncated bivariate normal over tomography lines with Pareto smoothing
                    ei = TwoByTwoEI(model_name='king99_pareto_modification')
                    ei.fit(
                        group_fraction=group_fraction,
                        votes_fraction=votes_fraction,
                        precinct_pops=precinct_pops,
                        demographic_group_name=demo_short,
                        candidate_name='Equipment Quality',
                        tune=200,
                        draws=1000,
                        chains=4,
                        progressbar=True
                    )

                    # Get sampled voting preferences
                    # sampled_voting_prefs is a list: [samples_for_group, samples_for_complement]
                    # We want the first element: samples for the demographic group
                    quality_samples = ei.sampled_voting_prefs[0]

                    # Save pyei built-in KDE plot (shows both in-group and out-group probability densities)
                    try:
                        fig, ax = plt.subplots(figsize=(10, 6))
                        ei.plot_kde(ax=ax)
                        ax.set_title(f'Equipment Quality EI - State {state_id} - {demo_short}')
                        ax.set_xlabel('Equipment Quality Score')
                        plot_filename = f'ei_kde_state_{state_id}_{demo_short.lower().replace(" ", "_")}.png'
                        fig.savefig(plots_dir / plot_filename, dpi=150, bbox_inches='tight')
                        plt.close(fig)
                        print(f"    Saved plot: {plot_filename}")
                    except Exception as plot_error:
                        print(f"    Warning: Could not save plot for {state_id}/{demo_short}: {plot_error}")

                    # Generate KDE from the posterior samples
                    kde = stats.gaussian_kde(quality_samples)
                    # Use fixed range 0 to 1 for equipment quality
                    x_grid = np.linspace(0, 1.0, 100)
                    kde_values = kde(x_grid)

                    # Normalize to [0, 1] range for relative probability output
                    max_kde = kde_values.max()
                    if max_kde > 0:
                        normalized_kde = kde_values / max_kde
                    else:
                        normalized_kde = kde_values
                    normalized_kde = np.clip(normalized_kde, 0, 1)

                    for x, y, z in zip(x_grid, normalized_kde, kde_values):
                        kde_results.append({
                            'state_id': state_id,
                            'demographic': demo_short,
                            'equipment_quality': round(x, 4),
                            'relative_probability': round(y, 4),
                            'probability_density': round(z, 4)
                        })

                except Exception as e:
                    # If EI model fails, skip this demographic/state
                    print(f"  EI failed for {state_id}/{demo_short}: {e}")
                    continue

        result_df = pd.DataFrame(kde_results)

        # Manual JSON export as requested
        if self.output_filename:
            import json
            from pathlib import Path
            output_dir = self.data_dir / 'output'
            json_dir = output_dir / 'json'
            json_dir.mkdir(parents=True, exist_ok=True)

            base_filename = Path(self.output_filename).stem
            json_relative_path = json_dir / f"{base_filename}_relative.json"
            json_density_path = json_dir / f"{base_filename}_density.json"

            # Group by state_id and demographic
            json_relative = {}
            json_density = {}

            if not result_df.empty:
                for state_id, state_group in result_df.groupby('state_id'):
                    json_relative[state_id] = {}
                    json_density[state_id] = {}

                    for demo, demo_group in state_group.groupby('demographic'):
                        json_relative[state_id][demo] = demo_group[
                            ['equipment_quality', 'relative_probability']
                        ].to_dict(orient='records')

                        json_density[state_id][demo] = demo_group[
                            ['equipment_quality', 'probability_density']
                        ].to_dict(orient='records')

            with open(json_relative_path, 'w') as f:
                json.dump(json_relative, f, indent=2)
            print(f"    Exported JSON: {json_relative_path}")

            with open(json_density_path, 'w') as f:
                json.dump(json_density, f, indent=2)
            print(f"    Exported JSON: {json_density_path}")

        return result_df


"""Ecological inference analysis of rejected ballots by demographics."""

import pandas as pd
import numpy as np
from typing import Dict, Type

from ..core import DataNode, register_node
from ..sources import CVAPCountyNode
from .regions import RegionsNode
from .rejected_ballots import RejectedBallotsNode


@register_node
class RejectedBallotsEINode(DataNode):
    """Ecological inference analysis of rejected ballots by demographic groups using PyEI.

    Uses King's Ecological Inference (TwoByTwoEI) with the king99_pareto_modification model,
    which is based on King (1997) with improvements for numerical stability.

    King's EI differs from Goodman's ER by:
    1. Respecting the [0,1] bounds on probabilities using the Method of Bounds
    2. Using a truncated bivariate normal distribution over the tomography lines
    3. Better handling of ecological fallacy by modeling precinct-level heterogeneity
    """

    dependencies = [RejectedBallotsNode, CVAPCountyNode, RegionsNode]
    output_filename = "Rejected_Ballots_EI_KDE.csv"

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
        rejected_with_fips = rejected_df.merge(
            regions_with_fips[['region_id', 'geo_id', 'state_id']],
            on='region_id',
            how='left'
        )

        # Calculate rejection fraction per region
        rejected_with_fips['total_ballots_with_rejected'] = (
            rejected_with_fips['total_ballots'] + rejected_with_fips['total_rejected_ballots']
        )
        rejected_with_fips['rejection_fraction'] = (
            rejected_with_fips['total_rejected_ballots'] /
            rejected_with_fips['total_ballots_with_rejected']
        ).fillna(0)

        # Filter out regions with no ballots
        rejected_with_fips = rejected_with_fips[rejected_with_fips['total_ballots_with_rejected'] > 0]

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
        # Ecological inference with only Alabama for demonstration
        # To run all states, uncomment: states = rejected_with_fips['state_id'].dropna().unique()
        # states = ['01', '12', '48']
        states = ['48']

        # Create plots directory for EI visualizations
        output_dir = self.data_dir / 'output'
        plots_dir = output_dir / 'plots' / 'rejected_ballots_ei'
        plots_dir.mkdir(parents=True, exist_ok=True)

        for state_id in states:
            state_rejected = rejected_with_fips[rejected_with_fips['state_id'] == state_id].copy()

            if len(state_rejected) < 5:
                continue

            for demo, demo_short in self.DEMOGRAPHIC_GROUPS.items():
                # Get demographic proportions for this state's counties
                state_cvap = cvap_with_totals[
                    (cvap_with_totals['lntitle'] == demo) &
                    (cvap_with_totals['fips'].isin(state_rejected['geo_id']))
                ][['fips', 'proportion']].copy()

                # Merge with rejection data
                analysis_df = state_rejected.merge(
                    state_cvap,
                    left_on='geo_id',
                    right_on='fips',
                    how='inner'
                )

                if len(analysis_df) < 5:
                    continue

                # Prepare data for PyEI
                group_fraction = analysis_df['proportion'].values
                votes_fraction = analysis_df['rejection_fraction'].values
                precinct_pops = analysis_df['total_ballots_with_rejected'].values.astype(float)

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
                        candidate_name='Rejected',
                        tune=200,
                        draws=1000,
                        chains=4,
                        progressbar=True
                    )

                    # Save pyei built-in KDE plot (shows both in-group and out-group probability densities)
                    try:
                        fig, ax = plt.subplots(figsize=(10, 6))
                        ei.plot_kde(ax=ax)
                        ax.set_title(f'Rejected Ballots EI - State {state_id} - {demo_short}')
                        ax.set_xlabel('Rejection Probability')
                        plot_filename = f'ei_kde_state_{state_id}_{demo_short.lower().replace(" ", "_")}.png'
                        fig.savefig(plots_dir / plot_filename, dpi=150, bbox_inches='tight')
                        plt.close(fig)
                        print(f"    Saved plot: {plot_filename}")
                    except Exception as plot_error:
                        print(f"    Warning: Could not save plot for {state_id}/{demo_short}: {plot_error}")

                    # Get sampled voting preferences
                    # sampled_voting_prefs is a list: [samples_for_group, samples_for_complement]
                    # We want the first element: samples for the demographic group
                    rejection_samples = ei.sampled_voting_prefs[0]

                    # Generate KDE from the posterior samples
                    kde = stats.gaussian_kde(rejection_samples)
                    x_grid = np.linspace(0, min(rejection_samples.max() * 1.2, 1.0), 100)
                    kde_values = kde(x_grid)

                    # Normalize
                    kde_values_norm = kde_values / kde_values.max() if kde_values.max() > 0 else kde_values

                    for x, y, z in zip(x_grid, kde_values, kde_values_norm):
                        kde_results.append({
                            'state_id': state_id,
                            'demographic': demo_short,
                            'rejection_probability': round(x, 4),
                            'probability_density': round(y, 4),
                            'relative_probability': round(z, 4)
                        })

                except Exception as e:
                    # If EI model fails, skip this demographic/state
                    print(f"  EI failed for {state_id}/{demo_short}: {e}")
                    continue

        result_df = pd.DataFrame(kde_results)

        # Manual JSON export
        if self.output_filename:
            import json
            from pathlib import Path
            output_dir = self.data_dir / 'output'
            json_dir = output_dir / 'json'
            json_dir.mkdir(parents=True, exist_ok=True)

            base_stem = Path(self.output_filename).stem

            # Prepare data structures
            json_output_relative = {}
            json_output_density = {}

            if not result_df.empty:
                for state_id, state_group in result_df.groupby('state_id'):
                    json_output_relative[state_id] = {}
                    json_output_density[state_id] = {}
                    for demo, demo_group in state_group.groupby('demographic'):
                        json_output_relative[state_id][demo] = demo_group[
                            ['rejection_probability', 'relative_probability']
                        ].to_dict(orient='records')

                        json_output_density[state_id][demo] = demo_group[
                            ['rejection_probability', 'probability_density']
                        ].to_dict(orient='records')

            # Export Relative Probability
            json_path_relative = json_dir / f"{base_stem}_Relative.json"
            with open(json_path_relative, 'w') as f:
                json.dump(json_output_relative, f, indent=2)
            print(f"    Exported JSON: {json_path_relative}")

            # Export Probability Density
            json_path_density = json_dir / f"{base_stem}_Density.json"
            with open(json_path_density, 'w') as f:
                json.dump(json_output_density, f, indent=2)
            print(f"    Exported JSON: {json_path_density}")

        return result_df

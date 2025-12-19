"""Non-linear regression analysis of precinct election results by demographics."""

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from pathlib import Path
from typing import Dict, Type

from ..core import DataNode, register_node
from .alabama_precinct_results import AlabamaPrecinctResultsNode
from .texas_precinct_results import TexasPrecinctResultsNode


@register_node
class PrecinctDemographicRegressionNode(DataNode):
    """Compute polynomial regression coefficients for party vote % vs demographic %.

    Performs quadratic polynomial regression (y = ax² + bx + c) for each combination
    of political party (Democratic, Republican) and demographic group (White, Hispanic,
    African American).

    The regression line coefficients can be used to visualize trend lines on a bubble
    chart where:
    - X-axis: Percentage of demographic group in precinct
    - Y-axis: Percentage of party votes in precinct

    Output columns:
    - party: Political party (Democratic, Republican)
    - demographic: Demographic group name
    - coef_a: Quadratic coefficient (x²)
    - coef_b: Linear coefficient (x)
    - coef_c: Constant term
    """

    dependencies = [AlabamaPrecinctResultsNode, TexasPrecinctResultsNode]
    output_filename = "Precinct_Demographic_Regression.csv"

    # Mapping of demographic column names to display names
    DEMOGRAPHICS = {
        'white_pct': 'White',
        'hispanic_pct': 'Hispanic',
        'african_american_pct': 'African American',
    }

    # Party configurations
    PARTIES = ['Democratic', 'Republican']

    def process(self, inputs: Dict[Type[DataNode], pd.DataFrame]) -> pd.DataFrame:
        states_data = {
            'Alabama': inputs[AlabamaPrecinctResultsNode],
            'Texas': inputs[TexasPrecinctResultsNode]
        }

        all_results = []

        for state_name, state_df in states_data.items():
            # Setup plot for testing
            fig, axes = plt.subplots(2, 2, figsize=(20, 16))
            axes = axes.flatten()
            fig.suptitle(f'{state_name} Precinct Voting vs Demographics', fontsize=16)

            for idx, (demo_col, demo_name) in enumerate(self.DEMOGRAPHICS.items()):
                ax = axes[idx]

                for party_name in self.PARTIES:
                    # Filter for this party's data
                    party_df = state_df[state_df['party'] == party_name].copy()

                    # Filter out rows with missing data
                    valid_mask = (
                        party_df['vote_percent'].notna() &
                        party_df[demo_col].notna()
                    )
                    valid_df = party_df[valid_mask]

                    if len(valid_df) < 10:
                        print(f"    Skipping {state_name} {party_name}/{demo_name}: insufficient data ({len(valid_df)} precincts)")
                        continue

                    # Extract x (demographic %) and y (party vote %)
                    x = valid_df[demo_col].values
                    y = valid_df['vote_percent'].values

                    # Bubble sizes (scaled)
                    # Use total_votes if available, otherwise use votes or default to uniform size
                    if 'total_votes' in valid_df.columns:
                        sizes = valid_df['total_votes'].fillna(0)
                    elif 'votes' in valid_df.columns:
                        sizes = valid_df['votes'].fillna(0)
                    else:
                        sizes = 10

                    if isinstance(sizes, pd.Series) and sizes.max() > 0:
                        sizes = sizes / sizes.max() * 200  # Scale max size to 200
                    elif not isinstance(sizes, (int, float)):
                        sizes = 10

                    # Plot bubbles
                    color = 'red' if party_name == 'Republican' else 'white'
                    edgecolor = 'darkred' if party_name == 'Republican' else 'blue'
                    ax.scatter(x, y, s=sizes, c=color, edgecolors=edgecolor, alpha=0.3, label=party_name)

                    # Perform quadratic polynomial regression: y = ax² + bx + c
                    # numpy.polyfit returns coefficients [a, b, c] for degree 2
                    try:
                        coefficients = np.polyfit(x, y, deg=2)
                        coef_a, coef_b, coef_c = coefficients

                        all_results.append({
                            'state': state_name,
                            'party': party_name,
                            'demographic': demo_name,
                            'coef_a': round(coef_a, 6),
                            'coef_b': round(coef_b, 6),
                            'coef_c': round(coef_c, 6),
                        })

                        # Plot regression line
                        x_range = np.linspace(0, 100, 100)
                        y_pred = coef_a * x_range**2 + coef_b * x_range + coef_c
                        line_color = 'red' if party_name == 'Republican' else 'blue'
                        ax.plot(x_range, y_pred, c=line_color, linewidth=2, linestyle='--')

                        print(f"    {state_name} {party_name}/{demo_name}: fitted polynomial regression")

                    except Exception as e:
                        print(f"    Error fitting {state_name} {party_name}/{demo_name}: {e}")
                        continue

                ax.set_title(f'Vote % vs {demo_name} %')
                ax.set_xlabel(f'{demo_name} %')
                ax.set_ylabel('Vote %')
                ax.set_xlim(0, 100)
                ax.set_ylim(0, 100)
                ax.legend()

            # Save plot
            plot_path = self.data_dir / 'output' / f'demographic_regression_bubbles_{state_name}.png'
            plt.tight_layout()
            plt.savefig(plot_path)
            plt.close()
            print(f"    Saved regression bubble chart to {plot_path}")

        result_df = pd.DataFrame(all_results)

        if result_df.empty:
            # Return empty DataFrame with expected columns
            return pd.DataFrame(columns=[
                'state', 'party', 'demographic', 'coef_a', 'coef_b', 'coef_c'
            ])

        # Export to JSON
        json_dir = self.data_dir / 'output/json'
        json_dir.mkdir(parents=True, exist_ok=True)
        json_path = json_dir / 'Precinct_Demographic_Regression.json'
        result_df.to_json(json_path, orient='records', indent=2)
        print(f"    Exported precinct demographic regression to {json_path}")

        return result_df


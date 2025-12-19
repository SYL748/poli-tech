"""Tests for precinct election pipeline nodes."""

import pytest
import pandas as pd
from pathlib import Path


class TestPrecinctElectionNodes:
    """Test cases for precinct election pipeline nodes."""

    def test_cvap_block_group_node_loads_alabama_data(self):
        """Test that CVAPBlockGroupNode loads Alabama block group data."""
        from election.pipeline.sources import CVAPBlockGroupNode
        
        node = CVAPBlockGroupNode(data_dir='.')
        df = node.process({})
        
        assert len(df) > 0, "Should load Alabama block group records"
        assert 'block_group_fips' in df.columns
        assert 'lntitle' in df.columns
        assert 'cvap_est' in df.columns
        # All records should be for Alabama (state FIPS 01)
        assert df['state_fips'].eq('01').all()

    def test_alabama_block_groups_node_loads_shapefile(self):
        """Test that AlabamaBlockGroupsNode loads the shapefile."""
        from election.pipeline.sources import AlabamaBlockGroupsNode
        
        node = AlabamaBlockGroupsNode(data_dir='.')
        gdf = node.process({})
        
        assert len(gdf) > 0, "Should load block group geometries"
        assert 'geometry' in gdf.columns
        assert 'block_group_fips' in gdf.columns
        assert 'county_fips' in gdf.columns

    def test_alabama_voting_districts_node_loads_shapefile(self):
        """Test that AlabamaVotingDistrictsNode loads the shapefile."""
        from election.pipeline.sources import AlabamaVotingDistrictsNode
        
        node = AlabamaVotingDistrictsNode(data_dir='.')
        gdf = node.process({})
        
        assert len(gdf) > 0, "Should load voting district geometries"
        assert 'geometry' in gdf.columns
        assert 'precinct_id' in gdf.columns
        assert 'precinct_name' in gdf.columns

    def test_precinct_demographic_regression_output_structure(self):
        """Test that the regression output has the expected structure."""
        output_path = Path('./output/Precinct_Demographic_Regression.csv')
        
        if not output_path.exists():
            pytest.skip("Regression output not yet generated")
        
        df = pd.read_csv(output_path)
        
        required_columns = ['party', 'demographic', 'coef_a', 'coef_b', 'coef_c', 'r_squared', 'n_precincts']
        for col in required_columns:
            assert col in df.columns, f"Missing column: {col}"
        
        # Should have 8 rows (2 parties × 4 demographics)
        assert len(df) == 8
        
        # Check parties
        assert set(df['party'].unique()) == {'Democratic', 'Republican'}
        
        # Check demographics
        expected_demos = {'White', 'Hispanic', 'African American', 'Asian'}
        assert set(df['demographic'].unique()) == expected_demos

    def test_alabama_precinct_results_output_structure(self):
        """Test that the precinct results output has the expected structure."""
        output_path = Path('./output/Alabama_Precinct_Results.csv')
        
        if not output_path.exists():
            pytest.skip("Precinct results output not yet generated")
        
        df = pd.read_csv(output_path)
        
        required_columns = [
            'county', 'county_fips', 'precinct_name',
            'dem_votes', 'rep_votes', 'total_votes',
            'dem_percent', 'rep_percent',
            'white_pct', 'hispanic_pct', 'african_american_pct', 'asian_pct'
        ]
        for col in required_columns:
            assert col in df.columns, f"Missing column: {col}"
        
        # Should have data for all 67 Alabama counties
        assert df['county'].nunique() == 67
        
        # Vote percentages should be between 0 and 100
        assert (df['dem_percent'] >= 0).all() and (df['dem_percent'] <= 100).all()
        assert (df['rep_percent'] >= 0).all() and (df['rep_percent'] <= 100).all()


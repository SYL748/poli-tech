package com.magic.MagicServer.facts.precinct.repository;

import com.magic.MagicServer.facts.precinct.entity.GinglesChartPrecinctEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for gingles_chart_precinct_data table (GUI-27)
 * Provides data access methods for Gingles chart visualization
 */
@Repository
public interface GinglesChartPrecinctRepository extends JpaRepository<GinglesChartPrecinctEntity, Long> {

	/**
	 * Find all Gingles chart data for precincts in a specific state with county name
	 * Performs JOIN with county table to fetch county name via county_fips
	 * Used for GUI-27 to display racially polarized voting patterns
	 * Note: Frontend is built for Texas (state_id=48) only
	 * 
	 * Returns Object[] with the following structure:
	 * [0] county.name (String)
	 * [1] g.countyFips (Integer)
	 * [2] g.precinctName (String)
	 * [3] g.precinctId (String)
	 * [4] g.totalVotes (Integer)
	 * [5] g.whitePct (Double)
	 * [6] g.hispanicPct (Double)
	 * [7] g.africanAmericanPct (Double)
	 * [8] g.party (String)
	 * [9] g.votePercent (Double)
	 * [10] g.votes (Integer)
	 * 
	 * @param stateId The state identifier (should be Texas, state_id=48)
	 * @return List of Object arrays with gingles data and county name
	 */
	@Query(value = "SELECT c.name, g.county_fips, g.precinct_name, g.precinct_id, g.total_votes, " +
			"g.white_pct, g.hispanic_pct, g.african_american_pct, g.party, g.vote_percent, g.votes " +
			"FROM gingles_chart_precinct_data g " +
			"JOIN county c ON g.county_fips = c.geo_id " +
			"WHERE g.state_id = :stateId", 
			nativeQuery = true)
	List<Object[]> findByStateIdWithCountyName(@Param("stateId") Integer stateId);
}


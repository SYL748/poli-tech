package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.VoterRegistrationSummaryByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for voter registration summary data by region
 * Handles queries for voter registration with party breakdown and completeness metrics
 */
public interface VoterRegistrationSummaryByRegionRepository extends JpaRepository<VoterRegistrationSummaryByRegionEntity, Integer> {

	/**
	 * Find voter registration summary for a specific region
	 * @param regionId The EAVS region identifier
	 * @return Optional containing voter registration summary if found
	 */
	Optional<VoterRegistrationSummaryByRegionEntity> findByRegionId(Integer regionId);

	/**
	 * Find voter registration summaries for all regions in a state
	 * Joins with eavs_region table to filter by state
	 * 
	 * @param stateId The state identifier
	 * @return List of voter registration summaries for all regions in the state
	 */
	@Query("SELECT v FROM VoterRegistrationSummaryByRegionEntity v " +
			"WHERE v.regionId IN (SELECT e.regionId FROM EavsRegionEntity e WHERE e.stateId = :stateId)")
	List<VoterRegistrationSummaryByRegionEntity> findByStateId(@Param("stateId") Integer stateId);
}


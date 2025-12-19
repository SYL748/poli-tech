package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.ActiveVotersByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for active voters data by region
 * Handles queries for GUI-7 and GUI-8: active voters table by EAVS geographic units
 */
public interface ActiveVotersByRegionRepository extends JpaRepository<ActiveVotersByRegionEntity, String> {

	/**
	 * Find all active voters data for regions in a specific state
	 * Joins with eavs_region to filter by state_id
	 * 
	 * @param stateId The state identifier
	 * @return List of active voters entities for all regions in the state
	 */
	@Query("SELECT a FROM ActiveVotersByRegionEntity a WHERE a.regionId IN " +
			"(SELECT r.regionId FROM EavsRegionEntity r WHERE r.stateId = :stateId)")
	List<ActiveVotersByRegionEntity> findByStateId(@Param("stateId") Integer stateId);

	/**
	 * Find active voters data by region ID
	 * @param regionId The region identifier
	 * @return Optional containing ActiveVotersByRegionEntity if found
	 */
	Optional<ActiveVotersByRegionEntity> findByRegionId(Integer regionId);
}


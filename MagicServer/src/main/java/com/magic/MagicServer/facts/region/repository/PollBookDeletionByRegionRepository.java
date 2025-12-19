package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.PollBookDeletionByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for poll book deletion data by region
 * Handles queries for GUI-8: Poll book deletions table by EAVS geographic units
 */
public interface PollBookDeletionByRegionRepository extends JpaRepository<PollBookDeletionByRegionEntity, String> {

	/**
	 * Find all poll book deletion data for regions in a specific state
	 * Joins with eavs_region to filter by state_id and get region name
	 * 
	 * @param stateId The state identifier
	 * @return List of poll book deletion entities for all regions in the state
	 */
	@Query("SELECT p FROM PollBookDeletionByRegionEntity p WHERE p.regionId IN " +
			"(SELECT r.regionId FROM EavsRegionEntity r WHERE r.stateId = :stateId)")
	List<PollBookDeletionByRegionEntity> findByStateId(@Param("stateId") Integer stateId);

	/**
	 * Find poll book deletion data by region ID
	 * @param regionId The region identifier
	 * @return Optional containing PollBookDeletionByRegionEntity if found
	 */
	Optional<PollBookDeletionByRegionEntity> findByRegionId(Integer regionId);
}


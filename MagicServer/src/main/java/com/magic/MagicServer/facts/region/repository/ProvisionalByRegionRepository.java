package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.ProvisionalByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for provisional ballot data by region
 * Handles queries for GUI-4: provisional ballot table by EAVS geographic units
 */
public interface ProvisionalByRegionRepository extends JpaRepository<ProvisionalByRegionEntity, String> {

	/**
	 * Find all provisional ballot data for regions in a specific state
	 * Joins with eavs_region to filter by state_id and get region name
	 * 
	 * @param stateId The state identifier
	 * @return List of provisional ballot entities for all regions in the state
	 */
	@Query("SELECT p FROM ProvisionalByRegionEntity p WHERE p.regionId IN " +
			"(SELECT r.regionId FROM EavsRegionEntity r WHERE r.stateId = :stateId)")
	List<ProvisionalByRegionEntity> findByStateId(@Param("stateId") Integer stateId);

	/**
	 * Find provisional ballot data by region ID
	 * @param regionId The region identifier
	 * @return Optional containing ProvisionalByRegionEntity if found
	 */
	Optional<ProvisionalByRegionEntity> findByRegionId(Integer regionId);
}


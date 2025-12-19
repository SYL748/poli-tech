package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.DisplayTypeOfVotingEquipmentByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for display_type_of_voting_equipment_by_region table (GUI-10)
 * Provides data access methods for equipment types used by region
 */
@Repository
public interface DisplayTypeOfVotingEquipmentByRegionRepository extends JpaRepository<DisplayTypeOfVotingEquipmentByRegionEntity, Integer> {

	/**
	 * Find equipment types by region ID
	 * @param regionId The region identifier
	 * @return Optional containing DisplayTypeOfVotingEquipmentByRegionEntity if found
	 */
	Optional<DisplayTypeOfVotingEquipmentByRegionEntity> findByRegionId(Integer regionId);

	/**
	 * Find all equipment types for regions in a specific state
	 * Joins with eavs_region to filter by state_id
	 * 
	 * @param stateId The state identifier
	 * @return List of equipment type entities for all regions in the state
	 */
	@Query("SELECT d FROM DisplayTypeOfVotingEquipmentByRegionEntity d WHERE d.regionId IN " +
			"(SELECT r.regionId FROM EavsRegionEntity r WHERE r.stateId = :stateId)")
	List<DisplayTypeOfVotingEquipmentByRegionEntity> findByStateId(@Param("stateId") Integer stateId);
}


package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.EquipmentQualityRejectionByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for equipment_quality_rejection_by_region table (GUI-25)
 * Provides data access methods for equipment quality vs rejected ballots bubble chart
 */
@Repository
public interface EquipmentQualityRejectionByRegionRepository extends JpaRepository<EquipmentQualityRejectionByRegionEntity, Integer> {

	/**
	 * Find all equipment quality and rejection records for regions in a specific state
	 * Uses JOIN with eavs_region table to filter by state_id
	 * 
	 * @param stateId The state identifier
	 * @return List of EquipmentQualityRejectionByRegionEntity for the state
	 */
	@Query("SELECT e FROM EquipmentQualityRejectionByRegionEntity e WHERE e.regionId IN " +
			"(SELECT r.regionId FROM EavsRegionEntity r WHERE r.stateId = :stateId)")
	List<EquipmentQualityRejectionByRegionEntity> findByStateId(@Param("stateId") Integer stateId);
}


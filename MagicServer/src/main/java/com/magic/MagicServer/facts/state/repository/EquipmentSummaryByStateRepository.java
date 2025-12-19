package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.EquipmentSummaryByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for equipment_summary_by_state table
 * Provides queries for equipment inventory and metrics at the state level
 * Denormalized table - all equipment details included directly
 * Matches: 6_Equipment_Summary_by_State_with_short_description.csv
 */
@Repository
public interface EquipmentSummaryByStateRepository extends JpaRepository<EquipmentSummaryByStateEntity, Long> {

	/**
	 * Find all equipment summary records for a specific state (GUI-6)
	 * Returns complete equipment data ordered by manufacturer and model
	 * No JOIN needed - all data is in the denormalized table
	 * 
	 * @param stateId The state identifier
	 * @return List of EquipmentSummaryByStateEntity ordered by manufacturer and model
	 */
	List<EquipmentSummaryByStateEntity> findByStateIdOrderByManufacturerAscModelNameAsc(Integer stateId);
}


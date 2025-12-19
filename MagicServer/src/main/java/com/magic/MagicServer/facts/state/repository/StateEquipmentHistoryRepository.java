package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.StateEquipmentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for state_equipment_history table
 * Provides queries for equipment history data by state and year
 */
@Repository
public interface StateEquipmentHistoryRepository extends JpaRepository<StateEquipmentHistoryEntity, StateEquipmentHistoryEntity.StateEquipmentHistoryId> {

	/**
	 * Find all equipment history records for a specific state across all years
	 * Returns rows ordered by year (2016, 2018, 2020, 2022, 2024)
	 * Used for GUI-14: Display voting equipment history for a state
	 * 
	 * @param stateId The state identifier
	 * @return List of StateEquipmentHistoryEntity ordered by year
	 */
	List<StateEquipmentHistoryEntity> findByStateIdOrderByYear(Integer stateId);
}


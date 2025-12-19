package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.StateEquipmentTotalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for state equipment totals by category
 * Handles queries for GUI-12: Equipment counts table on splash page
 */
public interface StateEquipmentTotalRepository extends JpaRepository<StateEquipmentTotalEntity, Integer> {

	/**
	 * Find all states with their equipment totals by category
	 * Used for GUI-12 to display equipment table on splash page
	 * 
	 * @return List of all states with equipment counts
	 */
	List<StateEquipmentTotalEntity> findAll();
}


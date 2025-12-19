package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.AverageEquipmentAgeByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for average equipment age by state
 * Handles queries for GUI-11: Equipment age choropleth map on splash page
 */
public interface AverageEquipmentAgeByStateRepository extends JpaRepository<AverageEquipmentAgeByStateEntity, Integer> {

	/**
	 * Find all states with their average equipment age
	 * Used for GUI-11 to display choropleth map on splash page
	 * 
	 * @return List of all states with average equipment age
	 */
	List<AverageEquipmentAgeByStateEntity> findAll();
}


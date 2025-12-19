package com.magic.MagicServer.jurisdiction.repository;

import com.magic.MagicServer.jurisdiction.entity.CountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for County entity
 * Handles queries for county dimensional data
 */
public interface CountyRepository extends JpaRepository<CountyEntity, Integer> {

	/**
	 * Find all counties belonging to a specific state
	 * @param stateId The state identifier
	 * @return List of counties in the state
	 */
	List<CountyEntity> findByStateId(Integer stateId);
}


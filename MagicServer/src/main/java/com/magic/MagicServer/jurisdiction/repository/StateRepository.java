package com.magic.MagicServer.jurisdiction.repository;

import com.magic.MagicServer.jurisdiction.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for State entity
 * Handles queries for state dimensional data
 */
public interface StateRepository extends JpaRepository<StateEntity, Integer> {

	/**
	 * Find a state by its state ID
	 * @param stateId The state identifier
	 * @return Optional containing the state if found
	 */
	Optional<StateEntity> findByStateId(Integer stateId);
}


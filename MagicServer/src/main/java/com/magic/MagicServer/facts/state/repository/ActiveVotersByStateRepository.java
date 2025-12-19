package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.ActiveVotersByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for active voters data by state
 * Handles queries for GUI-7: Active and inactive voter counts for bar chart display
 */
public interface ActiveVotersByStateRepository extends JpaRepository<ActiveVotersByStateEntity, Integer> {

	/**
	 * Find active voters data for a specific state
	 * @param stateId The state identifier
	 * @return Optional containing active voters data if found
	 */
	Optional<ActiveVotersByStateEntity> findByStateId(Integer stateId);
}


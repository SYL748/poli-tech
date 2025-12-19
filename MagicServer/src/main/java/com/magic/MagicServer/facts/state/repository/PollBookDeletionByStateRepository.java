package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.PollBookDeletionByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for poll book deletion data by state
 * Handles queries for GUI-8: Poll book deletions for bar chart display
 */
public interface PollBookDeletionByStateRepository extends JpaRepository<PollBookDeletionByStateEntity, Integer> {

	/**
	 * Find poll book deletion data for a specific state
	 * @param stateId The state identifier
	 * @return Optional containing poll book deletion data if found
	 */
	Optional<PollBookDeletionByStateEntity> findByStateId(Integer stateId);
}


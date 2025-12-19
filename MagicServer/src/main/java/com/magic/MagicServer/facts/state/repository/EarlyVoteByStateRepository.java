package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.EarlyVoteByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for early_vote_by_state table (GUI-23)
 * Provides data access methods for early voting data
 */
@Repository
public interface EarlyVoteByStateRepository extends JpaRepository<EarlyVoteByStateEntity, Integer> {

	/**
	 * Find early voting data by state ID
	 * @param stateId The state identifier
	 * @return Optional containing EarlyVoteByStateEntity if found
	 */
	Optional<EarlyVoteByStateEntity> findByStateId(Integer stateId);
}


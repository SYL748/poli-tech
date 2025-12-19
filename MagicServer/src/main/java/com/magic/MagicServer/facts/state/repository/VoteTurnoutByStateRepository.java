package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.VoteTurnoutByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for vote_turnout_by_state table
 * Provides data access methods for voter turnout data
 */
@Repository
public interface VoteTurnoutByStateRepository extends JpaRepository<VoteTurnoutByStateEntity, Integer> {

	/**
	 * Find voter turnout data by state ID
	 * @param stateId The state identifier
	 * @return Optional containing VoteTurnoutByStateEntity if found
	 */
	Optional<VoteTurnoutByStateEntity> findByStateId(Integer stateId);
}


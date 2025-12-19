package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.MailBallotRejectionByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for mail ballot rejection data by state
 * Handles queries for GUI-9: Mail ballot rejections for bar chart display
 */
public interface MailBallotRejectionByStateRepository extends JpaRepository<MailBallotRejectionByStateEntity, Integer> {

	/**
	 * Find mail ballot rejection data for a specific state
	 * @param stateId The state identifier
	 * @return Optional containing mail ballot rejection data if found
	 */
	Optional<MailBallotRejectionByStateEntity> findByStateId(Integer stateId);
}


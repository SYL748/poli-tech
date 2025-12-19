package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.RejectedBallotEiCurvePointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for rejected ballot ecological inference curve point data
 * Handles queries for GUI-29: Rejected Ballot EI KDE density visualization
 */
public interface RejectedBallotEiCurvePointRepository extends JpaRepository<RejectedBallotEiCurvePointEntity, Long> {

	/**
	 * Find all EI curve data points for a specific state
	 * Returns all demographic groups and their associated curve data
	 * Used to generate the complete EI KDE density visualization
	 * 
	 * @param stateId The state identifier
	 * @return List of all curve point entities for the state
	 */
	List<RejectedBallotEiCurvePointEntity> findByStateId(Integer stateId);

	/**
	 * Find EI curve data points for a specific state and demographic group
	 * Useful for filtering visualization by specific demographic
	 * 
	 * @param stateId The state identifier
	 * @param demographic The demographic group name (e.g., "White", "Hispanic", "African American")
	 * @return List of curve point entities for the state and demographic
	 */
	List<RejectedBallotEiCurvePointEntity> findByStateIdAndDemographic(Integer stateId, String demographic);
}


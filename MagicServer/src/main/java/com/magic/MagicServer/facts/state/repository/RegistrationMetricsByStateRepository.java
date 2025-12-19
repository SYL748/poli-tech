package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.dto.RegistrationMetricsByStateDto;
import com.magic.MagicServer.facts.state.entity.RegistrationMetricsByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for registration and turnout metrics by state
 * Handles queries for voter registration rates, CVAP, and turnout statistics
 */
public interface RegistrationMetricsByStateRepository extends JpaRepository<RegistrationMetricsByStateEntity, Integer> {

	/**
	 * Find registration metrics for a specific state
	 * @param stateId The state identifier
	 * @return Optional containing registration metrics if found
	 */
	Optional<RegistrationMetricsByStateEntity> findByStateId(Integer stateId);

	/**
	 * Find complete registration metrics with active voter data for a specific state
	 * Joins registration_metrics_by_state with active_voters_by_state
	 * @param stateId The state identifier
	 * @return Optional containing complete registration metrics DTO
	 */
	@Query("SELECT new com.magic.MagicServer.facts.state.dto.RegistrationMetricsByStateDto(" +
	       "r.stateId, r.totalVotesCast, r.totalCvap, r.registrationRate, r.turnoutRate, " +
	       "a.totalRegisteredVoters, a.activeRegisteredVoters) " +
	       "FROM RegistrationMetricsByStateEntity r " +
	       "JOIN ActiveVotersByStateEntity a ON r.stateId = a.stateId " +
	       "WHERE r.stateId = :stateId")
	Optional<RegistrationMetricsByStateDto> findCompleteMetricsByStateId(@Param("stateId") Integer stateId);
}


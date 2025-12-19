package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.PrecinctDemographicRegressionCoefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for precinct_demographic_regression_coef table
 * Used for GUI-27: Precinct demographic regression analysis
 */
@Repository
public interface PrecinctDemographicRegressionCoefRepository extends JpaRepository<PrecinctDemographicRegressionCoefEntity, Long> {

	/**
	 * Find all regression coefficients for a given state
	 *
	 * @param stateId The state identifier
	 * @return List of all regression coefficients for the state
	 */
	List<PrecinctDemographicRegressionCoefEntity> findByStateId(Integer stateId);
}


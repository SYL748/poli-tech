package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.EavsDataQualityByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for EAVS data quality score by state
 * Handles queries for EAVS Missing Data Metric
 */
public interface EavsDataQualityByStateRepository extends JpaRepository<EavsDataQualityByStateEntity, Integer> {

	/**
	 * Find data quality score by state ID
	 * Used to retrieve EAVS data quality metric for a specific state
	 * 
	 * @param stateId The state identifier
	 * @return Optional containing the data quality entity if found
	 */
	Optional<EavsDataQualityByStateEntity> findByStateId(Integer stateId);
}


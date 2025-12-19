package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.ProvisionalByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for provisional ballot data by state
 * Handles queries for GUI-3: Provisional ballot categories for bar chart display
 */
@Repository
public interface ProvisionalByStateRepository extends JpaRepository<ProvisionalByStateEntity, Integer> {

	/**
	 * Find provisional ballot data for a specific state
	 * @param stateId The state identifier
	 * @return Optional containing provisional data if found
	 */
	Optional<ProvisionalByStateEntity> findByStateId(Integer stateId);
}


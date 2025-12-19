package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.PoliticalPartyCvapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for political party CVAP data by state
 * Handles queries for GUI-2: Political party CVAP percentages for Florida and Illinois
 */
public interface PoliticalPartyCvapRepository extends JpaRepository<PoliticalPartyCvapEntity, PoliticalPartyCvapEntity.PoliticalPartyCvapId> {

	/**
	 * Find all political party CVAP percentages for a specific state
	 * @param stateId The state identifier
	 * @return List of political party CVAP entities
	 */
	List<PoliticalPartyCvapEntity> findByStateId(Integer stateId);
}


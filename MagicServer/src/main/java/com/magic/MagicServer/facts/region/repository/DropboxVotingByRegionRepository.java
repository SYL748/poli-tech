package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.DropboxVotingByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for dropbox_voting_by_region table (GUI-24)
 * Provides data access methods for drop box voting bubble chart visualization
 */
@Repository
public interface DropboxVotingByRegionRepository extends JpaRepository<DropboxVotingByRegionEntity, String> {

	/**
	 * Find all dropbox voting records for regions in a specific state
	 * Uses JOIN with eavs_region table to filter by state_id
	 * 
	 * @param stateId The state identifier
	 * @return List of DropboxVotingByRegionEntity for the state
	 */
	@Query("SELECT d FROM DropboxVotingByRegionEntity d WHERE d.regionId IN " +
			"(SELECT e.regionId FROM EavsRegionEntity e WHERE e.stateId = :stateId)")
	List<DropboxVotingByRegionEntity> findByStateId(@Param("stateId") Integer stateId);
}


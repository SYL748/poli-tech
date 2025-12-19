package com.magic.MagicServer.facts.region.repository;

import com.magic.MagicServer.facts.region.entity.MailBallotRejectionByRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for mail ballot rejection data by region
 * Handles queries for GUI-9: Mail ballot rejections table by EAVS geographic units
 */
public interface MailBallotRejectionByRegionRepository extends JpaRepository<MailBallotRejectionByRegionEntity, String> {

	/**
	 * Find all mail ballot rejection data for regions in a specific state
	 * Joins with eavs_region to filter by state_id and get region name
	 * 
	 * @param stateId The state identifier
	 * @return List of mail ballot rejection entities for all regions in the state
	 */
	@Query("SELECT m FROM MailBallotRejectionByRegionEntity m WHERE m.regionId IN " +
			"(SELECT r.regionId FROM EavsRegionEntity r WHERE r.stateId = :stateId)")
	List<MailBallotRejectionByRegionEntity> findByStateId(@Param("stateId") Integer stateId);

	/**
	 * Find mail ballot rejection data by region ID
	 * @param regionId The region identifier
	 * @return Optional containing MailBallotRejectionByRegionEntity if found
	 */
	Optional<MailBallotRejectionByRegionEntity> findByRegionId(Integer regionId);
}


package com.magic.MagicServer.voter.repository;

import com.magic.MagicServer.voter.entity.VoterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for voter list data
 * Handles paginated queries for individual voter records by region
 */
public interface VoterRepository extends JpaRepository<VoterEntity, Long> {

	/**
	 * Find voters by region ID with pagination
	 * Automatically handles:
	 * - No duplicate records across pages (consistent ordering by id)
	 * - Correct offset calculation
	 * - Total pages and elements counting
	 * 
	 * @param regionId The EAVS region identifier
	 * @param pageable Pagination parameters (page number, size, sort)
	 * @return Page of VoterEntity with pagination metadata
	 */
	Page<VoterEntity> findByRegionId(Integer regionId, Pageable pageable);

	/**
	 * Find voters by region ID and party affiliation with pagination
	 * Filters voters by both region and political party
	 * 
	 * @param regionId The EAVS region identifier
	 * @param party The political party (e.g., "Republican", "Democrat", "Other")
	 * @param pageable Pagination parameters (page number, size, sort)
	 * @return Page of VoterEntity with pagination metadata
	 */
	Page<VoterEntity> findByRegionIdAndParty(Integer regionId, String party, Pageable pageable);

	/**
	 * Count total voters in a region
	 * @param regionId The EAVS region identifier
	 * @return Total number of voters in the region
	 */
	Long countByRegionId(Integer regionId);

	/**
	 * Count total voters in a region by party affiliation
	 * @param regionId The EAVS region identifier
	 * @param party The political party
	 * @return Total number of voters in the region for that party
	 */
	Long countByRegionIdAndParty(Integer regionId, String party);
}


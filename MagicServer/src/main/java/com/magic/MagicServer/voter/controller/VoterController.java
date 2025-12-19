package com.magic.MagicServer.voter.controller;

import com.magic.MagicServer.voter.dto.VoterPageDto;
import com.magic.MagicServer.voter.service.VoterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for voter list data
 * Provides paginated endpoints for querying individual voter records by region
 */
@RestController
@RequestMapping("/api/voters")
public class VoterController {

	private final VoterService voterService;

	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}

	/**
	 * Get paginated voters for a region by region name with optional party filter
	 * 
	 * Endpoint: GET /api/voters/regions/by-name/{regionName}?page=0&party=Republican
	 * 
	 * Returns 7 voters per page for the specified region (county).
	 * Each page contains unique voters that haven't been seen in previous pages.
	 * Voters are sorted by ID to ensure consistent pagination.
	 * 
	 * Optional party filter:
	 * - party=Republican: Returns only Republican voters
	 * - party=Democrat: Returns only Democrat voters
	 * - party=Other: Returns only voters from other parties
	 * - No party parameter: Returns all voters regardless of party
	 * 
	 * Example:
	 * - Page 0: Voters 1-7
	 * - Page 1: Voters 8-14
	 * - Page 2: Voters 15-21
	 * 
	 * Response includes:
	 * - List of 7 voters (or fewer on last page)
	 * - Current page number (0-based)
	 * - Total pages available
	 * - Total number of voters in the region (filtered by party if applicable)
	 * - hasNext / hasPrevious flags for navigation
	 * 
	 * @param regionName The region (county) name (e.g., "Miami-Dade County")
	 * @param page The page number (0-based, defaults to 0)
	 * @param party Optional party filter (Republican, Democrat, Other, or null for all)
	 * @return VoterPageDto with paginated voters, 404 if region not found
	 */
	@GetMapping("/regions/by-name/{regionName}")
	public ResponseEntity<VoterPageDto> getVotersByRegionName(
			@PathVariable String regionName,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false) String party) {
		
		return voterService.getVotersByRegionName(regionName, page, party)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Get paginated voters for a region by region ID with optional party filter
	 * 
	 * Endpoint: GET /api/voters/regions/{regionId}?page=0&party=Republican
	 * 
	 * Returns 7 voters per page for the specified region.
	 * Each page contains unique voters that haven't been seen in previous pages.
	 * Voters are sorted by ID to ensure consistent pagination.
	 * 
	 * This endpoint is useful when you already know the region_id from other queries
	 * (e.g., from the eavs_region table or other fact tables).
	 * 
	 * Optional party filter:
	 * - party=Republican: Returns only Republican voters
	 * - party=Democrat: Returns only Democrat voters
	 * - party=Other: Returns only voters from other parties
	 * - No party parameter: Returns all voters regardless of party
	 * 
	 * Response includes:
	 * - List of 7 voters (or fewer on last page)
	 * - Current page number (0-based)
	 * - Total pages available
	 * - Total number of voters in the region (filtered by party if applicable)
	 * - hasNext / hasPrevious flags for navigation
	 * 
	 * @param regionId The EAVS region identifier
	 * @param page The page number (0-based, defaults to 0)
	 * @param party Optional party filter (Republican, Democrat, Other, or null for all)
	 * @return VoterPageDto with paginated voters, 404 if region not found
	 */
	@GetMapping("/regions/{regionId}")
	public ResponseEntity<VoterPageDto> getVotersByRegionId(
			@PathVariable Integer regionId,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false) String party) {
		
		return voterService.getVotersByRegionId(regionId, page, party)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}


package com.magic.MagicServer.voter.service;

import com.magic.MagicServer.jurisdiction.entity.EavsRegionEntity;
import com.magic.MagicServer.jurisdiction.repository.EavsRegionRepository;
import com.magic.MagicServer.voter.dto.VoterDto;
import com.magic.MagicServer.voter.dto.VoterPageDto;
import com.magic.MagicServer.voter.entity.VoterEntity;
import com.magic.MagicServer.voter.repository.VoterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for voter list data
 * Handles business logic for paginated voter queries by region
 */
@Service
public class VoterService {

	private static final int PAGE_SIZE = 7;

	private final VoterRepository voterRepository;
	private final EavsRegionRepository eavsRegionRepository;

	public VoterService(VoterRepository voterRepository, EavsRegionRepository eavsRegionRepository) {
		this.voterRepository = voterRepository;
		this.eavsRegionRepository = eavsRegionRepository;
	}

	/**
	 * Get paginated voters for a region by region name with optional party filter
	 * 
	 * @param regionName The region (county) name
	 * @param page The page number (0-based)
	 * @param party Optional party filter ("Republican", "Democrat", "Other", or null for all)
	 * @return Optional containing VoterPageDto if region found, empty otherwise
	 */
	public Optional<VoterPageDto> getVotersByRegionName(String regionName, Integer page, String party) {
		// Step 1: Find region_id from eavs_region table by name
		Optional<EavsRegionEntity> regionOpt = eavsRegionRepository.findByName(regionName);
		
		if (regionOpt.isEmpty()) {
			return Optional.empty();
		}
		
		Integer regionId = regionOpt.get().getRegionId();
		String resolvedRegionName = regionOpt.get().getName();
		
		// Step 2: Query voter_list with pagination (7 records per page, sorted by id for consistency)
		Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("id").ascending());
		Page<VoterEntity> voterPage;
		
		// Apply party filter if provided
		if (party != null && !party.trim().isEmpty()) {
			voterPage = voterRepository.findByRegionIdAndParty(regionId, party, pageable);
		} else {
			voterPage = voterRepository.findByRegionId(regionId, pageable);
		}
		
		// Step 3: Convert entities to DTOs
		List<VoterDto> voterDtos = voterPage.getContent().stream()
				.map(entity -> toVoterDto(entity, resolvedRegionName))
				.collect(Collectors.toList());
		
		// Step 4: Build paginated response
		VoterPageDto pageDto = new VoterPageDto();
		pageDto.setVoters(voterDtos);
		pageDto.setCurrentPage(voterPage.getNumber());
		pageDto.setTotalPages(voterPage.getTotalPages());
		pageDto.setTotalElements(voterPage.getTotalElements());
		pageDto.setHasNext(voterPage.hasNext());
		pageDto.setHasPrevious(voterPage.hasPrevious());
		pageDto.setPageSize(PAGE_SIZE);
		
		return Optional.of(pageDto);
	}

	/**
	 * Get paginated voters for a region by region ID with optional party filter
	 * 
	 * @param regionId The region identifier
	 * @param page The page number (0-based)
	 * @param party Optional party filter ("Republican", "Democrat", "Other", or null for all)
	 * @return Optional containing VoterPageDto if region found, empty otherwise
	 */
	public Optional<VoterPageDto> getVotersByRegionId(Integer regionId, Integer page, String party) {
		// Verify region exists
		Optional<EavsRegionEntity> regionOpt = eavsRegionRepository.findByRegionId(regionId);
		
		if (regionOpt.isEmpty()) {
			return Optional.empty();
		}
		
		String regionName = regionOpt.get().getName();
		
		// Query voter_list with pagination (7 records per page, sorted by id for consistency)
		Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("id").ascending());
		Page<VoterEntity> voterPage;
		
		// Apply party filter if provided
		if (party != null && !party.trim().isEmpty()) {
			voterPage = voterRepository.findByRegionIdAndParty(regionId, party, pageable);
		} else {
			voterPage = voterRepository.findByRegionId(regionId, pageable);
		}
		
		// Convert entities to DTOs
		List<VoterDto> voterDtos = voterPage.getContent().stream()
				.map(entity -> toVoterDto(entity, regionName))
				.collect(Collectors.toList());
		
		// Build paginated response
		VoterPageDto pageDto = new VoterPageDto();
		pageDto.setVoters(voterDtos);
		pageDto.setCurrentPage(voterPage.getNumber());
		pageDto.setTotalPages(voterPage.getTotalPages());
		pageDto.setTotalElements(voterPage.getTotalElements());
		pageDto.setHasNext(voterPage.hasNext());
		pageDto.setHasPrevious(voterPage.hasPrevious());
		pageDto.setPageSize(PAGE_SIZE);
		
		return Optional.of(pageDto);
	}

	/**
	 * Convert VoterEntity to VoterDto
	 * @param entity The voter entity
	 * @param regionName The region name (from eavs_region)
	 * @return VoterDto
	 */
	private VoterDto toVoterDto(VoterEntity entity, String regionName) {
		VoterDto dto = new VoterDto();
		dto.setId(entity.getId());
		dto.setRegionId(entity.getRegionId());
		dto.setRegionName(regionName);
		dto.setNameFull(entity.getNameFull());
		dto.setParty(entity.getParty());
		return dto;
	}
}


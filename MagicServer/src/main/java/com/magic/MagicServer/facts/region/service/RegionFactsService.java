package com.magic.MagicServer.facts.region.service;

import com.magic.MagicServer.facts.region.dto.ActiveVotersByRegionDto;
import com.magic.MagicServer.facts.region.dto.CountyChoroplethInfoDto;
import com.magic.MagicServer.facts.region.dto.DisplayTypeOfVotingEquipmentByRegionDto;
import com.magic.MagicServer.facts.region.dto.DropboxVotingByRegionDto;
import com.magic.MagicServer.facts.region.dto.EquipmentQualityRejectionByRegionDto;
import com.magic.MagicServer.facts.region.dto.MailBallotRejectionByRegionDto;
import com.magic.MagicServer.facts.region.dto.PollBookDeletionByRegionDto;
import com.magic.MagicServer.facts.region.dto.ProvisionalByRegionDto;
import com.magic.MagicServer.facts.region.dto.VoterRegistrationSummaryByRegionDto;
import com.magic.MagicServer.facts.region.entity.ActiveVotersByRegionEntity;
import com.magic.MagicServer.facts.region.entity.DisplayTypeOfVotingEquipmentByRegionEntity;
import com.magic.MagicServer.facts.region.entity.DropboxVotingByRegionEntity;
import com.magic.MagicServer.facts.region.entity.EquipmentQualityRejectionByRegionEntity;
import com.magic.MagicServer.facts.region.entity.MailBallotRejectionByRegionEntity;
import com.magic.MagicServer.facts.region.entity.PollBookDeletionByRegionEntity;
import com.magic.MagicServer.facts.region.entity.ProvisionalByRegionEntity;
import com.magic.MagicServer.facts.region.entity.VoterRegistrationSummaryByRegionEntity;
import com.magic.MagicServer.facts.region.repository.ActiveVotersByRegionRepository;
import com.magic.MagicServer.facts.region.repository.DisplayTypeOfVotingEquipmentByRegionRepository;
import com.magic.MagicServer.facts.region.repository.DropboxVotingByRegionRepository;
import com.magic.MagicServer.facts.region.repository.EquipmentQualityRejectionByRegionRepository;
import com.magic.MagicServer.facts.region.repository.MailBallotRejectionByRegionRepository;
import com.magic.MagicServer.facts.region.repository.PollBookDeletionByRegionRepository;
import com.magic.MagicServer.facts.region.repository.ProvisionalByRegionRepository;
import com.magic.MagicServer.facts.region.repository.VoterRegistrationSummaryByRegionRepository;
import com.magic.MagicServer.jurisdiction.entity.EavsRegionEntity;
import com.magic.MagicServer.jurisdiction.repository.EavsRegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for region-level facts data
 * Handles business logic for region metrics and analytics
 */
@Service
public class RegionFactsService {

	private final ProvisionalByRegionRepository provisionalByRegionRepository;
	private final ActiveVotersByRegionRepository activeVotersByRegionRepository;
	private final PollBookDeletionByRegionRepository pollBookDeletionByRegionRepository;
	private final MailBallotRejectionByRegionRepository mailBallotRejectionByRegionRepository;
	private final DropboxVotingByRegionRepository dropboxVotingByRegionRepository;
	private final EquipmentQualityRejectionByRegionRepository equipmentQualityRejectionByRegionRepository;
	private final DisplayTypeOfVotingEquipmentByRegionRepository displayTypeOfVotingEquipmentByRegionRepository;
	private final VoterRegistrationSummaryByRegionRepository voterRegistrationSummaryByRegionRepository;
	private final EavsRegionRepository eavsRegionRepository;

	public RegionFactsService(
			ProvisionalByRegionRepository provisionalByRegionRepository,
			ActiveVotersByRegionRepository activeVotersByRegionRepository,
			PollBookDeletionByRegionRepository pollBookDeletionByRegionRepository,
			MailBallotRejectionByRegionRepository mailBallotRejectionByRegionRepository,
			DropboxVotingByRegionRepository dropboxVotingByRegionRepository,
			EquipmentQualityRejectionByRegionRepository equipmentQualityRejectionByRegionRepository,
			DisplayTypeOfVotingEquipmentByRegionRepository displayTypeOfVotingEquipmentByRegionRepository,
			VoterRegistrationSummaryByRegionRepository voterRegistrationSummaryByRegionRepository,
			EavsRegionRepository eavsRegionRepository
	) {
		this.provisionalByRegionRepository = provisionalByRegionRepository;
		this.activeVotersByRegionRepository = activeVotersByRegionRepository;
		this.pollBookDeletionByRegionRepository = pollBookDeletionByRegionRepository;
		this.mailBallotRejectionByRegionRepository = mailBallotRejectionByRegionRepository;
		this.dropboxVotingByRegionRepository = dropboxVotingByRegionRepository;
		this.equipmentQualityRejectionByRegionRepository = equipmentQualityRejectionByRegionRepository;
		this.displayTypeOfVotingEquipmentByRegionRepository = displayTypeOfVotingEquipmentByRegionRepository;
		this.voterRegistrationSummaryByRegionRepository = voterRegistrationSummaryByRegionRepository;
		this.eavsRegionRepository = eavsRegionRepository;
	}

	/**
	 * Get provisional ballot categories for all regions in a state (GUI-3 GUI-4: table data)
	 * @param stateId The state identifier
	 * @return List of ProvisionalByRegionDto with region names and category counts
	 */
	public List<ProvisionalByRegionDto> getProvisionalByRegion(Integer stateId) {
		return provisionalByRegionRepository.findByStateId(stateId).stream()
				.map(this::toProvisionalByRegionDto)
				.collect(Collectors.toList());
	}

		/**
	 * Map ProvisionalByRegionEntity to ProvisionalByRegionDto
	 * Fetches region name from eavs_region table
	 */
	private ProvisionalByRegionDto toProvisionalByRegionDto(ProvisionalByRegionEntity entity) {
		ProvisionalByRegionDto dto = new ProvisionalByRegionDto();

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		dto.setVoterNotOnList(entity.getVoterNotOnList());
		dto.setVoterLackedId(entity.getVoterLackedId());
		dto.setElectionOfficialChallengedEligibility(entity.getElectionOfficialChallengedEligibility());
		dto.setAnotherPersonChallengedEligibility(entity.getAnotherPersonChallengedEligibility());
		dto.setVoterNotResident(entity.getVoterNotResident());
		dto.setVoterRegistrationNotUpdated(entity.getVoterRegistrationNotUpdated());
		dto.setVoterDidNotSurrenderMailBallot(entity.getVoterDidNotSurrenderMailBallot());
		dto.setJudgeExtendedVotingHours(entity.getJudgeExtendedVotingHours());
		dto.setVoterUsedSdr(entity.getVoterUsedSdr());
		dto.setOtherReason(entity.getOtherReason());
		dto.setTotalProvisional(entity.getTotalProvisional());

		return dto;
	}

	/**
	 * Get active voters data for all regions in a state (GUI-7 and GUI-8: table data)
	 * @param stateId The state identifier
	 * @return List of ActiveVotersByRegionDto with region names and voter counts
	 */
	public List<ActiveVotersByRegionDto> getActiveVotersByRegion(Integer stateId) {
		return activeVotersByRegionRepository.findByStateId(stateId).stream()
				.map(this::toActiveVotersByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map ActiveVotersByRegionEntity to ActiveVotersByRegionDto (GUI-7 and GUI-8)
	 * Fetches region name from eavs_region table
	 */
	private ActiveVotersByRegionDto toActiveVotersByRegionDto(ActiveVotersByRegionEntity entity) {
		ActiveVotersByRegionDto dto = new ActiveVotersByRegionDto();

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		dto.setTotalRegisteredVoter(entity.getTotalRegisteredVoters());
		dto.setActiveRegisteredVoter(entity.getActiveRegisteredVoters());
		dto.setInactiveRegisteredVoter(entity.getInactiveRegisteredVoters());
		dto.setActivePercentage(entity.getActivePercentage());

		return dto;
	}

	/**
	 * Get poll book deletion data for all regions in a state (GUI-8: table data)
	 * @param stateId The state identifier
	 * @return List of PollBookDeletionByRegionDto with region names and deletion counts
	 */
	public List<PollBookDeletionByRegionDto> getPollBookDeletionByRegion(Integer stateId) {
		return pollBookDeletionByRegionRepository.findByStateId(stateId).stream()
				.map(this::toPollBookDeletionByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map PollBookDeletionByRegionEntity to PollBookDeletionByRegionDto (GUI-8)
	 * Fetches region name from eavs_region table
	 */
	private PollBookDeletionByRegionDto toPollBookDeletionByRegionDto(PollBookDeletionByRegionEntity entity) {
		PollBookDeletionByRegionDto dto = new PollBookDeletionByRegionDto();

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		dto.setMoved(entity.getMoved());
		dto.setDeath(entity.getDeath());
		dto.setFelony(entity.getFelony());
		dto.setFailResponse(entity.getFailResponse());
		dto.setIncompetentToVote(entity.getIncompetentToVote());
		dto.setVoterRequest(entity.getVoterRequest());
		dto.setDuplicateRecord(entity.getDuplicateRecord());

		return dto;
	}

	/**
	 * Get mail ballot rejection data for all regions in a state (GUI-9: table data)
	 * @param stateId The state identifier
	 * @return List of MailBallotRejectionByRegionDto with region names and rejection counts
	 */
	public List<MailBallotRejectionByRegionDto> getMailBallotRejectionByRegion(Integer stateId) {
		return mailBallotRejectionByRegionRepository.findByStateId(stateId).stream()
				.map(this::toMailBallotRejectionByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map MailBallotRejectionByRegionEntity to MailBallotRejectionByRegionDto (GUI-9)
	 * Fetches region name from eavs_region table
	 */
	private MailBallotRejectionByRegionDto toMailBallotRejectionByRegionDto(MailBallotRejectionByRegionEntity entity) {
		MailBallotRejectionByRegionDto dto = new MailBallotRejectionByRegionDto();

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		dto.setTotalMailBallot(entity.getTotalMailBallot());
		dto.setTotalRejectedBallots(entity.getTotalRejectedBallots());
		dto.setRejectionPercentage(entity.getRejectionPercentage());

		dto.setLate(entity.getLate());
		dto.setMissingVoterSignature(entity.getMissingVoterSignature());
		dto.setMissingWitnessSignature(entity.getMissingWitnessSignature());
		dto.setNonMatchingVoterSignature(entity.getNonMatchingVoterSignature());
		dto.setUnofficialEnvelope(entity.getUnofficialEnvelope());
		dto.setBallotMissingFromEnvelope(entity.getBallotMissingFromEnvelope());
		dto.setNoSecrecyEnvelope(entity.getNoSecrecyEnvelope());
		dto.setMultipleBallotsInOneEnvelope(entity.getMultipleBallotsOneEnvelope());
		dto.setEnvelopeNotSealed(entity.getEnvelopeNotSealed());
		dto.setNoPostmark(entity.getNoPostmark());
		dto.setNoResidentAddressOnEnvelope(entity.getNoResidentAddressOnEnvelope());
		dto.setVoterDeceased(entity.getVoterDeceased());
		dto.setVoterAlreadyVoted(entity.getVoterAlreadyVoted());
		dto.setMissingDocumentation(entity.getMissingDocumentation());
		dto.setVoterNotEligible(entity.getVoterNotEligible());
		dto.setNoBallotApplication(entity.getNoBallotApplication());

		return dto;
	}

	/**
	 * Get drop box voting data for all regions in a state (GUI-24: bubble chart data)
	 * @param stateId The state identifier
	 * @return List of DropboxVotingByRegionDto with region names and voting metrics
	 */
	public List<DropboxVotingByRegionDto> getDropboxVotingByRegion(Integer stateId) {
		return dropboxVotingByRegionRepository.findByStateId(stateId).stream()
				.map(this::toDropboxVotingByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map DropboxVotingByRegionEntity to DropboxVotingByRegionDto (GUI-24)
	 * Fetches region name from eavs_region table
	 */
	private DropboxVotingByRegionDto toDropboxVotingByRegionDto(DropboxVotingByRegionEntity entity) {
		DropboxVotingByRegionDto dto = new DropboxVotingByRegionDto();

		dto.setRegionId(entity.getRegionId());

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		dto.setRepublicanVotes2024(entity.getRepublicanVotes2024());
		dto.setDemocratVotes2024(entity.getDemocratVotes2024());
		dto.setTotalPresidentialVotes2024(entity.getTotalPresidentialVotes2024());
		dto.setMailInBallots(entity.getMailInBallots());
		dto.setTotalBallots(entity.getTotalBallots());
		dto.setPercentRepublican(entity.getPercentRepublican());
		dto.setPercentMailInBallots(entity.getPercentMailInBallots());

		return dto;
	}

	/**
	 * Get equipment quality vs rejected ballots data for all regions in a state (GUI-25: bubble chart data)
	 * @param stateId The state identifier
	 * @return List of EquipmentQualityRejectionByRegionDto with region names, quality scores, and rejection percentages
	 */
	public List<EquipmentQualityRejectionByRegionDto> getEquipmentQualityRejectionByRegion(Integer stateId) {
		return equipmentQualityRejectionByRegionRepository.findByStateId(stateId).stream()
				.map(this::toEquipmentQualityRejectionByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map EquipmentQualityRejectionByRegionEntity to EquipmentQualityRejectionByRegionDto (GUI-25)
	 * Fetches region name from eavs_region table
	 */
	private EquipmentQualityRejectionByRegionDto toEquipmentQualityRejectionByRegionDto(EquipmentQualityRejectionByRegionEntity entity) {
		EquipmentQualityRejectionByRegionDto dto = new EquipmentQualityRejectionByRegionDto();

		dto.setRegionId(entity.getRegionId());

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		// Convert BigDecimal to Double for equipment quality score
		if (entity.getEquipmentQualityScore() != null) {
			dto.setEquipmentQualityScore(entity.getEquipmentQualityScore().doubleValue());
		}

		dto.setPercentRejectedBallots(entity.getPercentRejectedBallots());
		dto.setTotalBallots(entity.getTotalBallots());

		return dto;
	}

	/**
	 * Get county choropleth info for all regions in a state (GUI-5, GUI-7, GUI-8, GUI-9, GUI-10)
	 * Aggregates data from multiple region tables
	 * Returns all counties with their metrics for choropleth map display
	 * 
	 * @param stateId The state identifier
	 * @return List of CountyChoroplethInfoDto for all regions in the state
	 */
	public List<CountyChoroplethInfoDto> getCountyChoroplethInfo(Integer stateId) {
		// Get all regions for the state
		List<EavsRegionEntity> regions = eavsRegionRepository.findByStateId(stateId);
		
		return regions.stream()
				.map(region -> buildCountyChoroplethInfoDto(region.getRegionId(), region.getName()))
				.collect(Collectors.toList());
	}

	/**
	 * Build CountyChoroplethInfoDto for a single region
	 * Aggregates data from multiple tables with null safety
	 */
	private CountyChoroplethInfoDto buildCountyChoroplethInfoDto(Integer regionId, String countyName) {
		CountyChoroplethInfoDto dto = new CountyChoroplethInfoDto();
		dto.setRegionId(regionId);
		dto.setCountyName(countyName);

		// Get total provisional from provisional_by_region table
		provisionalByRegionRepository.findByRegionId(regionId).ifPresentOrElse(
				provisional -> dto.setTotalProvisional(provisional.getTotalProvisional()),
				() -> dto.setTotalProvisional(0)
		);

		// Get active voter percentage from active_voters_by_region table
		// Check if table has data; if not, return 0.0
		activeVotersByRegionRepository.findByRegionId(regionId).ifPresentOrElse(
				activeVoters -> {
					// Use stored percentage if available, otherwise calculate
					if (activeVoters.getActivePercentage() != null) {
						dto.setActiveVoterPercentage(activeVoters.getActivePercentage());
					} else {
						// Calculate percentage: active / total * 100
						Integer active = activeVoters.getActiveRegisteredVoters() != null ? activeVoters.getActiveRegisteredVoters() : 0;
						Integer total = activeVoters.getTotalRegisteredVoters() != null ? activeVoters.getTotalRegisteredVoters() : 0;
						
						if (total > 0) {
							double percentage = (active.doubleValue() / total.doubleValue()) * 100.0;
							dto.setActiveVoterPercentage(percentage);
						} else {
							dto.setActiveVoterPercentage(0.0);
						}
					}
				},
				() -> dto.setActiveVoterPercentage(0.0)  // Table not seeded or no record
		);

		// Get pollbook deletion percentage from poll_book_deletion_by_region table
		pollBookDeletionByRegionRepository.findByRegionId(regionId).ifPresentOrElse(
				pollbook -> {
					// Calculate total deletions
					Integer totalDeletions = 
						(pollbook.getMoved() != null ? pollbook.getMoved() : 0) +
						(pollbook.getDeath() != null ? pollbook.getDeath() : 0) +
						(pollbook.getFelony() != null ? pollbook.getFelony() : 0) +
						(pollbook.getFailResponse() != null ? pollbook.getFailResponse() : 0) +
						(pollbook.getIncompetentToVote() != null ? pollbook.getIncompetentToVote() : 0) +
						(pollbook.getVoterRequest() != null ? pollbook.getVoterRequest() : 0) +
						(pollbook.getDuplicateRecord() != null ? pollbook.getDuplicateRecord() : 0);
					
					Integer totalRegistered = pollbook.getTotalRegisteredUser() != null ? pollbook.getTotalRegisteredUser() : 0;
					
					if (totalRegistered > 0) {
						double percentage = (totalDeletions.doubleValue() / totalRegistered.doubleValue()) * 100.0;
						dto.setPollbookDeletionPercentage(percentage);
					} else {
						dto.setPollbookDeletionPercentage(0.0);
					}
				},
				() -> dto.setPollbookDeletionPercentage(0.0)
		);

		// Get rejection percentage from mail_ballot_rejection_by_region table
		mailBallotRejectionByRegionRepository.findByRegionId(regionId).ifPresentOrElse(
				rejection -> dto.setRejectionPercentage(rejection.getRejectionPercentage() != null ? rejection.getRejectionPercentage() : 0.0),
				() -> dto.setRejectionPercentage(0.0)
		);

		// Get equipment type from display_type_of_voting_equipment_by_region table
		displayTypeOfVotingEquipmentByRegionRepository.findByRegionId(regionId).ifPresentOrElse(
				equipment -> dto.setTypeOfEquipmentUsed(equipment.getTypeOfEquipmentUsed()),
				() -> dto.setTypeOfEquipmentUsed(List.of())  // Empty list if no data
		);

		return dto;
	}

	/**
	 * GUI-10: Get display type of voting equipment for all regions in a state
	 * Returns list of equipment types used by each region
	 * 
	 * @param stateId The state identifier
	 * @return List of DisplayTypeOfVotingEquipmentByRegionDto with region names and equipment types
	 */
	public List<DisplayTypeOfVotingEquipmentByRegionDto> getDisplayTypeOfVotingEquipmentByRegion(Integer stateId) {
		return displayTypeOfVotingEquipmentByRegionRepository.findByStateId(stateId).stream()
				.map(this::toDisplayTypeOfVotingEquipmentByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map DisplayTypeOfVotingEquipmentByRegionEntity to DisplayTypeOfVotingEquipmentByRegionDto
	 * Fetches region name from eavs_region table
	 */
	private DisplayTypeOfVotingEquipmentByRegionDto toDisplayTypeOfVotingEquipmentByRegionDto(DisplayTypeOfVotingEquipmentByRegionEntity entity) {
		DisplayTypeOfVotingEquipmentByRegionDto dto = new DisplayTypeOfVotingEquipmentByRegionDto();

		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});

		dto.setTypeOfEquipmentUsed(entity.getTypeOfEquipmentUsed());

		return dto;
	}

	/**
	 * Get voter registration summary for all regions in a state
	 * Returns party breakdown and completeness metrics for each region
	 * 
	 * @param stateId The state identifier
	 * @return List of VoterRegistrationSummaryByRegionDto
	 */
	public List<VoterRegistrationSummaryByRegionDto> getVoterRegistrationSummaryByRegion(Integer stateId) {
		return voterRegistrationSummaryByRegionRepository.findByStateId(stateId).stream()
				.map(this::toVoterRegistrationSummaryByRegionDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map VoterRegistrationSummaryByRegionEntity to VoterRegistrationSummaryByRegionDto
	 * Fetches region name from eavs_region table
	 */
	private VoterRegistrationSummaryByRegionDto toVoterRegistrationSummaryByRegionDto(VoterRegistrationSummaryByRegionEntity entity) {
		VoterRegistrationSummaryByRegionDto dto = new VoterRegistrationSummaryByRegionDto();
		
		dto.setRegionId(entity.getRegionId());
		dto.setGeoId(entity.getGeoId());
		dto.setTotalNumRegisteredVoters(entity.getTotalNumRegisteredVoters());
		dto.setTotalNumRepublican(entity.getTotalNumRepublican());
		dto.setTotalNumDemocratic(entity.getTotalNumDemocratic());
		dto.setTotalNumUnaffiliated(entity.getTotalNumUnaffiliated());
		dto.setTotalCvap(entity.getTotalCvap());
		dto.setRegisteredVoterPercentage(entity.getRegisteredVoterPercentage());
		dto.setCompleteness(entity.getCompleteness());
		dto.setContactCompleteness(entity.getContactCompleteness());
		
		// Fetch region name from eavs_region table
		eavsRegionRepository.findByRegionId(entity.getRegionId()).ifPresent(region -> {
			dto.setRegionName(region.getName());
		});
		
		return dto;
	}
}


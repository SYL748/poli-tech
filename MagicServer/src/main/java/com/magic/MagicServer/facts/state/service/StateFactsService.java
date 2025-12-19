package com.magic.MagicServer.facts.state.service;

import com.magic.MagicServer.facts.state.dto.ActiveVotersByStateDto;
import com.magic.MagicServer.facts.state.dto.AverageEquipmentAgeByStateDto;
import com.magic.MagicServer.facts.state.dto.EavsDataQualityByStateDto;
import com.magic.MagicServer.facts.state.dto.EquipmentEiCurvePointDto;
import com.magic.MagicServer.facts.state.dto.EquipmentSummaryByStateDto;
import com.magic.MagicServer.facts.state.dto.MailBallotRejectionByStateDto;
import com.magic.MagicServer.facts.state.dto.PoliticalPartyCvapDto;
import com.magic.MagicServer.facts.state.dto.PollBookDeletionByStateDto;
import com.magic.MagicServer.facts.state.dto.PrecinctDemographicRegressionCoefDto;
import com.magic.MagicServer.facts.state.dto.ProvisionalByStateDto;
import com.magic.MagicServer.facts.state.dto.RegistrationMetricsByStateDto;
import com.magic.MagicServer.facts.state.dto.RejectedBallotEiCurvePointDto;
import com.magic.MagicServer.facts.state.dto.StateComparisonDto;
import com.magic.MagicServer.facts.state.dto.StateEarlyVotingDto;
import com.magic.MagicServer.facts.state.dto.StateEquipmentTotalDto;
import com.magic.MagicServer.facts.state.dto.VotingEquipmentHistoryByStateDto;
import com.magic.MagicServer.facts.state.entity.ActiveVotersByStateEntity;
import com.magic.MagicServer.facts.state.entity.AverageEquipmentAgeByStateEntity;
import com.magic.MagicServer.facts.state.entity.EarlyVoteByStateEntity;
import com.magic.MagicServer.facts.state.entity.EavsDataQualityByStateEntity;
import com.magic.MagicServer.facts.state.entity.EquipmentEiCurvePointEntity;
import com.magic.MagicServer.facts.state.entity.EquipmentSummaryByStateEntity;
import com.magic.MagicServer.facts.state.entity.MailBallotRejectionByStateEntity;
import com.magic.MagicServer.facts.state.entity.PoliticalPartyCvapEntity;
import com.magic.MagicServer.facts.state.entity.PollBookDeletionByStateEntity;
import com.magic.MagicServer.facts.state.entity.PrecinctDemographicRegressionCoefEntity;
import com.magic.MagicServer.facts.state.entity.ProvisionalByStateEntity;
import com.magic.MagicServer.facts.state.entity.RejectedBallotEiCurvePointEntity;
import com.magic.MagicServer.facts.state.entity.MailAndDropboxByStateEntity;
import com.magic.MagicServer.facts.state.entity.StateEquipmentTotalEntity;
import com.magic.MagicServer.facts.state.entity.VoteTurnoutByStateEntity;
import com.magic.MagicServer.facts.state.entity.StateEquipmentHistoryEntity;
import com.magic.MagicServer.facts.state.repository.ActiveVotersByStateRepository;
import com.magic.MagicServer.facts.state.repository.AverageEquipmentAgeByStateRepository;
import com.magic.MagicServer.facts.state.repository.EarlyVoteByStateRepository;
import com.magic.MagicServer.facts.state.repository.EavsDataQualityByStateRepository;
import com.magic.MagicServer.facts.state.repository.EquipmentEiCurvePointRepository;
import com.magic.MagicServer.facts.state.repository.EquipmentSummaryByStateRepository;
import com.magic.MagicServer.facts.state.repository.MailBallotRejectionByStateRepository;
import com.magic.MagicServer.facts.state.repository.PoliticalPartyCvapRepository;
import com.magic.MagicServer.facts.state.repository.PollBookDeletionByStateRepository;
import com.magic.MagicServer.facts.state.repository.PrecinctDemographicRegressionCoefRepository;
import com.magic.MagicServer.facts.state.repository.ProvisionalByStateRepository;
import com.magic.MagicServer.facts.state.repository.RegistrationMetricsByStateRepository;
import com.magic.MagicServer.facts.state.repository.RejectedBallotEiCurvePointRepository;
import com.magic.MagicServer.facts.state.repository.MailAndDropboxByStateRepository;
import com.magic.MagicServer.facts.state.repository.StateEquipmentHistoryRepository;
import com.magic.MagicServer.facts.state.repository.StateEquipmentTotalRepository;
import com.magic.MagicServer.facts.state.repository.VoteTurnoutByStateRepository;
import com.magic.MagicServer.jurisdiction.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for state-level facts data
 * Handles business logic for state metrics and analytics
 */
@Service
public class StateFactsService {

	private final PoliticalPartyCvapRepository politicalPartyCvapRepository;
	private final ProvisionalByStateRepository provisionalByStateRepository;
	private final ActiveVotersByStateRepository activeVotersByStateRepository;
	private final PollBookDeletionByStateRepository pollBookDeletionByStateRepository;
	private final MailBallotRejectionByStateRepository mailBallotRejectionByStateRepository;
	private final AverageEquipmentAgeByStateRepository averageEquipmentAgeByStateRepository;
	private final StateEquipmentTotalRepository stateEquipmentTotalRepository;
	private final StateEquipmentHistoryRepository stateEquipmentHistoryRepository;
	private final EquipmentSummaryByStateRepository equipmentSummaryByStateRepository;
	private final MailAndDropboxByStateRepository mailAndDropboxByStateRepository;
	private final EarlyVoteByStateRepository earlyVoteByStateRepository;
	private final VoteTurnoutByStateRepository voteTurnoutByStateRepository;
	private final RegistrationMetricsByStateRepository registrationMetricsByStateRepository;
	private final EavsDataQualityByStateRepository eavsDataQualityByStateRepository;
	private final EquipmentEiCurvePointRepository equipmentEiCurvePointRepository;
	private final RejectedBallotEiCurvePointRepository rejectedBallotEiCurvePointRepository;
	private final PrecinctDemographicRegressionCoefRepository precinctDemographicRegressionCoefRepository;
	private final StateRepository stateRepository;

	public StateFactsService(
			PoliticalPartyCvapRepository politicalPartyCvapRepository,
			ProvisionalByStateRepository provisionalByStateRepository,
			ActiveVotersByStateRepository activeVotersByStateRepository,
			PollBookDeletionByStateRepository pollBookDeletionByStateRepository,
			MailBallotRejectionByStateRepository mailBallotRejectionByStateRepository,
			AverageEquipmentAgeByStateRepository averageEquipmentAgeByStateRepository,
			StateEquipmentTotalRepository stateEquipmentTotalRepository,
			StateEquipmentHistoryRepository stateEquipmentHistoryRepository,
			EquipmentSummaryByStateRepository equipmentSummaryByStateRepository,
			MailAndDropboxByStateRepository mailAndDropboxByStateRepository,
			EarlyVoteByStateRepository earlyVoteByStateRepository,
			VoteTurnoutByStateRepository voteTurnoutByStateRepository,
			RegistrationMetricsByStateRepository registrationMetricsByStateRepository,
			EavsDataQualityByStateRepository eavsDataQualityByStateRepository,
			EquipmentEiCurvePointRepository equipmentEiCurvePointRepository,
			RejectedBallotEiCurvePointRepository rejectedBallotEiCurvePointRepository,
			PrecinctDemographicRegressionCoefRepository precinctDemographicRegressionCoefRepository,
			StateRepository stateRepository
	) {
		this.politicalPartyCvapRepository = politicalPartyCvapRepository;
		this.provisionalByStateRepository = provisionalByStateRepository;
		this.activeVotersByStateRepository = activeVotersByStateRepository;
		this.pollBookDeletionByStateRepository = pollBookDeletionByStateRepository;
		this.mailBallotRejectionByStateRepository = mailBallotRejectionByStateRepository;
		this.averageEquipmentAgeByStateRepository = averageEquipmentAgeByStateRepository;
		this.stateEquipmentTotalRepository = stateEquipmentTotalRepository;
		this.stateEquipmentHistoryRepository = stateEquipmentHistoryRepository;
		this.equipmentSummaryByStateRepository = equipmentSummaryByStateRepository;
		this.mailAndDropboxByStateRepository = mailAndDropboxByStateRepository;
		this.earlyVoteByStateRepository = earlyVoteByStateRepository;
		this.voteTurnoutByStateRepository = voteTurnoutByStateRepository;
		this.registrationMetricsByStateRepository = registrationMetricsByStateRepository;
		this.eavsDataQualityByStateRepository = eavsDataQualityByStateRepository;
		this.equipmentEiCurvePointRepository = equipmentEiCurvePointRepository;
		this.rejectedBallotEiCurvePointRepository = rejectedBallotEiCurvePointRepository;
		this.precinctDemographicRegressionCoefRepository = precinctDemographicRegressionCoefRepository;
		this.stateRepository = stateRepository;
	}

	/**
	 * Get political party CVAP percentages for a state (GUI-2: Florida and Illinois only)
	 * @param stateId The state identifier
	 * @return List of PoliticalPartyCvapDto
	 */
	public List<PoliticalPartyCvapDto> getPoliticalPartyCvapByState(Integer stateId) {
		return politicalPartyCvapRepository.findByStateId(stateId).stream()
				.map(this::toPoliticalPartyCvapDto)
				.collect(Collectors.toList());
	}

	/**
	 * Get provisional ballot categories for a state (GUI-3: bar chart data)
	 * @param stateId The state identifier
	 * @return Optional containing ProvisionalByStateDto if found
	 */
	public Optional<ProvisionalByStateDto> getProvisionalByState(Integer stateId) {
		return provisionalByStateRepository.findByStateId(stateId)
				.map(this::toProvisionalByStateDto);
	}

		/**
	 * Map ProvisionalByStateEntity to ProvisionalByStateDto
	 */
	private ProvisionalByStateDto toProvisionalByStateDto(ProvisionalByStateEntity entity) {
		ProvisionalByStateDto dto = new ProvisionalByStateDto();
		dto.setStateId(entity.getStateId());
		dto.setVoterNotOnList(entity.getVoterNotOnList());
		dto.setVoterLackedId(entity.getVoterLackedId());
		dto.setElectionOfficialChallengedEligibility(entity.getElectionOfficialChallengedEligibility());
		dto.setAnotherPersonChallengedEligibility(entity.getAnotherPersonChallengedEligibility());
		dto.setVoterNotResident(entity.getVoterNotResident());
		dto.setVoterRegistrationNotUpdated(entity.getVoterRegistrationNotUpdated());
		dto.setVoterDidNotSurrenderMailBallot(entity.getVoterDidNotSurrenderMailBallot());
		dto.setJudgeExtendedVotingHours(entity.getJudgeExtendedVotingHours());
		dto.setVoterUsedSdr(entity.getVoterUsedSdr());
		dto.setOtherReasons(entity.getOtherReason());
		return dto;
	}

	/**
	 * Map PoliticalPartyCvapEntity to PoliticalPartyCvapDto
	 */
	private PoliticalPartyCvapDto toPoliticalPartyCvapDto(PoliticalPartyCvapEntity entity) {
		PoliticalPartyCvapDto dto = new PoliticalPartyCvapDto();
		dto.setStateId(entity.getStateId());
		dto.setName(entity.getName());
		dto.setCvapPercent(entity.getCvapPct());
		return dto;
	}

	/**
	 * Get active voters data for a state (GUI-7: bar chart data)
	 * @param stateId The state identifier
	 * @return Optional containing ActiveVotersByStateDto if found
	 */
	public Optional<ActiveVotersByStateDto> getActiveVotersByState(Integer stateId) {
		return activeVotersByStateRepository.findByStateId(stateId)
				.map(this::toActiveVotersByStateDto);
	}

	/**
	 * Map ActiveVotersByStateEntity to ActiveVotersByStateDto (GUI-7)
	 * Calculates inactive registered voters as difference between total and active
	 */
	private ActiveVotersByStateDto toActiveVotersByStateDto(ActiveVotersByStateEntity entity) {
		ActiveVotersByStateDto dto = new ActiveVotersByStateDto();
		dto.setStateId(entity.getStateId());
		dto.setTotalRegisteredVoters(entity.getTotalRegisteredVoters());
		dto.setActiveRegisteredVoters(entity.getActiveRegisteredVoters());
		dto.setInactiveRegisteredVoters(entity.getTotalRegisteredVoters() - entity.getActiveRegisteredVoters());
		return dto;
	}

	/**
	 * Get registration and turnout metrics for a state
	 * Joins data from registration_metrics_by_state and active_voters_by_state tables
	 * @param stateId The state identifier
	 * @return Optional containing RegistrationMetricsByStateDto if found
	 */
	public Optional<RegistrationMetricsByStateDto> getRegistrationMetricsByState(Integer stateId) {
		return registrationMetricsByStateRepository.findCompleteMetricsByStateId(stateId);
	}

	/**
	 * Get poll book deletion data for a state (GUI-8: bar chart data)
	 * @param stateId The state identifier
	 * @return Optional containing PollBookDeletionByStateDto if found
	 */
	public Optional<PollBookDeletionByStateDto> getPollBookDeletionByState(Integer stateId) {
		return pollBookDeletionByStateRepository.findByStateId(stateId)
				.map(this::toPollBookDeletionByStateDto);
	}

	/**
	 * Map PollBookDeletionByStateEntity to PollBookDeletionByStateDto (GUI-8)
	 */
	private PollBookDeletionByStateDto toPollBookDeletionByStateDto(PollBookDeletionByStateEntity entity) {
		PollBookDeletionByStateDto dto = new PollBookDeletionByStateDto();
		dto.setStateId(entity.getStateId());
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
	 * Get mail ballot rejection data for a state (GUI-9: bar chart data)
	 * @param stateId The state identifier
	 * @return Optional containing MailBallotRejectionByStateDto if found
	 */
	public Optional<MailBallotRejectionByStateDto> getMailBallotRejectionByState(Integer stateId) {
		return mailBallotRejectionByStateRepository.findByStateId(stateId)
				.map(this::toMailBallotRejectionByStateDto);
	}

	/**
	 * Map MailBallotRejectionByStateEntity to MailBallotRejectionByStateDto (GUI-9)
	 */
	private MailBallotRejectionByStateDto toMailBallotRejectionByStateDto(MailBallotRejectionByStateEntity entity) {
		MailBallotRejectionByStateDto dto = new MailBallotRejectionByStateDto();
		dto.setStateId(entity.getStateId());
		dto.setLate(entity.getLate());
		dto.setTotalMailBallot(entity.getTotalMailBallot());
		dto.setTotalRejectedBallots(entity.getTotalRejectedBallots());
		
		// Calculate rejection percentage
		if (entity.getTotalMailBallot() != null && entity.getTotalMailBallot() > 0 && entity.getTotalRejectedBallots() != null) {
			double percentage = (entity.getTotalRejectedBallots().doubleValue() / entity.getTotalMailBallot().doubleValue()) * 100.0;
			dto.setRejectionPercentage(percentage);
		} else {
			dto.setRejectionPercentage(0.0);
		}
		
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
	 * Get average equipment age for all states (GUI-11: choropleth map)
	 * @return List of AverageEquipmentAgeByStateDto for all states
	 */
	public List<AverageEquipmentAgeByStateDto> getAllAverageEquipmentAge() {
		return averageEquipmentAgeByStateRepository.findAll().stream()
				.map(this::toAverageEquipmentAgeByStateDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map AverageEquipmentAgeByStateEntity to AverageEquipmentAgeByStateDto (GUI-11)
	 */
	private AverageEquipmentAgeByStateDto toAverageEquipmentAgeByStateDto(AverageEquipmentAgeByStateEntity entity) {
		AverageEquipmentAgeByStateDto dto = new AverageEquipmentAgeByStateDto();
		dto.setStateId(entity.getStateId());
		dto.setAverageAgeOfEquipment(entity.getAverageAgeOfEquipment());
		return dto;
	}

	/**
	 * Get equipment totals by category for all states (GUI-12: equipment table)
	 * @return List of StateEquipmentTotalDto for all states
	 */
	public List<StateEquipmentTotalDto> getAllStateEquipmentTotals() {
		return stateEquipmentTotalRepository.findAll().stream()
				.map(this::toStateEquipmentTotalDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map StateEquipmentTotalEntity to StateEquipmentTotalDto (GUI-12)
	 * Fetches state name from state table
	 */
	private StateEquipmentTotalDto toStateEquipmentTotalDto(StateEquipmentTotalEntity entity) {
		StateEquipmentTotalDto dto = new StateEquipmentTotalDto();
		dto.setStateId(entity.getStateId());

		// Fetch state name from state table
		stateRepository.findByStateId(entity.getStateId()).ifPresent(state -> {
			dto.setStateName(state.getName());
		});

		dto.setDreNoVvpat(entity.getDreNoVvpat());
		dto.setDreWithVvpat(entity.getDreWithVvpat());
		dto.setBallotMarkingDevice(entity.getBallotMarkingDevice());
		dto.setScanner(entity.getScanner());

		return dto;
	}

	/**
	 * Get state comparison data for a specific state (GUI-15)
	 * @param stateId The state identifier
	 * @return Optional containing StateComparisonDto if found
	 */
	public Optional<StateComparisonDto> getStateComparison(Integer stateId) {
		return mailAndDropboxByStateRepository.findByStateId(stateId)
				.map(this::toStateComparisonDto);
	}

	/**
	 * Map MailAndDropboxByStateEntity to StateComparisonDto (GUI-15)
	 * Converts integer state ID to string for frontend compatibility
	 */
	private StateComparisonDto toStateComparisonDto(MailAndDropboxByStateEntity entity) {
		StateComparisonDto dto = new StateComparisonDto();
		
		// Convert state ID to string
		dto.setStateId(entity.getStateId() != null ? entity.getStateId().toString() : null);
		
		// Set policy attributes
		dto.setFelonyVotingRights(entity.getFelonyVotingRights());
		
		// Convert Double to Integer for earlyVotingPeriodDays (DTO expects Integer, entity has Double)
		if (entity.getEarlyVotingPeriodDays() != null) {
			dto.setEarlyVotingPeriodDays(entity.getEarlyVotingPeriodDays().intValue());
		}
		
		dto.setVoterIdRequirement(entity.getVoterIdRequirement());
		
		// Convert Boolean to String for DTO fields
		dto.setSameDayRegistration(entity.getSameDayRegistration() != null ? entity.getSameDayRegistration().toString() : null);
		dto.setAbsenteeRequestDeadline(entity.getAbsenteeRequestDeadline());
		dto.setAutomaticVoterRegistration(entity.getAutomaticVoterRegistration() != null ? entity.getAutomaticVoterRegistration().toString() : null);
		dto.setNoExcuseAbsenteeVoting(entity.getNoExcuseAbsenteeVoting());
		
		// Set voting metrics
		dto.setPercentMailBallot(entity.getPercentMailBallot());
		dto.setPercentDropbox(entity.getPercentDropbox());
		dto.setTurnoutRate(entity.getTurnOutRate());
		
		return dto;
	}

	/**
	 * Get early voting data for a specific state (GUI-23)
	 * All data is now stored in the early_vote_by_state table including totals and percentages
	 * 
	 * @param stateId The state identifier
	 * @return Optional containing StateEarlyVotingDto if found
	 */
	public Optional<StateEarlyVotingDto> getEarlyVoting(Integer stateId) {
		return earlyVoteByStateRepository.findByStateId(stateId)
				.map(this::toStateEarlyVotingDto);
	}

	/**
	 * Map EarlyVoteByStateEntity to StateEarlyVotingDto (GUI-23)
	 * Uses stored values from the entity including all totals and percentages
	 */
	private StateEarlyVotingDto toStateEarlyVotingDto(EarlyVoteByStateEntity earlyVote) {
		
		StateEarlyVotingDto dto = new StateEarlyVotingDto();
		
		// Convert state ID to string
		dto.setStateId(earlyVote.getStateId() != null ? earlyVote.getStateId().toString() : null);
		
		// Set raw counts using new column names
		dto.setTotalVotesCast(earlyVote.getTotalVotesCast());
		dto.setInPersonEarly(earlyVote.getInPersonEarly());
		dto.setMailAccepted(earlyVote.getMailAccepted());
		dto.setDropboxAccepted(earlyVote.getDropboxAccepted());
		dto.setUocavaAccepted(earlyVote.getUocavaAccepted());
		dto.setProvisionalCounted(earlyVote.getProvisionalCounted());
		dto.setTotalEarly(earlyVote.getTotalEarly());
		
		// Use stored percentages from the database
		dto.setPercentInPersonEarly(earlyVote.getPercentInPersonEarly());
		dto.setPercentMail(earlyVote.getPercentMail());
		dto.setPercentDropbox(earlyVote.getPercentDropbox());
		dto.setPercentUocava(earlyVote.getPercentUocava());
		dto.setPercentProvisional(earlyVote.getPercentProvisional());
		dto.setPercentTotalEarly(earlyVote.getPercentTotalEarly());
		
		return dto;
	}

	/**
	 * Get equipment summary for a specific state (GUI-6)
	 * Returns complete equipment data from denormalized table
	 * Matches: 6_Equipment_Summary_by_State_with_short_description.csv
	 * 
	 * @param stateId The state identifier
	 * @return List of EquipmentSummaryByStateDto with complete equipment details
	 */
	public List<EquipmentSummaryByStateDto> getEquipmentSummaryByState(Integer stateId) {
		return equipmentSummaryByStateRepository
				.findByStateIdOrderByManufacturerAscModelNameAsc(stateId)
				.stream()
				.map(this::toEquipmentSummaryByStateDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map EquipmentSummaryByStateEntity to EquipmentSummaryByStateDto (GUI-6)
	 * Converts database entity to frontend-compatible DTO format
	 * 
	 * @param entity EquipmentSummaryByStateEntity from database
	 * @return Complete EquipmentSummaryByStateDto with all fields populated
	 */
	private EquipmentSummaryByStateDto toEquipmentSummaryByStateDto(EquipmentSummaryByStateEntity entity) {
		EquipmentSummaryByStateDto dto = new EquipmentSummaryByStateDto();
		
		// Convert state_id to string
		dto.setStateId(entity.getStateId() != null ? entity.getStateId().toString() : null);
		
		// Set equipment details from denormalized table
		dto.setManufacturer(entity.getManufacturer());
		dto.setModelName(entity.getModelName());
		dto.setQuantity(entity.getQuantity());
		dto.setEquipmentType(entity.getEquipmentType());
		
		// Convert age from BigDecimal to Double
		dto.setAge(entity.getAge() != null ? entity.getAge().doubleValue() : null);
		
		dto.setOs(entity.getOs());
		dto.setCertificationLevel(entity.getCertificationLevel());
		
		// Scanning rate and error rate are already strings
		dto.setScanningRate(entity.getScanningRate());
		dto.setErrorRate(entity.getErrorRate());
		
		// Convert BigDecimal to Double for reliability and quality
		dto.setReliability(entity.getReliability() != null ? entity.getReliability().doubleValue() : null);
		dto.setQuality(entity.getQuality() != null ? entity.getQuality().doubleValue() : null);
		
		dto.setShortDescription(entity.getShortDescription());
		
		return dto;
	}

	/**
	 * Get equipment history for a specific state (GUI-14)
	 * Returns equipment counts by category for all years (flexible design)
	 * 
	 * Database stores: N rows (one per year)
	 * DTO returns: 1 object with list of year objects (extensible for future years)
	 * 
	 * @param stateId The state identifier
	 * @return Optional containing VotingEquipmentHistoryByStateDto if data exists
	 */
	public Optional<VotingEquipmentHistoryByStateDto> getEquipmentHistoryByState(Integer stateId) {
		List<StateEquipmentHistoryEntity> historyRecords = 
			stateEquipmentHistoryRepository.findByStateIdOrderByYear(stateId);
		
		if (historyRecords.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(toVotingEquipmentHistoryByStateDto(historyRecords, stateId));
	}

	/**
	 * Map list of StateEquipmentHistoryEntity to VotingEquipmentHistoryByStateDto (GUI-14)
	 * Simple 1:1 mapping - each entity row becomes a YearEquipmentData object
	 * No pivoting needed - structure matches normalized database design
	 * 
	 * @param historyRecords List of StateEquipmentHistoryEntity (ordered by year)
	 * @param stateId The state identifier
	 * @return VotingEquipmentHistoryByStateDto with list of year data
	 */
	private VotingEquipmentHistoryByStateDto toVotingEquipmentHistoryByStateDto(
			List<StateEquipmentHistoryEntity> historyRecords, 
			Integer stateId) {
		
		VotingEquipmentHistoryByStateDto dto = new VotingEquipmentHistoryByStateDto();
		dto.setStateId(stateId.toString());
		
		// Map each entity to a YearEquipmentData object
		List<VotingEquipmentHistoryByStateDto.YearEquipmentData> yearDataList = 
			historyRecords.stream()
				.map(this::toYearEquipmentData)
				.collect(Collectors.toList());
		
		dto.setYears(yearDataList);
		
		return dto;
	}

	/**
	 * Map StateEquipmentHistoryEntity to YearEquipmentData
	 * Simple field-by-field mapping
	 * 
	 * @param entity The equipment history entity for one year
	 * @return YearEquipmentData object
	 */
	private VotingEquipmentHistoryByStateDto.YearEquipmentData toYearEquipmentData(
			StateEquipmentHistoryEntity entity) {
		
		VotingEquipmentHistoryByStateDto.YearEquipmentData yearData = 
			new VotingEquipmentHistoryByStateDto.YearEquipmentData();
		
		yearData.setYear(entity.getYear());
		yearData.setDreNoVvpat(entity.getDreNoVvpat());
		yearData.setDreWithVvpat(entity.getDreWithVvpat());
		yearData.setBallotMarkingDevice(entity.getBallotMarkingDevice());
		yearData.setScanner(entity.getScanner());
		
		return yearData;
	}

	/**
	 * Get EAVS data quality score for a state
	 * Returns data quality metric from EAVS Missing Data Metric
	 * 
	 * @param stateId The state identifier
	 * @return Optional containing EavsDataQualityByStateDto if found
	 */
	public Optional<EavsDataQualityByStateDto> getEavsDataQualityByState(Integer stateId) {
		return eavsDataQualityByStateRepository.findByStateId(stateId)
				.map(this::toEavsDataQualityByStateDto);
	}

	/**
	 * Map EavsDataQualityByStateEntity to EavsDataQualityByStateDto
	 */
	private EavsDataQualityByStateDto toEavsDataQualityByStateDto(EavsDataQualityByStateEntity entity) {
		EavsDataQualityByStateDto dto = new EavsDataQualityByStateDto();
		dto.setStateId(entity.getStateId());
		dto.setDataQualityScore(entity.getDataQualityScore());
		return dto;
	}

	/**
	 * GUI-28: Get equipment ecological inference curve data for a state
	 * Returns EI KDE density data grouped by demographic for visualization
	 * 
	 * Response format matches Equipment_Quality_EI_KDE_density.json:
	 * {
	 *   "stateId": {
	 *     "White": [
	 *       { "equipment_quality": 0.0, "probability_density": 0.0 },
	 *       ...
	 *     ],
	 *     "Hispanic": [...],
	 *     "African American": [...]
	 *   }
	 * }
	 * 
	 * @param stateId The state identifier
	 * @return Optional containing EquipmentEiCurvePointDto if data found
	 */
	public Optional<EquipmentEiCurvePointDto> getEquipmentEiCurveByState(Integer stateId) {
		List<EquipmentEiCurvePointEntity> entities = equipmentEiCurvePointRepository.findByStateId(stateId);
		
		if (entities.isEmpty()) {
			return Optional.empty();
		}
		
		// Group entities by demographic
		Map<String, List<EquipmentEiCurvePointDto.CurveDataPoint>> demographicData = entities.stream()
			.collect(Collectors.groupingBy(
				EquipmentEiCurvePointEntity::getDemographic,
				Collectors.mapping(
					entity -> new EquipmentEiCurvePointDto.CurveDataPoint(
						entity.getEquipmentQuality(),
						entity.getProbabilityDensity()
					),
					Collectors.toList()
				)
			));
		
		EquipmentEiCurvePointDto dto = new EquipmentEiCurvePointDto();
		dto.setStateId(stateId);
		dto.setDemographicData(demographicData);
		
		return Optional.of(dto);
	}

	/**
	 * GUI-29: Get rejected ballot ecological inference curve data for a state
	 * Returns EI KDE density data grouped by demographic for visualization
	 * 
	 * Response format matches Rejected_Ballots_EI_KDE_Density.json:
	 * {
	 *   "stateId": {
	 *     "White": [
	 *       { "rejection_probability": 0.0, "probability_density": 0.0 },
	 *       ...
	 *     ],
	 *     "Hispanic": [...],
	 *     "African American": [...]
	 *   }
	 * }
	 * 
	 * @param stateId The state identifier
	 * @return Optional containing RejectedBallotEiCurvePointDto if data found
	 */
	public Optional<RejectedBallotEiCurvePointDto> getRejectedBallotEiCurveByState(Integer stateId) {
		List<RejectedBallotEiCurvePointEntity> entities = rejectedBallotEiCurvePointRepository.findByStateId(stateId);
		
		if (entities.isEmpty()) {
			return Optional.empty();
		}
		
		// Group entities by demographic
		Map<String, List<RejectedBallotEiCurvePointDto.CurveDataPoint>> demographicData = entities.stream()
			.collect(Collectors.groupingBy(
				RejectedBallotEiCurvePointEntity::getDemographic,
				Collectors.mapping(
					entity -> new RejectedBallotEiCurvePointDto.CurveDataPoint(
						entity.getRejectionProbability(),
						entity.getProbabilityDensity()
					),
					Collectors.toList()
				)
			));
		
		RejectedBallotEiCurvePointDto dto = new RejectedBallotEiCurvePointDto();
		dto.setStateId(stateId);
		dto.setDemographicData(demographicData);
		
		return Optional.of(dto);
	}

	/**
	 * Get precinct demographic regression coefficients for a state (GUI-27)
	 * @param stateId The state identifier
	 * @return List of PrecinctDemographicRegressionCoefDto
	 */
	public List<PrecinctDemographicRegressionCoefDto> getPrecinctDemographicRegressionCoefByState(Integer stateId) {
		// Get state name
		String stateName = stateRepository.findById(stateId)
				.map(state -> state.getName())
				.orElse(null);
		
		if (stateName == null) {
			return List.of();
		}
		
		// Get all regression coefficients for the state
		List<PrecinctDemographicRegressionCoefEntity> entities = precinctDemographicRegressionCoefRepository.findByStateId(stateId);
		
		// Convert to DTOs
		return entities.stream()
				.map(entity -> toPrecinctDemographicRegressionCoefDto(entity, stateName))
				.collect(Collectors.toList());
	}

	/**
	 * Convert entity to DTO for precinct demographic regression coefficients
	 * @param entity The entity to convert
	 * @param stateName The state name
	 * @return PrecinctDemographicRegressionCoefDto
	 */
	private PrecinctDemographicRegressionCoefDto toPrecinctDemographicRegressionCoefDto(
			PrecinctDemographicRegressionCoefEntity entity, String stateName) {
		PrecinctDemographicRegressionCoefDto dto = new PrecinctDemographicRegressionCoefDto();
		dto.setState(stateName);
		dto.setParty(entity.getPoliticalParty());
		dto.setDemographic(entity.getDemographic());
		dto.setCoefA(entity.getCoefA());
		dto.setCoefB(entity.getCoefB());
		dto.setCoefC(entity.getCoefC());
		return dto;
	}
}


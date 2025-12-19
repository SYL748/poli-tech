package com.magic.MagicServer.facts;

import com.magic.MagicServer.facts.precinct.dto.GinglesChartPrecinctDto;
import com.magic.MagicServer.facts.precinct.service.PrecinctFactsService;
import com.magic.MagicServer.facts.region.dto.ActiveVotersByRegionDto;
import com.magic.MagicServer.facts.region.dto.CountyChoroplethInfoDto;
import com.magic.MagicServer.facts.region.dto.DisplayTypeOfVotingEquipmentByRegionDto;
import com.magic.MagicServer.facts.region.dto.DropboxVotingByRegionDto;
import com.magic.MagicServer.facts.region.dto.EquipmentQualityRejectionByRegionDto;
import com.magic.MagicServer.facts.region.dto.MailBallotRejectionByRegionDto;
import com.magic.MagicServer.facts.region.dto.PollBookDeletionByRegionDto;
import com.magic.MagicServer.facts.region.dto.ProvisionalByRegionDto;
import com.magic.MagicServer.facts.region.dto.VoterRegistrationSummaryByRegionDto;
import com.magic.MagicServer.facts.region.service.RegionFactsService;
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
import com.magic.MagicServer.facts.state.service.StateFactsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Central REST Controller for all fact data
 * Routes calls to region, state, precinct, block, and equipment analytics services
 */
@RestController
@RequestMapping("/api/facts")
public class FactsController {

	private final StateFactsService stateFactsService;
	private final RegionFactsService regionFactsService;
	private final PrecinctFactsService precinctFactsService;

	public FactsController(
			StateFactsService stateFactsService,
			RegionFactsService regionFactsService,
			PrecinctFactsService precinctFactsService
	) {
		this.stateFactsService = stateFactsService;
		this.regionFactsService = regionFactsService;
		this.precinctFactsService = precinctFactsService;
	}

	/**
	 * GUI-2: Get political party CVAP percentages for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/political-party-cvap
	 * 
	 * Returns political party CVAP (Citizen Voting Age Population) percentages
	 * for Political Party detailed states (Florida and Illinois only).
	 * 
	 * The percentage of CVAP voters eligible to vote is calculated as:
	 * (number of 2024 EAVS registered voters) / (2023 ACS CVAP)
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of PoliticalPartyCvapDto with party names and percentages
	 */
	@GetMapping("/states/{stateId}/political-party-cvap")
	public List<PoliticalPartyCvapDto> getPoliticalPartyCvapByState(@PathVariable Integer stateId) {
		return stateFactsService.getPoliticalPartyCvapByState(stateId);
	}

	/**
	 * GUI-3: Get provisional ballot categories for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/provisional
	 * 
	 * Returns provisional ballot data broken down by category (E2a-E2i) for
	 * displaying in a bar chart. Categories include reasons why provisional
	 * ballots were cast, such as voter not on list, voter lacked ID, etc.
	 * 
	 * The data includes an "other reasons" category for any provisional ballots
	 * not covered by the specific categories.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return ProvisionalByStateDto with all category counts, 404 if not found
	 */
	@GetMapping("/states/{stateId}/provisional")
	public ResponseEntity<ProvisionalByStateDto> getProvisionalByState(@PathVariable Integer stateId) {
		return stateFactsService.getProvisionalByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-4: Get provisional ballot categories by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/provisional
	 * 
	 * Returns provisional ballot data broken down by category and EAVS geographic unit
	 * for displaying in a data table. Each row represents an EAVS geographic unit
	 * (county or equivalent) with columns for each provisional ballot category.
	 * 
	 * This data is used to populate the provisional ballot detail table showing
	 * regional breakdown of provisional ballot reasons (E2a-E2i).
	 * 
	 * Controller uses Spring's DispatcherServlet to route the request to the appropriate service.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of ProvisionalByRegionDto with region names and category counts
	 */
	@GetMapping("/states/{stateId}/regions/provisional")
	public List<ProvisionalByRegionDto> getProvisionalByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getProvisionalByRegion(stateId);
	}

	/**
	 * GUI-7: Get active voters data for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/active-voters
	 * 
	 * Returns active and inactive voter registration data for displaying in a bar chart.
	 * Shows the number of active voters, inactive voters, and total registered voters
	 * (calculated as active + inactive).
	 * 
	 * This data corresponds to EAVS 2024 question A1 (active registered voters) and
	 * A2 (inactive registered voters).
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return ActiveVotersByStateDto with voter counts, 404 if not found
	 */
	@GetMapping("/states/{stateId}/active-voters")
	public ResponseEntity<ActiveVotersByStateDto> getActiveVotersByState(@PathVariable Integer stateId) {
		return stateFactsService.getActiveVotersByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Get registration and turnout metrics for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/registration-metrics
	 * 
	 * Returns comprehensive voter registration and turnout data for a state, combining
	 * data from registration_metrics_by_state and active_voters_by_state tables.
	 * Includes total votes cast, CVAP, registration rate, turnout rate, and active/total voter counts.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return RegistrationMetricsByStateDto with all metrics, 404 if not found
	 */
	@GetMapping("/states/{stateId}/registration-metrics")
	public ResponseEntity<RegistrationMetricsByStateDto> getRegistrationMetricsByState(@PathVariable Integer stateId) {
		return stateFactsService.getRegistrationMetricsByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Get EAVS data quality score for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/data-quality
	 * 
	 * Returns the EAVS data quality score (from EAVS Missing Data Metric) for a specific state.
	 * The score ranges from 0.0 to 1.0, where higher values indicate better data quality.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return EavsDataQualityByStateDto with data quality score, 404 if not found
	 */
	@GetMapping("/states/{stateId}/data-quality")
	public ResponseEntity<EavsDataQualityByStateDto> getEavsDataQualityByState(@PathVariable Integer stateId) {
		return stateFactsService.getEavsDataQualityByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-28: Get equipment ecological inference curve data for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/equipment-ei-curve
	 * 
	 * Returns equipment quality ecological inference (EI) kernel density estimation (KDE)
	 * data for visualization. The data shows the relationship between equipment quality
	 * scores and probability density across different demographic groups.
	 * 
	 * Response format:
	 * {
	 *   "stateId": 48,
	 *   "demographicData": {
	 *     "White": [
	 *       { "equipmentQuality": 0.0, "probabilityDensity": 0.0 },
	 *       { "equipmentQuality": 0.0101, "probabilityDensity": 0.0 },
	 *       ...
	 *     ],
	 *     "Hispanic": [...],
	 *     "African American": [...]
	 *   }
	 * }
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return EquipmentEiCurvePointDto with curve data by demographic, 404 if not found
	 */
	@GetMapping("/states/{stateId}/equipment-ei-curve")
	public ResponseEntity<EquipmentEiCurvePointDto> getEquipmentEiCurveByState(@PathVariable Integer stateId) {
		return stateFactsService.getEquipmentEiCurveByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-29: Get rejected ballot ecological inference curve data for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/rejected-ballot-ei-curve
	 * 
	 * Returns rejected ballot ecological inference (EI) kernel density estimation (KDE)
	 * data for visualization. The data shows the relationship between ballot rejection
	 * probability and probability density across different demographic groups.
	 * 
	 * Response format:
	 * {
	 *   "stateId": 48,
	 *   "demographicData": {
	 *     "White": [
	 *       { "rejectionProbability": 0.0, "probabilityDensity": 0.0 },
	 *       { "rejectionProbability": 0.0001, "probabilityDensity": 0.0 },
	 *       ...
	 *     ],
	 *     "Hispanic": [...],
	 *     "African American": [...]
	 *   }
	 * }
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return RejectedBallotEiCurvePointDto with curve data by demographic, 404 if not found
	 */
	@GetMapping("/states/{stateId}/rejected-ballot-ei-curve")
	public ResponseEntity<RejectedBallotEiCurvePointDto> getRejectedBallotEiCurveByState(@PathVariable Integer stateId) {
		return stateFactsService.getRejectedBallotEiCurveByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-27: Get precinct demographic regression coefficients for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/precinct-demographic-regression
	 * 
	 * Response Format:
	 * [
	 *   {
	 *     "state": "Alabama",
	 *     "party": "Democratic",
	 *     "demographic": "White",
	 *     "coef_a": 0.000345,
	 *     "coef_b": -0.949716,
	 *     "coef_c": 95.698004
	 *   },
	 *   ...
	 * ]
	 *
	 * Available for: Alabama (stateId=1), Texas (stateId=48)
	 */
	@GetMapping("/states/{stateId}/precinct-demographic-regression")
	public ResponseEntity<List<PrecinctDemographicRegressionCoefDto>> getPrecinctDemographicRegressionByState(@PathVariable Integer stateId) {
		List<PrecinctDemographicRegressionCoefDto> results = stateFactsService.getPrecinctDemographicRegressionCoefByState(stateId);
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(results);
	}

	/**
	 * GUI-7 and GUI-8: Get active voters data by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/active-voters
	 * 
	 * Returns active and inactive voter registration data broken down by EAVS geographic unit
	 * for displaying in a data table. Each row represents an EAVS geographic unit (county
	 * or equivalent) with columns for active voters, inactive voters, and total voters.
	 * 
	 * This data allows users to see regional variations in voter registration status and
	 * can be used to display a choropleth map showing the percentage of active voters
	 * by region for detailed states.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of ActiveVotersByRegionDto with region names and voter counts
	 */
	@GetMapping("/states/{stateId}/regions/active-voters")
	public List<ActiveVotersByRegionDto> getActiveVotersByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getActiveVotersByRegion(stateId);
	}

	/**
	 * GUI-8: Get poll book deletion data for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/pollbook-deletions
	 * 
	 * Returns poll book deletion data broken down by category (A12b-A12h) for
	 * displaying in a bar chart. Categories include reasons why voters were removed
	 * from the registration rolls, such as moved, death, felony, etc.
	 * 
	 * This data corresponds to EAVS 2024 questions A12b through A12h regarding
	 * voter roll maintenance and deletions.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return PollBookDeletionByStateDto with all category counts, 404 if not found
	 */
	@GetMapping("/states/{stateId}/pollbook-deletions")
	public ResponseEntity<PollBookDeletionByStateDto> getPollBookDeletionByState(@PathVariable Integer stateId) {
		return stateFactsService.getPollBookDeletionByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-8: Get poll book deletion data by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/pollbook-deletions
	 * 
	 * Returns poll book deletion data broken down by category and EAVS geographic unit
	 * for displaying in a data table. Each row represents an EAVS geographic unit
	 * (county or equivalent) with columns for each poll book deletion category.
	 * 
	 * This data is used to populate the poll book deletion detail table showing
	 * regional breakdown of voter roll maintenance reasons (A12b-A12h).
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of PollBookDeletionByRegionDto with region names and deletion counts
	 */
	@GetMapping("/states/{stateId}/regions/pollbook-deletions")
	public List<PollBookDeletionByRegionDto> getPollBookDeletionByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getPollBookDeletionByRegion(stateId);
	}

	/**
	 * GUI-9: Get mail ballot rejection data for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/mail-ballot-rejections
	 * 
	 * Returns mail ballot rejection data broken down by category (C9b-C9q) for
	 * displaying in a bar chart. Categories include reasons why mail ballots were rejected,
	 * such as late arrival, missing signature, non-matching signature, etc.
	 * 
	 * This data corresponds to EAVS 2024 questions C9b through C9q regarding
	 * mail ballot rejection reasons.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return MailBallotRejectionByStateDto with all category counts, 404 if not found
	 */
	@GetMapping("/states/{stateId}/mail-ballot-rejections")
	public ResponseEntity<MailBallotRejectionByStateDto> getMailBallotRejectionByState(@PathVariable Integer stateId) {
		return stateFactsService.getMailBallotRejectionByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-9: Get mail ballot rejection data by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/mail-ballot-rejections
	 * 
	 * Returns mail ballot rejection data broken down by category and EAVS geographic unit
	 * for displaying in a data table. Each row represents an EAVS geographic unit
	 * (county or equivalent) with columns for each mail ballot rejection category.
	 * 
	 * This data is used to populate the mail ballot rejection detail table showing
	 * regional breakdown of rejection reasons (C9b-C9q).
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of MailBallotRejectionByRegionDto with region names and rejection counts
	 */
	@GetMapping("/states/{stateId}/regions/mail-ballot-rejections")
	public List<MailBallotRejectionByRegionDto> getMailBallotRejectionByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getMailBallotRejectionByRegion(stateId);
	}

	/**
	 * GUI-11: Get average equipment age for all states
	 * 
	 * Endpoint: GET /api/facts/states/equipment-age
	 * 
	 * Returns average age of voting equipment for all states for displaying on a
	 * choropleth map on the splash page. The choropleth bins show age from 1 year
	 * through 10 years, with an additional category for older than 10 years.
	 * 
	 * Colors should be in the same hue with saturation increasing with older devices.
	 * 
	 * @return List of AverageEquipmentAgeByStateDto with state ID and average age
	 */
	@GetMapping("/states/equipment-age")
	public List<AverageEquipmentAgeByStateDto> getAllAverageEquipmentAge() {
		return stateFactsService.getAllAverageEquipmentAge();
	}

	/**
	 * GUI-12: Get voting equipment totals by category for all states
	 * 
	 * Endpoint: GET /api/facts/states/equipment-totals
	 * 
	 * Returns equipment counts by category (DRE no VVPAT, DRE with VVPAT, ballot marking
	 * device, scanner) for all states. Used to display a table on the splash page where
	 * each row represents a state and each column shows the count of devices in that category.
	 * 
	 * This allows users to compare equipment types across states and identify which states
	 * use which types of voting equipment.
	 * 
	 * @return List of StateEquipmentTotalDto with state names and equipment counts by category
	 */
	@GetMapping("/states/equipment-totals")
	public List<StateEquipmentTotalDto> getAllStateEquipmentTotals() {
		return stateFactsService.getAllStateEquipmentTotals();
	}

	/**
	 * GUI-24: Get drop box voting bubble chart data by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/dropbox-voting
	 * 
	 * Returns drop box voting metrics broken down by EAVS geographic unit for displaying
	 * in a bubble chart. Each bubble represents an EAVS geographic unit with:
	 * - Color determined by dominant political party (red or blue)
	 * - X-axis position: percentage of Republican votes
	 * - Y-axis position: percentage of drop box voting
	 * 
	 * This data allows visualization of the relationship between political party preference
	 * and drop box voting usage across regions within Republican-dominated and Democratic-
	 * dominated states.
	 * 
	 * Note: Returns all regional dropbox entries for the specified state at once.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of DropboxVotingByRegionDto with region names and voting metrics
	 */
	@GetMapping("/states/{stateId}/regions/dropbox-voting")
	public List<DropboxVotingByRegionDto> getDropboxVotingByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getDropboxVotingByRegion(stateId);
	}

	/**
	 * GUI-23: Get early voting data for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/early-voting
	 * 
	 * Returns comprehensive early voting data for comparing Republican and Democratic states
	 * (Iowa and Illinois). The data includes:
	 * - Raw counts for each early voting method
	 * - Total votes cast (from vote_turnout_by_state table)
	 * - Total early votes (calculated sum of all early voting methods)
	 * - Percentage for each early voting method relative to total votes cast
	 * 
	 * Early voting methods included:
	 * - In-person early voting
	 * - Mail/absentee ballots
	 * - Drop box voting
	 * - UOCAVA (military and overseas) ballots
	 * - Provisional ballots counted
	 * 
	 * All percentages are calculated on-the-fly as:
	 * (method_count / total_votes_cast) × 100
	 * 
	 * @param stateId The state identifier (Iowa or Illinois for comparison)
	 * @return StateEarlyVotingDto with counts and percentages, 404 if not found
	 */
	@GetMapping("/states/{stateId}/early-voting")
	public ResponseEntity<StateEarlyVotingDto> getEarlyVoting(@PathVariable Integer stateId) {
		return stateFactsService.getEarlyVoting(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-15: Get state comparison data for Political Party states
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/comparison
	 * 
	 * Returns comprehensive comparison data for Republican and Democratic states (Illinois and Iowa).
	 * The data includes voting policies, registration requirements, and voting method percentages
	 * to enable side-by-side comparison of how different state policies impact voter participation.
	 * 
	 * Data includes:
	 * - Felony voting rights policies
	 * - Early voting period length
	 * - Voter ID requirements
	 * - Same-day registration availability
	 * - Absentee ballot request deadlines
	 * - Automatic voter registration status
	 * - No-excuse absentee voting availability
	 * - Percentage of mail ballots
	 * - Percentage of drop box ballots
	 * - Voter turnout rate
	 * 
	 * @param stateId The state identifier (Illinois or Iowa for Political Party comparison)
	 * @return StateComparisonDto with all comparison metrics, 404 if not found
	 */
	@GetMapping("/states/{stateId}/comparison")
	public ResponseEntity<StateComparisonDto> getStateComparison(@PathVariable Integer stateId) {
		return stateFactsService.getStateComparison(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-25: Get equipment quality vs rejected ballots bubble chart data by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/equipment-quality-rejection
	 * 
	 * Returns equipment quality and rejected ballot data broken down by EAVS geographic unit
	 * for displaying in a bubble chart. Each bubble represents an EAVS geographic unit with:
	 * - X-axis position: Equipment quality score (0-100 scale)
	 * - Y-axis position: Percentage of rejected ballots
	 * 
	 * This visualization helps identify correlation between voting equipment quality and
	 * ballot rejection rates across different regions within a state.
	 * 
	 * The percentage of rejected ballots is calculated as:
	 * (Mail-in rejected + Provisional rejected + UOCAVA rejected) / Total ballots cast
	 * 
	 * Note: Returns all regional equipment quality and rejection entries for the specified state.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of EquipmentQualityRejectionByRegionDto with region names, quality scores, and rejection percentages
	 */
	@GetMapping("/states/{stateId}/regions/equipment-quality-rejection")
	public List<EquipmentQualityRejectionByRegionDto> getEquipmentQualityRejectionByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getEquipmentQualityRejectionByRegion(stateId);
	}

	/**
	 * GUI-10: Get display type of voting equipment by region for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/display-equipment-types
	 * 
	 * Returns the types of voting equipment used in each EAVS geographic unit for displaying
	 * in a data table. Each row represents an EAVS geographic unit (county or equivalent) with:
	 * - regionName: Name of the region/county
	 * - typeOfEquipmentUsed: Array of equipment type strings (e.g., ["Scanner", "Ballot Marking Device"])
	 * 
	 * Possible equipment types include:
	 * - Scanner
	 * - Ballot Marking Device
	 * - DRE with VVPAT
	 * - DRE without VVPAT
	 * 
	 * This data allows users to see what types of voting equipment are deployed in each region
	 * and identify regions that may need equipment upgrades or standardization.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of DisplayTypeOfVotingEquipmentByRegionDto with region names and equipment type arrays
	 */
	@GetMapping("/states/{stateId}/regions/display-equipment-types")
	public List<DisplayTypeOfVotingEquipmentByRegionDto> getDisplayTypeOfVotingEquipmentByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getDisplayTypeOfVotingEquipmentByRegion(stateId);
	}

	/**
	 * Get voter registration summary for all regions in a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/voter-registration-summary
	 * 
	 * Returns voter registration data with party breakdown and completeness metrics for each region.
	 * Each row represents an EAVS geographic unit (county or equivalent) with:
	 * - regionId: The EAVS region identifier
	 * - regionName: Name of the county/region
	 * - geoId: County FIPS code
	 * - totalNumRegisteredVoters: Total registered voters
	 * - totalNumRepublican: Number of Republican registered voters
	 * - totalNumDemocratic: Number of Democrat registered voters
	 * - totalNumUnaffiliated: Number of unaffiliated/other registered voters
	 * - totalCvap: Total Citizen Voting Age Population
	 * - registeredVoterPercentage: Percentage of CVAP that is registered
	 * - completeness: Data completeness metric (0.0 to 1.0)
	 * - contactCompleteness: Contact information completeness metric (0.0 to 1.0)
	 * 
	 * Use this for analyzing voter registration patterns and data quality by region.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of VoterRegistrationSummaryByRegionDto with registration data and metrics
	 */
	@GetMapping("/states/{stateId}/regions/voter-registration-summary")
	public List<VoterRegistrationSummaryByRegionDto> getVoterRegistrationSummaryByRegion(@PathVariable Integer stateId) {
		return regionFactsService.getVoterRegistrationSummaryByRegion(stateId);
	}

	/**
	 * GUI-5, GUI-7, GUI-8, GUI-9, GUI-10: Get county choropleth info for all regions in a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/regions/county-choropleth-info
	 * 
	 * Returns aggregated county-level data for choropleth map visualization.
	 * For each county (region) in the state, provides:
	 * - County name
	 * - Total provisional ballots (from provisional_by_region)
	 * - Active voter percentage (from active_voters_by_region)
	 * - Pollbook deletion percentage (from poll_book_deletion_by_region)
	 * - Mail ballot rejection percentage (from mail_ballot_rejection_by_region)
	 * - Type of voting equipment used (from display_type_of_voting_equipment_by_region)
	 * 
	 * NOTE: If active_voters_by_region table is not seeded or has no record for a region,
	 * active voter percentage will default to 0.0
	 * 
	 * @param stateId The state identifier
	 * @return List of county choropleth info DTOs for all regions in the state
	 */
	@GetMapping("/states/{stateId}/regions/county-choropleth-info")
	public List<CountyChoroplethInfoDto> getCountyChoroplethInfo(@PathVariable Integer stateId) {
		return regionFactsService.getCountyChoroplethInfo(stateId);
	}

	/**
	 * GUI-6: Get equipment summary for a specific state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/equipment-summary
	 * 
	 * Returns comprehensive voting equipment summary for a state, including details about
	 * each unique voting device (manufacturer and model) used in that state. Data includes:
	 * - Equipment specifications (manufacturer, model name, type, description)
	 * - Current state inventory (quantity, age)
	 * - Technical details (operating system, certification level)
	 * - Performance metrics (scanning rate, error rate, reliability, quality)
	 * 
	 * The data is displayed in a table on the state detail page.
	 * 
	 * Data Source: Denormalized equipment_summary_by_state table
	 * - All equipment details stored directly in the table
	 * - Matches: 6_Equipment_Summary_by_State_with_short_description.csv
	 * - Ordered by manufacturer and model name
	 * 
	 * Certification categories include:
	 * - VVSG 2.0 certified
	 * - VVSG 2.0 applied
	 * - VVSG 1.1 certified
	 * - VVSG 1.0 certified
	 * - Not certified
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return List of EquipmentSummaryByStateDto with equipment details ordered by manufacturer and model
	 */
	@GetMapping("/states/{stateId}/equipment-summary")
	public List<EquipmentSummaryByStateDto> getEquipmentSummaryByState(@PathVariable Integer stateId) {
		return stateFactsService.getEquipmentSummaryByState(stateId);
	}

	/**
	 * GUI-14: Get voting equipment history for a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/equipment-history
	 * 
	 * Returns equipment counts by category across federal election years (2016-2024).
	 * Used to display bar graphs showing equipment trends over time for a specific state.
	 * Each bar graph shows one equipment category with bars for each election year.
	 * 
	 * Equipment categories:
	 * - DRE without VVPAT (Direct Recording Electronic without paper trail)
	 * - DRE with VVPAT (Direct Recording Electronic with paper trail)
	 * - Ballot Marking Device
	 * - Scanner (Optical scan)
	 * 
	 * Years covered: 2016, 2018, 2020, 2022, 2024 (federal election years)
	 * 
	 * Data transformation:
	 * - Database stores: 5 rows (one per year) with normalized structure
	 * - Response returns: 1 object with 20 fields (pivoted: years as columns)
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return VotingEquipmentHistoryByStateDto with equipment counts by year, 404 if not found
	 */
	@GetMapping("/states/{stateId}/equipment-history")
	public ResponseEntity<VotingEquipmentHistoryByStateDto> getEquipmentHistoryByState(@PathVariable Integer stateId) {
		return stateFactsService.getEquipmentHistoryByState(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * GUI-27: Get Gingles chart data for precincts in a state
	 * 
	 * Endpoint: GET /api/facts/states/{stateId}/precincts/gingles-chart
	 * 
	 * Returns Gingles chart data for all precincts in the specified state (Alabama only).
	 * The Gingles chart displays two bubbles for each precinct:
	 * - One bubble for Democratic vote percentage (blue)
	 * - One bubble for Republican vote percentage (red)
	 * 
	 * X-axis: Percentage of a selected demographic group (white, Hispanic, African American)
	 * Y-axis: Percentage of Democratic or Republican votes
	 * 
	 * This visualization helps identify racially polarized voting patterns as required
	 * for Voting Rights Act preclearance analysis.
	 * 
	 * @param stateId The state identifier (should be Alabama for preclearance state)
	 * @return List of GinglesChartPrecinctDto with demographic and voting percentages
	 */
	@GetMapping("/states/{stateId}/precincts/gingles-chart")
	public List<GinglesChartPrecinctDto> getGinglesChartData(@PathVariable Integer stateId) {
		return precinctFactsService.getGinglesChartData(stateId);
	}
}


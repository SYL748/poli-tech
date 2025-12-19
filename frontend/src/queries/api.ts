import type { AverageStateEquipmentAge, ChoroplethData, CVAPPercentage, DropBoxVotingBubleChart, EAVSRegisteredTrends, EIRejectedBallot, EquipmentQualityVSRejectedBallot, EquipmentSummary, GingleChart, MailBallotRejectionRegionSummary, MailBallotRejectionSummary, PagedVotersResponse, PoliticalPartyEarlyVotingComparison, PoliticalPartyStatesComparison, PollBookDeletionRegionSummary, PollBookDeletionSummary, ProvisionalRegionSummary, ProvisionalSummary, Quality, RegistrationAndTurnoutComparison, RegressionCoefficientRow, StateCenter, VoterRegionSummary, VoterRegistrationRegionRow, VoterSummary, VotingEquipmentHistory, VotingEquipmentRecord, VotingEquipmentTotalsAllStates } from "../utils/Types";
import API from "./apiClient";
import type { FeatureCollection } from "geojson";

// Gui-2 Quality Score
export const getQualityScore = async (stateId: string): Promise<Quality> =>
    API.get(`/api/facts/states/${stateId}/data-quality`)
// geo
// Choropleth Data
export const getChoroplethData = async (stateId: string): Promise<ChoroplethData[]> =>
    API.get(`/api/facts/states/${stateId}/regions/county-choropleth-info`);
// Gui-1 US States GeoJSON
export const getUsStateGeoJSON = async (): Promise<FeatureCollection> =>
    API.get('/api/geo/states');
// Gui-2 US Counties GeoJSON
export const getUsCountyGeoJSON = async (stateId: string): Promise<FeatureCollection> =>
    API.get(`/api/geo/states/${stateId}/counties`);
// Gui-20 State Center
export const getStateCenter = async (stateId: string): Promise<StateCenter> =>
    API.get(`/api/geo/state-centers/${stateId}`);

//Gui-20 State GeoJson
export const getStateGeoJSON = async (stateId: string): Promise<FeatureCollection> =>
    API.get(`/api/geo/states/${stateId}`);


// navbar
// Gui-11 Average Equipment Age All States
export const getAverageStateEquipmentAge = async (): Promise<AverageStateEquipmentAge[]> =>
    API.get('/api/facts/states/equipment-age');
// Gui-12 Voting Equipment Totals All States
export const getVotingEquipmentTotalsAllStates = async (): Promise<VotingEquipmentTotalsAllStates[]> =>
    API.get('/api/facts/states/equipment-totals');
// Gui-13 National Voting Equipment Overview
export const getVotingEquipmentOverview = async (): Promise<VotingEquipmentRecord[]> =>
    API.get('/api/equipment/overview');
// Gui-14 State Voting Equipment History
export const getVotingEquipmentHistory = async (stateId: string = "12"): Promise<VotingEquipmentHistory> =>
    API.get(`/api/facts/states/${stateId}/equipment-history`);
// Gui-15 Political Party State Comparison
export const getPoliticalPartyStatesComparison = async (stateId: string = "12"): Promise<PoliticalPartyStatesComparison> =>
    API.get(`/api/facts/states/${stateId}/comparison`);
// Gui-21 Gui-22 Registration and Turnout Comparison
export const getRegistrationAndTurnoutComparison = async (stateId: string = "12"): Promise<RegistrationAndTurnoutComparison> =>
    API.get(`/api/facts/states/${stateId}/registration-metrics`);
// Gui-23 Political Party Early Voting Comparison
export const getPoliticalPartyEarlyVotingComparison = async (stateId: string = "12"): Promise<PoliticalPartyEarlyVotingComparison> =>
    API.get(`/api/facts/states/${stateId}/early-voting`);




// stateDetails
export const getCVAPPercentage = async (stateId: string = "12"): Promise<CVAPPercentage> =>
    API.get(`/api/facts/states/${stateId}/political-party-cvap`);
// Gui-3 Provisional Summary
export const getProvisionalSummary = async (stateId: string = "12"): Promise<ProvisionalSummary> =>
    API.get(`/api/facts/states/${stateId}/provisional`);
// Gui-4 Provisional Region Summary
export const getProvisionalRegionSummary = async (stateId: string = "12"): Promise<ProvisionalRegionSummary[]> =>
    API.get(`/api/facts/states/${stateId}/regions/provisional`);
// Gui-6 Equipment Summary for Specific State
export const getStateEquipmentTable = async (stateId: string = "12"): Promise<VotingEquipmentRecord[]> =>
    API.get(`/api/facts/states/${stateId}/equipment-summary`)
// Gui-7 Active Voter State Summary
export const getVoterSummary = async (stateId: string = "12"): Promise<VoterSummary> =>
    API.get(`/api/facts/states/${stateId}/active-voters`);
// Gui-7 Active Voter Region Summary
export const getVoterRegionSummary = async (stateId: string = "12"): Promise<VoterRegionSummary[]> =>
    API.get(`/api/facts/states/${stateId}/regions/active-voters`);
// Gui-8 Poll Book Deletion State Summary
export const getPollBookDeletionSummary = async (stateId: string = "12"): Promise<PollBookDeletionSummary> =>
    API.get(`/api/facts/states/${stateId}/pollbook-deletions`);
// Gui-8 Poll Book Deletion Region Summary
export const getPollBookDeletionRegionSummary = async (stateId: string = "12"): Promise<PollBookDeletionRegionSummary[]> =>
    API.get(`/api/facts/states/${stateId}/regions/pollbook-deletions`);
// Gui-9 Mail Ballot Rejection State Summary
export const getMailBallotRejectionSummary = async (stateId: string = "12"): Promise<MailBallotRejectionSummary> =>
    API.get(`/api/facts/states/${stateId}/mail-ballot-rejections`);
// Gui-9 Mail Ballot Rejection Region Summary
export const getMailBallotRejectionRegionSummary = async (stateId: string = "12"): Promise<MailBallotRejectionRegionSummary[]> =>
    API.get(`/api/facts/states/${stateId}/regions/mail-ballot-rejections`);
// Gui-24 DropBox Voting Bubble Chart
export const getMailInBubbleChart = async (stateId: string = "12"): Promise<DropBoxVotingBubleChart> =>
    API.get(`/api/facts/states/${stateId}/regions/dropbox-voting`);
// Gui-25 voting equipment quality and rejected ballots bubble chart
export const getVotingVSRejectBubbleChart = async (stateId: string = "12"): Promise<EquipmentQualityVSRejectedBallot> =>
    API.get(`/api/facts/states/${stateId}/regions/equipment-quality-rejection`);
// Gui-27 Gingle's Chart
export const getGingleChart = async (stateId: string = "48"): Promise<GingleChart> =>
    API.get(`/api/facts/states/${stateId}/precincts/gingles-chart`)
export const getCoefficient = async (stateId: string = "48"): Promise<RegressionCoefficientRow> =>
    API.get(`/api/facts/states/${stateId}/precinct-demographic-regression`)
// Gui-28 Ecological Inference of Equipment
export const getEIEquipment = async (stateId: string = "48"): Promise<EIRejectedBallot> =>
    API.get(`/api/facts/states/${stateId}/equipment-ei-curve`)
// Gui-29 EI Rejected Ballot
export const getEIRejectBallot = async (stateId: string = "48"): Promise<EIRejectedBallot> =>
    API.get(`/api/facts/states/${stateId}/rejected-ballot-ei-curve`)

// Gui-30 EAVS Registered Voter Trends (temporary JSON loading, will be API later)
import eavsRegisteredTrendsData from "../test-json-files/eavs_registered_trends_by_state.json";
export const getEAVSRegisteredTrends = async (stateId: string): Promise<EAVSRegisteredTrends | null> => {
    const stateIdNum = parseInt(stateId, 10);
    const data = eavsRegisteredTrendsData as EAVSRegisteredTrends[];
    return data.find(s => s.state_id === stateIdNum) || null;
}

// Gui-19
export const getVotersByRegion = async (
    regionId: number | string,
    page: number = 0
): Promise<PagedVotersResponse> => {
    return API.get(`/api/voters/regions/${regionId}?page=${page}`);
};

export const getRepublicanVotersByRegion = async (
    regionId: number | string,
    page: number = 0
): Promise<PagedVotersResponse> => {
    return API.get(`/api/voters/regions/${regionId}?page=${page}&party=Republican`);
};

export const getDemocratVotersByRegion = async (
    regionId: number | string,
    page: number = 0
): Promise<PagedVotersResponse> => {
    return API.get(`/api/voters/regions/${regionId}?page=${page}&party=Democrat`);
};
export const getVoterRegistrationRegionSum = async (stateId: string = "12"): Promise<VoterRegistrationRegionRow[]> =>
    API.get(`/api/facts/states/${stateId}/regions/voter-registration-summary`)
export type ChoroplethData = {
    countyName: string;
    totalProvisional: number;
    activeVoterPercentage: number;
    pollbookDeletionPercentage: number;
    rejectionPercentage: number;
    typeOfEquipmentUsed: string;
}
// Gui-2 Quality Score
export type Quality = {
    stateId: number,
    dataQualityScore: number
}
// Gui-2 CVAP percentage
export type CVAPPercentage = {
    stateId: number
    name: string
    cvapPercent: number
}
// Gui-3 Provisional State Summary
export type ProvisionalSummary = {
    "stateId": number;
    "voterNotOnList": number;
    "voterLackedId": number;
    "electionOfficialChallengedEligibility": number;
    "anotherPersonChallengedEligibility": number;
    "voterNotResident": number;
    "voterRegistrationNotUpdated": number;
    "voterDidNotSurrenderMailBallot": number;
    "judgeExtendedVotingHours": number;
    "voterUsedSdr": number;
    "otherReasons": number;
}
// Gui-4 Provisional Region Summary
export type ProvisionalRegionSummary = {
    "regionName": string;
    "voterNotOnList": number;
    "voterLackedId": number;
    "electionOfficialChallengedEligibility": number;
    "anotherPersonChallengedEligibility": number;
    "voterNotResident": number;
    "voterRegistrationNotUpdated": number;
    "voterDidNotSurrenderMailBallot": number;
    "judgeExtendedVotingHours": number;
    "voterUsedSdr": number;
    "otherReason": number;
}
// Gui-6 Equipment Summary for Specific State
export type EquipmentSummary = {
    "stateId": string;
    "make": string;
    "model": string;
    "quantity": string;
    "equipmentType": string;
    "age": number;
    "operatingSystem": string;
    "description": string;
    "cert": string;
    "scanRate": number;
    "errorRate": number;
    "reliability": number;
    "discontinued": boolean;
}
// Gui-7 Active Voter State Summary
export type VoterSummary = {
    "stateId": number;
    "activeRegisteredVoters": number;
    "inactiveRegisteredVoters": number;
    "totalRegisteredVoters": number;
}
// Gui-7 Active Voter Region Summary
export type VoterRegionSummary = {
    "regionName": string;
    "activeRegisteredVoter": number;
    "inactiveRegisteredVoter": number;
    "totalRegisteredVoter": number;
}
// Gui-8 Poll Book Deletion State Summary
export type PollBookDeletionSummary = {
    "stateId": number;
    "moved": number;
    "death": number;
    "felony": number;
    "failResponse": number;
    "incompetentToVote": number;
    "voterRequest": number;
    "duplicateRecord": number;
}
//Gui-8 Poll Book Deletion Region Summary
export type PollBookDeletionRegionSummary = {
    "regionName": string;
    "moved": number;
    "death": number;
    "felony": number;
    "failResponse": number;
    "incompetentToVote": number;
    "voterRequest": number;
    "duplicateRecord": number;
};
// Gui-9 Mail Ballot Rejection State Summary
export type MailBallotRejectionSummary = {
    "stateId": number;
    "late": number;
    "missingVoterSignature": number;
    "missingWitnessSignature": number;
    "nonMatchingVoterSignature": number;
    "unofficialEnvelope": number;
    "ballotMissingFromEnvelope": number;
    "noSecrecyEnvelope": number;
    "multipleBallotsInOneEnvelope": number;
    "envelopeNotSealed": number;
    "noPostmark": number;
    "noResidentAddressOnEnvelope": number;
    "voterDeceased": number;
    "voterAlreadyVoted": number;
    "missingDocumentation": number;
    "voterNotEligible": number;
    "noBallotApplication": number;
}
// Gui-9 Mail Ballot Rejection Region Summary
export type MailBallotRejectionRegionSummary = {
    "regionName": string;
    "late": number;
    "missingVoterSignature": number;
    "missingWitnessSignature": number;
    "nonMatchingVoterSignature": number;
    "unofficialEnvelope": number;
    "ballotMissingFromEnvelope": number;
    "noSecrecyEnvelope": number;
    "multipleBallotsInOneEnvelope": number;
    "envelopeNotSealed": number;
    "noPostmark": number;
    "noResidentAddressOnEnvelope": number;
    "voterDeceased": number;
    "voterAlreadyVoted": number;
    "missingDocumentation": number;
    "voterNotEligible": number;
    "noBallotApplication": number;
}
// Gui-11 Average Equipment Age All States
export type AverageStateEquipmentAge = {
    "stateId": number;
    "averageAgeOfEquipment": number;
}
// Gui-12 Voting Equipment Totals All States
export type VotingEquipmentTotalsAllStates = {
    "stateId": number;
    "stateName": string;
    "dreNoVvpat": number;
    "dreWithVvpat": number;
    "ballotMarkingDevice": number;
    "scanner": number;
}
// Gui-13 National Voting Equipment Overview
export type VotingEquipmentRecord = {
    stateId: string;
    manufacturer: string;
    modelName: string;
    quantity: number;
    equipmentType: string;

    age: number | null;
    os: string | null;
    certificationLevel: string;

    scanningRate: number | string | null;
    errorRate: string | null;

    reliability: number;
    quality: number;

    shortDescription: string;

    discontinued?: boolean; // If true, equipment is no longer available from manufacturer

}

// Gui-14 State Voting Equipment History
type VotingEquipmentYear = {
    "year": number;
    "dreNoVvpat": number;
    "dreWithVvpat": number;
    "ballotMarkingDevice": number;
    "scanner": number;
};
export type VotingEquipmentHistory = {
    "stateId": string;
    "years": [
        VotingEquipmentYear,
        VotingEquipmentYear,
        VotingEquipmentYear,
        VotingEquipmentYear,
        VotingEquipmentYear
    ];
}
// Gui-15 Political Party State Comparison
export type PoliticalPartyStatesComparison = {
    "stateId": string;
    "felonyVotingRights": string;
    "earlyVotingPeriodDays": number;
    "voterIdRequirement": string;
    "sameDayRegistration": string;
    "absenteeRequestDeadline": string;
    "automaticVoterRegistration": string;
    "noExcuseAbsenteeVoting": boolean;
    "percentMailBallot": number;
    "percentDropbox": number;
    "turnoutRate": number;
}
// Gui-20 State Center
export type StateCenter = {
    "stateId": number;
    "name": string;
    "lon": number;
    "lat": number;
    "zoom": number;
}
// Gui-21 Gui-22 Registration and Turnout Comparison
export type RegistrationAndTurnoutComparison = {
    "stateId": number;
    "totalVotesCast": number;
    "totalCvap": number;
    "registrationRate": number;
    "turnoutRate": number;
    "totalRegisteredVoters": number;
    "activeRegisteredVoters": number;
}
// Gui-23 Political Party Early Voting Comparison
export type PoliticalPartyEarlyVotingComparison = {
    "stateId": string;
    "totalVotesCast": number;
    "inPersonEarly": number;
    "mailAccepted": number;
    "dropboxAccepted": number;
    "uocavaAccepted": number;
    "provisionalCounted": number;
    "totalEarly": number;
    "percentInPersonEarly": number;
    "percentMail": number;
    "percentDropbox": number;
    "percentUocava": number;
    "percentProvisional": number;
    "percentTotalEarly": number;
}
// Gui-24 DropBox Voting Bubble Chart
export type DropBoxVotingBubleChart = {
    "regionId": number;
    "regionName": string;
    "republicanVotes2024": number;
    "democratVotes2024": number;
    "totalPresidentialVotes2024": number;
    "mailInBallots": number;
    "totalBallots": number;
    "percentRepublican": number;
    "percentMailInBallots": number;
}
// Gui-25 voting equipment quality and rejected ballots bubble chart
export type EquipmentQualityVSRejectedBallot = {
    regionId: number;
    regionName: string;
    equipmentQualityScore: number;
    percentRejectedBallots: number;
    totalBallots: number;
};
// Gui-27 Gingle's Chart
export type PrecinctPartyResult = {
    county: string
    countyFips: string
    precinctName: string
    precinctId: string
    totalVotes: number
    whitePct: number
    hispanicPct: number
    africanAmericanPct: number
    party: "Democratic" | "Republican" | string
    votePercent: number
    votes: number
}
export type GingleChart = PrecinctPartyResult[]

type Party = "Democratic" | "Republican"
type Demographic = "White" | "Hispanic" | "African American"

export type RegressionCoefficientRow = {
    state: string
    party: Party
    demographic: Demographic
    coefA: number
    coefB: number
    coefC: number
}

// Gui-28 Gui-29 Ecological Inference of Equipment
type EIPoint = {
    equipmentQuality: number;
    probabilityDensity: number;
};
type DemographicGroupStrict = "White" | "Hispanic" | "African American";
export type EIRejectedBallot = {
    stateId: number;
    demographicData: Record<DemographicGroupStrict, EIPoint[]>;
}

// Gui-30 EAVS Registered Voter Trends
export type EAVSRegion = {
    region_id: string;
    region_name: string;
    a1a_2016: number;
    a1a_2020: number;
    a1a_2024: number;
}

export type EAVSRegisteredTrends = {
    state_id: number;
    state_name: string;
    x_label: string;
    y_label: string;
    line_labels: string[];
    region_count: number;
    regions: EAVSRegion[];
}

export type Parties = "Republican" | "Democrat" | "Other";

export interface Voter {
    id: number;
    regionId: number;
    regionName: string;
    nameFull: string;
    party: Parties;
}

export interface PagedVotersResponse {
    voters: Voter[];
    currentPage: number;
    totalPages: number;
    totalElements: number;
    hasNext: boolean;
    hasPrevious: boolean;
    pageSize: number;
}
export type VoterRegistrationRegionRow = {
    regionId: number;
    regionName: string;
    geoId: number;

    totalNumRegisteredVoters: number;
    totalNumRepublican: number;
    totalNumDemocratic: number;
    totalNumUnaffiliated: number;

    totalCvap: number;
    registeredVoterPercentage: number;

    completeness: number;
    contactCompleteness: number;
};


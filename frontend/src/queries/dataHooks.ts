import { useQuery } from "@tanstack/react-query"
import { getAverageStateEquipmentAge, getChoroplethData, getCoefficient, getCVAPPercentage, getDemocratVotersByRegion, getEAVSRegisteredTrends, getEIEquipment, getEIRejectBallot, getGingleChart, getMailBallotRejectionRegionSummary, getMailBallotRejectionSummary, getMailInBubbleChart, getPoliticalPartyEarlyVotingComparison, getPoliticalPartyStatesComparison, getPollBookDeletionRegionSummary, getPollBookDeletionSummary, getProvisionalRegionSummary, getProvisionalSummary, getQualityScore, getRegistrationAndTurnoutComparison, getRepublicanVotersByRegion, getStateCenter, getStateEquipmentTable, getStateGeoJSON, getUsCountyGeoJSON, getUsStateGeoJSON, getVoterRegionSummary, getVoterRegistrationRegionSum, getVotersByRegion, getVoterSummary, getVotingEquipmentHistory, getVotingEquipmentOverview, getVotingEquipmentTotalsAllStates, getVotingVSRejectBubbleChart } from "./api"


export const useQualityScore = (stateId: string) => {
    const {
        data: quality,
        isLoading: isQualityLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['Quality', stateId],
        queryFn: () => getQualityScore(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { quality, isQualityLoading };
}
// GEO
// Choropleth Data
export const useChoroplethData = (stateId: string) => {
    const {
        data: choroplethData,
        isLoading: isChoroplethDataLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['ChoroplethData', stateId],
        queryFn: () => getChoroplethData(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { choroplethData, isChoroplethDataLoading };
}
// Gui-1 US States GeoJSON
export const useUsStateGeoJSON = () => {
    const {
        data: states,
        isLoading: isStateLoading
    } = useQuery({
        queryKey: ["UsStateGeoJSON"],
        queryFn: getUsStateGeoJSON,
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    })
    return { states, isStateLoading }
}
// Gui-2 US Counties GeoJSON
export const useUsCountyGeoJSON = (stateId: string) => {
    const {
        data: counties,
        isLoading: isCountyLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['UsCountyGeoJSON', stateId],
        queryFn: () => getUsCountyGeoJSON(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { counties, isCountyLoading };
}
// Gui-20 State Center
export const useStateCenter = (stateId: string) => {
    const {
        data: stateCenter,
        isLoading: isStateCenterLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['StateCenter', stateId],
        queryFn: () => getStateCenter(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { stateCenter, isStateCenterLoading };
}
//Gui-20 State GeoJson
export const useStateGeoJSON = (stateId: string) => {
    const {
        data: stateGeoJSON,
        isLoading: isStateGeoJSONLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['StateGeoJSON', stateId],
        queryFn: () => getStateGeoJSON(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { stateGeoJSON, isStateGeoJSONLoading };
}



// NAV
// Gui-11 Average Equipment Age All States
export const useAverageStateEquipmentAge = () => {
    const {
        data: averageStateEquipmentAge,
        isLoading: isAverageStateEquipmentAgeLoading
    } = useQuery({
        queryKey: ["AverageStateEquipmentAge"],
        queryFn: getAverageStateEquipmentAge,
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    })
    return { averageStateEquipmentAge, isAverageStateEquipmentAgeLoading }
}
// Gui-12 Voting Equipment Totals All States
export const useVotingEquipmentTotalsAllStates = () => {
    const {
        data: votingEquipmentTotalsAllStates,
        isLoading: isVotingEquipmentTotalsAllStatesLoading
    } = useQuery({
        queryKey: ["VotingEquipmentTotalsAllStates"],
        queryFn: getVotingEquipmentTotalsAllStates,
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    })
    return { votingEquipmentTotalsAllStates, isVotingEquipmentTotalsAllStatesLoading }
}
// Gui-13 National Voting Equipment Overview
export const useVotingEquipmentOverview = () => {
    const {
        data: votingEquipmentOverview,
        isLoading: isVotingEquipmentOverviewLoading
    } = useQuery({
        queryKey: ["VotingEquipmentOverview"],
        queryFn: getVotingEquipmentOverview,
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    })
    return { votingEquipmentOverview, isVotingEquipmentOverviewLoading }
}
// Gui-14 State Voting Equipment History
export const useVotingEquipmentHistory = (stateId: string = "12") => {
    const {
        data: votingEquipmentHistory,
        isLoading: isVotingEquipmentHistoryLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['VotingEquipmentHistory', stateId],
        queryFn: () => getVotingEquipmentHistory(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { votingEquipmentHistory, isVotingEquipmentHistoryLoading };
}
// Gui-15 Political Party State Comparison
export const usePoliticalPartyStatesComparison = (stateId: string = "12") => {
    const {
        data: politicalPartyStatesComparison,
        isLoading: isPoliticalPartyStatesComparisonLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['PoliticalPartyStatesComparison', stateId],
        queryFn: () => getPoliticalPartyStatesComparison(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { politicalPartyStatesComparison, isPoliticalPartyStatesComparisonLoading };
}
// Gui-21 Gui-22 Registration and Turnout Comparison
export const useRegistrationAndTurnoutComparison = (stateId: string = "12") => {
    const {
        data: registrationAndTurnoutComparison,
        isLoading: isRegistrationAndTurnoutComparisonLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['RegistrationAndTurnoutComparison', stateId],
        queryFn: () => getRegistrationAndTurnoutComparison(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { registrationAndTurnoutComparison, isRegistrationAndTurnoutComparisonLoading };
}
// Gui-23 Political Party Early Voting Comparison
export const usePoliticalPartyEarlyVotingComparison = (stateId: string = "12") => {
    const {
        data: politicalPartyEarlyVotingComparison,
        isLoading: isPoliticalPartyEarlyVotingComparisonLoading
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['PoliticalPartyEarlyVotingComparison', stateId],
        queryFn: () => getPoliticalPartyEarlyVotingComparison(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { politicalPartyEarlyVotingComparison, isPoliticalPartyEarlyVotingComparisonLoading };
}



// STATEDETAIL
// Gui-2 CVAP Percentage
export const useCVAPPercentage = (stateId: string = "12") => {
    const {
        data: cvapPercentage,
        isLoading: isCVAPPercentageLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['CVAPPercentage', stateId],
        queryFn: () => getCVAPPercentage(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { cvapPercentage, isCVAPPercentageLoading };
}
// Gui-3 Provisional Summary
export const useProvisionalSummary = (stateId: string = "12") => {
    const {
        data: provisionalSummary,
        isLoading: isProvisionalSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['ProvisionalSummary', stateId],
        queryFn: () => getProvisionalSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { provisionalSummary, isProvisionalSummaryLoading };
};

// Gui-4 Provisional Region Summary
export const useProvisionalRegionSummary = (stateId: string = "12") => {
    const {
        data: provisionalRegionSummary,
        isLoading: isProvisionalRegionSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['ProvisionalRegionSummary', stateId],
        queryFn: () => getProvisionalRegionSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { provisionalRegionSummary, isProvisionalRegionSummaryLoading };
}
// Gui-6 Equipment Summary for Specific State
export const useGetStateEquipmentTable = (stateId: string = "12") => {
    const {
        data: StateEquipmentSummaryTable,
        isLoading: isStateEquipmentSummaryTableLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['StateEquipmentSummaryTable', stateId],
        queryFn: () => getStateEquipmentTable(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { StateEquipmentSummaryTable, isStateEquipmentSummaryTableLoading };
}
// Gui-7 Active Voter State Summary
export const useVoterSummary = (stateId: string = "12") => {
    const {
        data: voterSummary,
        isLoading: isVoterSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['VoterSummary', stateId],
        queryFn: () => getVoterSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { voterSummary, isVoterSummaryLoading };
}
//Gui-7 Active Voter Region Summary
export const useVoterRegionSummary = (stateId: string = "12") => {
    const {
        data: voterRegionSummary,
        isLoading: isVoterRegionSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['VoterRegionSummary', stateId],
        queryFn: () => getVoterRegionSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { voterRegionSummary, isVoterRegionSummaryLoading };
}
// Gui-8 Poll Book Deletion State Summary
export const usePollBookDeletionSummary = (stateId: string = "12") => {
    const {
        data: pollBookDeletionSummary,
        isLoading: isPollBookDeletionSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['PollBookDeletionSummary', stateId],
        queryFn: () => getPollBookDeletionSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { pollBookDeletionSummary, isPollBookDeletionSummaryLoading };
}
// Gui-8 Poll Book Deletion Region Summary
export const usePollBookDeletionRegionSummary = (stateId: string = "12") => {
    const {
        data: pollBookDeletionRegionSummary,
        isLoading: isPollBookDeletionRegionSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['PollBookDeletionRegionSummary', stateId],
        queryFn: () => getPollBookDeletionRegionSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { pollBookDeletionRegionSummary, isPollBookDeletionRegionSummaryLoading };
}
// Gui-9 Mail Ballot Rejection State Summary
export const useMailBallotRejectionSummary = (stateId: string = "12") => {
    const {
        data: mailBallotRejectionSummary,
        isLoading: isMailBallotRejectionSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['MailBallotRejectionSummary', stateId],
        queryFn: () => getMailBallotRejectionSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { mailBallotRejectionSummary, isMailBallotRejectionSummaryLoading };
}
// Gui-9 Mail Ballot Rejection Region Summary
export const useMailBallotRejectionRegionSummary = (stateId: string = "12") => {
    const {
        data: mailBallotRejectionRegionSummary,
        isLoading: isMailBallotRejectionRegionSummaryLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['MailBallotRejectionRegionSummary', stateId],
        queryFn: () => getMailBallotRejectionRegionSummary(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { mailBallotRejectionRegionSummary, isMailBallotRejectionRegionSummaryLoading };
}
// Gui-24 DropBox Voting Bubble Chart
export const useDropBoxVotingBubbleChart = (stateId: string = "12") => {
    const {
        data: mailBallotRejectionBubbleChart,
        isLoading: isMailBallotRejectionBubbleChartLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['MailBallotRejectionBubbleChart', stateId],
        queryFn: () => getMailInBubbleChart(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { mailBallotRejectionBubbleChart, isMailBallotRejectionBubbleChartLoading };
}
// Gui-25 voting equipment quality and rejected ballots bubble chart
export const useVotingVSRejectBubbleChart = (stateId: string = "12") => {
    const {
        data: votingVSRejectBubbleChart,
        isLoading: isVotingVSRejectBubbleChartLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['VotingVSRejectBubbleChart', stateId],
        queryFn: () => getVotingVSRejectBubbleChart(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { votingVSRejectBubbleChart, isVotingVSRejectBubbleChartLoading };
}
// Gui-27 Gingle's Chart
export const useGingleChart = (stateId: string = "48") => {
    const {
        data: GingleChart,
        isLoading: isGingleChartLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['GingleChart', stateId],
        queryFn: () => getGingleChart(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { GingleChart, isGingleChartLoading };
}
export const useCoefficient = (stateId: string = "48") => {
    const {
        data: Coefficient,
        isLoading: isCoefficientLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['Coefficient', stateId],
        queryFn: () => getCoefficient(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { Coefficient, isCoefficientLoading };
}
// Gui-28 Ecological Inference of Equipment
export const useEIEquipment = (stateId: string = "48") => {
    const {
        data: EIEquipment,
        isLoading: isEIEquipmentLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['EIEquipment', stateId],
        queryFn: () => getEIEquipment(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { EIEquipment, isEIEquipmentLoading };
}
// Gui-29 EI Rejected Ballot
export const useEIRejectionBallot = (stateId: string = "48") => {
    const {
        data: EIRejction,
        isLoading: isEIRejctionLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['EIRejction', stateId],
        queryFn: () => getEIRejectBallot(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { EIRejction, isEIRejctionLoading };
}

// Gui-30 EAVS Registered Voter Trends
export const useEAVSRegisteredTrends = (stateId: string) => {
    const {
        data: eavsTrends,
        isLoading: isEAVSTrendsLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['EAVSRegisteredTrends', stateId],
        queryFn: () => getEAVSRegisteredTrends(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { eavsTrends, isEAVSTrendsLoading };
}

// Gui-19
export const useVotersByRegion = (regionId: string, page: number = 0) => {
    const enabled = regionId !== "";
    const {
        data: votersPage,
        isLoading: isVotersLoading,
    } = useQuery({
        queryKey: ["votersByRegion", regionId, page],
        queryFn: () => getVotersByRegion(regionId, page),
        enabled,
        staleTime: 5 * 60 * 1000,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false,
    });

    return {
        votersPage,
        isVotersLoading,
    };
};

export const useRepublicanVotersByRegion = (regionId: string, page: number = 0) => {
    const enabled = regionId !== "";

    const {
        data: votersRepPage,
        isLoading: isvotersRepPageLoading,
    } = useQuery({
        queryKey: ["votersRepPage", regionId, page],
        queryFn: () => getRepublicanVotersByRegion(regionId, page),
        enabled,
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false,
    });

    return { votersRepPage, isvotersRepPageLoading };
};

export const useDemocratVotersByRegion = (regionId: string, page: number = 0) => {
    const enabled = regionId !== "";

    const {
        data: votersDemPage,
        isLoading: isvotersDemPageLoading,
    } = useQuery({
        queryKey: ["votersDemPage", regionId, page],
        queryFn: () => getDemocratVotersByRegion(regionId, page),
        enabled,
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false,
    });

    return { votersDemPage, isvotersDemPageLoading };
};

export const useVoterRegistrationRegionSum = (stateId: string = "12") => {
    const {
        data: VoterReistrationRegionSum,
        isLoading: isVoterReistrationRegionSumsLoading,
    } = useQuery({
        enabled: !!stateId,
        queryKey: ['VoterReistrationRegionSum', stateId],
        queryFn: () => getVoterRegistrationRegionSum(stateId),
        staleTime: Infinity,
        retry: false,
        refetchOnWindowFocus: false,
        refetchOnReconnect: false
    });
    return { VoterReistrationRegionSum, isVoterReistrationRegionSumsLoading };
}
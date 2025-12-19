import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Box,
    ToggleButton,
    ToggleButtonGroup,
    Chip,
    CircularProgress
} from '@mui/material';
import { useState } from 'react';
import { useRegistrationAndTurnoutComparison, usePoliticalPartyStatesComparison, usePoliticalPartyEarlyVotingComparison } from '../queries/dataHooks';

const PoliticalPartyStates = () => {
    const [selectedView, setSelectedView] =
        useState<'votingData' | 'rights' | 'earlyVoting'>('votingData');

    // Fetch registration data for Iowa (19), Illinois (17), and Arkansas (5)
    const { registrationAndTurnoutComparison: iowaData, isRegistrationAndTurnoutComparisonLoading: iowaLoading } =
        useRegistrationAndTurnoutComparison('19');
    const { registrationAndTurnoutComparison: illinoisData, isRegistrationAndTurnoutComparisonLoading: illinoisLoading } =
        useRegistrationAndTurnoutComparison('17');
    const { registrationAndTurnoutComparison: arkansasData, isRegistrationAndTurnoutComparisonLoading: arkansasLoading } =
        useRegistrationAndTurnoutComparison('5');

    // Fetch comparison data for Iowa (19), Illinois (17), and Arkansas (5)
    const { politicalPartyStatesComparison: iowaCompData, isPoliticalPartyStatesComparisonLoading: iowaCompLoading } =
        usePoliticalPartyStatesComparison('19');
    const { politicalPartyStatesComparison: illinoisCompData, isPoliticalPartyStatesComparisonLoading: illinoisCompLoading } =
        usePoliticalPartyStatesComparison('17');
    const { politicalPartyStatesComparison: arkansasCompData, isPoliticalPartyStatesComparisonLoading: arkansasCompLoading } =
        usePoliticalPartyStatesComparison('5');

    // Fetch early voting data for Iowa (19), Illinois (17), and Arkansas (5)
    const { politicalPartyEarlyVotingComparison: iowaEarlyData, isPoliticalPartyEarlyVotingComparisonLoading: iowaEarlyLoading } =
        usePoliticalPartyEarlyVotingComparison('19');
    const { politicalPartyEarlyVotingComparison: illinoisEarlyData, isPoliticalPartyEarlyVotingComparisonLoading: illinoisEarlyLoading } =
        usePoliticalPartyEarlyVotingComparison('17');
    const { politicalPartyEarlyVotingComparison: arkansasEarlyData, isPoliticalPartyEarlyVotingComparisonLoading: arkansasEarlyLoading } =
        usePoliticalPartyEarlyVotingComparison('5');

    const votingData = iowaData && illinoisData && arkansasData ? [
        {
            category: "Total Registered Voters",
            iowa: `${iowaData.totalRegisteredVoters.toLocaleString()} voters`,
            illinois: `${illinoisData.totalRegisteredVoters.toLocaleString()} voters`,
            arkansas: `${arkansasData.totalRegisteredVoters.toLocaleString()} voters`
        },
        {
            category: "Registration Rate",
            iowa: `${iowaData.registrationRate.toFixed(2)}% of eligible voters`,
            illinois: `${illinoisData.registrationRate.toFixed(2)}% of eligible voters`,
            arkansas: `${arkansasData.registrationRate.toFixed(2)}% of eligible voters`
        },
        {
            category: "Total Votes Cast",
            iowa: `${iowaData.totalVotesCast.toLocaleString()} votes`,
            illinois: `${illinoisData.totalVotesCast.toLocaleString()} votes`,
            arkansas: `${arkansasData.totalVotesCast.toLocaleString()} votes`
        },
        {
            category: "Turnout Rate",
            iowa: `${iowaData.turnoutRate.toFixed(2)}% of eligible voters`,
            illinois: `${illinoisData.turnoutRate.toFixed(2)}% of eligible voters`,
            arkansas: `${arkansasData.turnoutRate.toFixed(2)}% of eligible voters`
        }
    ] : [];

    const votingRightAndProcedure = iowaCompData && illinoisCompData && arkansasCompData ? [
        {
            category: "Felony Voting Rights",
            iowa: iowaCompData.felonyVotingRights,
            illinois: illinoisCompData.felonyVotingRights,
            arkansas: arkansasCompData.felonyVotingRights
        },
        {
            category: "Percentage of Mail Ballots",
            iowa: `${iowaCompData.percentMailBallot.toFixed(2)}% of ballots cast`,
            illinois: `${illinoisCompData.percentMailBallot.toFixed(2)}% of ballots cast`,
            arkansas: `${arkansasCompData.percentMailBallot.toFixed(2)}% of ballots cast`
        },
        {
            category: "Percentage of Drop Box Ballots",
            iowa: `${iowaCompData.percentDropbox.toFixed(2)}% of ballots cast`,
            illinois: `${illinoisCompData.percentDropbox.toFixed(2)}% of ballots cast`,
            arkansas: `${arkansasCompData.percentDropbox.toFixed(2)}% of ballots cast`
        },
        {
            category: "Turnout Rate",
            iowa: `${iowaCompData.turnoutRate.toFixed(2)}% of eligible voters`,
            illinois: `${illinoisCompData.turnoutRate.toFixed(2)}%`,
            arkansas: `${arkansasCompData.turnoutRate.toFixed(2)}% of eligible voters`
        },
        {
            category: "Early Voting Period",
            iowa: `${iowaCompData.earlyVotingPeriodDays} days`,
            illinois: `${illinoisCompData.earlyVotingPeriodDays} days`,
            arkansas: `${arkansasCompData.earlyVotingPeriodDays} days`
        },
        {
            category: "Voter ID Requirement",
            iowa: iowaCompData.voterIdRequirement,
            illinois: illinoisCompData.voterIdRequirement,
            arkansas: arkansasCompData.voterIdRequirement
        },
        {
            category: "Same Day Registration",
            iowa: iowaCompData.sameDayRegistration === "true" ? "Yes" : "No",
            illinois: illinoisCompData.sameDayRegistration === "true" ? "Yes" : "No",
            arkansas: arkansasCompData.sameDayRegistration === "true" ? "Yes" : "No"
        },
        {
            category: "Absentee Request Deadline",
            iowa: iowaCompData.absenteeRequestDeadline,
            illinois: illinoisCompData.absenteeRequestDeadline,
            arkansas: arkansasCompData.absenteeRequestDeadline
        },
        {
            category: "Automatic Voter Registration",
            iowa: iowaCompData.automaticVoterRegistration === "true" ? "Yes" : "No",
            illinois: illinoisCompData.automaticVoterRegistration === "true" ? "Yes" : "No",
            arkansas: arkansasCompData.automaticVoterRegistration === "true" ? "Yes" : "No"
        },
        {
            category: "No Excuse Absentee Voting",
            iowa: iowaCompData.noExcuseAbsenteeVoting ? "Yes" : "No",
            illinois: illinoisCompData.noExcuseAbsenteeVoting ? "Yes" : "No",
            arkansas: arkansasCompData.noExcuseAbsenteeVoting ? "Yes" : "No"
        }
    ] : [];

    const earlyVotingStates = iowaEarlyData && illinoisEarlyData && arkansasEarlyData ? [
        {
            category: "Total Votes Cast",
            iowa: `${iowaEarlyData.totalVotesCast.toLocaleString()} votes`,
            illinois: `${illinoisEarlyData.totalVotesCast.toLocaleString()} votes`,
            arkansas: `${arkansasEarlyData.totalVotesCast.toLocaleString()} votes`
        },
        {
            category: "In-Person Early",
            iowa: `${iowaEarlyData.inPersonEarly.toLocaleString()} votes (${iowaEarlyData.percentInPersonEarly.toFixed(2)}% of total votes cast)`,
            illinois: `${illinoisEarlyData.inPersonEarly.toLocaleString()} votes (${illinoisEarlyData.percentInPersonEarly.toFixed(2)}% of total votes cast)`,
            arkansas: `${arkansasEarlyData.inPersonEarly.toLocaleString()} votes (${arkansasEarlyData.percentInPersonEarly.toFixed(2)}% of total votes cast)`
        },
        {
            category: "Mail Accepted",
            iowa: `${iowaEarlyData.mailAccepted.toLocaleString()} votes (${iowaEarlyData.percentMail.toFixed(2)}% of total votes cast)`,
            illinois: `${illinoisEarlyData.mailAccepted.toLocaleString()} votes (${illinoisEarlyData.percentMail.toFixed(2)}% of total votes cast)`,
            arkansas: `${arkansasEarlyData.mailAccepted.toLocaleString()} votes (${arkansasEarlyData.percentMail.toFixed(2)}% of total votes cast)`
        },
        {
            category: "Dropbox Accepted",
            iowa: `${iowaEarlyData.dropboxAccepted.toLocaleString()} votes (${iowaEarlyData.percentDropbox.toFixed(2)}% of total votes cast)`,
            illinois: `${illinoisEarlyData.dropboxAccepted.toLocaleString()} votes (${illinoisEarlyData.percentDropbox.toFixed(2)}% of total votes cast)`,
            arkansas: `${arkansasEarlyData.dropboxAccepted.toLocaleString()} votes (${arkansasEarlyData.percentDropbox.toFixed(2)}% of total votes cast)`
        },
        {
            category: "UOCAVA Accepted",
            iowa: `${iowaEarlyData.uocavaAccepted.toLocaleString()} votes (${iowaEarlyData.percentUocava.toFixed(2)}% of total votes cast)`,
            illinois: `${illinoisEarlyData.uocavaAccepted.toLocaleString()} votes (${illinoisEarlyData.percentUocava.toFixed(2)}% of total votes cast)`,
            arkansas: `${arkansasEarlyData.uocavaAccepted.toLocaleString()} votes (${arkansasEarlyData.percentUocava.toFixed(2)}% of total votes cast)`
        },
        {
            category: "Provisional Counted",
            iowa: `${iowaEarlyData.provisionalCounted.toLocaleString()} votes (${iowaEarlyData.percentProvisional.toFixed(2)}% of total votes cast)`,
            illinois: `${illinoisEarlyData.provisionalCounted.toLocaleString()} votes (${illinoisEarlyData.percentProvisional.toFixed(2)}% of total votes cast)`,
            arkansas: `${arkansasEarlyData.provisionalCounted.toLocaleString()} votes (${arkansasEarlyData.percentProvisional.toFixed(2)}% of total votes cast)`
        },
        {
            category: "Total Early",
            iowa: `${iowaEarlyData.totalEarly.toLocaleString()} votes (${iowaEarlyData.percentTotalEarly.toFixed(2)}% of total votes cast)`,
            illinois: `${illinoisEarlyData.totalEarly.toLocaleString()} votes (${illinoisEarlyData.percentTotalEarly.toFixed(2)}% of total votes cast)`,
            arkansas: `${arkansasEarlyData.totalEarly.toLocaleString()} votes (${arkansasEarlyData.percentTotalEarly.toFixed(2)}% of total votes cast)`
        }
    ] : [];

    const getCurrentData = () => {
        switch (selectedView) {
            case 'votingData':
                return votingData;
            case 'rights':
                return votingRightAndProcedure;
            case 'earlyVoting':
                return earlyVotingStates;
            default:
                return [];
        }
    };

    const currentData = getCurrentData();
    const isLoading = selectedView === 'votingData'
        ? (iowaLoading || illinoisLoading || arkansasLoading)
        : selectedView === 'rights'
            ? (iowaCompLoading || illinoisCompLoading || arkansasCompLoading)
            : selectedView === 'earlyVoting'
                ? (iowaEarlyLoading || illinoisEarlyLoading || arkansasEarlyLoading)
                : false;

    return (
        <Box
            sx={{
                height: "100vh",
                display: "flex",
                flexDirection: "column",
                overflow: "hidden",
                pt: 2,
                px: 4,
                pb: 4,
                width: "100%"
            }}
        >
            {/* Top Toggle Bar */}
            <Box sx={{ mb: 2, display: 'flex', justifyContent: 'center', width: "100%" }}>
                <ToggleButtonGroup
                    value={selectedView}
                    exclusive
                    onChange={(_, newView) => newView && setSelectedView(newView)}
                    aria-label="comparison type"
                    sx={{
                        bgcolor: '#fff',
                        borderRadius: '999px',
                        padding: '4px',
                        '& .MuiToggleButton-root': {
                            px: 3,
                            py: 1,
                            fontSize: '1rem',
                            fontWeight: 500,
                            border: 'none',
                            borderRadius: '999px',
                            textTransform: 'none',
                            '&.Mui-selected': {
                                backgroundColor: '#1976d2',
                                color: '#fff',
                                '&:hover': { backgroundColor: '#1565c0' }
                            }
                        }
                    }}
                >
                    <ToggleButton value="votingData">Voting Data</ToggleButton>
                    <ToggleButton value="rights">Voting Rights & Procedures</ToggleButton>
                    <ToggleButton value="earlyVoting">Early Voting Data</ToggleButton>
                </ToggleButtonGroup>
            </Box>

            {/* Table */}
            <Box sx={{ flex: 1, width: "100%", display: "flex", justifyContent: "center" }}>
                {isLoading ? (
                    <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                        <CircularProgress />
                    </Box>
                ) : (
                    <Box sx={{ width: "100%", maxWidth: "85%" }}>
                        <TableContainer component={Paper} elevation={2}>
                            <Table>
                                <TableHead>
                                    <TableRow sx={{ backgroundColor: '#f5f5f5' }}>
                                        <TableCell sx={{ width: '25%', fontWeight: 'bold' }}>
                                            Category
                                        </TableCell>

                                        <TableCell
                                            sx={{
                                                width: '25%',
                                                backgroundColor: '#ffebee',
                                                borderLeft: '1px solid #e0e0e0',
                                                fontWeight: 'bold',
                                                textAlign: 'center',
                                                height: 60
                                            }}
                                        >
                                            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: 1 }}>
                                                Iowa
                                                <Chip
                                                    label="Republican"
                                                    size="small"
                                                    sx={{
                                                        backgroundColor: '#ffcdd2',
                                                        color: '#c62828'
                                                    }}
                                                />
                                            </Box>
                                        </TableCell>

                                        <TableCell
                                            sx={{
                                                width: '25%',
                                                backgroundColor: '#ffebee',
                                                borderLeft: '1px solid #e0e0e0',
                                                fontWeight: 'bold',
                                                textAlign: 'center',
                                                height: 60
                                            }}
                                        >
                                            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: 1 }}>
                                                Arkansas
                                                <Chip
                                                    label="Republican"
                                                    size="small"
                                                    sx={{
                                                        backgroundColor: '#ffcdd2',
                                                        color: '#c62828'
                                                    }}
                                                />
                                            </Box>
                                        </TableCell>

                                        <TableCell
                                            sx={{
                                                width: '25%',
                                                backgroundColor: '#e3f2fd',
                                                borderLeft: '1px solid #e0e0e0',
                                                fontWeight: 'bold',
                                                textAlign: 'center'
                                            }}
                                        >
                                            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: 1 }}>
                                                Illinois
                                                <Chip
                                                    label="Democratic"
                                                    size="small"
                                                    sx={{
                                                        backgroundColor: '#bbdefb',
                                                        color: '#1565c0'
                                                    }}
                                                />
                                            </Box>
                                        </TableCell>
                                    </TableRow>
                                </TableHead>

                                <TableBody>
                                    {currentData.map((row, index) => (
                                        <TableRow
                                            key={index}
                                            sx={{
                                                '&:hover': { backgroundColor: '#f5f5f5' },
                                                '&:nth-of-type(even)': { backgroundColor: '#fafafa' },
                                                height: selectedView === 'rights' ? 40 : 60
                                            }}
                                        >
                                            <TableCell sx={{ fontWeight: 'medium' }}>
                                                {row.category}
                                            </TableCell>
                                            <TableCell sx={{ borderLeft: '1px solid #e0e0e0', textAlign: 'center' }}>
                                                {row.iowa}
                                            </TableCell>
                                            <TableCell sx={{ borderLeft: '1px solid #e0e0e0', textAlign: 'center' }}>
                                                {row.arkansas}
                                            </TableCell>
                                            <TableCell sx={{ borderLeft: '1px solid #e0e0e0', textAlign: 'center' }}>
                                                {row.illinois}
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Box>
                )}
            </Box>
        </Box>
    );
};

export default PoliticalPartyStates;

import {
	Box,
	Card,
	CardContent,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
	Typography,
	Chip,
	CircularProgress
} from '@mui/material';
import { useRegistrationAndTurnoutComparison } from '../queries/dataHooks';

interface VoterRegistrationData {
	stateId: number;
	activeRegisteredVoters: number;
	totalRegisteredVoters: number;
	totalCvap: number;
	totalVotesCast: number;
	registrationRate: number;
	turnoutRate: number;
}

const STATE_INFO: Record<number, { name: string; type: string }> = {
	19: { name: 'Iowa', type: 'Opt-in' },
	17: { name: 'Illinois', type: 'Opt-out + Same-day' },
	10: { name: 'Delaware', type: 'Opt-out' },
	5: { name: 'Arkansas', type: 'Opt-in' }
};

const VoterRegistrationStates = () => {
	const iowa = useRegistrationAndTurnoutComparison('19');
	const illinois = useRegistrationAndTurnoutComparison('17');
	const delaware = useRegistrationAndTurnoutComparison('10');

	const data: VoterRegistrationData[] = [
		iowa.registrationAndTurnoutComparison,
		delaware.registrationAndTurnoutComparison,
		illinois.registrationAndTurnoutComparison,
	].filter(Boolean) as VoterRegistrationData[];

	const loading =
		iowa.isRegistrationAndTurnoutComparisonLoading ||
		illinois.isRegistrationAndTurnoutComparisonLoading ||
		delaware.isRegistrationAndTurnoutComparisonLoading;

	const formatPercentage = (rate: number) => `${(rate * 100).toFixed(2)}%`;
	const formatNumberWithUnit = (n: number, unit: string) =>
		`${n.toLocaleString()} ${unit}`;
	const formatPercentageWithNote = (rate: number, note: string) =>
		`${formatPercentage(rate)} ${note}`;

	if (loading) {
		return (
			<Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>
				<CircularProgress />
			</Box>
		);
	}

	return (
		<Box
			sx={{
				display: "flex",
				flexDirection: "column",
				overflow: "hidden",
				pt: 3,
				px: 4,
				width: "100%"
			}}
		>
			{/* Title */}
			<Box sx={{ mb: 3, textAlign: "center" }}>
				<Typography variant="h5" fontWeight="bold">
					Voter Registration Comparison by State Type
				</Typography>
			</Box>

			{/* Centered Table */}
			<Box
				sx={{
					flex: 1,
					overflow: "hidden",
					width: "100%",
					display: "flex",
					justifyContent: "center",
				}}
			>
				<Box sx={{ width: "70%" }}>
					<Card elevation={2} sx={{ display: "inline-block", width: "fit-content" }}>
						<CardContent sx={{ p: 0, "&:last-child": { pb: 0 } }}>
							<TableContainer>
								<Table
									sx={{
										tableLayout: "fixed",
										"& td, & th": {
											height: 60,
										},
										"& td:first-of-type, & th:first-of-type": {
											width: 100,
										},

										"& td:not(:first-of-type), & th:not(:first-of-type)": {
											width: 120,
											textAlign: "center",
										},
									}}
								>
									<TableHead>
										<TableRow sx={{ backgroundColor: "#f5f5f5" }}>
											<TableCell sx={{ width: "150px", fontWeight: "bold" }}>
												<Typography variant="body1" fontWeight="bold">
													State
												</Typography>
											</TableCell>

											{data.map((state) => (
												<TableCell key={state.stateId} sx={{ textAlign: "center" }}>
													<Typography variant="body1" fontWeight="bold">
														{STATE_INFO[state.stateId].name}
													</Typography>
												</TableCell>
											))}
										</TableRow>
									</TableHead>

									<TableBody>
										{/* Registration Type */}
										<TableRow sx={{ "&:nth-of-type(even)": { backgroundColor: "#fafafa" } }}>
											<TableCell>Registration Type</TableCell>

											{data.map((state) => {
												const chipColor =
													state.stateId === 19
														? { bgcolor: "#e3f2fd", color: "#1565c0" }
														: state.stateId === 10
															? { bgcolor: "#f3e5f5", color: "#7b1fa2" }
															: { bgcolor: "#e8f5e9", color: "#2e7d32" };

												return (
													<TableCell key={state.stateId} sx={{ textAlign: "center" }}>
														<Chip
															label={STATE_INFO[state.stateId].type}
															size="small"
															sx={{
																fontWeight: "bold",
																...chipColor,
															}}
														/>
													</TableCell>
												);
											})}
										</TableRow>

										{/* Voting Eligible Population */}
										<TableRow sx={{ "&:nth-of-type(even)": { backgroundColor: "#fafafa" } }}>
											<TableCell>Voting Eligible Population</TableCell>
											{data.map((s) => (
												<TableCell key={s.stateId} sx={{ textAlign: "center" }}>
													{formatNumberWithUnit(s.totalCvap, "voters")}
												</TableCell>
											))}
										</TableRow>

										{/* Registered Voters */}
										<TableRow sx={{ "&:nth-of-type(even)": { backgroundColor: "#fafafa" } }}>
											<TableCell>Registered Voters</TableCell>
											{data.map((s) => (
												<TableCell key={s.stateId} sx={{ textAlign: "center" }}>
													{formatNumberWithUnit(s.totalRegisteredVoters, "voters")}
												</TableCell>
											))}
										</TableRow>

										{/* Registration Rate */}
										<TableRow sx={{ "&:nth-of-type(even)": { backgroundColor: "#fafafa" } }}>
											<TableCell>Registration Rate</TableCell>
											{data.map((s) => (
												<TableCell key={s.stateId} sx={{ textAlign: "center" }}>
													{formatPercentageWithNote(s.registrationRate / 100, "of eligible")}
												</TableCell>
											))}
										</TableRow>

										{/* Votes Cast */}
										<TableRow sx={{ "&:nth-of-type(even)": { backgroundColor: "#fafafa" } }}>
											<TableCell>Votes Cast</TableCell>
											{data.map((s) => (
												<TableCell key={s.stateId} sx={{ textAlign: "center" }}>
													{formatNumberWithUnit(s.totalVotesCast, "votes")}
												</TableCell>
											))}
										</TableRow>

										{/* Turnout Rate */}
										<TableRow sx={{ "&:nth-of-type(even)": { backgroundColor: "#fafafa" } }}>
											<TableCell>Turnout Rate</TableCell>
											{data.map((s) => (
												<TableCell key={s.stateId} sx={{ textAlign: "center" }}>
													{formatPercentageWithNote(s.turnoutRate / 100, "of eligible")}
												</TableCell>
											))}
										</TableRow>
									</TableBody>
								</Table>
							</TableContainer>
						</CardContent>
					</Card>
				</Box>
			</Box>
		</Box>
	);
};

export default VoterRegistrationStates;
import { useState } from "react";

import { useParams } from "react-router-dom";
import { Box, Typography } from "@mui/material";
import BarChart from "../components/BarChart";
import ButtonGroupColumn from "../components/ButtonGroupColumn";
import CustomTable from "../components/CustomTable";
import { useProvisionalRegionSummary, useProvisionalSummary } from "../queries/dataHooks";
import { ProvisionalColumns, ReturnedProvisionalFields } from "../utils/TableChartStructure";

const ProvisionalBallots = () => {
    const { id } = useParams()
    if (!id) <div>Loading...</div>
    // Nav Buttons
    const [view, setView] = useState<"State Category" | "Region Table">("State Category");
    const buttons = [
        { label: "State Category", onClick: () => setView("State Category") },
        { label: "Region Table", onClick: () => setView("Region Table") },
    ];
    const { provisionalSummary, isProvisionalSummaryLoading } = useProvisionalSummary(id)
    const { provisionalRegionSummary, isProvisionalRegionSummaryLoading } = useProvisionalRegionSummary(id)

    // Table 
    // rows: RegionName | numbers . . .
    let rows: (string | number)[][] = [];
    if (!isProvisionalRegionSummaryLoading) {
        rows = provisionalRegionSummary?.map(r => {
            // Field contains the keys of data
            const values = ReturnedProvisionalFields.map(f => r[f]);
            const total = values.reduce((sum, v) => sum + v, 0);
            return [
                r.regionName,
                ...values,
                total
            ];
        }) ?? [];
    }

    // Bar Chart 
    let chartData: { label: string; value: number }[] = [];
    if (!isProvisionalSummaryLoading && provisionalSummary) {
        chartData = [
            { label: "Not on List", value: provisionalSummary.voterNotOnList },
            { label: "No ID", value: provisionalSummary.voterLackedId },
            { label: "Official Challenge Eligibilty", value: provisionalSummary.electionOfficialChallengedEligibility },
            { label: "Another Challenge Eligibility", value: provisionalSummary.anotherPersonChallengedEligibility },
            { label: "Not Resident", value: provisionalSummary.voterNotResident },
            { label: "Registration Not Updated", value: provisionalSummary.voterRegistrationNotUpdated },
            { label: "Didn't Surrender Mail Ballot", value: provisionalSummary.voterDidNotSurrenderMailBallot },
            { label: "Extended Voting Hours", value: provisionalSummary.judgeExtendedVotingHours },
            { label: "Used SDR", value: provisionalSummary.voterUsedSdr },
            { label: "Other Reason", value: provisionalSummary.otherReasons }
        ];
    }
    if (isProvisionalRegionSummaryLoading || isProvisionalSummaryLoading) {
        return <div>Loading...</div>
    }
    return (
        <>
            <ButtonGroupColumn buttons={buttons} activeLabel={view} />
            {view == "State Category" &&
                <Box style={{ width: "90%", height: "85%", marginRight: "10%", marginTop: "5%" }}>
                    <BarChart
                        data={chartData}
                        title="Provisional Ballot Category"
                        // horizontal={true}
                    />
                </Box>
            }
            {view == "Region Table" &&
                <>
                    <Box
                        sx={{
                            width: "90%",
                            height: "85%",
                            marginRight: "10%",
                            marginTop: "5%",
                            overflow: "hidden",
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center",
                        }}
                    >
                        <Typography
                            variant="body1"
                            sx={{
                                fontWeight: 600,
                                mb: 2,
                                textAlign: "center",
                            }}
                        >
                            Provisional Ballot By Region
                        </Typography>

                        <CustomTable columns={ProvisionalColumns} rows={rows} rowsPerPage={5} maxChars={18} />
                    </Box>
                </>
            }
        </>
    )
}
export default ProvisionalBallots
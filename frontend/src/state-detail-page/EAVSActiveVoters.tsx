import { Box, Typography } from "@mui/material";
import BarChart from "../components/BarChart";
import ButtonGroupColumn from "../components/ButtonGroupColumn";
import CustomTable from "../components/CustomTable";
import { useState } from "react";
import { useParams } from "react-router-dom";
import { useVoterRegionSummary, useVoterSummary } from "../queries/dataHooks";
import { ActiveVoterColumn, ReturnedActiveVoter } from "../utils/TableChartStructure";


const EAVSActiveVoters = () => {
    const { id } = useParams()
    if (!id) <div>Loading...</div>
    const [view, setView] = useState<"State Category" | "Region Table">("State Category");
    const buttons = [
        { label: "State Category", onClick: () => setView("State Category") },
        { label: "Region Table", onClick: () => setView("Region Table") },
    ];
    const { voterSummary, isVoterSummaryLoading } = useVoterSummary(id)
    const { voterRegionSummary, isVoterRegionSummaryLoading } = useVoterRegionSummary(id)
    let chartData: { label: string; value: number }[] = [];
    let rows: (string | number)[][] = [];
    if (!isVoterRegionSummaryLoading) {
        rows = voterRegionSummary?.map(r => {
                    // Field contains the keys of data
                    const values = ReturnedActiveVoter.map(f => r[f]);
                    const total = values.reduce((sum, v) => sum + v, 0);
                    return [
                        r.regionName,
                        ...values,
                        total
                    ];
                }) ?? [];
    }
    if (!isVoterSummaryLoading && voterSummary) {
        chartData = [
            { label: "Active Registered Voters", value: voterSummary.activeRegisteredVoters },
            { label: "Inactive Registered Voters", value: voterSummary.inactiveRegisteredVoters },
            { label: "Total Registered Voters", value: voterSummary.totalRegisteredVoters }
        ];
    }

    if (isVoterSummaryLoading || isVoterRegionSummaryLoading) {
        return <div>Loading...</div>
    }
    return (
        <>
            <ButtonGroupColumn buttons={buttons} activeLabel={view} />
            {view == "State Category" &&
                <div style={{ width: "90%", height: "85%", marginRight: "10%", marginTop: "5%" }}>
                    <BarChart
                        data={chartData}
                        title="Active Voter Summary"
                        // horizontal={false}
                    />
                </div>
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
                            Active Voter By Region
                        </Typography>

                        <CustomTable columns={ActiveVoterColumn} rows={rows} />
                    </Box>
                </>
            }
        </>
    )
}
export default EAVSActiveVoters;
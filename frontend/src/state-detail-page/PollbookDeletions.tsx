import { usePollBookDeletionRegionSummary, usePollBookDeletionSummary, useVoterRegionSummary } from "../queries/dataHooks"
import { useParams } from "react-router-dom";
import ButtonGroupColumn from "../components/ButtonGroupColumn";
import BarChart from "../components/BarChart";
import { Box, Typography } from "@mui/material";
import CustomTable from "../components/CustomTable";
import { useState } from "react";
import { ActiveVoterColumn, PollbookDeletionColumns, ReturnedActiveVoter, ReturnedPollbookDeletionFields } from "../utils/TableChartStructure";

const PollbookDeletions = () => {
    const { id } = useParams();
    if (!id) {
        return <div>No state ID provided.</div>
    }
    const [view, setView] = useState<"State Category" | "Region Table">("State Category");
    const buttons = [
        { label: "State Category", onClick: () => setView("State Category") },
        { label: "Region Table", onClick: () => setView("Region Table") },
    ];
    const { pollBookDeletionSummary, isPollBookDeletionSummaryLoading } = usePollBookDeletionSummary(id);
    const { voterRegionSummary, isVoterRegionSummaryLoading } = useVoterRegionSummary(id)
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
    let chartData: { label: string; value: number }[] = [];
    if (!isPollBookDeletionSummaryLoading && pollBookDeletionSummary) {
        chartData = [
            { label: "Moved", value: pollBookDeletionSummary.moved },
            { label: "Death", value: pollBookDeletionSummary.death },
            { label: "Felony", value: pollBookDeletionSummary.felony },
            { label: "Fail Response", value: pollBookDeletionSummary.failResponse },
            { label: "Incompetent To Vote", value: pollBookDeletionSummary.incompetentToVote },
            { label: "Voter Request", value: pollBookDeletionSummary.voterRequest },
            { label: "Duplicate Record", value: pollBookDeletionSummary.duplicateRecord }
        ];
    }
    if (isPollBookDeletionSummaryLoading || isVoterRegionSummaryLoading)
        return <div>Loading...</div>
    return (
        <>
            <ButtonGroupColumn buttons={buttons} activeLabel={view} />
            {view == "State Category" &&

                <Box sx={{ width: "90%", height: "85%", mr: "10%", mt: "5%" }}>
                    <BarChart
                        data={chartData}
                        title="PollBook Deletion Reasons"
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
                            mt: "5%",
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

                        <CustomTable columns={ActiveVoterColumn} rows={rows} rowsPerPage={5} />
                    </Box>
                </>
            }
        </>
    )
}
export default PollbookDeletions
import { useState } from "react";
import { useParams } from "react-router-dom";
import { useMailBallotRejectionRegionSummary, useMailBallotRejectionSummary } from "../queries/dataHooks";
import ButtonGroupColumn from "../components/ButtonGroupColumn";
import BarChart from "../components/BarChart";
import { Box, Typography } from "@mui/material";
import CustomTable from "../components/CustomTable";
import { MailBallotRejectionColumns, ReturnedMailBallotRejectionFields } from "../utils/TableChartStructure";

const MailBallotRejections = () => {
    const { id } = useParams()
    if (!id) <div>Loading...</div>
    const [view, setView] = useState<"State Category" | "Region Table">("State Category");
    const buttons = [
        { label: "State Category", onClick: () => setView("State Category") },
        { label: "Region Table", onClick: () => setView("Region Table") },
    ];
    const { mailBallotRejectionSummary, isMailBallotRejectionSummaryLoading } = useMailBallotRejectionSummary(id)
    const { mailBallotRejectionRegionSummary, isMailBallotRejectionRegionSummaryLoading } = useMailBallotRejectionRegionSummary(id)
    let rows: (string | number)[][] = [];
    if (!isMailBallotRejectionRegionSummaryLoading && mailBallotRejectionRegionSummary) {
        rows = mailBallotRejectionRegionSummary?.map(r => {
                    const values = ReturnedMailBallotRejectionFields.map(f => r[f]);
                    const total = values.reduce((sum, v) => sum + v, 0);
                    return [
                        r.regionName,
                        ...values,
                        total
                    ];
                }) ?? [];
    }
    let chartData: { label: string; value: number }[] = [];
    if (!isMailBallotRejectionSummaryLoading && mailBallotRejectionSummary) {
        chartData = [
            { label: "Late", value: mailBallotRejectionSummary.late },
            { label: "Missing Voter Signature", value: mailBallotRejectionSummary.missingVoterSignature },
            { label: "Missing Witness Signature", value: mailBallotRejectionSummary.missingWitnessSignature },
            { label: "Signature Mismatch", value: mailBallotRejectionSummary.nonMatchingVoterSignature },
            { label: "Unofficial Envelope", value: mailBallotRejectionSummary.unofficialEnvelope },
            { label: "Missing Ballot From Evelope", value: mailBallotRejectionSummary.ballotMissingFromEnvelope },
            { label: "No Secrecy Envelope", value: mailBallotRejectionSummary.noSecrecyEnvelope },
            { label: "Multiple Ballots in Envelope", value: mailBallotRejectionSummary.multipleBallotsInOneEnvelope },
            { label: "Envelope Not Sealed", value: mailBallotRejectionSummary.envelopeNotSealed },
            { label: "No Postmark", value: mailBallotRejectionSummary.noPostmark },
            { label: "No Address On Envelope", value: mailBallotRejectionSummary.noResidentAddressOnEnvelope },
            { label: "Voter Deceased", value: mailBallotRejectionSummary.voterDeceased },
            { label: "Voter Already Voted", value: mailBallotRejectionSummary.voterAlreadyVoted },
            { label: "Missing Documentation", value: mailBallotRejectionSummary.missingDocumentation },
            { label: "Voter Not Eligible", value: mailBallotRejectionSummary.voterNotEligible },
            { label: "No Ballot Application", value: mailBallotRejectionSummary.noBallotApplication }
        ];
    }
    if (isMailBallotRejectionSummaryLoading || isMailBallotRejectionRegionSummaryLoading) {
        return <div>Loading...</div>
    }
    return (
        <>
            <ButtonGroupColumn buttons={buttons} activeLabel={view} />
            {view == "State Category" &&
                <div style={{ width: "100%", height: "85%", marginRight:"3%", marginTop: "5%" }}>
                    <BarChart
                        data={chartData}
                        title="Mail Ballot Rejection Category"
                        // horizontal={true}
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
                            Mail Ballot Rejection By Region
                        </Typography>

                        <CustomTable columns={MailBallotRejectionColumns} rows={rows} rowsPerPage={5} maxChars={16} />
                    </Box>
                </>
            }
        </>
    )
}
export default MailBallotRejections
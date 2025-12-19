import { Box, Typography } from "@mui/material";
import { useVoterRegistrationRegionSum } from "../queries/dataHooks";
import {
  ReturnedVoterRegistrationRegionSumFields,
  VoterRegistrationRegionSumFields
} from "../utils/TableChartStructure";
import CustomTable from "../components/CustomTable";

const VoterRegistrationData = () => {
  const { VoterReistrationRegionSum, isVoterReistrationRegionSumsLoading } =
    useVoterRegistrationRegionSum("12");

  if (isVoterReistrationRegionSumsLoading) return <div>Loading...</div>;

  const rows: (string | number)[][] =
    VoterReistrationRegionSum?.map(r => {
      const values = ReturnedVoterRegistrationRegionSumFields.map(f => r[f] ?? "N/A");
      return [r.regionName, ...values];
    }) ?? [];

  return (
    <Box
      sx={{
        width: "90%",
        height: "85%",
        marginRight: "10%",
        mt: "5%",
        overflow: "hidden",
        display: "flex",
        flexDirection: "column",
        alignItems: "center"
      }}
    >
      <Typography variant="body1" sx={{ fontWeight: 600, mb: 2, textAlign: "center" }}>
        Voter Registration By Region
      </Typography>

      <CustomTable columns={VoterRegistrationRegionSumFields as any} rows={rows} rowsPerPage={5} total = {false}/>
    </Box>
  );
};

export default VoterRegistrationData;

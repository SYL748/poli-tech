import React, { useMemo } from "react";
import { Box, Typography } from "@mui/material";
import { useParams } from "react-router-dom";
import { useGetStateEquipmentTable } from "../queries/dataHooks";
import CustomTable from "../components/CustomTable";
import { ReturnStateVotingEquipmentSummary } from "../utils/TableChartStructure";
import type { VotingEquipmentRecord } from "../utils/Types";

/** ✅ paste your JSON here (or import it) */
const DISCONTINUED_LIST = [
  { manufacturer: "MicroVote", model_name: "Infinity", discontinued: "TRUE" },
  { manufacturer: "Dominion", model_name: "ImageCast Remote", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "iVotronic", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "Model 100", discontinued: "TRUE" },
  { manufacturer: "Populex", model_name: "Slate", discontinued: "TRUE" },
  { manufacturer: "Premier Election Solutions (Diebold)", model_name: "AccuVote OS", discontinued: "TRUE" },
  { manufacturer: "Premier Election Solutions (Diebold)", model_name: "AccuVote OSX", discontinued: "TRUE" },
  { manufacturer: "Sequoia Voting Systems", model_name: "AVC Advantage", discontinued: "TRUE" },
  { manufacturer: "Smartmatic/Los Angeles County", model_name: "VSAP BMD", discontinued: "TRUE" },
  { manufacturer: "Smartmatic/Los Angeles County", model_name: "VSAP Tally", discontinued: "TRUE" },
  { manufacturer: "Smartmatic/Los Angeles County", model_name: "VSAP Interactive Sample Ballot", discontinued: "TRUE" },
  { manufacturer: "Triad Governmental Systems", model_name: "Accessible Ballot Marking Tool (ABMT)", discontinued: "TRUE" },
  { manufacturer: "Unisyn", model_name: "OpenElect OVI", discontinued: "TRUE" },
  { manufacturer: "Unisyn", model_name: "OpenElect OVI-VC", discontinued: "TRUE" },
  { manufacturer: "Unisyn", model_name: "OpenElect OVCS", discontinued: "TRUE" },
  { manufacturer: "Unisyn", model_name: "OpenElect OVO", discontinued: "TRUE" },
  { manufacturer: "Voatz", model_name: "Mobile App", discontinued: "TRUE" },
  { manufacturer: "Votem", model_name: "CastIron Voting Platform", discontinued: "TRUE" },
  { manufacturer: "VotingWorks", model_name: "VxMark", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "Model 650", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "DS650", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "Model 150", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "Model 550", discontinued: "TRUE" },
  { manufacturer: "ES&S/Unisyn", model_name: "InkaVote", discontinued: "TRUE" },
  { manufacturer: "AVS", model_name: "WINVote", discontinued: "TRUE" },
  { manufacturer: "Avante", model_name: "Vote-Trakker", discontinued: "TRUE" },
  { manufacturer: "AVS", model_name: "WINScan", discontinued: "TRUE" },
  { manufacturer: "DFM Associates", model_name: "Mark-A-Vote", discontinued: "TRUE" },
  { manufacturer: "Danaher", model_name: "Shouptronic 1242", discontinued: "TRUE" },
  { manufacturer: "Hart InterCivic", model_name: "Verity Duo", discontinued: "TRUE" },
  { manufacturer: "Hart InterCivic", model_name: "Verity Touch", discontinued: "TRUE" },
  { manufacturer: "Democracy Live", model_name: "Secure Select", discontinued: "TRUE" },
  { manufacturer: "ES&S", model_name: "AutoMARK", discontinued: "TRUE" },
] as const;

const ROWS_PER_PAGE = 5;

const EquipmentColumns = [
  "Manufacturer",
  "Model Name",
  "Equipment Type",
  "Quantity",
  "Age",
  "OS",
  "Certification Level",
  "Scanning Rate",
  "Error Rate",
  "Reliability",
  "Quality",
  "Short Description",
];

// normalize to avoid casing/extra spaces breaking matches
const norm = (s: unknown) =>
  String(s ?? "")
    .trim()
    .toLowerCase()
    .replace(/\s+/g, " ");

const makeKey = (manufacturer: unknown, model: unknown) =>
  `${norm(manufacturer)}||${norm(model)}`;

const formatValue = (value: unknown, key?: string): string => {
  if (value === null || value === undefined || value === "") return "—";

  if (key === "reliability" && typeof value === "number") {
    return `${(value * 100).toFixed(0)}%`;
  }
  if (key === "age" && typeof value === "number") {
    return `${value} yr${value !== 1 ? "s" : ""}`;
  }

  return String(value);
};

const StateVotingEquipmentSummary = () => {
  const { id } = useParams();
  if (!id) return <div>Loading...</div>;

  const { StateEquipmentSummaryTable, isStateEquipmentSummaryTableLoading } =
    useGetStateEquipmentTable(id);

  // ✅ build lookup set once
  const discontinuedSet = useMemo(() => {
    return new Set(
      DISCONTINUED_LIST.filter((x) => String(x.discontinued).toUpperCase() === "TRUE").map((x) =>
        makeKey(x.manufacturer, x.model_name)
      )
    );
  }, []);

  const rows: React.ReactNode[][] = useMemo(() => {
    const data = (Array.isArray(StateEquipmentSummaryTable)
      ? StateEquipmentSummaryTable
      : StateEquipmentSummaryTable
      ? [StateEquipmentSummaryTable]
      : []) as VotingEquipmentRecord[];

    return data.map((r) => {
      const k = makeKey((r as any).manufacturer, (r as any).modelName);
      const isDiscontinued = discontinuedSet.has(k);

      const values = ReturnStateVotingEquipmentSummary.map((f) =>
        formatValue((r as any)?.[f], f)
      );

      // ✅ if match found in JSON -> mark entire row red (text)
      if (isDiscontinued) {
        return values.map((v, i) => (
          <span key={i} style={{ color: "#d32f2f", fontWeight: 600 }}>
            {v}
          </span>
        ));
      }

      return values;
    });
  }, [StateEquipmentSummaryTable, discontinuedSet]);

  if (isStateEquipmentSummaryTableLoading) return <div>Loading...</div>;

  return (
    <Box sx={{ width: "95%", mr: "5%" }}>
      <Typography
        variant="h6"
        sx={{ fontWeight: 700, mb: 2, textAlign: "center" }}
      >
        State Voting Equipment Summary
      </Typography>

      <CustomTable
        columns={EquipmentColumns}
        rows={rows as any}
        rowsPerPage={ROWS_PER_PAGE}
        maxChars={24}
        total={false}
      />
    </Box>
  );
};

export default StateVotingEquipmentSummary;

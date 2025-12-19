import { useMemo, useState } from "react";
import {
  Box,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
  Chip,
  TablePagination,
} from "@mui/material";
import { useVotingEquipmentOverview } from "../queries/dataHooks";

/** This is what your TABLE wants */
export type VotingEquipment = {
  make: string;
  type: string;
  model: string;
  age: number | null;
  operatingSystem: string | null;
  cert: string | null;
  scanRate: string | number | null;
  errorRate: string | number | null;
  reliability: number | null; // assumed 0..1
  quality: number | null;     // assumed 0..1
  discontinued: boolean;
  quantity: number;
};

/**
 * This is what your API/Hooks likely returns (record shape).
 * Add/remove fields to match your actual backend response.
 */
export type VotingEquipmentRecord = {
  // common alt names
  manufacturer?: string | null;
  modelName?: string | null;
  equipmentType?: string | null;
  os?: string | null;
  certificationLevel?: string | null;
  scanningRate?: string | number | null;

  // sometimes already in your "table" naming
  make?: string | null;
  model?: string | null;
  type?: string | null;
  operatingSystem?: string | null;
  cert?: string | null;
  scanRate?: string | number | null;

  // data
  quantity?: number | null;
  age?: number | null;
  errorRate?: string | number | null;
  reliability?: number | null;
  quality?: number | null;
  discontinued?: boolean | null;
};

// ---------------- UI constants ----------------
const ROW_HEIGHT = 61;
const MAX_LINES = 2;
const ROWS_PER_PAGE = 8;
const MIN_TABLE_WIDTH = 1500;
const HEAD_BG = "#f2f2f2";
const STATUS_COL_WIDTH = 190;
const PROVIDER_COL_WIDTH = 140;

// ---------------- helpers ----------------
const CellText = ({ children }: { children: React.ReactNode }) => (
  <Box
    sx={{
      display: "-webkit-box",
      WebkitLineClamp: MAX_LINES,
      WebkitBoxOrient: "vertical",
      overflow: "hidden",
      textOverflow: "ellipsis",
      whiteSpace: "normal",
      lineHeight: 1.35,
    }}
  >
    {children}
  </Box>
);

const baseCellSx = {
  height: ROW_HEIGHT,
  maxHeight: ROW_HEIGHT,
  padding: "8px 12px",
  verticalAlign: "middle",
};

const leftCell = { ...baseCellSx, textAlign: "left" as const };
const centerCell = { ...baseCellSx, textAlign: "center" as const };
const rightCell = { ...baseCellSx, textAlign: "right" as const, whiteSpace: "nowrap" };

const stickyFirstCol = {
  position: "sticky" as const,
  left: 0,
  zIndex: 3,
  boxShadow: "2px 0 0 rgba(0,0,0,0.06)",
};

const naChip = (label = "N/A") => (
  <Chip
    label={label}
    size="small"
    sx={{
      bgcolor: "#f44336",
      color: "white",
      fontWeight: "bold",
      fontSize: "0.75rem",
    }}
  />
);

const clamp01 = (v: number) => Math.max(0, Math.min(1, v));

// >= 0.85 => green, 0.70–0.85 => yellow, < 0.70 => red
const chipColor = (v01: number) =>
  v01 >= 0.85 ? "#4caf50" : v01 >= 0.7 ? "#ff9800" : "#f44336";

// N/A and 0 => red
const metricChip = (v: number | null) => {
  if (v === null || v === undefined) return naChip("N/A");
  const v01 = clamp01(v);
  if (v01 === 0) return naChip("0.00");

  return (
    <Chip
      label={v01.toFixed(2)}
      size="small"
      sx={{
        bgcolor: chipColor(v01),
        color: "white",
        fontWeight: "bold",
        fontSize: "0.75rem",
      }}
    />
  );
};

const cleanCert = (cert: string | null | undefined): string | null => {
  const s = cert?.trim();
  if (!s) return null;
  return s.replace(/certified/gi, "").trim();
};

const formatScanRate = (scanRate: string | number | null | undefined): string | null => {
  if (scanRate === null || scanRate === undefined || scanRate === "") return null;
  if (typeof scanRate === "number") return `${scanRate} ballots/min`;
  const s = String(scanRate).trim();
  return s || null;
};

// percent conversion, no exponential; fraction outputs "<"
const formatErrorRatePercent = (errorRate: string | number | null | undefined): string | null => {
  if (errorRate === null || errorRate === undefined || errorRate === "") return null;

  const formatPct = (pct: number) => {
    const s = pct.toFixed(6).replace(/0+$/, "").replace(/\.$/, "");
    return `${s}%`;
  };

  if (typeof errorRate === "number") {
    // numeric is assumed rate 0..1
    return formatPct(errorRate * 100);
  }

  const s = String(errorRate).trim();
  if (!s) return null;

  // fraction like "<=1/500,000" or "1/1000000" -> always output "<"
  const m = s.match(/(?:<=|<)?\s*(\d+(?:\.\d+)?)\s*\/\s*([\d,]+)/);
  if (m) {
    const num = parseFloat(m[1]);
    const denom = parseFloat(m[2].replace(/,/g, ""));
    if (Number.isFinite(num) && Number.isFinite(denom) && denom !== 0) {
      const pct = (num / denom) * 100;
      return `<${formatPct(pct)}`;
    }
  }

  if (/%$/.test(s)) return s;
  return s;
};

/** ✅ Adapter: record -> table row */
const toVotingEquipment = (r: VotingEquipmentRecord): VotingEquipment => {
  return {
    make: (r.make ?? r.manufacturer ?? "N/A") || "N/A",
    type: (r.type ?? r.equipmentType ?? "N/A") || "N/A",
    model: (r.model ?? r.modelName ?? "N/A") || "N/A",
    age: r.age ?? null,
    operatingSystem: (r.operatingSystem ?? r.os ?? null) || null,
    cert: (r.cert ?? r.certificationLevel ?? null) || null,
    scanRate: (r.scanRate ?? r.scanningRate ?? null) ?? null,
    errorRate: r.errorRate ?? null,
    reliability: r.reliability ?? null,
    quality: r.quality ?? null,
    discontinued: Boolean(r.discontinued),
    quantity: r.quantity ?? 0,
  };
};

// ---------------- component ----------------
const EquipmentOverview = () => {
  const { votingEquipmentOverview, isVotingEquipmentOverviewLoading } = useVotingEquipmentOverview();
  const [page, setPage] = useState(0);

  // ✅ DO NOT cast. Map records -> UI type.
  const records: VotingEquipmentRecord[] = Array.isArray(votingEquipmentOverview)
    ? (votingEquipmentOverview as VotingEquipmentRecord[])
    : [];

  const data: VotingEquipment[] = useMemo(() => records.map(toVotingEquipment), [records]);

  const sortedData = useMemo(() => {
    return [...data].sort((a, b) =>
      a.make !== b.make ? a.make.localeCompare(b.make) : a.model.localeCompare(b.model)
    );
  }, [data]);

  const displayRows = useMemo(() => {
    return sortedData.slice(page * ROWS_PER_PAGE, page * ROWS_PER_PAGE + ROWS_PER_PAGE);
  }, [sortedData, page]);

  if (isVotingEquipmentOverviewLoading) return <div>Loading...</div>;

  return (
    <Box sx={{ width: "100%", px: 4, pt: 3 }}>
      <Typography variant="h5" sx={{ mb: 3, fontWeight: "bold", textAlign: "center" }}>
        US Voting Equipment Summary – 2024
      </Typography>

      <Paper
        sx={{
          width: "90%",
          height: "78vh",
          mx: "auto",
          display: "flex",
          flexDirection: "column",
          overflow: "hidden",
        }}
      >
        <TableContainer
          sx={{
            flex: 1,
            overflowX: "auto",
            overflowY: "hidden",
            "&::-webkit-scrollbar": { height: 10 },
          }}
        >
          <Table
            size="small"
            stickyHeader
            sx={{
              tableLayout: "fixed",
              minWidth: MIN_TABLE_WIDTH,
            }}
          >
            <TableHead>
              <TableRow
                sx={{
                  "& .MuiTableCell-head": {
                    bgcolor: HEAD_BG,
                    color: "#111",
                    fontWeight: 700,
                  },
                }}
              >
                {/* ✅ sticky first column */}
                <TableCell
                  sx={{
                    ...leftCell,
                    ...stickyFirstCol,
                    bgcolor: HEAD_BG,
                    width: PROVIDER_COL_WIDTH,
                  }}
                >
                  Provider
                </TableCell>

                <TableCell sx={{ ...leftCell, width: 160 }}>Type</TableCell>
                <TableCell sx={{ ...leftCell, width: 160 }}>Model</TableCell>
                <TableCell sx={{ ...rightCell, width: 90 }}>Quantity</TableCell>
                <TableCell sx={{ ...rightCell, width: 70 }}>Age</TableCell>
                <TableCell sx={{ ...centerCell, width: 220 }}>Operating System</TableCell>
                <TableCell sx={{ ...centerCell, width: 160 }}>Certification</TableCell>
                <TableCell sx={{ ...rightCell, width: 140 }}>Scan Rate</TableCell>
                <TableCell sx={{ ...rightCell, width: 150 }}>Error Rate</TableCell>
                <TableCell sx={{ ...centerCell, width: 110 }}>Reliability</TableCell>
                <TableCell sx={{ ...centerCell, width: 90 }}>Quality</TableCell>
                <TableCell sx={{ ...centerCell, width: STATUS_COL_WIDTH }}>Status</TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {displayRows.map((e, i) => {
                const rowBg = i % 2 === 0 ? "#ffffff" : "#f2f2f2";

                const cert = cleanCert(e.cert);
                const scanRate = formatScanRate(e.scanRate);
                const errPct = formatErrorRatePercent(e.errorRate);

                return (
                  <TableRow
                    key={`${e.make}-${e.model}-${i}`}
                    sx={{
                      height: ROW_HEIGHT,
                      bgcolor: rowBg,
                    }}
                  >
                    <TableCell
                      sx={{
                        ...leftCell,
                        ...stickyFirstCol,
                        bgcolor: rowBg,
                        width: PROVIDER_COL_WIDTH,
                      }}
                    >
                      <CellText>{e.make}</CellText>
                    </TableCell>

                    <TableCell sx={leftCell}>
                      <CellText>{e.type}</CellText>
                    </TableCell>

                    <TableCell sx={leftCell}>
                      <CellText>{e.model}</CellText>
                    </TableCell>

                    <TableCell sx={rightCell}>{Number(e.quantity).toLocaleString()}</TableCell>

                    <TableCell sx={rightCell}>{e.age === null ? naChip() : e.age}</TableCell>

                    <TableCell sx={centerCell}>
                      {e.operatingSystem ? <CellText>{e.operatingSystem}</CellText> : naChip()}
                    </TableCell>

                    <TableCell sx={centerCell}>
                      {cert ? <CellText>{cert}</CellText> : naChip()}
                    </TableCell>

                    <TableCell sx={rightCell}>{scanRate ? scanRate : naChip()}</TableCell>

                    <TableCell sx={rightCell}>{errPct ? errPct : naChip()}</TableCell>

                    <TableCell sx={centerCell}>{metricChip(e.reliability)}</TableCell>
                    <TableCell sx={centerCell}>{metricChip(e.quality)}</TableCell>

                    <TableCell sx={{ ...centerCell, width: STATUS_COL_WIDTH }}>
                      {e.discontinued ? (
                        <Chip
                          label="Discontinued"
                          size="small"
                          sx={{
                            bgcolor: "#f44336",
                            color: "white",
                            fontWeight: "bold",
                            minWidth: 135,
                            justifyContent: "center",
                            "& .MuiChip-label": { px: 1.5 },
                          }}
                        />
                      ) : (
                        <Chip
                          label="Active"
                          size="small"
                          sx={{
                            bgcolor: "#4caf50",
                            color: "white",
                            fontWeight: "bold",
                            minWidth: 90,
                            justifyContent: "center",
                            "& .MuiChip-label": { px: 1.5 },
                          }}
                        />
                      )}
                    </TableCell>
                  </TableRow>
                );
              })}

              {sortedData.length === 0 && (
                <TableRow>
                  <TableCell colSpan={12} align="center">
                    No equipment found.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>

        <TablePagination
          component="div"
          count={sortedData.length}
          page={page}
          rowsPerPage={ROWS_PER_PAGE}
          rowsPerPageOptions={[ROWS_PER_PAGE]}
          onPageChange={(_, p) => setPage(p)}
        />
      </Paper>
    </Box>
  );
};

export default EquipmentOverview;

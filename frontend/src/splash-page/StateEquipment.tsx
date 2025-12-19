import { useState, useMemo, useEffect, useRef } from "react";
import * as d3 from "d3";
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
  CircularProgress,
  Pagination,
} from "@mui/material";

import {
  useVotingEquipmentTotalsAllStates,
  useVotingEquipmentHistory,
} from "../queries/dataHooks";

type ChartDatum = {
  year: string;
  value: number;
};

interface StateEquipmentData {
  stateId: number;
  stateName: string;
  dreNoVvpat: number;
  dreWithVvpat: number;
  ballotMarkingDevice: number;
  scanner: number;
}

interface YearData {
  year: number;
  dreNoVvpat: number;
  dreWithVvpat: number;
  ballotMarkingDevice: number;
  scanner: number;
}

const VotingEquipmentHistory = ({ stateId }: { stateId: string }) => {
  const { votingEquipmentHistory, isVotingEquipmentHistoryLoading } =
    useVotingEquipmentHistory(stateId);

  const containerRef = useRef<HTMLDivElement>(null);
  const initializedRef = useRef(false);

  useEffect(() => {
    if (
      !containerRef.current ||
      !votingEquipmentHistory?.years?.length ||
      stateId === ""
    )
      return;

    const container = containerRef.current;
    const years = votingEquipmentHistory.years;

    const containerWidth = container.clientWidth;
    const containerHeight = container.clientHeight;
    if (containerWidth <= 0 || containerHeight <= 0) return;

    const chartWidth = containerWidth / 2;
    const chartHeight = containerHeight / 2;

    const margin = { top: 40, right: 20, bottom: 40, left: 60 };
    const width = chartWidth - margin.left - margin.right;
    const height = chartHeight - margin.top - margin.bottom;

    const charts: { key: keyof YearData; label: string; color: string }[] = [
      { key: "dreNoVvpat", label: "DRE No VVPAT", color: "#e53935" },
      { key: "dreWithVvpat", label: "DRE with VVPAT", color: "#1976d2" },
      {
        key: "ballotMarkingDevice",
        label: "Ballot Marking Device",
        color: "#388e3c",
      },
      { key: "scanner", label: "Scanner", color: "#7b1fa2" },
    ];

    if (!initializedRef.current) {
      d3.select(container).selectAll("*").remove();
      initializedRef.current = true;
    }

    charts.forEach((chart, index) => {
      const row = Math.floor(index / 2);
      const col = index % 2;

      let svg = d3
        .select(container)
        .select<SVGSVGElement>(`svg[data-key="${chart.key}"]`);

      if (svg.empty()) {
        svg = d3
          .select(container)
          .append("svg")
          .attr("data-key", chart.key)
          .style("position", "absolute");
      }

      svg
        .attr("width", chartWidth)
        .attr("height", chartHeight)
        .style("left", `${col * chartWidth}px`)
        .style("top", `${row * chartHeight}px`);

      let g = svg.select<SVGGElement>("g.chart-root");

      if (g.empty()) {
        g = svg
          .append("g")
          .attr("class", "chart-root")
          .attr("transform", `translate(${margin.left},${margin.top})`);
      }

      const data: ChartDatum[] = years.map((y) => ({
        year: y.year.toString(),
        value: y[chart.key],
      }));

      const xScale = d3
        .scaleBand()
        .domain(data.map((d) => d.year))
        .range([0, width])
        .padding(0.2);

      const maxValue = d3.max(data, (d) => d.value) ?? 0;

      const yScale = d3
        .scaleLinear()
        .domain(maxValue === 0 ? [0, 1] : [0, maxValue * 1.1])
        .range([height, 0]);

      g.selectAll("*").remove();

      g.append("text")
        .attr("x", width / 2)
        .attr("y", -15)
        .attr("text-anchor", "middle")
        .style("font-weight", 700)
        .text(chart.label);

      g.append("g")
        .attr("transform", `translate(0,${height})`)
        .call(d3.axisBottom(xScale))
        .style("font-size", "11px");

      g.append("g")
        .call(d3.axisLeft(yScale).ticks(5))
        .style("font-size", "11px");

      g.selectAll("rect")
        .data<ChartDatum>(data, (d) => (d as ChartDatum).year)
        .join("rect")
        .attr("x", (d) => xScale(d.year)!)
        .attr("y", (d) => (d.value === 0 ? height : yScale(d.value)))
        .attr("width", xScale.bandwidth())
        .attr("height", (d) => (d.value === 0 ? 0 : height - yScale(d.value)))
        .attr("fill", chart.color)
        .attr("opacity", (d) => (d.value === 0 ? 0 : 0.8));

      g.selectAll("text.bar-label")
        .data<ChartDatum>(data, (d) => (d as ChartDatum).year)
        .join("text")
        .attr("class", "bar-label")
        .attr("x", (d) => xScale(d.year)! + xScale.bandwidth() / 2)
        .attr("y", (d) => (d.value === 0 ? height - 2 : yScale(d.value) - 4))
        .attr("text-anchor", "middle")
        .style("font-size", "11px")
        .style("fill", "#333")
        .text((d) => d.value.toLocaleString());
    });
  }, [stateId, votingEquipmentHistory?.years]);

  if (isVotingEquipmentHistoryLoading && stateId !== "") {
    return (
      <Box
        sx={{
          height: "100%",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box
      ref={containerRef}
      sx={{
        width: "100%",
        height: "100%",
        position: "relative",
        minHeight: 0,
      }}
    />
  );
};

const StateEquipment = () => {
  const { votingEquipmentTotalsAllStates, isVotingEquipmentTotalsAllStatesLoading } =
    useVotingEquipmentTotalsAllStates();

  const [selectedState, setSelectedState] = useState<StateEquipmentData | null>(null);
  const [page, setPage] = useState(1);

  const itemsPerPage = 9;
  const data = (votingEquipmentTotalsAllStates || []) as StateEquipmentData[];

  const paginatedData = useMemo(() => {
    const start = (page - 1) * itemsPerPage;
    return data.slice(start, start + itemsPerPage);
  }, [data, page]);

  const totalPages = Math.ceil(data.length / itemsPerPage);

  useEffect(() => {
    if (!data.length || selectedState) return;

    const alabama = data.find((d) => d.stateName.toLowerCase() === "alabama");
    if (alabama) setSelectedState(alabama);
  }, [data, selectedState]);

  if (isVotingEquipmentTotalsAllStatesLoading) {
    return (
      <Box
        sx={{
          height: "100%",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box sx={{ width: "100%", p: 3 }}>
      <Box sx={{ display: "flex", gap: 3, height: "100%", mt: 2 }}>
        {/* TABLE */}
        <Paper sx={{ flex: 1, display: "flex", flexDirection: "column" }}>
          <Typography variant="h6" sx={{ p: 2, pb: 1, fontWeight: 800 }}>
            Voting Equipment by State – 2024
          </Typography>

          <TableContainer sx={{ flex: 1 }}>
            <Table stickyHeader>
              <TableHead>
                <TableRow
                  sx={{
                    "& .MuiTableCell-head": {
                      fontWeight: 800, // ✅ bold headers
                      bgcolor: "#f2f2f2", // optional header background
                    },
                  }}
                >
                  <TableCell>State</TableCell>
                  <TableCell align="right">DRE no VVPAT</TableCell>
                  <TableCell align="right">DRE with VVPAT</TableCell>
                  <TableCell align="right">Ballot Marking</TableCell>
                  <TableCell align="right">Scanner</TableCell>
                </TableRow>
              </TableHead>

              <TableBody>
                {paginatedData.map((row, index) => (
                  <TableRow
                    key={row.stateId}
                    hover
                    selected={selectedState?.stateId === row.stateId}
                    onClick={() => setSelectedState(row)}
                    sx={{
                      cursor: "pointer",
                      backgroundColor:
                        index % 2 === 0 ? "background.default" : "action.hover",
                    }}
                  >
                    <TableCell sx={{ fontWeight: 600 /* optional: bold state names */ }}>
                      {row.stateName}
                    </TableCell>
                    <TableCell align="right" sx={{ pr: 3 }}>
                      {row.dreNoVvpat.toLocaleString()}
                    </TableCell>
                    <TableCell align="right" sx={{ pr: 3 }}>
                      {row.dreWithVvpat.toLocaleString()}
                    </TableCell>
                    <TableCell align="right" sx={{ pr: 3 }}>
                      {row.ballotMarkingDevice.toLocaleString()}
                    </TableCell>
                    <TableCell align="right" sx={{ pr: 3 }}>
                      {row.scanner.toLocaleString()}
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>

          <Box sx={{ p: 2, display: "flex", justifyContent: "space-between" }}>
            <Typography variant="caption">
              {(page - 1) * itemsPerPage + 1}–
              {Math.min(page * itemsPerPage, data.length)} of {data.length}
            </Typography>
            <Pagination count={totalPages} page={page} onChange={(_, v) => setPage(v)} />
          </Box>
        </Paper>

        {/* CHART */}
        <Paper sx={{ flex: 1, p: 2, display: "flex", flexDirection: "column" }}>
          <Typography variant="h6" sx={{ mb: 1, fontWeight: 800 }}>
            Equipment History – {selectedState?.stateName ?? ""}
          </Typography>

          <Box sx={{ position: "relative", flex: 1 }}>
            <Box
              sx={{
                position: "absolute",
                inset: 0,
                visibility: selectedState ? "visible" : "hidden",
              }}
            >
              <VotingEquipmentHistory stateId={selectedState?.stateId.toString() ?? "01"} />
            </Box>

            {!selectedState && (
              <Box
                sx={{
                  position: "absolute",
                  inset: 0,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <Typography>Select a state</Typography>
              </Box>
            )}
          </Box>
        </Paper>
      </Box>
    </Box>
  );
};

export default StateEquipment;

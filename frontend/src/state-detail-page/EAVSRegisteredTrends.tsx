import { Box, Typography } from "@mui/material";
import * as d3 from "d3";
import { useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import { useEAVSRegisteredTrends } from "../queries/dataHooks";
import type { EAVSRegion } from "../utils/Types";

const LINE_COLORS = {
  "2016": "#ff9800",
  "2020": "#9c27b0",
  "2024": "#009688",
};


const formatK = (n: number) => (n >= 1000 ? `${Math.round(n / 1000)}K` : d3.format(",")(n));
const toNum0 = (v: any) => {
  const n = typeof v === "string" ? Number(v.replace(/,/g, "")) : Number(v);
  return Number.isFinite(n) ? n : 0;
};

const EAVSRegisteredTrends = () => {
  const { id } = useParams();
  const svgRef = useRef<SVGSVGElement | null>(null);
  const tooltipRef = useRef<HTMLDivElement | null>(null);

  const { eavsTrends, isEAVSTrendsLoading } = useEAVSRegisteredTrends(id || "12");

  useEffect(() => {
    if (!eavsTrends || !svgRef.current) return;

    const svg = d3.select(svgRef.current);
    const parent = svgRef.current.parentElement;
    if (!parent) return;

    const drawChart = () => {
      const containerWidth = parent.clientWidth;
      const containerHeight = parent.clientHeight;

      const margin = { top: 40, right: 120, bottom: 70, left: 80 };
      const width = containerWidth - margin.left - margin.right;
      const height = containerHeight - margin.top - margin.bottom;

      if (
        containerWidth <= 0 ||
        containerHeight <= 0 ||
        width <= 0 ||
        height <= 0 ||
        !eavsTrends?.regions?.length
      ) {
        return;
      }

      // ✅ sanitize data so lines don't break on NaN/null/undefined
      const regions: EAVSRegion[] = eavsTrends.regions.map((r) => ({
        ...r,
        a1a_2016: toNum0((r as any).a1a_2016),
        a1a_2020: toNum0((r as any).a1a_2020),
        a1a_2024: toNum0((r as any).a1a_2024),
      }));

      svg.selectAll("*").remove();
      svg.attr("width", containerWidth).attr("height", containerHeight);

      const chart = svg
        .append("g")
        .attr("transform", `translate(${margin.left},${margin.top})`);

      const xLabel = eavsTrends.x_label;
      const yLabel = eavsTrends.y_label;

      const x = d3
        .scaleLinear()
        .domain([0, regions.length - 1])
        .range([0, width]);

      const allValues = regions.flatMap((r) => [r.a1a_2016, r.a1a_2020, r.a1a_2024]);
      const yMax = d3.max(allValues) || 0;

      const y = d3
        .scaleLinear()
        .domain([0, yMax * 1.05])
        .nice()
        .range([height, 0]);

      // Gridlines
      chart
        .append("g")
        .attr("class", "grid")
        .call(d3.axisLeft(y).tickSize(-width).tickFormat(() => ""))
        .call((g) => g.select(".domain").remove())
        .selectAll("line")
        .attr("stroke", "#e0e0e0")
        .attr("stroke-dasharray", "3 3");

      // Line generators
      const line2016 = d3
        .line<EAVSRegion>()
        .x((_, i) => x(i))
        .y((d) => y(d.a1a_2016))
        .curve(d3.curveMonotoneX);

      const line2020 = d3
        .line<EAVSRegion>()
        .x((_, i) => x(i))
        .y((d) => y(d.a1a_2020))
        .curve(d3.curveMonotoneX);

      const line2024 = d3
        .line<EAVSRegion>()
        .x((_, i) => x(i))
        .y((d) => y(d.a1a_2024))
        .curve(d3.curveMonotoneX);

      // Draw lines
      chart
        .append("path")
        .datum(regions)
        .attr("fill", "none")
        .attr("stroke", LINE_COLORS["2016"])
        .attr("stroke-width", 2.5)
        .attr("stroke-opacity", 0.85)
        .attr("d", line2016);

      chart
        .append("path")
        .datum(regions)
        .attr("fill", "none")
        .attr("stroke", LINE_COLORS["2020"])
        .attr("stroke-width", 2.5)
        .attr("stroke-opacity", 0.85)
        .attr("d", line2020);

      chart
        .append("path")
        .datum(regions)
        .attr("fill", "none")
        .attr("stroke", LINE_COLORS["2024"])
        .attr("stroke-width", 3)
        .attr("stroke-opacity", 0.9)
        .attr("d", line2024);

      // Bubbles
      const showBubbles = regions.length <= 150;
      const bubbleRadius = regions.length > 80 ? 3 : regions.length > 40 ? 4 : 5;

      if (showBubbles) {
        chart
          .selectAll(".bubble-2016")
          .data(regions)
          .join("circle")
          .attr("class", "bubble-2016")
          .attr("cx", (_, i) => x(i))
          .attr("cy", (d) => y(d.a1a_2016))
          .attr("r", bubbleRadius)
          .attr("fill", LINE_COLORS["2016"])
          .attr("stroke", "#fff")
          .attr("stroke-width", 1)
          .attr("opacity", 0.8);

        chart
          .selectAll(".bubble-2020")
          .data(regions)
          .join("circle")
          .attr("class", "bubble-2020")
          .attr("cx", (_, i) => x(i))
          .attr("cy", (d) => y(d.a1a_2020))
          .attr("r", bubbleRadius)
          .attr("fill", LINE_COLORS["2020"])
          .attr("stroke", "#fff")
          .attr("stroke-width", 1)
          .attr("opacity", 0.8);

        chart
          .selectAll(".bubble-2024")
          .data(regions)
          .join("circle")
          .attr("class", "bubble-2024")
          .attr("cx", (_, i) => x(i))
          .attr("cy", (d) => y(d.a1a_2024))
          .attr("r", bubbleRadius)
          .attr("fill", LINE_COLORS["2024"])
          .attr("stroke", "#fff")
          .attr("stroke-width", 1)
          .attr("opacity", 0.85);
      }

      // X Axis
      chart
        .append("g")
        .attr("transform", `translate(0,${height})`)
        .call(d3.axisBottom(x).ticks(0).tickFormat(() => ""))

      chart
        .append("text")
        .attr("x", width / 2)
        .attr("y", height + 50)
        .attr("text-anchor", "middle")
        .style("font-size", "13px")
        .style("font-weight", "500")
        .text(xLabel);

      // Y Axis
      chart
        .append("g")
        .call(d3.axisLeft(y).tickFormat((d) => formatK(Number(d))))
        .selectAll("text")
        .style("font-size", "11px");

      chart
        .append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -60)
        .attr("text-anchor", "middle")
        .style("font-size", "13px")
        .style("font-weight", "500")
        .text(yLabel);

      // Legend
      const legend = chart.append("g").attr("transform", `translate(${width + 20}, 20)`);

      const legendItems = [
        { label: "2024", color: LINE_COLORS["2024"] },
        { label: "2020", color: LINE_COLORS["2020"] },
        { label: "2016", color: LINE_COLORS["2016"] },
      ];

      legendItems.forEach((item, i) => {
        const g = legend.append("g").attr("transform", `translate(0, ${i * 28})`);

        g.append("line")
          .attr("x1", 0)
          .attr("x2", 25)
          .attr("y1", 0)
          .attr("y2", 0)
          .attr("stroke", item.color)
          .attr("stroke-width", 3);

        g.append("circle")
          .attr("cx", 12.5)
          .attr("cy", 0)
          .attr("r", 5)
          .attr("fill", item.color)
          .attr("stroke", "#fff")
          .attr("stroke-width", 1);

        g.append("text")
          .attr("x", 35)
          .attr("y", 4)
          .style("font-size", "12px")
          .style("font-weight", "600")
          .text(item.label);
      });

      // Tooltip overlay
      const tooltip = d3.select(tooltipRef.current);

      chart
        .append("rect")
        .attr("width", width)
        .attr("height", height)
        .attr("fill", "transparent")
        .on("mousemove", function (event) {
          const [mx] = d3.pointer(event);
          const idx = Math.round(x.invert(mx));
          if (idx < 0 || idx >= regions.length) return;

          const region = regions[idx];

          tooltip
            .style("display", "block")
            .style("left", `${event.pageX + 15}px`)
            .style("top", `${event.pageY - 10}px`)
            .html(`
              <strong>${region.region_name}</strong><br/>
              <span style="color:${LINE_COLORS["2024"]}">2024:</span> ${d3.format(",")(region.a1a_2024)}<br/>
              <span style="color:${LINE_COLORS["2020"]}">2020:</span> ${d3.format(",")(region.a1a_2020)}<br/>
              <span style="color:${LINE_COLORS["2016"]}">2016:</span> ${d3.format(",")(region.a1a_2016)}
            `);

          chart.selectAll(".hover-line").remove();
          chart
            .append("line")
            .attr("class", "hover-line")
            .attr("x1", x(idx))
            .attr("x2", x(idx))
            .attr("y1", 0)
            .attr("y2", height)
            .attr("stroke", "#666")
            .attr("stroke-width", 1)
            .attr("stroke-dasharray", "4 4");

          chart.selectAll(".hover-dot").remove();

          const addDot = (val: number, color: string) => {
            chart
              .append("circle")
              .attr("class", "hover-dot")
              .attr("cx", x(idx))
              .attr("cy", y(val))
              .attr("r", 8)
              .attr("fill", color)
              .attr("stroke", "#fff")
              .attr("stroke-width", 2);
          };

          addDot(region.a1a_2024, LINE_COLORS["2024"]);
          addDot(region.a1a_2020, LINE_COLORS["2020"]);
          addDot(region.a1a_2016, LINE_COLORS["2016"]);
        })
        .on("mouseout", function () {
          tooltip.style("display", "none");
          chart.selectAll(".hover-line").remove();
          chart.selectAll(".hover-dot").remove();
        });
    };

    drawChart();

    const resizeObserver = new ResizeObserver(() => drawChart());
    resizeObserver.observe(parent);

    return () => resizeObserver.disconnect();
  }, [eavsTrends]);

  if (isEAVSTrendsLoading) {
    return (
      <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100%" }}>
        Loading...
      </Box>
    );
  }

  if (!eavsTrends) {
    return (
      <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100%" }}>
        No data available for this state
      </Box>
    );
  }

  return (
    <Box
      sx={{
        width: "90%",
        height: "85%",
        marginRight: "10%",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Typography variant="h6" sx={{ fontWeight: 600, textAlign: "center" }}>
        EAVS Registered Voter Trends
      </Typography>

      <Typography variant="body2" sx={{ color: "text.secondary", textAlign: "center" }}>
      </Typography>

      <Box sx={{ width: "100%", height: "100%", position: "relative" }}>
        <svg ref={svgRef} style={{ width: "100%", height: "100%" }} />
        <div
          ref={tooltipRef}
          style={{
            position: "fixed",
            display: "none",
            backgroundColor: "white",
            border: "1px solid #ccc",
            borderRadius: "4px",
            padding: "8px 12px",
            fontSize: "12px",
            boxShadow: "0 2px 8px rgba(0,0,0,0.15)",
            pointerEvents: "none",
            zIndex: 9999,
          }}
        />
      </Box>
    </Box>
  );
};

export default EAVSRegisteredTrends;

import { useEffect, useRef, useState } from "react";
import * as d3 from "d3";
import { useVotingVSRejectBubbleChart } from "../queries/dataHooks";
import { useParams } from "react-router-dom";

interface EquipmentQualityData {
  regionId: number;
  regionName: string;
  equipmentQualityScore: number;
  percentRejectedBallots: number;
  totalBallots: number;
}

const EquipmentQualityVsRejection = () => {
  const { id } = useParams();
  if (!id) return <div>Loading...</div>;

  const containerRef = useRef<HTMLDivElement>(null);
  const svgRef = useRef<SVGSVGElement>(null);
  const tooltipRef = useRef<HTMLDivElement>(null);

  const { votingVSRejectBubbleChart, isVotingVSRejectBubbleChartLoading } =
    useVotingVSRejectBubbleChart(id);

  const data = Array.isArray(votingVSRejectBubbleChart)
    ? (votingVSRejectBubbleChart as EquipmentQualityData[])
    : [];

  const [resizeKey, setResizeKey] = useState(0);
  const isLoading = isVotingVSRejectBubbleChartLoading;

  useEffect(() => {
    const handleResize = () => setResizeKey((k) => k + 1);
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  useEffect(() => {
    if (
      !data ||
      data.length === 0 ||
      !containerRef.current ||
      !svgRef.current ||
      !tooltipRef.current
    ) {
      return;
    }

    const container = containerRef.current;
    const svg = d3.select(svgRef.current);

    const containerWidth = container.clientWidth;
    const containerHeight = container.clientHeight;
    if (!containerWidth || !containerHeight) return;

    // Clear previous content
    svg.selectAll("*").remove();

    const margin = { top: 60, right: 40, bottom: 70, left: 80 };
    const width = containerWidth - margin.left - margin.right;
    const height = containerHeight - margin.top - margin.bottom;

    const g = svg
      .attr("width", containerWidth)
      .attr("height", containerHeight)
      .append("g")
      .attr("transform", `translate(${margin.left},${margin.top})`);

    // Scales
    const xScale = d3.scaleLinear().domain([0, 1]).range([0, width]);

    const maxYValue = d3.max(data, (d) => d.percentRejectedBallots) || 5;
    const yScale = d3
      .scaleLinear()
      .domain([0, maxYValue * 1.15])
      .range([height, 0]);

    const sizeScale = d3
      .scaleSqrt()
      .domain([
        d3.min(data, (d) => d.totalBallots) || 0,
        d3.max(data, (d) => d.totalBallots) || 10000,
      ])
      .range([7, 30]);

    // Grid lines
    g.append("g")
      .attr("class", "grid")
      .attr("opacity", 0.1)
      .call(
        d3
          .axisLeft(yScale)
          .tickSize(-width)
          .tickFormat(() => "")
      );

    g.append("g")
      .attr("class", "grid")
      .attr("opacity", 0.1)
      .attr("transform", `translate(0,${height})`)
      .call(
        d3
          .axisBottom(xScale)
          .tickSize(-height)
          .tickFormat(() => "")
      );

    // X-axis
    g.append("g")
      .attr("transform", `translate(0,${height})`)
      .call(
        d3
          .axisBottom(xScale)
          .ticks(10)
          .tickFormat((d) => `${(+d * 100).toFixed(0)}%`)
      )
      .style("font-size", "12px");

    g.append("text")
      .attr("x", width / 2)
      .attr("y", height + 45)
      .style("text-anchor", "middle")
      .style("font-size", "14px")
      .style("font-weight", "bold")
      .text("Equipment Quality Score");

    // Y-axis
    g.append("g")
      .call(
        d3
          .axisLeft(yScale)
          .ticks(8)
          .tickFormat((d) => `${(+d).toFixed(1)}%`)
      )
      .style("font-size", "12px");

    g.append("text")
      .attr("transform", "rotate(-90)")
      .attr("x", -height / 2)
      .attr("y", -50)
      .style("text-anchor", "middle")
      .style("font-size", "14px")
      .style("font-weight", "bold")
      .text("Percentage of Rejected Ballots");

    // Title
    g.append("text")
      .attr("x", width / 2)
      .attr("y", -20)
      .style("text-anchor", "middle")
      .style("font-size", "16px")
      .style("font-weight", "bold")
      .text("Equipment Quality vs Ballot Rejection Rate");

    // Tooltip functions
    const showTooltip = (event: MouseEvent, d: EquipmentQualityData) => {
      const tooltip = d3.select(tooltipRef.current);

      tooltip
        .style("opacity", 1)
        .style("left", `${event.pageX + 15}px`)
        .style("top", `${event.pageY - 15}px`)
        .html(`
          <strong>${d.regionName}</strong><br/>
          <strong>Quality:</strong> ${(d.equipmentQualityScore * 100).toFixed(1)}%<br/>
          <strong>Rejected Ballots:</strong> ${d.percentRejectedBallots.toFixed(2)}%<br/>
          <strong>Total Ballots:</strong> ${d.totalBallots.toLocaleString()}
        `);
    };

    const hideTooltip = () => {
      d3.select(tooltipRef.current).style("opacity", 0);
    };

    // Draw bubble groups
    const bubbleGroups = g
      .selectAll("g.bubble")
      .data(data)
      .enter()
      .append("g")
      .attr("class", "bubble")
      .attr(
        "transform",
        (d) =>
          `translate(${xScale(d.equipmentQualityScore)},${yScale(
            d.percentRejectedBallots
          )})`
      );

    // Draw circles (single color)
    bubbleGroups
      .append("circle")
      .attr("r", (d) => sizeScale(d.totalBallots))
      .attr("fill", "#4caf50")
      .attr("opacity", 0.7)
      .attr("stroke", "#fff")
      .attr("stroke-width", 1.5)
      .style("cursor", "pointer");

    // Hover interactions
    bubbleGroups
      .on("mouseover", function (event, d) {
        d3.select(this)
          .select("circle")
          .transition()
          .duration(200)
          .attr("opacity", 1)
          .attr("stroke-width", 2.5);

        showTooltip(event as MouseEvent, d);
      })
      .on("mousemove", (event, d) => showTooltip(event as MouseEvent, d))
      .on("mouseout", function () {
        d3.select(this)
          .select("circle")
          .transition()
          .duration(200)
          .attr("opacity", 0.7)
          .attr("stroke-width", 1.5);

        hideTooltip();
      });

    // ✅ Legend: ONLY bubble size note
    const legend = g.append("g").attr("transform", `translate(${width - 220}, 10)`);

    legend
      .append("circle")
      .attr("cx", 0)
      .attr("cy", 0)
      .attr("r", 8)
      .attr("fill", "none")
      .attr("stroke", "#666")
      .attr("stroke-width", 1);

    legend
      .append("text")
      .attr("x", 15)
      .attr("y", 5)
      .style("font-size", "12px")
      .style("fill", "#444")
      .text("Bubble Size = Total Ballots");
  }, [data, resizeKey]);

  if (isLoading) return <div>Loading...</div>;

  return (
    <div
      ref={containerRef}
      style={{
        position: "relative",
        width: "90%",
        height: "85%",
        marginRight: "10%",
        overflow: "hidden",
      }}
    >
      <svg
        ref={svgRef}
        style={{
          width: "100%",
          height: "100%",
          display: "block",
        }}
      />
      <div
        ref={tooltipRef}
        style={{
          position: "fixed",
          opacity: 0,
          backgroundColor: "rgba(0, 0, 0, 0.9)",
          color: "white",
          padding: "12px",
          borderRadius: "6px",
          pointerEvents: "none",
          fontSize: "12px",
          lineHeight: "1.6",
          zIndex: 10000,
          boxShadow: "0 2px 8px rgba(0,0,0,0.3)",
        }}
      />
    </div>
  );
};

export default EquipmentQualityVsRejection;

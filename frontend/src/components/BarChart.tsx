import * as d3 from "d3";
import { useEffect, useRef } from "react";
import { useLocation } from "react-router-dom";

interface BarChartProps {
	data: { label: string; value: number }[];
	width?: number | string;
	height?: number | string;
	maxBars?: number;
	title?: string;
	horizontal?: boolean;
	backgroundColor?: string;
}

const BarChart = ({
	data,
	width = "100%",
	height = "100%",
	maxBars = 20,
	title = "",
	horizontal = false,
	backgroundColor = "transparent",
}: BarChartProps) => {
	const { pathname } = useLocation();
	let currentPage = pathname.split("/").pop() || "";
	let needWider = false;
	if (currentPage === "mail-ballot-rejections") {
		needWider = true
	}
	const svgRef = useRef<SVGSVGElement | null>(null);

	useEffect(() => {
		if (!data || data.length === 0) return;
		const svg = d3.select(svgRef.current);
		const parent = svgRef.current?.parentElement;
		if (!parent) return;

		const drawChart = (numericWidth: number, numericHeight: number) => {
			let leftBound = 100;
			svg.selectAll("*").remove();
			if (needWider) {
				leftBound = 60;
			}
			const margin = { top: 50, right: 30, bottom: 80, left: leftBound };
			const innerWidth = numericWidth - margin.left - margin.right;
			const innerHeight = numericHeight - margin.top - margin.bottom;
			const visibleData = data.slice(0, maxBars);

			// Background
			svg
				.append("rect")
				.attr("width", numericWidth)
				.attr("height", numericHeight)
				.attr("fill", backgroundColor);

			const chart = svg
				.append("g")
				.attr("transform", `translate(${margin.left},${margin.top})`);

			// Vertical bar chart
			if (!horizontal) {
				const x = d3
					.scaleBand()
					.domain(visibleData.map((d) => d.label))
					.range([0, innerWidth])
					.padding(0.2);

				const maxValue = d3.max(visibleData, (d) => d.value)!;

				const y = d3
					.scaleLinear()
					.domain(maxValue === 0 ? [0, 1] : [0, maxValue])
					.nice()
					.range([innerHeight, 0]);

				// Horizontal dashed gridlines
				chart
					.append("g")
					.attr("class", "grid")
					.call(
						d3.axisLeft(y)
							.tickSize(-innerWidth)
							.tickFormat(() => "")
					)
					.call((g) => g.select(".domain").remove())
					.selectAll("line")
					.attr("stroke", "#ccc")
					.attr("stroke-dasharray", "3 3")
					.attr("opacity", 0.7);

				// Vertical dashed gridlines
				chart
					.append("g")
					.attr("class", "grid-vertical")
					.attr("transform", `translate(0,${innerHeight})`)
					.call(
						d3.axisBottom(x)
							.tickSize(-innerHeight)
							.tickFormat(() => "")
					)
					.call((g) => g.select(".domain").remove())
					.selectAll("line")
					.attr("stroke", "#ddd")
					.attr("stroke-dasharray", "3 3")
					.attr("opacity", 0.6);

				// Axes
				chart
					.append("g")
					.attr("transform", `translate(0,${innerHeight})`)
					.call(d3.axisBottom(x))
					.selectAll("text")
					.attr("transform", "rotate(-25)")
					.style("text-anchor", "end")
					.style("font-size", "11px");

				chart
					.append("g")
					.call(d3.axisLeft(y).tickFormat(d3.format(",")))
					.style("font-size", "12px");

				// Bars
				chart
					.selectAll("rect")
					.data(visibleData)
					.join("rect")
					.attr("x", (d) => x(d.label)!)
					.attr("y", (d) => y(d.value))
					.attr("width", x.bandwidth())
					.attr("height", (d) => innerHeight - y(d.value))
					.attr("fill", "#66bb6a");

				// Values above bars
				chart
					.selectAll("text.value")
					.data(visibleData)
					.join("text")
					.attr("class", "value")
					.attr("x", (d) => x(d.label)! + x.bandwidth() / 2)
					.attr("y", (d) => y(d.value) - 5)
					.attr("text-anchor", "middle")
					.style("font-size", "12px")
					.text((d) => d3.format(",")(d.value));
			}

			// Horizontal bar chart
			else {
				const y = d3
					.scaleBand()
					.domain(visibleData.map((d) => d.label))
					.range([0, innerHeight])
					.padding(0.2);

				const maxValue = d3.max(visibleData, (d) => d.value)!;
				const x = d3
					.scaleLinear()
					.domain(maxValue === 0 ? [0, 1] : [0, maxValue])
					.nice()
					.range([0, innerWidth]);

				// Vertical dashed gridlines
				chart
					.append("g")
					.attr("class", "grid")
					.attr("transform", `translate(0,${innerHeight})`)
					.call(
						d3.axisBottom(x)
							.tickSize(-innerHeight)
							.tickFormat(() => "")
					)
					.call((g) => g.select(".domain").remove())
					.selectAll("line")
					.attr("stroke", "#ccc")
					.attr("stroke-dasharray", "3 3")
					.attr("opacity", 0.6);

				// Axes
				const yAxis = chart.append("g").call(d3.axisLeft(y)).style("font-size", "12px");
				yAxis.selectAll("line").remove();

				// Wrap Y-axis labels (~22 chars per line, max 2 lines, aligned for 1–2 cases)
				yAxis.selectAll("text").each(function (d) {
					const self = d3.select(this);
					const label = String(d);
					const maxChars = 19;
					const lineHeight = 14;

					// Split words safely without breaking mid-word
					const words = label.split(/\s+/);
					const lines: string[] = [];
					let currentLine = "";

					words.forEach((word) => {
						if ((currentLine + word).length > maxChars) {
							lines.push(currentLine.trim());
							currentLine = word + " ";
						} else {
							currentLine += word + " ";
						}
					});
					if (currentLine) lines.push(currentLine.trim());

					if (lines.length > 2) {
						lines.length = 2;
						lines[1] = lines[1].slice(0, maxChars - 3).trim() + "…";
					}

					// Clear and reset label
					self.text(null);

					const x = -7;
					const startDy = lines.length === 2 ? -lineHeight / 2 + 3 : 0.32 * 12;

					lines.forEach((line, i) => {
						self
							.append("tspan")
							.text(line)
							.attr("x", x)
							.attr("dy", i === 0 ? startDy : lineHeight)
							.attr("text-anchor", "end");
					});
				});

				chart
					.append("g")
					.attr("transform", `translate(0,${innerHeight})`)
					.call(d3.axisBottom(x))
					.style("font-size", "12px");

				// Bars
				chart
					.selectAll("rect")
					.data(visibleData)
					.join("rect")
					.attr("y", (d) => y(d.label)!)
					.attr("x", 0)
					.attr("height", y.bandwidth())
					.attr("width", (d) => x(d.value))
					.attr("fill", "#4da3ff");

				// Values
				chart
					.selectAll("text.value")
					.data(visibleData)
					.join("text")
					.attr("class", "value")
					.attr("x", (d) => x(d.value) + 5)
					.attr("y", (d) => y(d.label)! + y.bandwidth() / 2 + 4)
					.attr("text-anchor", "start")
					.style("font-size", "12px")
					.text((d) => d.value);
			}

			// Title
			if (title) {
				svg
					.append("text")
					.attr("x", numericWidth / 2)
					.attr("y", 25)
					.attr("text-anchor", "middle")
					.style("font-size", "16px")
					.style("font-weight", "bold")
					.text(title);
			}
		};

		// initial draw
		const initialWidth = parent.clientWidth
		const initialHeight = parent.clientHeight
		drawChart(initialWidth, initialHeight)

		// redraw chart whenever parent size changes
		const resizeObserver = new ResizeObserver(() => {
			const newWidth = parent.clientWidth;
			const newHeight = parent.clientHeight;
			drawChart(newWidth, newHeight);
		});
		resizeObserver.observe(parent);

		return () => resizeObserver.disconnect();
	}, [data, width, height, maxBars, title, horizontal, backgroundColor]);

	return <svg ref={svgRef} width={width} height={height} style={{ overflow: "visible" }} />;
};

export default BarChart;
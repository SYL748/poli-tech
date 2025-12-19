import { useEffect, useRef, useState } from 'react';
import * as d3 from 'd3';
import { useDropBoxVotingBubbleChart } from '../queries/dataHooks';
import { useParams } from 'react-router-dom';

interface BubbleData {
    regionName: string;
    republicanVotes2024: number;
    democratVotes2024: number;
    percentRepublican: number;
    totalBallots: number;
    percentMailInBallots: number;
}

const MailInBallotBubbleChart = () => {
    const { id } = useParams();
    if (!id) return <div>Loading...</div>;

    const containerRef = useRef<HTMLDivElement>(null);
    const svgRef = useRef<SVGSVGElement>(null);
    const tooltipRef = useRef<HTMLDivElement>(null);

    const { mailBallotRejectionBubbleChart, isMailBallotRejectionBubbleChartLoading } = useDropBoxVotingBubbleChart(id);

    const data = (mailBallotRejectionBubbleChart || []) as BubbleData[];

    // Simple key that bumps on resize to force redraw
    const [resizeKey, setResizeKey] = useState(0);

    // Listen for window resize and bump the key
    useEffect(() => {
        const handleResize = () => {
            setResizeKey(k => k + 1);
        };

        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    useEffect(() => {
        if (!svgRef.current || !containerRef.current || !data.length) return;

        // Clear previous chart
        d3.select(svgRef.current).selectAll('*').remove();

        // Get container dimensions (re-read them every time effect runs)
        const containerWidth = containerRef.current.clientWidth;
        const containerHeight = containerRef.current.clientHeight;
        if (!containerWidth || !containerHeight) return;

        // Dimensions and margins
        const margin = { top: 60, right: 40, bottom: 70, left: 80 };
        const width = containerWidth - margin.left - margin.right;
        const height = containerHeight - margin.top - margin.bottom;

        // Create SVG
        const svg = d3.select(svgRef.current)
            .attr('width', width + margin.left + margin.right)
            .attr('height', height + margin.top + margin.bottom)
            .append('g')
            .attr('transform', `translate(${margin.left},${margin.top})`);

        // Scales
        const xScale = d3.scaleLinear()
            .domain([0, 100]) // Percent republican range (API returns 0-100)
            .range([0, width]);

        const yScale = d3.scaleLinear()
            .domain([0, d3.max(data, d => d.percentMailInBallots) || 50])
            .range([height, 0]);

        const sizeScale = d3.scaleSqrt()
            .domain([
                d3.min(data, d => d.totalBallots) || 0,
                d3.max(data, d => d.totalBallots) || 0
            ])
            .range([7, 30]);

        // Color scale based on party dominance
        const colorScale = (d: BubbleData) =>
            d.republicanVotes2024 > d.democratVotes2024 ? '#e53935' : '#1e88e5';

        // Add grid lines
        svg.append('g')
            .attr('class', 'grid')
            .attr('opacity', 0.1)
            .call(
                d3.axisLeft(yScale)
                    .tickSize(-width)
                    .tickFormat(() => '')
            );

        svg.append('g')
            .attr('class', 'grid')
            .attr('opacity', 0.1)
            .attr('transform', `translate(0,${height})`)
            .call(
                d3.axisBottom(xScale)
                    .tickSize(-height)
                    .tickFormat(() => '')
            );

        // X-axis
        svg.append('g')
            .attr('transform', `translate(0,${height})`)
            .call(
                d3.axisBottom(xScale)
                    .tickFormat(d => `${(+d).toFixed(0)}%`)
            )
            .style('font-size', '12px');

        svg.append('text')
            .attr('x', width / 2)
            .attr('y', height + 45)
            .style('text-anchor', 'middle')
            .style('font-size', '14px')
            .style('font-weight', 'bold')
            .text('Percentage of Republican Votes');

        // Y-axis
        svg.append('g')
            .call(
                d3.axisLeft(yScale)
                    .tickFormat(d => `${(+d).toFixed(0)}%`)
            )
            .style('font-size', '12px');

        svg.append('text')
            .attr('transform', 'rotate(-90)')
            .attr('x', -height / 2)
            .attr('y', -50)
            .style('text-anchor', 'middle')
            .style('font-size', '14px')
            .style('font-weight', 'bold')
            .text('Percentage of Mail-In Ballots');

        // Title
        svg.append('text')
            .attr('x', width / 2)
            .attr('y', -15)
            .style('text-anchor', 'middle')
            .style('font-size', '16px')
            .style('font-weight', 'bold')
            .text('Mail-In Ballot Usage by Political Party Affiliation');

        // Reference line at 50% Republican
        svg.append('line')
            .attr('x1', xScale(50))
            .attr('x2', xScale(50))
            .attr('y1', 0)
            .attr('y2', height)
            .attr('stroke', '#666')
            .attr('stroke-width', 1)
            .attr('stroke-dasharray', '5,5')
            .attr('opacity', 0.5);

        // Tooltip functions
        const showTooltip = (event: MouseEvent, d: BubbleData) => {
            const tooltip = d3.select(tooltipRef.current);
            const partyWinner =
                d.republicanVotes2024 > d.democratVotes2024 ? 'Republican' : 'Democrat';

            tooltip
                .style('opacity', 1)
                .style('left', `${event.pageX + 15}px`)
                .style('top', `${event.pageY - 15}px`)
                .html(`
                    <strong>${d.regionName}</strong><br/>
                    <strong>Party:</strong> ${partyWinner}<br/>
                    <strong>Republican Votes:</strong> ${d.republicanVotes2024.toLocaleString()}<br/>
                    <strong>Democratic Votes:</strong> ${d.democratVotes2024.toLocaleString()}<br/>
                    <strong>% Republican:</strong> ${d.percentRepublican.toFixed(1)}%<br/>
                    <strong>% Mail-In:</strong> ${d.percentMailInBallots.toFixed(1)}%<br/>
                    <strong>Total Ballots:</strong> ${d.totalBallots.toLocaleString()}
                `);
        };

        const hideTooltip = () => {
            d3.select(tooltipRef.current).style('opacity', 0);
        };

        // Draw bubble groups (circle + text)
        const bubbleGroups = svg
            .selectAll('g.bubble')
            .data(data)
            .enter()
            .append('g')
            .attr('class', 'bubble')
            .attr('transform', d =>
                `translate(${xScale(d.percentRepublican)},${yScale(
                    d.percentMailInBallots
                )})`
            );

        // Draw circles
        bubbleGroups
            .append('circle')
            .attr('r', d => sizeScale(d.totalBallots))
            .attr('fill', d => colorScale(d))
            .attr('opacity', 0.7)
            .attr('stroke', '#fff')
            .attr('stroke-width', 1.5)
            .style('cursor', 'pointer');

        // Add hover interactions
        bubbleGroups
            .on('mouseover', function (event, d) {
                d3.select(this)
                    .select('circle')
                    .transition()
                    .duration(200)
                    .attr('opacity', 1)
                    .attr('stroke-width', 2.5);

                showTooltip(event as MouseEvent, d);
            })
            .on('mousemove', (event, d) => showTooltip(event as MouseEvent, d))
            .on('mouseout', function () {
                d3.select(this)
                    .select('circle')
                    .transition()
                    .duration(200)
                    .attr('opacity', 0.7)
                    .attr('stroke-width', 1.5);

                hideTooltip();
            });

        // Legend
        const legend = svg.append('g')
            .attr('transform', `translate(${width - 150}, 10)`);

        // Republican legend
        legend.append('circle')
            .attr('cx', 0)
            .attr('cy', 0)
            .attr('r', 8)
            .attr('fill', '#e53935')
            .attr('opacity', 0.7);

        legend.append('text')
            .attr('x', 15)
            .attr('y', 5)
            .style('font-size', '12px')
            .text('Republican');

        // Democrat legend
        legend.append('circle')
            .attr('cx', 0)
            .attr('cy', 25)
            .attr('r', 8)
            .attr('fill', '#1e88e5')
            .attr('opacity', 0.7);

        legend.append('text')
            .attr('x', 15)
            .attr('y', 30)
            .style('font-size', '12px')
            .text('Democratic');

        // Empty circle
        legend.append('circle')
            .attr('cx', -10)
            .attr('cy', 50)
            .attr('r', 5)
            .attr('fill', 'none')
            .attr('stroke', '#666')
            .attr('stroke-width', 1);

        // Size note text
        legend.append('text')
            .attr('x', 0)
            .attr('y', 55)
            .style('font-size', '11px')
            .style('font-style', 'italic')
            .style('fill', '#666')
            .text('Bubble Size = Total Ballots');

    }, [data, resizeKey]); // redraw when data or window size changes

    if(isMailBallotRejectionBubbleChartLoading) return <div> Loading... </div>

    return (
        <div
            ref={containerRef}
            style={{
                position: 'relative',
                width: '90%',
                height: '85%',
                marginRight: '10%',
                overflow: 'hidden',
            }}
        >
            <svg
                ref={svgRef}
                style={{
                    width: '100%',
                    height: '100%',
                    display: 'block',
                }}
            />
            <div
                ref={tooltipRef}
                style={{
                    position: 'fixed',
                    opacity: 0,
                    backgroundColor: 'rgba(0, 0, 0, 0.9)',
                    color: 'white',
                    padding: '12px',
                    borderRadius: '6px',
                    pointerEvents: 'none',
                    fontSize: '12px',
                    lineHeight: '1.6',
                    zIndex: 10000,
                    boxShadow: '0 2px 8px rgba(0,0,0,0.3)',
                }}
            />
        </div>
    );
};

export default MailInBallotBubbleChart;
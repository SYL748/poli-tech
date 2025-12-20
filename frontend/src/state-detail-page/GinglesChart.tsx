import { useEffect, useLayoutEffect, useMemo, useRef, useState } from "react"
import * as d3 from "d3"
import { Box, FormControl, InputLabel, Select, MenuItem } from "@mui/material"
import { useParams } from "react-router-dom"
import { useCoefficient, useGingleChart } from "../queries/dataHooks"

type Party = "Democratic" | "Republican"
type DemographicKey = "White" | "Hispanic" | "African American"

type PrecinctResult = {
    county: string
    precinctName: string
    party: Party | string
    votePercent: number
    whitePct: number
    hispanicPct: number
    africanAmericanPct: number
}

type RegressionCoefficients = {
    coefA: number
    coefB: number
    coefC: number
}

type CoefficientRow = {
    party: Party
    demographic: DemographicKey
    coefA: number
    coefB: number
    coefC: number
}

const demographicFieldMap: Record<DemographicKey, keyof PrecinctResult> = {
    White: "whitePct",
    Hispanic: "hispanicPct",
    "African American": "africanAmericanPct"
}

const MARGIN = { top: 60, right: 40, bottom: 70, left: 80 }


function useElementSize<T extends HTMLElement>(ref: React.RefObject<T | null>) {
    const [size, setSize] = useState({ width: 0, height: 0 })

    useLayoutEffect(() => {
        const el = ref.current
        if (!el) return

        const measure = () => {
            const r = el.getBoundingClientRect()
            setSize({ width: r.width, height: r.height })
        }

        measure()
        const ro = new ResizeObserver(measure)
        ro.observe(el)

        return () => ro.disconnect()
    }, [ref])

    return size
}


const GinglesChart = () => {
    const { id } = useParams()
    const stateId = id ?? ""

    const { GingleChart } = useGingleChart(stateId)
    const { Coefficient } = useCoefficient(stateId)

    const containerRef = useRef<HTMLDivElement>(null)
    const svgRef = useRef<SVGSVGElement>(null)
    const canvasRef = useRef<HTMLCanvasElement>(null)

    const { width: W, height: H } = useElementSize(containerRef)

    const [selectedDemographic, setSelectedDemographic] =
        useState<DemographicKey>("African American")

    const precinctResults = useMemo<PrecinctResult[]>(() => {
        if (!Array.isArray(GingleChart)) return []
        return GingleChart
    }, [GingleChart])

    const regressionCoeffs = useMemo(() => {
        const map: Record<
            DemographicKey,
            Partial<Record<Party, RegressionCoefficients>>
        > = { White: {}, Hispanic: {}, "African American": {} }

        if (!Array.isArray(Coefficient)) return map

        for (const r of Coefficient as CoefficientRow[]) {
            map[r.demographic][r.party] = {
                coefA: r.coefA,
                coefB: r.coefB,
                coefC: r.coefC
            }
        }

        return map
    }, [Coefficient])

    const demographicField = demographicFieldMap[selectedDemographic]


    const screenPoints = useMemo(() => {
        const innerW = W - MARGIN.left - MARGIN.right
        const innerH = H - MARGIN.top - MARGIN.bottom
        if (innerW <= 0 || innerH <= 0) return []

        const xScale = d3.scaleLinear().domain([0, 100]).range([0, innerW])
        const yScale = d3.scaleLinear().domain([0, 100]).range([innerH, 0])

        return precinctResults.map(d => {
            const xVal = Number(d[demographicField])
            const yVal = d.votePercent

            return {
                sx: MARGIN.left + xScale(xVal),
                sy: MARGIN.top + yScale(yVal),
                d
            }
        })
    }, [W, H, precinctResults, demographicField])


    useEffect(() => {
        if (!svgRef.current || !canvasRef.current) return

        const innerW = W - MARGIN.left - MARGIN.right
        const innerH = H - MARGIN.top - MARGIN.bottom
        if (innerW <= 0 || innerH <= 0) return


        const svgRoot = d3.select(svgRef.current)
        svgRoot.attr("width", W).attr("height", H)
        svgRoot.selectAll("*").remove()

        const svg = svgRoot
            .append("g")
            .attr("transform", `translate(${MARGIN.left},${MARGIN.top})`)

        const xScale = d3.scaleLinear().domain([0, 100]).range([0, innerW])
        const yScale = d3.scaleLinear().domain([0, 100]).range([innerH, 0])

        svg
            .append("g")
            .attr("transform", `translate(0,${innerH})`)
            .call(d3.axisBottom(xScale).ticks(8).tickFormat(d => `${d}%`))
            .call(g =>
                g.selectAll("text")
                    .style("font-size", "14px")
                    .style("font-weight", "600")
            )

        svg
            .append("g")
            .call(d3.axisLeft(yScale).ticks(6).tickFormat(d => `${d}%`))
            .call(g =>
                g.selectAll("text")
                    .style("font-size", "14px")
                    .style("font-weight", "600")
            )


        svg
            .append("text")
            .attr("x", innerW / 2)
            .attr("y", innerH + 50)
            .attr("text-anchor", "middle")
            .attr("font-size", 14)
            .attr("font-weight", 700)
            .text(`Percentage of ${selectedDemographic} Population`)

        svg
            .append("text")
            .attr("transform", "rotate(-90)")
            .attr("x", -innerH / 2)
            .attr("y", -55)
            .attr("text-anchor", "middle")
            .attr("font-size", 14)
            .attr("font-weight", 700)
            .text("Vote Percentage")

        svg
            .append("text")
            .attr("x", innerW / 2)
            .attr("y", -25)
            .attr("text-anchor", "middle")
            .attr("font-size", 16)
            .attr("font-weight", 800)
            .text("Gingles Chart: Racially Polarized Voting")


        const drawRegression = (party: Party, color: string) => {
            const c = regressionCoeffs[selectedDemographic]?.[party]
            if (!c) return

            const data: [number, number][] = d3.range(0, 101).map(x => [
                x,
                Math.max(0, Math.min(100, c.coefA * x * x + c.coefB * x + c.coefC))
            ])

            const lineGen = d3
                .line<[number, number]>()
                .x(d => xScale(d[0]))
                .y(d => yScale(d[1]))

            svg
                .append("path")
                .attr("fill", "none")
                .attr("stroke", color)
                .attr("stroke-width", 3)
                .attr("opacity", 1)
                .attr("d", lineGen(data)!)
        }

        drawRegression("Democratic", "#0d47a1") // dark navy blue
        drawRegression("Republican", "#8e0000") // dark deep red


        const canvas = canvasRef.current
        const ctx = canvas.getContext("2d")!
        const dpr = window.devicePixelRatio || 1

        canvas.width = W * dpr
        canvas.height = H * dpr
        canvas.style.width = `${W}px`
        canvas.style.height = `${H}px`

        ctx.setTransform(dpr, 0, 0, dpr, 0, 0)
        ctx.clearRect(0, 0, W, H)

        for (const p of screenPoints) {
            ctx.fillStyle =
                p.d.party === "Democratic"
                    ? "rgba(21,101,192,0.30)"
                    : "rgba(198,40,40,0.30)"
            ctx.beginPath()
            ctx.arc(p.sx, p.sy, 3.5, 0, Math.PI * 2)
            ctx.fill()
        }
    }, [W, H, screenPoints, regressionCoeffs, selectedDemographic])


    return (
        <Box
            sx={{
                mt: "3%",
                width: "90%",
                height: "92%",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                mr: "10%"
            }}
        >
            <Box
                ref={containerRef}
                sx={{
                    position: "relative",
                    width: "100%",
                    flex: 1,
                    overflow: "hidden",
                    minHeight: 520
                }}
            >
                <Box sx={{ position: "absolute", top: 5, left: 10, zIndex: 5 }}>
                    <FormControl size="small" sx={{ minWidth: 170 }}>
                        <InputLabel>Demographic Group</InputLabel>
                        <Select
                            value={selectedDemographic}
                            label="Demographic Group"
                            onChange={e =>
                                setSelectedDemographic(e.target.value as DemographicKey)
                            }
                        >
                            <MenuItem value="White">White</MenuItem>
                            <MenuItem value="Hispanic">Hispanic</MenuItem>
                            <MenuItem value="African American">African American</MenuItem>
                        </Select>
                    </FormControl>
                </Box>

                <canvas
                    ref={canvasRef}
                    style={{
                        position: "absolute",
                        inset: 0,
                        width: "100%",
                        height: "100%",
                        zIndex: 1
                    }}
                />

                <svg
                    ref={svgRef}
                    style={{
                        position: "absolute",
                        inset: 0,
                        width: "100%",
                        height: "100%",
                        pointerEvents: "none",
                        zIndex: 2
                    }}
                />
            </Box>
        </Box>
    )
}

export default GinglesChart

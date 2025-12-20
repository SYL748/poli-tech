import { useEffect, useMemo, useRef, useState } from "react"
import { useParams } from "react-router-dom"
import * as d3 from "d3"
import Box from "@mui/material/Box"
import Checkbox from "@mui/material/Checkbox"
import FormControlLabel from "@mui/material/FormControlLabel"
import Typography from "@mui/material/Typography"
import { useEIEquipment } from "../queries/dataHooks"

const MARGIN = { top: 30, right: 20, bottom: 55, left: 60 }

// FORCE smooth knobs (same idea as before)
const FIT_GRID_POINTS = 260
const PRE_SMOOTH_WINDOW = 9

type Row = {
  equipment_quality: number
  probability_density: number
}

function smoothSeries(data: Row[], window = PRE_SMOOTH_WINDOW): Row[] {
  const half = Math.floor(window / 2)
  return data.map((d, i) => {
    let sum = 0
    let count = 0
    for (let k = -half; k <= half; k++) {
      const idx = i + k
      if (idx >= 0 && idx < data.length) {
        sum += data[idx].probability_density
        count++
      }
    }
    return { equipment_quality: d.equipment_quality, probability_density: sum / count }
  })
}

function trimTailsSmart(data: Row[], epsRatio = 0.001, padPoints = 20): Row[] {
  if (data.length === 0) return []

  const peak = Math.max(...data.map(d => d.probability_density))
  if (!Number.isFinite(peak) || peak <= 0) return []

  const eps = peak * epsRatio

  const snapped = data.map(d => ({
    ...d,
    probability_density: d.probability_density <= eps ? 0 : d.probability_density
  }))

  let firstNonZero = -1
  for (let i = 0; i < snapped.length; i++) {
    if (snapped[i].probability_density > 0) {
      firstNonZero = i
      break
    }
  }

  let lastNonZero = -1
  for (let i = snapped.length - 1; i >= 0; i--) {
    if (snapped[i].probability_density > 0) {
      lastNonZero = i
      break
    }
  }

  if (firstNonZero === -1 || lastNonZero === -1 || firstNonZero >= lastNonZero) return []

  const start = firstNonZero > 0 ? firstNonZero - 1 : Math.max(0, firstNonZero - padPoints)
  const end =
    lastNonZero < snapped.length - 1 ? lastNonZero + 1 : Math.min(snapped.length - 1, lastNonZero + padPoints)

  let out = snapped.slice(start, end + 1).map(d => ({ ...d }))
  if (out.length < 2) return []

  while (out.length > 2 && out[0].probability_density === 0 && out[1].probability_density === 0) out.shift()
  while (
    out.length > 2 &&
    out[out.length - 1].probability_density === 0 &&
    out[out.length - 2].probability_density === 0
  )
    out.pop()

  return out
}

// FORCE smooth: fit a single Gaussian (weighted by density), then render that curve.
function gaussianFit(xs: number[], ys: number[]) {
  const pairs = xs
    .map((x, i) => ({ x, y: ys[i] }))
    .filter(p => Number.isFinite(p.x) && Number.isFinite(p.y) && p.y > 0)

  if (pairs.length < 3) return null

  const sumW = pairs.reduce((acc, p) => acc + p.y, 0)
  if (!Number.isFinite(sumW) || sumW <= 0) return null

  const mu = pairs.reduce((acc, p) => acc + p.x * p.y, 0) / sumW
  const varX = pairs.reduce((acc, p) => acc + p.y * (p.x - mu) * (p.x - mu), 0) / sumW
  let sigma = Math.sqrt(Math.max(0, varX))

  const yMax = pairs.reduce((acc, p) => Math.max(acc, p.y), 0)

  const minX = Math.min(...pairs.map(p => p.x))
  const maxX = Math.max(...pairs.map(p => p.x))
  const span = Math.max(1e-9, maxX - minX)

  // same "force smooth" sigma floor
  const sigmaFloor = span / 18
  if (!Number.isFinite(sigma) || sigma < sigmaFloor) sigma = sigmaFloor

  return { mu, sigma, amp: yMax, minX, maxX }
}

function useResizeObserver<T extends HTMLElement>(ref: React.RefObject<T | null>) {
  const [size, setSize] = useState({ width: 0, height: 0 })

  useEffect(() => {
    const el = ref.current
    if (!el) return

    const ro = new ResizeObserver(entries => {
      const cr = entries[0]?.contentRect
      if (!cr) return
      setSize({ width: cr.width, height: cr.height })
    })

    ro.observe(el)
    return () => ro.disconnect()
  }, [ref])

  return size
}

function extractDemographicData(payload: any): Record<string, any[]> {
  if (!payload) return {}
  if (payload.demographicData && typeof payload.demographicData === "object") return payload.demographicData
  if (payload.data?.demographicData && typeof payload.data.demographicData === "object") return payload.data.demographicData

  if (Array.isArray(payload)) {
    const first = payload.find((x: any) => x?.demographicData) ?? payload[0]
    if (first?.demographicData && typeof first.demographicData === "object") return first.demographicData
  }

  return {}
}

function toRow(point: any): Row | null {
  if (!point) return null

  const x =
    point.equipment_quality ??
    point.equipmentQuality ??
    point.equipment_quality_score ??
    point.x ??
    point.quality ??
    null

  const y =
    point.probability_density ??
    point.probabilityDensity ??
    point.density ??
    point.y ??
    null

  const equipment_quality = Number(x)
  const probability_density = Number(y)

  if (!Number.isFinite(equipment_quality) || !Number.isFinite(probability_density)) return null
  return { equipment_quality, probability_density }
}

const EquipmentEI = () => {
  const { id } = useParams()

  const chartBoxRef = useRef<HTMLDivElement | null>(null)
  const svgRef = useRef<SVGSVGElement | null>(null)
  const { width, height } = useResizeObserver(chartBoxRef)

  const stateId = id ?? ""
  const { EIEquipment, isEIEquipmentLoading } = useEIEquipment(stateId)

  const [visible, setVisible] = useState<Record<string, boolean>>({
    "African American": true,
    "Hispanic": true,
    "White": true
  })

  const groups = useMemo(
    () => [
      { label: "African American", color: "#1f77b4" },
      { label: "Hispanic", color: "#2ca02c" },
      { label: "White", color: "#d62728" }
    ],
    []
  )

  const demographicData = useMemo(() => extractDemographicData(EIEquipment), [EIEquipment])

  const series = useMemo(() => {
    return groups.map(g => {
      const rawPoints: any[] = (demographicData as any)[g.label] ?? []

      const cleaned: Row[] = rawPoints
        .map(toRow)
        .filter((d): d is Row => !!d)
        .sort((a, b) => a.equipment_quality - b.equipment_quality)

      if (cleaned.length < 2) return { label: g.label, color: g.color, data: [] as Row[] }

      // light pre-smooth then Gaussian-fit => FORCE smooth
      const pre = smoothSeries(cleaned, PRE_SMOOTH_WINDOW).map(d => ({
        ...d,
        probability_density: Math.max(0, d.probability_density)
      }))

      const xs = pre.map(d => d.equipment_quality)
      const ys = pre.map(d => d.probability_density)

      const fit = gaussianFit(xs, ys)

      if (!fit) {
        const fallback = trimTailsSmart(pre, 0.001, 20)
        return { label: g.label, color: g.color, data: fallback }
      }

      const xMaxLocal = Math.max(0, fit.maxX)
      const n = FIT_GRID_POINTS
      const grid = d3.range(n).map(i => (i / (n - 1)) * xMaxLocal)

      const gaussianRows: Row[] = grid.map(x => ({
        equipment_quality: x,
        probability_density: fit.amp * Math.exp(-((x - fit.mu) * (x - fit.mu)) / (2 * fit.sigma * fit.sigma))
      }))

      const trimmed = trimTailsSmart(gaussianRows, 0.001, 20)

      return { label: g.label, color: g.color, data: trimmed }
    })
  }, [groups, demographicData])

  const yMax = useMemo(() => d3.max(series, s => d3.max(s.data, d => d.probability_density) ?? 0) ?? 0, [series])

  const xMax = useMemo(() => {
    const mx = d3.max(series, s => d3.max(s.data, d => d.equipment_quality) ?? 0) ?? 0
    return mx > 0 ? mx : 1
  }, [series])

  useEffect(() => {
    if (!svgRef.current) return
    if (!width || !height) return

    const svg = d3.select(svgRef.current)
    svg.selectAll("*").remove()

    const innerWidth = Math.max(0, width - MARGIN.left - MARGIN.right)
    const innerHeight = Math.max(0, height - MARGIN.top - MARGIN.bottom)
    if (innerWidth <= 0 || innerHeight <= 0) return

    svg.attr("width", width).attr("height", height)

    const g = svg.append("g").attr("transform", `translate(${MARGIN.left},${MARGIN.top})`)

    const x = d3.scaleLinear().domain([0, xMax]).range([0, innerWidth])
    const y = d3
      .scaleLinear()
      .domain([0, yMax || 1e-6])
      .range([innerHeight, 0])
      .clamp(true)

    g.append("g").attr("transform", `translate(0,${innerHeight})`).call(d3.axisBottom(x))
    g.append("g").call(d3.axisLeft(y))

    const xGrid = d3.axisBottom(x).ticks(8).tickSize(-innerHeight).tickFormat(() => "" as any)
    const yGrid = d3.axisLeft(y).ticks(6).tickSize(-innerWidth).tickFormat(() => "" as any)

    g.append("g")
      .attr("class", "grid grid-x")
      .attr("transform", `translate(0,${innerHeight})`)
      .call(xGrid)
      .call(sel => sel.select(".domain").remove())
      .call(sel => sel.selectAll("line").attr("stroke-opacity", 0.25).attr("stroke-dasharray", "4,4"))

    g.append("g")
      .attr("class", "grid grid-y")
      .call(yGrid)
      .call(sel => sel.select(".domain").remove())
      .call(sel => sel.selectAll("line").attr("stroke-opacity", 0.25).attr("stroke-dasharray", "4,4"))

    g.append("text")
      .attr("x", innerWidth / 2)
      .attr("y", innerHeight + 42)
      .attr("text-anchor", "middle")
      .attr("font-size", 12)
      .text("Equipment Quality")

    g.append("text")
      .attr("transform", "rotate(-90)")
      .attr("x", -innerHeight / 2)
      .attr("y", -46)
      .attr("text-anchor", "middle")
      .attr("font-size", 12)
      .text("Probability Density (of Equipment Access)")

    // ✅ use curveBasis for extra smooth drawing (same as reject-ballot version)
    const area = d3
      .area<Row>()
      .curve(d3.curveBasis)
      .x(d => x(d.equipment_quality))
      .y0(innerHeight)
      .y1(d => y(d.probability_density))

    const line = d3
      .line<Row>()
      .curve(d3.curveBasis)
      .x(d => x(d.equipment_quality))
      .y(d => y(d.probability_density))

    series.forEach(s => {
      if (!visible[s.label] || s.data.length === 0) return
      g.append("path").datum(s.data).attr("fill", s.color).attr("opacity", 0.25).attr("d", area)
      g.append("path").datum(s.data).attr("fill", "none").attr("stroke", s.color).attr("stroke-width", 2).attr("d", line)
    })
  }, [width, height, series, visible, yMax, xMax])

  return (
    <Box sx={{ width: "88%", height: "80%", mt: 2, display: "flex", flexDirection: "column", gap: 1 }}>
      <Typography variant="h6" fontWeight={700} align="center" sx={{ width: "100%" }}>
        Equipment EI Probability Curve
      </Typography>

      <Typography variant="body2" align="center" sx={{ opacity: 0.75 }}>
        {isEIEquipmentLoading ? "Loading EI KDE data..." : series.every(s => s.data.length === 0) ? "No EI KDE data" : ""}
      </Typography>

      <Box
        ref={chartBoxRef}
        sx={{
          width: "100%",
          flex: 1,
          minHeight: 220,
          minWidth: 0,
          display: "flex"
        }}
      >
        <svg ref={svgRef} />
      </Box>

      <Box sx={{ display: "flex", flexWrap: "wrap", gap: 1.5, alignItems: "center" }}>
        <Typography variant="subtitle2" fontWeight={700} sx={{ mr: 1 }}>
          Demographics:
        </Typography>

        {groups.map(g => (
          <FormControlLabel
            key={g.label}
            sx={{ m: 0, "& .MuiFormControlLabel-label": { color: g.color, fontSize: 14 } }}
            control={
              <Checkbox
                checked={!!visible[g.label]}
                onChange={() => setVisible(v => ({ ...v, [g.label]: !v[g.label] }))}
                sx={{ p: 0.5, color: g.color, "&.Mui-checked": { color: g.color } }}
              />
            }
            label={g.label}
          />
        ))}
      </Box>
    </Box>
  )
}

export default EquipmentEI
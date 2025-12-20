import { useEffect, useMemo, useRef, useState, type MutableRefObject } from "react";
import { useLocation, useParams } from "react-router-dom";
import type { FeatureCollection } from "geojson";
import L from "leaflet";
import { MapContainer, TileLayer, GeoJSON, Pane, useMap } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet.pattern";
import {
	Box,
	Button,
	ButtonGroup,
	CircularProgress,
	Dialog,
	DialogContent,
	DialogTitle,
	Pagination,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
	TextField,
	Typography
} from "@mui/material";

import {
	useChoroplethData,
	useCVAPPercentage,
	useQualityScore,
	useStateCenter,
	useStateGeoJSON,
	useUsCountyGeoJSON,
	useVotersByRegion,
	useRepublicanVotersByRegion,
	useDemocratVotersByRegion,
	useVoterRegistrationRegionSum
} from "../queries/dataHooks";

import {
	isDemocrat,
	isPoliticalParty,
	ispreClearanceStates,
	isRepublican,
	isVoterRegistrationState
} from "../utils/Utils";

interface StateMapProps {
	isDetail?: boolean;
	viewType?: string;
}

/* TYPES FOR VOTERS API */

type VoterParty = "Republican" | "Democrat" | "Other" | string;

type VoterRow = {
	id: number;
	regionId: number;
	regionName: string;
	nameFull: string;
	party: VoterParty;
};

type PagedVotersResponse = {
	voters: VoterRow[];
	currentPage: number; // backend returns 0-based
	totalPages: number;
	totalElements: number;
	hasNext: boolean;
	hasPrevious: boolean;
	pageSize: number;
};

type PartyFilter = "All" | "Republican" | "Democrat";

/* HELPERS */

function formatMetricLabel(metric: string): string {
	switch (metric) {
		case "totalProvisional":
			return "Total Provisional Ballots";
		case "activeVoterPercentage":
			return "Active Voters (%)";
		case "pollbookDeletionPercentage":
			return "Pollbook Deletions (%)";
		case "rejectionPercentage":
			return "Mail Ballot Rejection (%)";
		case "registeredVoterPercentage":
			return "Registered Voters (%)";
		case "typeOfEquipmentUsed":
			return "Equipment";
		default:
			return metric;
	}
}

function normalizeCountyName(name: string): string {
	return name
		.replace(/ County$/i, "")
		.replace(/[^a-zA-Z0-9\s]/g, " ")
		.replace(/\s+/g, "")
		.trim()
		.toLowerCase();
}

function createBins(values: number[]) {
	const valid = values.filter(v => typeof v === "number" && !isNaN(v)).sort((a, b) => a - b);
	if (valid.length === 0) return { bins: [], min: 0, max: 0 };

	const min = Math.min(...valid);

	const p95Index = Math.floor(valid.length * 0.95);
	let max = valid[p95Index];

	const base = max < 500 ? 10 : 100;
	const roundedMin = Math.floor(min / base) * base;
	const roundedMax = Math.ceil(max / base) * base;

	const step = (roundedMax - roundedMin) / 5;

	const bins = [
		roundedMin + step * 1,
		roundedMin + step * 2,
		roundedMin + step * 3,
		roundedMin + step * 4,
		roundedMax
	].map(v => Math.floor(v / base) * base);

	return { bins, min: roundedMin, max: roundedMax };
}

function pickMetric(page: string) {
	switch (page) {
		case "provisional-ballot":
			return "totalProvisional";
		case "2024-EAVS-active-voters":
			return "activeVoterPercentage";
		case "pollbook-deletions":
			return "pollbookDeletionPercentage";
		case "mail-ballot-rejections":
			return "rejectionPercentage";
		case "state-voting-equipment":
			return "typeOfEquipmentUsed";

		// voter registration views
		case "voter-registration-data":
			return "registeredVoterPercentage";

		default:
			return "";
	}
}

function isPercentageMetric(metric: string): boolean {
	return [
		"activeVoterPercentage",
		"pollbookDeletionPercentage",
		"rejectionPercentage",
		"registeredVoterPercentage"
	].includes(metric);
}

function createPercentageBins(values: number[], useActualMin = false) {
	const valid = values.filter(v => typeof v === "number" && !isNaN(v)).sort((a, b) => a - b);
	if (valid.length === 0) return { bins: [], min: 0, max: 0 };

	const min = useActualMin ? Math.floor(valid[0]) : 0;

	if (valid.every(v => v === 0)) return { bins: [20, 40, 60, 80, 100], min: 0, max: 100 };

	const p95Index = Math.floor(valid.length * 0.95);
	const rawMax = valid[p95Index];
	let max = Math.min(100, Math.ceil(rawMax));

	if (max <= min) max = min + 1;

	const step = Math.ceil((max - min) / 5);
	max = min + step * 5;

	if (max > 100) {
		max = 100;
		const step2 = Math.ceil((max - min) / 5);
		const bins = Array.from({ length: 5 }, (_, i) => min + step2 * (i + 1));
		return { bins, min, max };
	}

	const bins = Array.from({ length: 5 }, (_, i) => min + step * (i + 1));
	return { bins, min, max };
}

const BIN_COLORS = ["#e8f5e9", "#a5d6a7", "#66bb6a", "#236326ff", "#042204ff"];

function getBinColor(value: number, bins: number[]) {
	if (value <= bins[0]) return BIN_COLORS[0];
	if (value <= bins[1]) return BIN_COLORS[1];
	if (value <= bins[2]) return BIN_COLORS[2];
	if (value <= bins[3]) return BIN_COLORS[3];
	return BIN_COLORS[4];
}

/* EQUIPMENT PATTERNS */

const EQUIPMENT_TYPES = ["DRE with VVPAT", "DRE no VVPAT", "Scanner", "Ballot Marking Device"] as const;
type EquipmentType = (typeof EQUIPMENT_TYPES)[number];
type PatternMap = Record<EquipmentType, any>;

const EQUIPMENT_COLORS: Record<EquipmentType, string> = {
	"DRE with VVPAT": "#00b503ff",
	"DRE no VVPAT": "#E6B800",
	Scanner: "#ff4e22ff",
	"Ballot Marking Device": "#0800ffff"
};

function parseEquipmentTypes(raw: unknown): EquipmentType[] {
	if (!raw) return [];

	const normalizeOne = (s: string): EquipmentType | null => {
		const t = s.trim();
		const lower = t.toLowerCase();

		if (lower === "dre with vvpat") return "DRE with VVPAT";
		if (lower === "dre no vvpat" || lower === "dre without vvpat") return "DRE no VVPAT";
		if (lower === "scanner") return "Scanner";
		if (lower === "ballot marking device" || lower === "bmd") return "Ballot Marking Device";

		if (lower.includes("dre") && lower.includes("vvpat") && (lower.includes("with") || lower.includes("w/")))
			return "DRE with VVPAT";
		if (lower.includes("dre") && lower.includes("vvpat") && (lower.includes("no") || lower.includes("without")))
			return "DRE no VVPAT";

		return null;
	};

	const asStrings: string[] = Array.isArray(raw) ? raw.map(String) : [String(raw)];
	const split = asStrings
		.flatMap(s => s.split(/[,|;/]+/g))
		.map(s => s.trim())
		.filter(Boolean);

	const picked = split.map(normalizeOne).filter((x): x is EquipmentType => x !== null);
	const set = new Set(picked);
	return EQUIPMENT_TYPES.filter(t => set.has(t));
}

function EquipmentPatternDefs({
	patternsRef,
	setReady
}: {
	patternsRef: MutableRefObject<PatternMap | null>;
	setReady: (v: boolean) => void;
}) {
	const map = useMap();

	useEffect(() => {
		if (!map) return;

		if (patternsRef.current) {
			setReady(true);
			return;
		}

		const StripePattern = (L as any).StripePattern;

		const makeStripe = (opts: any) =>
			new StripePattern({
				weight: 2,
				spaceWeight: 6,
				opacity: 1,
				spaceOpacity: 0,
				...opts
			});

		const dreWith = makeStripe({ color: EQUIPMENT_COLORS["DRE with VVPAT"], angle: 0 });
		const dreNo = makeStripe({ color: EQUIPMENT_COLORS["DRE no VVPAT"], angle: 90 });
		const scanner = makeStripe({ color: EQUIPMENT_COLORS["Scanner"], angle: 45 });
		const bmd = makeStripe({ color: EQUIPMENT_COLORS["Ballot Marking Device"], angle: -45 });

		dreWith.addTo(map);
		dreNo.addTo(map);
		scanner.addTo(map);
		bmd.addTo(map);

		patternsRef.current = {
			"DRE with VVPAT": dreWith,
			"DRE no VVPAT": dreNo,
			Scanner: scanner,
			"Ballot Marking Device": bmd
		};

		setReady(true);

		return () => {
			try {
				dreWith.remove();
				dreNo.remove();
				scanner.remove();
				bmd.remove();
			} catch {
				// ignore
			}
			patternsRef.current = null;
			setReady(false);
		};
	}, [map, patternsRef, setReady]);

	return null;
}

/* MAP */

let currentHoveredLayer: L.Path | null = null;

const StateMap = ({ isDetail = false }: StateMapProps) => {
	const { id } = useParams();
	const { pathname } = useLocation();

	// Only enable click->popup for Florida
	const isFlorida = id === "12";

	const isDemo = isDemocrat(id!);
	const isRep = isRepublican(id!);
	const isPreclearance = ispreClearanceStates(id!);
	const isVoterRegistered = isVoterRegistrationState(id!);
	const isPolitical = isPoliticalParty(id!);

	const { cvapPercentage, isCVAPPercentageLoading } = useCVAPPercentage(id);
	const cvapValue =
		Array.isArray(cvapPercentage) ? cvapPercentage[0]?.cvapPercent : cvapPercentage?.cvapPercent;

	const showCvap = isPolitical && !isCVAPPercentageLoading && Number.isFinite(cvapValue);

	let preWord = "";
	if (isDemo) preWord = "Democratic - ";
	else if (isRep) preWord = "Republican - ";
	else if (isPreclearance) preWord = "Preclearance - ";
	else if (isVoterRegistered) preWord = "Voter Registration - ";

	const { quality, isQualityLoading } = useQualityScore(id!);

	let currentPage = pathname.split("/").pop() || "";
	if (!isNaN(Number(currentPage))) currentPage = "provisional-ballot";

	const metric = pickMetric(currentPage);
	const isEquipmentMetric = metric === "typeOfEquipmentUsed";
	const isVoterRegistrationMetric = metric === "registeredVoterPercentage";

	if (!id) return <div>No state ID provided.</div>;

	const { stateGeoJSON, isStateGeoJSONLoading } = useStateGeoJSON(id);
	const { counties, isCountyLoading } = useUsCountyGeoJSON(id);
	const { stateCenter, isStateCenterLoading } = useStateCenter(id);
	const { choroplethData, isChoroplethDataLoading } = useChoroplethData(id);

	const { VoterReistrationRegionSum, isVoterReistrationRegionSumsLoading } =
		useVoterRegistrationRegionSum(id);

	// choose which dataset powers the choropleth
	const choroplethRows = isVoterRegistrationMetric
		? (VoterReistrationRegionSum ?? [])
		: (choroplethData ?? []);

	const isActiveChoroplethLoading = isVoterRegistrationMetric
		? isVoterReistrationRegionSumsLoading
		: isChoroplethDataLoading;

	const [hoverInfo, setHoverInfo] = useState<{ name: string; value: number | string } | null>(null);

	const [openVotersDialog, setOpenVotersDialog] = useState(false);
	const [selectedRegionId, setSelectedRegionId] = useState<string>("");
	const [selectedRegionName, setSelectedRegionName] = useState<string>("");

	// Keep page when switching affiliations
	const [page, setPage] = useState(0);

	// Filter buttons
	const [partyFilter, setPartyFilter] = useState<PartyFilter>("All");

	// Jump-to-page input
	const [pageJumpInput, setPageJumpInput] = useState<string>("");

	const regionEnabled = isFlorida && openVotersDialog && selectedRegionId !== "";

	const { votersPage: allPage, isVotersLoading: isAllLoading } = useVotersByRegion(
		regionEnabled && partyFilter === "All" ? selectedRegionId : "",
		page
	) as unknown as { votersPage?: PagedVotersResponse; isVotersLoading: boolean };

	const { votersRepPage, isvotersRepPageLoading } = useRepublicanVotersByRegion(
		regionEnabled && partyFilter === "Republican" ? selectedRegionId : "",
		page
	) as unknown as { votersRepPage?: PagedVotersResponse; isvotersRepPageLoading: boolean };

	const { votersDemPage, isvotersDemPageLoading } = useDemocratVotersByRegion(
		regionEnabled && partyFilter === "Democrat" ? selectedRegionId : "",
		page
	) as unknown as { votersDemPage?: PagedVotersResponse; isvotersDemPageLoading: boolean };

	const activePage: PagedVotersResponse | undefined =
		partyFilter === "All" ? allPage : partyFilter === "Republican" ? votersRepPage : votersDemPage;

	const isActiveLoading =
		partyFilter === "All"
			? isAllLoading
			: partyFilter === "Republican"
				? isvotersRepPageLoading
				: isvotersDemPageLoading;

	// If you switch filter and the page is now out-of-range
	useEffect(() => {
		if (!activePage) return;
		const totalPages = Math.max(1, activePage.totalPages);
		if (page > totalPages - 1) setPage(totalPages - 1);
	}, [activePage, page]);

	const stateName = useMemo(() => {
		const fc = stateGeoJSON as any;
		return fc?.features?.[0]?.properties?.NAME ?? "";
	}, [stateGeoJSON, id]);

	// Map county -> record (works for both choroplethData and voter-registration sums)
	const choroplethByCounty = useMemo(() => {
		const map = new Map<string, any>();
		(choroplethRows ?? []).forEach((row: any) => {
			const name = row.countyName ?? row.regionName ?? row.name ?? "";
			const key = normalizeCountyName(name);
			if (key) map.set(key, row);
		});
		return map;
	}, [choroplethRows]);

	let bins: number[] = [];
	let min = 0;
	let max = 0;

	if (choroplethRows && choroplethRows.length > 0 && !isEquipmentMetric) {
		const values = choroplethRows.map((c: any) => {
			const v = c[metric as keyof typeof c];
			return typeof v === "number" ? v : NaN;
		});

		if (isPercentageMetric(metric)) {
			const useActualMin =
				metric === "activeVoterPercentage" || metric === "registeredVoterPercentage";
			({ bins, min, max } = createPercentageBins(values, useActualMin));
		} else {
			({ bins, min, max } = createBins(values));
		}
	}

	const US_BOUNDS: [[number, number], [number, number]] = [
		[20.0, -130.0],
		[52.0, -60.0]
	];

	const mapRef = useRef<L.Map | null>(null);
	const [view, setView] = useState<{ lat: number; lon: number; zoom: number } | null>(null);

	const stateFeature = useMemo(() => {
		if (!id || !stateGeoJSON || !stateCenter) return null;

		if (isDetail) {
			if (!counties) return [];
			return counties;
		}
		const state = stateGeoJSON as FeatureCollection;
		return state ? [state] : [];
	}, [id, isDetail, counties, stateGeoJSON, stateCenter]);

	useEffect(() => {
		if (stateCenter) {
			setView({
				lat: stateCenter.lat,
				lon: stateCenter.lon,
				zoom: stateCenter.zoom
			});
		}
	}, [stateCenter]);

	const patternsRef = useRef<PatternMap | null>(null);
	const [patternsReady, setPatternsReady] = useState(false);

	if (
		isStateCenterLoading ||
		isStateGeoJSONLoading ||
		isCountyLoading ||
		!view ||
		(isDetail && isActiveChoroplethLoading)
	) {
		return <div>Loading map…</div>;
	}
	if (isPoliticalParty(id!) && isCVAPPercentageLoading) return <div>Loading...</div>;
	if (isQualityLoading) return <div>Loading...</div>;

	const resetVotersDialogState = () => {
		setSelectedRegionId("");
		setSelectedRegionName("");
		setPartyFilter("All");
		setPage(0);
		setPageJumpInput("");
	};

	const handleCloseDialog = () => {
		setOpenVotersDialog(false);
	};

	const handleCountyClick = (rawName: string, record: any) => {
		if (!isFlorida) return;
		if (!record) return;

		const rid = record?.regionId;
		const regionId = typeof rid === "number" ? String(rid) : typeof rid === "string" ? rid : "";
		const regionName = (record?.countyName as string | undefined) ?? (record?.regionName as string | undefined) ?? rawName;

		console.log("Clicked county:", rawName, "regionId:", regionId, "regionName:", regionName);

		if (!regionId) return;

		setSelectedRegionId(regionId);
		setSelectedRegionName(regionName);

		// Default to page 0 when opening a NEW region
		setPage(0);
		setPageJumpInput("");

		// Always open on click
		setOpenVotersDialog(true);
	};

	const handleJumpToPage = () => {
		if (!activePage) return;

		const totalPages = Math.max(1, activePage.totalPages);

		const parsed = Number(pageJumpInput);
		if (!Number.isFinite(parsed)) return;

		// user enters 1-based
		let target = Math.floor(parsed) - 1;
		if (target < 0) target = 0;
		if (target > totalPages - 1) target = totalPages - 1;

		setPage(target);
	};

	return (
		<>
			<Box sx={{ height: "80%", width: "85%", position: "relative" }}>
				{stateName && (
					<Box
						sx={{
							position: "absolute",
							left: 0,
							right: 0,
							top: 0,
							transform: "translateY(-110%)",
							zIndex: 2000,
							pointerEvents: "none",
							color: "black",
							fontSize: 21,
							fontWeight: 900
						}}
					>
						{preWord}
						{stateName + " "}
						{showCvap && (
							<Box
								component="span"
								sx={{
									ml: 1,
									fontSize: 12,
									fontWeight: 600,
									opacity: 0.9
								}}
							>
								CVAP Eligible: {cvapValue}%{" "}
							</Box>
						)}
						<Box
							component="span"
							sx={{
								ml: 1,
								fontSize: 12,
								fontWeight: 600,
								opacity: 0.9
							}}
						>
							EAVS Score: {quality?.dataQualityScore}
						</Box>
					</Box>
				)}

				<MapContainer
					ref={mapRef as any}
					center={[view.lat, view.lon]}
					zoom={view.zoom}
					maxZoom={16}
					minZoom={4.5}
					zoomSnap={0.5}
					maxBounds={US_BOUNDS}
					zoomDelta={0.5}
					inertia={false}
					style={{ height: "100%", width: "100%" }}
					attributionControl={false}
				>
					<TileLayer
						url="https://tile.jawg.io/jawg-sunny/{z}/{x}/{y}{r}.png?access-token=bAKlXLNR88trfplZUWrdagL7a3iyn9BOkENljAjiWldzn3Sf3gLRiv1boap3MCnb"
						attribution='&copy; <a href="https://www.jawg.io/">Jawg Maps</a> &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
						subdomains={["a", "b", "c", "d"]}
						noWrap
					/>

					<Pane name="equipmentPatterns" style={{ zIndex: 350 }} />
					<Pane name="equipmentOutline" style={{ zIndex: 450 }} />

					{isDetail && isEquipmentMetric && (
						<EquipmentPatternDefs patternsRef={patternsRef} setReady={setPatternsReady} />
					)}

					{isDetail ? (
						isEquipmentMetric ? (
							<>
								{patternsReady &&
									EQUIPMENT_TYPES.map(type => (
										<GeoJSON
											key={`pattern-${type}`}
											pane="equipmentPatterns"
											data={stateFeature as any}
											style={(feature: any) => {
												const rawName = feature?.properties?.NAME || "";
												const countyKey = normalizeCountyName(rawName);
												const record = choroplethByCounty.get(countyKey);

												const list = parseEquipmentTypes(record?.typeOfEquipmentUsed);
												if (!list.includes(type)) {
													return {
														stroke: false,
														weight: 0,
														opacity: 0,
														fillOpacity: 0,
														interactive: false
													} as any;
												}

												const pattern = patternsRef.current?.[type];

												return {
													stroke: false,
													weight: 0,
													opacity: 0,
													fillOpacity: 0.8,
													fillPattern: pattern,
													interactive: false
												} as any;
											}}
										/>
									))}

								<GeoJSON
									key={`outline-${metric}`}
									pane="equipmentOutline"
									data={stateFeature as any}
									onEachFeature={(feature: any, layer: L.Layer & L.Path) => {
										const rawName = feature?.properties?.NAME || "";
										const countyKey = normalizeCountyName(rawName);
										const record = choroplethByCounty.get(countyKey);
										const equipmentList = parseEquipmentTypes(record?.typeOfEquipmentUsed);

										const valueStr = equipmentList.length > 0 ? equipmentList.join(", ") : "N/A";

										layer.setStyle({
											weight: 3,
											color: "#000",
											fillOpacity: 0.001,
											fillColor: "#ffffff"
										});

										layer.on("mouseover", () => {
											if (currentHoveredLayer && currentHoveredLayer !== layer) {
												currentHoveredLayer.setStyle({
													weight: 3,
													color: "#000",
													fillOpacity: 0.001
												});
											}

											layer.setStyle({
												weight: 4,
												color: "#333",
												fillOpacity: 0.001
											});

											currentHoveredLayer = layer;
											setHoverInfo({ name: rawName, value: valueStr });
										});

										layer.on("mouseout", () => {
											currentHoveredLayer = null;

											layer.setStyle({
												weight: 3,
												color: "#000",
												fillOpacity: 0.001,
												fillColor: "#ffffff"
											});

											setHoverInfo(null);
										});

										layer.on("click", () => handleCountyClick(rawName, record));
									}}
								/>
							</>
						) : (
							<GeoJSON
								key={metric}
								data={stateFeature as any}
								onEachFeature={(feature: any, layer: L.Layer & L.Path) => {
									const rawName = feature?.properties?.NAME || "";
									const countyKey = normalizeCountyName(rawName);
									const record = choroplethByCounty.get(countyKey);
									const raw = record?.[metric as keyof typeof record];
									const value = typeof raw === "number" ? Number(raw.toFixed(2)) : raw ?? "N/A";

									layer.setStyle({
										weight: 2,
										color: "#000",
										fillOpacity: 0.85,
										fillColor: typeof value === "number" ? getBinColor(value, bins) : BIN_COLORS[0]
									});

									layer.on("mouseover", () => {
										if (currentHoveredLayer && currentHoveredLayer !== layer) {
											currentHoveredLayer.setStyle({
												weight: 2,
												color: "#000",
												fillOpacity: 0.85
											});
										}

										layer.setStyle({
											weight: 4,
											color: "#333",
											fillOpacity: 1
										});

										currentHoveredLayer = layer;
										setHoverInfo({ name: rawName, value });
									});

									layer.on("mouseout", () => {
										currentHoveredLayer = null;

										const v = record?.[metric as keyof typeof record];
										layer.setStyle({
											weight: 2,
											color: "#000",
											fillOpacity: 0.85,
											fillColor: typeof v === "number" ? getBinColor(v, bins) : BIN_COLORS[0]
										});

										setHoverInfo(null);
									});

									layer.on("click", () => handleCountyClick(rawName, record));
								}}
							/>
						)
					) : (
						<GeoJSON
							data={stateFeature as any}
							style={{
								color: "#000",
								weight: 2,
								fillColor: "#4dabf7",
								fillOpacity: 0.35
							}}
						/>
					)}

					{hoverInfo && (
						<Box
							sx={{
								pointerEvents: "none",
								position: "absolute",
								bottom: 16,
								left: 16,
								bgcolor: "white",
								padding: "8px 12px",
								borderRadius: 1.5,
								boxShadow: 3,
								zIndex: 9999,
								fontSize: "14px"
							}}
						>
							<strong>{hoverInfo.name} County</strong>
							<br />
							{metric !== "" && (
								<>
									{formatMetricLabel(metric)}: <strong>{hoverInfo.value}</strong>
								</>
							)}
						</Box>
					)}
				</MapContainer>

				{/* Legend */}
				{isDetail && metric !== "" && (
					<Box sx={{ mt: 1.5, display: "flex", flexDirection: "column", alignItems: "center" }}>
						{isEquipmentMetric ? (
							<Box
								sx={{
									display: "grid",
									gridTemplateColumns: "max-content max-content",
									columnGap: 0.75,
									rowGap: 1,
									justifyContent: "center",
									width: "100%",
									maxWidth: "90%",
									alignItems: "center"
								}}
							>
								<Box sx={{ display: "flex", alignItems: "center", gap: 0.75 }}>
									<Box
										sx={{
											width: 28,
											height: 16,
											border: "1px solid #000",
											backgroundColor: "#fff",
											backgroundImage: `repeating-linear-gradient(0deg, ${EQUIPMENT_COLORS["DRE with VVPAT"]} 0px, ${EQUIPMENT_COLORS["DRE with VVPAT"]} 2px, transparent 2px, transparent 8px)`
										}}
									/>
									<Box sx={{ fontSize: 13, fontWeight: 700 }}>DRE with VVPAT</Box>
								</Box>

								<Box sx={{ display: "flex", alignItems: "center", gap: 0.75 }}>
									<Box
										sx={{
											width: 28,
											height: 16,
											border: "1px solid #000",
											backgroundColor: "#fff",
											backgroundImage: `repeating-linear-gradient(90deg, ${EQUIPMENT_COLORS["DRE no VVPAT"]} 0px, ${EQUIPMENT_COLORS["DRE no VVPAT"]} 2px, transparent 2px, transparent 8px)`
										}}
									/>
									<Box sx={{ fontSize: 13, fontWeight: 700 }}>DRE no VVPAT</Box>
								</Box>

								<Box sx={{ display: "flex", alignItems: "center", gap: 0.75 }}>
									<Box
										sx={{
											width: 28,
											height: 16,
											border: "1px solid #000",
											backgroundColor: "#fff",
											backgroundImage: `repeating-linear-gradient(45deg, ${EQUIPMENT_COLORS["Scanner"]} 0px, ${EQUIPMENT_COLORS["Scanner"]} 2px, transparent 2px, transparent 8px)`
										}}
									/>
									<Box sx={{ fontSize: 13, fontWeight: 700 }}>Scanner</Box>
								</Box>

								<Box sx={{ display: "flex", alignItems: "center", gap: 0.75 }}>
									<Box
										sx={{
											width: 28,
											height: 16,
											border: "1px solid #000",
											backgroundColor: "#fff",
											backgroundImage: `repeating-linear-gradient(-45deg, ${EQUIPMENT_COLORS["Ballot Marking Device"]} 0px, ${EQUIPMENT_COLORS["Ballot Marking Device"]} 2px, transparent 2px, transparent 8px)`
										}}
									/>
									<Box sx={{ fontSize: 13, fontWeight: 700 }}>Ballot Marking Device</Box>
								</Box>
							</Box>
						) : (
							<>
								<Box
									sx={{
										display: "flex",
										width: "100%",
										maxWidth: "90%",
										border: "1px solid #000",
										height: 16
									}}
								>
									{bins.map((_, i) => (
										<Box
											key={i}
											sx={{
												flex: 1,
												height: "100%",
												bgcolor: BIN_COLORS[i],
												borderRight: i === bins.length - 1 ? "none" : "1px solid #000"
											}}
										/>
									))}
								</Box>

								<Box
									sx={{
										display: "flex",
										width: "100%",
										maxWidth: "90%",
										justifyContent: "space-between",
										fontSize: "13px",
										mt: 0.5
									}}
								>
									{bins.map((_, i) => {
										const isLast = i === bins.length - 1;
										const suffix = isPercentageMetric(metric) ? "%" : "";

										return (
											<Box key={i} sx={{ textAlign: "center", flex: 1 }}>
												{i === 0
													? `${min}${suffix}–${bins[0]}${suffix}`
													: isLast
														? `>${bins[i - 1]}${suffix}`
														: `${bins[i - 1]}${suffix}–${bins[i]}${suffix}`}
											</Box>
										);
									})}
								</Box>
							</>
						)}
					</Box>
				)}
			</Box>

			{/* POPUP TABLE (Florida only) */}
			<Dialog
				open={openVotersDialog}
				onClose={handleCloseDialog}
				maxWidth="sm"
				fullWidth
				PaperProps={{
					sx: { maxWidth: 520 }
				}}
				TransitionProps={{
					onExited: () => {
						resetVotersDialogState();
					}
				}}
			>
				<DialogTitle
					sx={{
						fontWeight: 800,
						px: 2,
						width: "90%",
						mx: "auto"
					}}
				>
					Voters – {selectedRegionName || "Region"}
				</DialogTitle>

				<DialogContent
					sx={{
						px: 2,
						width: "90%",
						mx: "auto"
					}}
				>
					{!openVotersDialog ? null : !isFlorida ? (
						<Typography color="text.secondary">
							This popup is enabled only for Florida (stateId 12).
						</Typography>
					) : (
						<>
							{/* Filter buttons */}
							<Box sx={{ display: "flex", justifyContent: "space-between", alignItems: "center", mb: 1 }}>
								<ButtonGroup size="small" variant="outlined">
									<Button
										variant={partyFilter === "All" ? "contained" : "outlined"}
										onClick={() => setPartyFilter("All")}
									>
										All
									</Button>
									<Button
										variant={partyFilter === "Republican" ? "contained" : "outlined"}
										onClick={() => setPartyFilter("Republican")}
									>
										Republicans
									</Button>
									<Button
										variant={partyFilter === "Democrat" ? "contained" : "outlined"}
										onClick={() => setPartyFilter("Democrat")}
									>
										Democrats
									</Button>
								</ButtonGroup>
							</Box>

							{isActiveLoading ? (
								<Box sx={{ display: "flex", justifyContent: "center", py: 4 }}>
									<CircularProgress />
								</Box>
							) : !activePage ? (
								<Typography color="error">
									No data returned for this region (or page out of range).
								</Typography>
							) : (
								<>
									<Box sx={{ display: "flex", justifyContent: "space-between", mb: 1 }}>
										<Typography variant="body2" sx={{ color: "text.secondary" }}>
											Total: {activePage.totalElements.toLocaleString()} voters
											{partyFilter !== "All" ? ` (${partyFilter})` : ""}
										</Typography>
										<Typography variant="body2" sx={{ color: "text.secondary" }}>
											Page {page + 1} of {Math.max(1, activePage.totalPages)}
										</Typography>
									</Box>

									<TableContainer
										sx={{
											maxHeight: 440,
											border: "1px solid rgba(0,0,0,0.12)",
											borderRadius: 1
										}}
									>
										<Table size="small" stickyHeader>
											<TableHead>
												<TableRow>
													<TableCell sx={{ fontWeight: 900 }}>Name</TableCell>
													<TableCell sx={{ fontWeight: 900, width: 140 }}>Party</TableCell>
												</TableRow>
											</TableHead>
											<TableBody>
												{activePage.voters.map(v => (
													<TableRow key={`${v.regionId}-${v.id}`} hover>
														<TableCell>{v.nameFull}</TableCell>
														<TableCell>{v.party}</TableCell>
													</TableRow>
												))}
											</TableBody>
										</Table>
									</TableContainer>

									{/* Pagination + Jump-to-page */}
									<Box sx={{ display: "flex", justifyContent: "center", mt: 2 }}>
										<Pagination
											count={Math.max(1, activePage.totalPages)}
											page={page + 1}
											onChange={(_, next) => setPage(next - 1)}
											size="small"
											siblingCount={1}
											boundaryCount={1}
										/>
									</Box>

									<Box
										sx={{
											mt: 2,
											display: "flex",
											gap: 1,
											justifyContent: "center",
											alignItems: "center"
										}}
									>
										<TextField
											size="small"
											label="Jump to page"
											value={pageJumpInput}
											onChange={e => setPageJumpInput(e.target.value)}
											onKeyDown={e => {
												if (e.key === "Enter") handleJumpToPage();
											}}
											sx={{ width: 160 }}
										/>
										<Button size="small" variant="contained" onClick={handleJumpToPage}>
											Go
										</Button>
									</Box>
								</>
							)}
						</>
					)}
				</DialogContent>
			</Dialog>
		</>
	);
};

export default StateMap;

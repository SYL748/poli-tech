import { MapContainer, GeoJSON, TileLayer } from "react-leaflet";
import type { FeatureCollection } from "geojson";
import { Box } from "@mui/material";
import "leaflet/dist/leaflet.css";
import type { Topology, GeometryCollection } from "topojson-specification";
import { topology as toTopology } from "topojson-server";
import { mesh as topoMesh, feature as topoFeature } from "topojson-client";
import { useNavigate } from "react-router-dom";
import { detailedStates } from "../utils/Utils";
import { useUsStateGeoJSON } from "../queries/dataHooks";
import { useRef } from "react";
import L from "leaflet";

type StatesObjects = { states: GeometryCollection };

const US_BOUNDS: [[number, number], [number, number]] = [
    [24.396308, -124.848974],
    [49.384358, -66.885444],
];

function normalizeStateName(s: string) {
    return s.replace(/\s+/g, "").trim().toLowerCase();
}

const PREFIX_BY_STATE: Record<string, string> = {
    delaware: "Detailed State",
    florida: "Voter Registration State",
    arkansas: "Republican Dominate",
    illinois: "Democratic Dominate",
    iowa: "Republican Dominate",
    texas: "Preclearance State",
};

function formatHoverLabel(stateName: string) {
    const key = normalizeStateName(stateName);
    const prefix = PREFIX_BY_STATE[key];
    return prefix ? `${prefix} - ${stateName}` : stateName;
}

export default function LeafletMap() {
    const { states, isStateLoading } = useUsStateGeoJSON();
    const DetailedStates = detailedStates;

    const navigate = useNavigate();

    const hoverBoxRef = useRef<HTMLDivElement | null>(null);

    const setHoverText = (name: string | null) => {
        if (!hoverBoxRef.current) return;

        hoverBoxRef.current.style.display = name ? "block" : "none";
        hoverBoxRef.current.textContent = name ?? "";
    };

    if (isStateLoading) return <div>Loading map...</div>;

    const topo = toTopology({ states: states as FeatureCollection }, 1e6) as unknown as Topology<StatesObjects>;

    const statesFC = topoFeature(topo, topo.objects.states) as FeatureCollection;
    const stateBorders = topoMesh(topo, topo.objects.states, (a, b) => a !== b) as any;
    const nationalOutline = topoMesh(topo, topo.objects.states, (a, b) => a === b) as any;

    return (
        <Box
            sx={{
                height: "100%",
                width: "100%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                flexDirection: "column",
            }}
        >
            <Box sx={{ height: "80%", width: "80%", backgroundColor: "black", position: "relative" }}>
                <MapContainer
                    center={[37.8, -96]}
                    zoom={4.5}
                    minZoom={4.5}
                    maxZoom={10}
                    zoomSnap={0.5}
                    zoomDelta={0.5}
                    maxBounds={US_BOUNDS}
                    maxBoundsViscosity={1.0}
                    scrollWheelZoom
                    doubleClickZoom
                    zoomControl
                    attributionControl={false}
                    style={{ height: "100%", width: "100%" }}
                >
                    <TileLayer
                        url="https://tile.jawg.io/jawg-sunny/{z}/{x}/{y}{r}.png?access-token=bAKlXLNR88trfplZUWrdagL7a3iyn9BOkENljAjiWldzn3Sf3gLRiv1boap3MCnb"
                        attribution='&copy; <a href="https://www.jawg.io/">Jawg Maps</a> &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        subdomains={["a", "b", "c", "d"]}
                        noWrap
                    />

                    <GeoJSON
                        data={stateBorders}
                        interactive={false}
                        style={{ color: "#000", weight: 1, opacity: 1, fillOpacity: 0 }}
                    />

                    <GeoJSON
                        data={statesFC}
                        style={(feature) => {
                            const stateCode = (feature as any)?.properties?.STATE;
                            const isDetailed = DetailedStates.includes(stateCode);
                            return {
                                color: "#000",
                                weight: 0,
                                fillColor: isDetailed ? "#22c55e" : "transparent",
                                fillOpacity: isDetailed ? 0.35 : 0,
                            };
                        }}
                        onEachFeature={(feature: any, layer) => {
                            const stateCode = feature?.properties?.STATE;
                            const isDetailed = DetailedStates.includes(stateCode);

                            const stateLabel =
                                feature?.properties?.NAME ||
                                feature?.properties?.State ||
                                feature?.properties?.state_name ||
                                stateCode;

                            layer.on({
                                mouseover: () => {
                                    setHoverText(formatHoverLabel(String(stateLabel)));

                                    (layer as L.Path).setStyle({
                                        weight: 2,
                                        fillOpacity: isDetailed ? 0.6 : 0.5,
                                        fillColor: isDetailed ? "#22c55e" : "#4dabf7",
                                    });
                                },
                                mouseout: () => {
                                    setHoverText(null);

                                    (layer as L.Path).setStyle({
                                        weight: 0,
                                        fillColor: isDetailed ? "#22c55e" : "transparent",
                                        fillOpacity: isDetailed ? 0.35 : 0,
                                    });
                                },
                                click: () => {
                                    const stateName = feature?.properties?.STATE;
                                    navigate(`/state/${stateName}`);
                                },
                            });
                        }}
                    />

                    <GeoJSON
                        data={nationalOutline}
                        interactive={false}
                        style={{ color: "#000", weight: 1, opacity: 1, fillOpacity: 0 }}
                    />
                </MapContainer>


                <Box
                    ref={hoverBoxRef}
                    sx={{
                        position: "absolute",
                        left: 12,
                        bottom: 12,
                        zIndex: 1000,
                        display: "none",
                        bgcolor: "rgba(255,255,255,0.95)",
                        px: 1.5,
                        py: 1,
                        borderRadius: 1.5,
                        boxShadow: 2,
                        fontSize: 14,
                        fontWeight: 700,
                        pointerEvents: "none",
                        minWidth: 140,
                        textAlign: "left",
                    }}
                />
            </Box>
        </Box>
    );
}

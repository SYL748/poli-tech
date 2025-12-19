import { MapContainer, GeoJSON, TileLayer } from "react-leaflet";
import type { FeatureCollection } from "geojson";
import { Box, Typography } from "@mui/material";
import "leaflet/dist/leaflet.css";
import type { Topology, GeometryCollection } from "topojson-specification";
import { topology as toTopology } from "topojson-server";
import { mesh as topoMesh, feature as topoFeature } from "topojson-client";
import { useState, useMemo } from "react";
import { useAverageStateEquipmentAge, useUsStateGeoJSON } from "../queries/dataHooks";

type StatesObjects = { states: GeometryCollection };

const US_BOUNDS: [[number, number], [number, number]] = [
    [24.396308, -124.848974],
    [49.384358, -66.885444],
];
function getColor(v: number): string {
    if (v <= 1) return "transparent";

    if (v <= 2) return "#f7fcf5";
    if (v <= 3) return "#e5f5e0";
    if (v <= 4) return "#c7e9c0";
    if (v <= 5) return "#a1d99b";
    if (v <= 6) return "#74c476";
    if (v <= 7) return "#41ab5d";
    if (v <= 8) return "#238b45";
    if (v <= 9) return "#006d2c";
    if (v <= 10) return "#003110ff";

    return "#001407ff";
}



export default function LeafletEquipmentAge() {
    const { states, isStateLoading } = useUsStateGeoJSON();

    const [hoveredFips, setHoveredFips] = useState<number | null>(null);
    const { averageStateEquipmentAge, isAverageStateEquipmentAgeLoading } = useAverageStateEquipmentAge()
    const [tooltipData, setTooltipData] = useState<{
        stateName: string;
        averageAge: number;
    } | null>(null);

    /** Build map: FIPS → averageAge */
    const equipmentAgeMap = useMemo(() => {
        const rows = averageStateEquipmentAge ?? [];
        return new Map(rows.map(ea => [ea.stateId, ea.averageAgeOfEquipment]));
    }, [averageStateEquipmentAge]);

    /** Process TopoJSON (memoized) */
    const { statesFC, stateBorders, nationalOutline } = useMemo(() => {
        if (!states) return { statesFC: null, stateBorders: null, nationalOutline: null };

        const topo = toTopology(
            { states: states as FeatureCollection },
            1e7
        ) as unknown as Topology<StatesObjects>;

        const statesFC = topoFeature(topo, topo.objects.states) as FeatureCollection;

        const stateBorders = topoMesh(
            topo,
            topo.objects.states,
            (a, b) => a !== b
        );

        const nationalOutline = topoMesh(
            topo,
            topo.objects.states,
            (a, b) => a === b
        );

        return { statesFC, stateBorders, nationalOutline };
    }, [states]);

    /** Style for each state */
    const styleFunction = (feature: any) => {
        const fips = Number(feature.properties.STATE);
        const age = equipmentAgeMap.get(fips) ?? -1;

        return {
            color: "#000",
            weight: 0,
            fillOpacity: hoveredFips === fips ? 0.95 : 0.85,
            fillColor: getColor(age),
        };
    };

    /** Event handlers per state */
    const onEachFeature = (feature: any, layer: any) => {
        const fips = Number(feature.properties.STATE);
        const name = feature.properties.NAME;
        const age = equipmentAgeMap.get(fips) ?? -1;

        layer.on({
            mouseover: () => {
                setHoveredFips(fips);
                setTooltipData({ stateName: name, averageAge: age });
            },
            mouseout: () => {
                setHoveredFips(null);
                setTooltipData(null);
            },
        });
    };

    if (isStateLoading || !statesFC || isAverageStateEquipmentAgeLoading) {
        return <Typography variant="h6">Loading...</Typography>;
    }

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
            <Box sx={{ position: "relative", height: "80%", width: "80%" }}>
                <MapContainer
                    center={[37.8, -96]}
                    zoom={4.5}
                    minZoom={4.5}
                    maxZoom={10}
                    zoomSnap={0.5}
                    zoomDelta={0.5}
                    maxBounds={US_BOUNDS}
                    maxBoundsViscosity={1.0}
                    scrollWheelZoom={false}
                    doubleClickZoom={false}
                    zoomControl={false}
                    attributionControl={false}
                    style={{ height: "100%", width: "100%" }}
                >
                    <TileLayer
                        url="https://tile.jawg.io/jawg-sunny/{z}/{x}/{y}{r}.png?access-token=bAKlXLNR88trfplZUWrdagL7a3iyn9BOkENljAjiWldzn3Sf3gLRiv1boap3MCnb"
                        subdomains={["a", "b", "c", "d"]}
                        noWrap={true}
                    />

                    <GeoJSON data={statesFC} style={styleFunction} onEachFeature={onEachFeature} />

                    <GeoJSON
                        data={stateBorders}
                        style={{ color: "#000", weight: 3, opacity: 1, fillOpacity: 0 }}
                    />

                    <GeoJSON
                        data={nationalOutline}
                        style={{ color: "#000", weight: 3, opacity: 1, fillOpacity: 0 }}
                    />
                </MapContainer>

                {/* Tooltip */}
                {tooltipData && (
                    <Box
                        sx={{
                            position: "absolute",
                            left: 24,
                            bottom: 24,
                            backgroundColor: "rgba(255, 255, 255, 0.9)",
                            padding: "8px",
                            borderRadius: 2,
                            pointerEvents: "none",
                            boxShadow: 1,
                            border: "1px solid #e0e0e0",
                            minWidth: 200,
                            zIndex: 1001,
                        }}
                    >
                        <Typography variant="subtitle1" fontWeight="bold">
                            {tooltipData.stateName}
                        </Typography>

                        <Typography variant="body2">
                            Average Equipment Age:{" "}
                            {tooltipData.averageAge >= 0
                                ? tooltipData.averageAge.toFixed(1)
                                : "N/A"}{" "}
                            years
                        </Typography>
                    </Box>
                )}


                {/* Legend */}
                <Box
                    sx={{
                        position: "absolute",
                        bottom: 24,
                        right: 24,
                        backgroundColor: "rgba(255, 255, 255, 0.9)",
                        padding: "8px",
                        borderRadius: 2,
                        pointerEvents: "none",
                        boxShadow: 1,
                        border: "1px solid #e0e0e0",
                        zIndex: 9999,
                    }}
                >
                    <Typography variant="body1" fontWeight="bold">
                        Equipment Age Legend
                    </Typography>

                    {/* <=1 through <=10 */}
                    {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map((age) => (
                        <Box key={age} sx={{ display: "flex", alignItems: "center", gap: 1 }}>
                            <Box
                                sx={{
                                    width: 24,
                                    height: 16,
                                    border: "1px solid #d0d0d0",
                                    borderRadius: 1,
                                    backgroundColor: getColor(age),
                                }}
                            />
                            <Typography variant="body2">{age} years</Typography>
                        </Box>
                    ))}

                    {/* >10 */}
                    <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
                        <Box
                            sx={{
                                width: 24,
                                height: 16,
                                border: "1px solid #d0d0d0",
                                borderRadius: 1,
                                backgroundColor: getColor(11),
                            }}
                        />
                        <Typography variant="body2">{"> 10 years"}</Typography>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
}

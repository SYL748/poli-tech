import { Box, Button } from "@mui/material";
import MapIcon from "@mui/icons-material/Map";
import SettingsIcon from "@mui/icons-material/Settings";
import GroupsIcon from "@mui/icons-material/Groups";
import HowToVoteIcon from "@mui/icons-material/HowToVote";
import ScheduleIcon from "@mui/icons-material/Schedule";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import { Link as RouterLink, useLocation } from "react-router-dom";
import { useTheme } from "@mui/material/styles";

const NavBar = () => {
    const theme = useTheme();
    const location = useLocation();
    const isActive = (path: string) => {
        if (path === "/") return location.pathname === "/";
        return location.pathname.startsWith(path);
    };
    const navPaths = ["/", "/equipment/age", "/equipment/state", "/equipment/overview", "/political-party-states", "/voter-registration-states"];
    const isNavRoute = navPaths.includes(location.pathname);
    return (
        <Box
            sx={{
                display: "flex",
                gap: 2,
                bgcolor: "#fff",
                height: `calc(${(theme.mixins.toolbar as any).minHeight}px - 5px)`,
                p: 0.5,
                borderRadius: "999px",

            }}
        >
            <Button
                component={RouterLink}
                to="/"
                startIcon={<MapIcon />}
                sx={{
                    whiteSpace: "nowrap",
                    minWidth: "fit-content",
                    textTransform: "none",
                    borderRadius: "999px",
                    bgcolor: isNavRoute && isActive("/") ? "#1976d2" : "transparent",
                    color: isNavRoute && isActive("/") ? "#fff" : "#000",
                    "&:hover": {
                        bgcolor: isNavRoute && isActive("/") ? "#1565c0" : "rgba(25,118,210,0.08)",
                    },
                }}
            >
                US Map
            </Button>

            <Button
                component={RouterLink}
                to="/equipment/age"
                startIcon={<ScheduleIcon />}
                sx={{
                    whiteSpace: "nowrap",
                    minWidth: "fit-content",
                    textTransform: "none",
                    borderRadius: "999px",
                    bgcolor: isNavRoute && isActive("/equipment/age") ? "#1976d2" : "transparent",
                    color: isNavRoute && isActive("/equipment/age") ? "#fff" : "#000",
                    "&:hover": {
                        bgcolor: isNavRoute && isActive("/equipment/age")
                            ? "#1565c0"
                            : "rgba(25,118,210,0.08)",
                    },
                }}
            >
                Equipment Ages
            </Button>

            <Button
                component={RouterLink}
                to="/equipment/state"
                startIcon={<LocationOnIcon />}
                sx={{
                    whiteSpace: "nowrap",
                    minWidth: "fit-content",
                    textTransform: "none",
                    borderRadius: "999px",
                    bgcolor: isNavRoute && isActive("/equipment/state") ? "#1976d2" : "transparent",
                    color: isNavRoute && isActive("/equipment/state") ? "#fff" : "#000",
                    "&:hover": {
                        bgcolor: isNavRoute && isActive("/equipment/state")
                            ? "#1565c0"
                            : "rgba(25,118,210,0.08)",
                    },
                }}
            >
                State Equipment
            </Button>

            <Button
                component={RouterLink}
                to="/equipment/overview"
                startIcon={<SettingsIcon />}
                sx={{
                    whiteSpace: "nowrap",
                    minWidth: "fit-content",
                    textTransform: "none",
                    borderRadius: "999px",
                    bgcolor: isNavRoute && isActive("/equipment/overview") ? "#1976d2" : "transparent",
                    color: isNavRoute && isActive("/equipment/overview") ? "#fff" : "#000",
                    "&:hover": {
                        bgcolor: isNavRoute && isActive("/equipment/overview")
                            ? "#1565c0"
                            : "rgba(25,118,210,0.08)",
                    },
                }}
            >
                Equipment Overview
            </Button>

            <Button
                component={RouterLink}
                to="/political-party-states"
                startIcon={<GroupsIcon />}
                sx={{
                    whiteSpace: "nowrap",
                    minWidth: "fit-content",
                    textTransform: "none",
                    borderRadius: "999px",
                    bgcolor: isNavRoute && isActive("/political-party-states") ? "#1976d2" : "transparent",
                    color: isNavRoute && isActive("/political-party-states") ? "#fff" : "#000",
                    "&:hover": {
                        bgcolor: isNavRoute && isActive("/political-party-states") ? "#1565c0" : "rgba(25,118,210,0.08)",
                    },
                }}
            >
                Political Comparison
            </Button>

            <Button
                component={RouterLink}
                to="/voter-registration-states"
                startIcon={<HowToVoteIcon />}
                sx={{
                    whiteSpace: "nowrap",
                    minWidth: "fit-content",
                    textTransform: "none",
                    borderRadius: "999px",
                    bgcolor: isNavRoute && isActive("/voter-registration-states") ? "#1976d2" : "transparent",
                    color: isNavRoute && isActive("/voter-registration-states") ? "#fff" : "#000",
                    "&:hover": {
                        bgcolor: isNavRoute && isActive("/voter-registration-states") ? "#1565c0" : "rgba(25,118,210,0.08)",
                    },
                }}
            >
                Voter Registration States
            </Button>

        </Box>
    );
};

export default NavBar;
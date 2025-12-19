import { AppBar, Toolbar, Typography, Link as MUILink, Box } from "@mui/material";
import MapIcon from '@mui/icons-material/Map';
import { Link as RouterLink } from "react-router-dom";
import NavBar from "./Nav";

const Banner = () => {
    return (
        <AppBar
            elevation={0}
            sx={{
                backgroundColor: "#d7d7d7ff",
                borderBottom: "1px solid #ccc",
            }}
        >
            <Toolbar sx = {{display: "flex", justifyContent: "space-between"}} >
                <MUILink
                    component={RouterLink}
                    to="/"
                    underline="none"
                    color="inherit"
                    sx={{ display: "flex", alignItems: "center", gap: 1, textDecoration: "none" }}
                >
                    <MapIcon fontSize="large" sx={{ color: "#223C97" }} />
                    <Typography variant="h5" fontWeight={900} color="text.primary">Magic</Typography>
                </MUILink>
                <Box sx={{ position: "absolute", left: "50%", transform: "translateX(-50%)" }} >
                    <NavBar />
                </Box>
            </Toolbar>
        </AppBar>
    )
}

export default Banner
import { Box, Toolbar } from "@mui/material"
import { Outlet } from "react-router-dom"
import Banner from "../components/Banner";


const MapWrapper = () => {
    return (
        <Box
            sx={{
                display: "flex",
                flexDirection: "column",
                height: "100vh",
            }}
        >
            <Box sx={{ position: "fixed", top: 0, left: 0, right: 0, zIndex: 1 }}>
                <Banner/>
            </Box>
            <Toolbar/>
            <Box
                sx={{
                    flex: 1,
                    display: "flex",
                    minHeight: 0,
                    overscrollBehavior: "contain"
                }}
            >
                <Outlet />
            </Box>
        </Box>
    );
}
export default MapWrapper
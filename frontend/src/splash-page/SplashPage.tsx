import { Box } from "@mui/material";
import LeafletMap from "../components/LeafletMap"

const SplashPage = () => {
    return (
        <Box
            sx={{
                height: "100%",
                width: "100%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                flexDirection: "column"
            }}
        >
            <LeafletMap />
        </Box>
    );
}
export default SplashPage
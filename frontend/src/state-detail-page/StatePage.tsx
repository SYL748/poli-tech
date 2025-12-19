import { Box } from "@mui/material";
import StateMap from "../components/StateMap";
import { Outlet, useParams } from "react-router-dom";
import RightPanelSelect from "../components/DropDown";
import { useEffect, useState } from "react";
import { detailedStates } from "../utils/Utils";

const StatePage = () => {
    const { id } = useParams();
    const [isDetail, setIsDetail] = useState(false);
    const DetailedState = detailedStates;
    useEffect(() => {
        if (id && DetailedState.includes(id)) {
            setIsDetail(true);
        } else {
            setIsDetail(false);
        }
    }, [id]);
    return (
        <Box
            sx={{
                height: "100%",
                width: "100%",
                display: "flex",
                flexDirection: "row",
            }}
        >
            <Box
                sx={{
                    flex: 0.8,
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                }}
            >
                <StateMap isDetail={isDetail} />
            </Box>

            <Box
                sx={{
                    flex: 1.2,
                    position: "relative",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    minWidth: 0,
                }}
            >
                <Box sx={{ position: "absolute", top: 12, right: 12, zIndex: 1100 }}>
                    <RightPanelSelect isDetail={isDetail} />
                </Box>
                <Outlet context={{ isDetail }} />
            </Box>
        </Box>
    );
};
export default StatePage;
import { FormControl, MenuItem, Select } from "@mui/material";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import { isPoliticalParty, ispreClearanceStates, isVoterRegistrationState } from "../utils/Utils";

interface StateMapProps {
    isDetail?: boolean;
}

export default function RightPanelSelect({ isDetail = false }: StateMapProps) {
    const { id } = useParams();
    const { pathname } = useLocation();
    const navigate = useNavigate();
    const isPolitical = isPoliticalParty(id!)
    const isPreclearance = ispreClearanceStates(id!)
    const isVoterRegistration = isVoterRegistrationState(id!)

    const BASE_STATE_MENUS = [
        { label: "Provisional Ballots", route: "" },
        { label: "Equipment Summary", route: "state-voting-equipment" },
        { label: "Active Voters", route: "2024-EAVS-active-voters" },
        { label: "Pollbook Deletions", route: "pollbook-deletions" },
        { label: "Mail Ballot Rejections", route: "mail-ballot-rejections" },
        { label: "Equipment Quality vs Rejection", route: "quality_vs_rejection" },
        { label: "EAVS Registered Trends", route: "eavs-registered-trends" }
    ];

    let STATE_MENUS = [...BASE_STATE_MENUS];

    if (isPolitical) {
        STATE_MENUS.push({ label: "Mail In Ballot Bubble Chart", route: "mail-ballot-bubble" });
    }
    if (isPreclearance) {
        STATE_MENUS.push({ label: "Gingle's Chart", route: "gingle-chart" });
        STATE_MENUS.push({ label: "Equipment Ecological Inference", route: "equipment-ei" });
        STATE_MENUS.push({ label: "Rejected Ballots Ecological Inference", route: "reject-ballot-ei" });
    }
    if (isVoterRegistration) {
        STATE_MENUS.push({ label: "Voter Registration Data", route: "voter-registration-data" });
    }

    const seg = pathname.split("/").pop();
    const current = STATE_MENUS.some(m => m.route === seg) ? (seg as string) : STATE_MENUS[0].route;

    const handleChange = (e: any) => {
        const next = e.target.value as string;
        navigate(next ? `/state/${id}/${next}` : `/state/${id}`);
    };

    return (
        <FormControl size="small" sx={{ minWidth: 220, bgcolor: "#fff" }}>
            <Select
                value={current}
                onChange={handleChange}
                displayEmpty
                renderValue={(value) => {
                    const item = STATE_MENUS.find(m => m.route === (value as string));
                    return item ? item.label : STATE_MENUS[0].label;
                }}
            >
                {STATE_MENUS.map(m => (
                    <MenuItem key={m.route || "index"} value={m.route}>
                        {m.label}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    );
}

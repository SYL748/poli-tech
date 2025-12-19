import { Box, Button } from "@mui/material";

interface ButtonGroupColumnProps {
    buttons: {
        label: string;
        onClick: () => void;
    }[];
    activeLabel?: string;
}

const ButtonGroupColumn = ({ buttons, activeLabel }: ButtonGroupColumnProps) => {
    return (
        <Box
            sx={{
                position: "absolute",
                top: 12,
                left: 12,
                display: "flex",
                flexDirection: "row",
                gap: 0,
                zIndex: 1200,
            }}
        >
            {buttons.map(({ label, onClick }) => (
                <Button
                    key={label}
                    onClick={onClick}
                    sx={{
                        borderRadius: 0,
                        textTransform: "uppercase",
                        fontWeight: 400,
                        letterSpacing: 1,
                        px: 2.5,
                        py: 1,
                        fontSize: "14px",
                        borderBottom: activeLabel === label ? "2px solid" : "2px solid transparent",
                        color: activeLabel === label ? "primary.main" : "text.secondary",
                        borderColor: activeLabel === label ? "primary.main" : "transparent",
                    }}
                >
                    {label}
                </Button>
            ))}
        </Box>
    )
}

export default ButtonGroupColumn
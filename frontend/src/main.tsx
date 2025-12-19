import React from 'react'
import ReactDOM from "react-dom/client";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import CssBaseline from "@mui/material/CssBaseline";
import App from './App.tsx';
import { GlobalStyles } from '@mui/material';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <CssBaseline />
            <GlobalStyles
                styles={{
                    html: { height: "100%", overscrollBehavior: "none" },
                    body: { height: "100%", overflow: "hidden", overscrollBehavior: "none" },
                    "#root": { height: "100%" },

                    ".leaflet-container .leaflet-interactive:focus": {
                        outline: "none !important"
                    },
                    ".leaflet-container .leaflet-interactive:focus-visible": {
                        outline: "none !important"
                    }
                }}
            />
            <App />
        </QueryClientProvider>
    </React.StrictMode>,
)

import { BrowserRouter, Route, Routes } from "react-router-dom"

import SplashPage from "./splash-page/SplashPage"
import StatePage from "./state-detail-page/StatePage"
import EquipmentOverview from "./splash-page/EquipmentOverview"
import PollbookDeletions from "./state-detail-page/PollbookDeletions"
import MailBallotRejections from "./state-detail-page/MailBallotRejections"
import EquipmentAge from "./splash-page/EquipmentAge"
import StateEquipment from "./splash-page/StateEquipment"
import PoliticalPartyStates from "./splash-page/PoliticalPartyStates"
import VoterRegistrationStates from "./splash-page/VoterRegistrationsStates"
import EquipementQualityVsRejection from "./state-detail-page/EquipmentQualityVsRejection"
import MapWrapper from "./components/MapWrapper"
import ProvisionalBallots from "./state-detail-page/ProvisionalBallots"
import EAVSActiveVoters from "./state-detail-page/EAVSActiveVoters"
import StateVotingEquipmentSummary from "./state-detail-page/StateVotingEquipmentSummary"
import MailInBallotBubbleChart from "./state-detail-page/MailInBallotBubbleChart"
import EquipmentEI from "./state-detail-page/EquipmentEI"
import RejectBallotEI from "./state-detail-page/RejectBallotEI"
import GinglesChart from "./state-detail-page/GinglesChart"
import EAVSRegisteredTrends from "./state-detail-page/EAVSRegisteredTrends"
import VoterRegistrationData from "./state-detail-page/VoterRegistrationData"

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MapWrapper />}>
                    <Route index element={<SplashPage />} />
                    <Route path="equipment/age" element={<EquipmentAge />} />
                    <Route path="equipment/state" element={<StateEquipment />} />
                    <Route path="equipment/overview" element={<EquipmentOverview />} />
                    <Route path="political-party-states" element={<PoliticalPartyStates />} />
                    <Route path="voter-registration-states" element={<VoterRegistrationStates />} />
                    <Route path="state/:id" element={<StatePage />}>
                        <Route index element={<ProvisionalBallots />} /> 
                        <Route path="state-voting-equipment" element={<StateVotingEquipmentSummary />} /> 
                        <Route path="2024-EAVS-active-voters" element={<EAVSActiveVoters />} />
                        <Route path="pollbook-deletions" element={<PollbookDeletions />} />
                        <Route path="mail-ballot-rejections" element={<MailBallotRejections />} />
                        <Route path="mail-ballot-bubble" element={<MailInBallotBubbleChart />} />
                        <Route path="quality_vs_rejection" element={<EquipementQualityVsRejection />} />
                        <Route path="equipment-ei" element={<EquipmentEI />} />
                        <Route path="reject-ballot-ei" element={<RejectBallotEI />} />
                        <Route path="gingle-chart" element={<GinglesChart />} />
                        <Route path="eavs-registered-trends" element={<EAVSRegisteredTrends />} />
                        <Route path="voter-registration-data" element={<VoterRegistrationData />} />
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter >
    )
}

export default App

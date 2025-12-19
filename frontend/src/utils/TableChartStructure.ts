// Gui-3 Provisional Table Chart Structure
export const ProvisionalColumns = [
    "Region",
    "Not on List",
    "No ID",
    "Official Challenge Eligibilty",
    "Another Challenge Eligibility",
    "Not Resident",
    "Registration Not Updated",
    "Didn't Surrender Mail Ballot",
    "Extended Voting Hours",
    "Used SDR",
    "Other\nReason",
    "Total"
];
export const ReturnedProvisionalFields = [
    "voterNotOnList",
    "voterLackedId",
    "electionOfficialChallengedEligibility",
    "anotherPersonChallengedEligibility",
    "voterNotResident",
    "voterRegistrationNotUpdated",
    "voterDidNotSurrenderMailBallot",
    "judgeExtendedVotingHours",
    "voterUsedSdr",
    "otherReason"
] as const;

// Gui-7 ActiveVoter Registration
export const ActiveVoterColumn = [
    "Region",
    "Total Registered",
    "Active Registered",
    "Inactive Registered",
    "Total"
];
export const ReturnedActiveVoter = [
    "totalRegisteredVoter",
    "activeRegisteredVoter",
    "inactiveRegisteredVoter"
] as const

// Gui-8 Pollbook Deletions Table Chart Structure
export const PollbookDeletionColumns = [
    "Region",
    "Moved",
    "Death",
    "Felony",
    "Fail Response",
    "Incompetent To Vote",
    "Voter Request",
    "Duplicate Record",
    "Total"
];
export const ReturnedPollbookDeletionFields = [
    "moved",
    "death",
    "felony",
    "failResponse",
    "incompetentToVote",
    "voterRequest",
    "duplicateRecord"
] as const;

// Gui-9 Mail Ballot Rejections Table Chart Structure
export const MailBallotRejectionColumns = [
    "Region",
    "Late",
    "Missing Voter Signature",
    "Missing Witness Signature",
    "Signature Mismatch",
    "Unofficial Envelope",
    "Missing Ballot From Evelope",
    "No Secrecy Envelope",
    "Multiple Ballots in Envelope",
    "Envelope Not Sealed",
    "No Postmark",
    "No Address On Envelope",
    "Voter Deceased",
    "Voter Already Voted",
    "Missing Documentation",
    "Voter Not Eligible",
    "No Ballot Application",
    "Total"
]
export const ReturnedMailBallotRejectionFields = [
    "late",
    "missingVoterSignature",
    "missingWitnessSignature",
    "nonMatchingVoterSignature",
    "unofficialEnvelope",
    "ballotMissingFromEnvelope",
    "noSecrecyEnvelope",
    "multipleBallotsInOneEnvelope",
    "envelopeNotSealed",
    "noPostmark",
    "noResidentAddressOnEnvelope",
    "voterDeceased",
    "voterAlreadyVoted",
    "missingDocumentation",
    "voterNotEligible",
    "noBallotApplication"
] as const


export const VoterRegistrationRegionSumFields = [
    "Region",
    "Total Registered",
    "Number of Republican",
    "Number of Democratic",
    "Unaffiliated Counts",
    "Completeness",
    "Contact Completeness",
]
export const ReturnedVoterRegistrationRegionSumFields = [
    "totalNumRegisteredVoters",
    "totalNumRepublican",
    "totalNumDemocratic",
    "totalNumUnaffiliated",
    "completeness",
    "contactCompleteness"
] as const
export const ReturnStateVotingEquipmentSummary = [
    "manufacturer",
    "modelName",
    "equipmentType",
    "quantity",
    "age",
    "os",
    "certificationLevel",
    "scanningRate",
    "errorRate",
    "reliability",
    "quality",
    "shortDescription"
]
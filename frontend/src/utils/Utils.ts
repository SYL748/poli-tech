export const detailedStates:string[] = ['10','19','17','12','05',"48"];
export const politicalPartyStates:string[] = ['19', '17', '05'];
export const democrat:string[] = ['17'];
export const republican:string[] = ['19', '05'];
export const preClearanceStates:string[] = ["48"];
export const voterRegistrationStates:string[] = ["12"];

export const isPoliticalParty = (id:string) => 
    politicalPartyStates.includes(id)
export const ispreClearanceStates = (id:string) =>
    preClearanceStates.includes(id)
export const isVoterRegistrationState = (id:string) => 
    voterRegistrationStates.includes(id)
export const isDemocrat = (id:string) => 
    democrat.includes(id)
export const isRepublican = (id:string) => 
    republican.includes(id)

// Florida county -> regionId mapping
export const FL_COUNTY_TO_REGION_ID: Record<string, number> = {
  "Alachua County": 454,
  "Baker County": 455,
  "Bay County": 456,
  "Bradford County": 457,
  "Brevard County": 458,
  "Broward County": 459,
  "Calhoun County": 460,
  "Charlotte County": 461,
  "Citrus County": 462,
  "Clay County": 463,
  "Collier County": 464,
  "Columbia County": 465,
  "Desoto County": 466,
  "Dixie County": 467,
  "Duval County": 468,
  "Escambia County": 469,
  "Flagler County": 470,
  "Franklin County": 471,
  "Gadsden County": 472,
  "Gilchrist County": 473,
  "Glades County": 474,
  "Gulf County": 475,
  "Hamilton County": 476,
  "Hardee County": 477,
  "Hendry County": 478,
  "Hernando County": 479,
  "Highlands County": 480,
  "Hillsborough County": 481,
  "Holmes County": 482,
  "Indian River County": 483,
  "Jackson County": 484,
  "Jefferson County": 485,
  "Lafayette County": 486,
  "Lake County": 487,
  "Lee County": 488,
  "Leon County": 489,
  "Levy County": 490,
  "Liberty County": 491,
  "Madison County": 492,
  "Manatee County": 493,
  "Marion County": 494,
  "Martin County": 495,
  "Miami-Dade County": 496,
  "Monroe County": 497,
  "Nassau County": 498,
  "Okaloosa County": 499,
  "Okeechobee County": 500,
  "Orange County": 501,
  "Osceola County": 502,
  "Palm Beach County": 503,
  "Pasco County": 504,
  "Pinellas County": 505,
  "Polk County": 506,
  "Putnam County": 507,
  "St. Johns County": 508,
  "St. Lucie County": 509,
  "Santa Rosa County": 510,
  "Sarasota County": 511,
  "Seminole County": 512,
  "Sumter County": 513,
  "Suwannee County": 514,
  "Taylor County": 515,
  "Union County": 516,
  "Volusia County": 517,
  "Wakulla County": 518,
  "Walton County": 519,
  "Washington County": 520,
} as const;

export function getFloridaRegionId(countyName: string): number | undefined {
  return FL_COUNTY_TO_REGION_ID[countyName];
}
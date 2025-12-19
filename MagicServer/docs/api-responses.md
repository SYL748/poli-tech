# API Response Examples

**Version:** 1.0 (Current)  
**Last Updated:** December 2025  
**Total Endpoints Documented:** 22

This document contains example JSON responses for all API endpoints in the MagicServer application.

---

## Geo API - Geographic Data Endpoints

### GUI-1: Get All States for Splash Page Map

**Endpoint:** `GET /api/geo/states`

**Use Case:** Display all 48 mainland states on the splash page map. Users can click on any state to navigate to the state detail page. Detailed states should be highlighted with thicker borders.

**Response:** Array of StateGeoDto objects

**Example Response:**

```json
[
  {
    "stateId": 1,
    "name": "Alabama",
    "boundary": {
      "properties": {
        "population": 5024279,
        "NAME": "Alabama",
        "STATE_ID": 1
      },
      "geometry": {
        "type": "MultiPolygon",
        "coordinates": [
          [
            [
              [-88.473227, 30.223334],
              [-88.394438, 30.367688],
              [-88.295738, 30.409772],
              [-88.180823, 30.402882],
              [-87.592794, 30.278044],
              [-87.530702, 30.274377],
              [-87.524865, 30.276213]
            ]
          ]
        ]
      }
    },
    "lon": -86.9023,
    "lat": 32.3182,
    "zoom": 6.5
  },
  {
    "stateId": 5,
    "name": "California",
    "boundary": {
      "properties": {
        "population": 39538223,
        "NAME": "California",
        "STATE_ID": 5
      },
      "geometry": {
        "type": "MultiPolygon",
        "coordinates": [
          [
            [
              [-124.409591, 42.009517],
              [-124.38977, 41.993815],
              [-124.382103, 41.978428],
              [-124.363425, 41.968641],
              [-124.344092, 41.961124]
            ]
          ]
        ]
      }
    },
    "lon": -119.4179,
    "lat": 36.7783,
    "zoom": 6.0
  },
  {
    "stateId": 43,
    "name": "Texas",
    "boundary": {
      "properties": {
        "population": 29145505,
        "NAME": "Texas",
        "STATE_ID": 43
      },
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [-106.645646, 31.754455],
            [-106.52874, 31.783038],
            [-106.324471, 31.776982],
            [-105.964568, 31.784902],
            [-105.691528, 31.828388]
          ]
        ]
      }
    },
    "lon": -99.9018,
    "lat": 31.9686,
    "zoom": 6.5
  }
  // ... more states (48 total)
]
```

**Field Descriptions:**

- `stateId` (Integer): Unique state identifier (1-56)
- `name` (String): Full state name
- `boundary` (Object): Geographic boundary data containing the state's geometry
  - `properties` (Object): Metadata about the state
    - `NAME` (String): State name (for map labels)
    - `STATE_ID` (Integer): State ID
    - Additional properties may include population, demographic data, etc.
  - `geometry` (Object): The actual geographic shape
    - `type` (String): "Polygon" or "MultiPolygon"
    - `coordinates` (Array): Array of coordinate pairs [longitude, latitude]
- `lon` (Double): Center longitude for map centering
- `lat` (Double): Center latitude for map centering
- `zoom` (Double): Recommended zoom level for this state

**Frontend Usage:**

1. Iterate through all states and draw boundaries using the `geometry` coordinates
2. Use `stateId` to determine which states are "detailed states" and highlight them with thicker borders
3. On state click, use `stateId` to navigate to `/state/{stateId}` route
4. When rendering state names, use `boundary.properties.NAME`

---

### GUI-20: Get Specific State for State Detail Page

**Endpoint:** `GET /api/geo/states/{stateId}`

**Use Case:** Display a specific state on the state detail page. The map should be centered at the provided coordinates with the specified zoom level, fitting the state within a GUI component (not full screen). Internal boundaries for EAVS geographic units should be shown for detailed states.

**Example Request:** `GET /api/geo/states/5` (California)

**Response:** Single StateGeoDto object

**Example Response:**

```json
{
  "stateId": 5,
  "name": "California",
  "boundary": {
    "properties": {
      "population": 39538223,
      "area_sq_mi": 163696,
      "capital": "Sacramento",
      "NAME": "California",
      "STATE_ID": 5
    },
    "geometry": {
      "type": "MultiPolygon",
      "coordinates": [
        [
          [
            [-124.409591, 42.009517],
            [-124.38977, 41.993815],
            [-124.382103, 41.978428],
            [-124.363425, 41.968641],
            [-124.344092, 41.961124],
            [-124.31533, 41.944742],
            [-124.302726, 41.934137],
            [-124.293025, 41.92071],
            [-124.27905, 41.90661],
            [-124.265244, 41.892353],
            [-124.25161, 41.877939],
            [-124.238147, 41.863368],
            [-124.224857, 41.848642],
            [-124.211739, 41.833762],
            [-124.198793, 41.818729]
          ]
        ]
      ]
    }
  },
  "lon": -119.4179,
  "lat": 36.7783,
  "zoom": 6.0
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- Same as GUI-1, but for a single state
- `lon`, `lat`, `zoom`: Critical for GUI-20 - use these to center and zoom the map appropriately

**Frontend Usage:**

1. Extract `lon`, `lat`, `zoom` and set map view to these coordinates
2. Draw the state boundary using `boundary.geometry.coordinates`
3. Display state name using `name` or `boundary.properties.NAME`
4. Fit the state within the allocated GUI component using the provided zoom level
5. If this is a detailed state, overlay EAVS geographic unit boundaries (fetched from other endpoints)

**Error Handling:**

- If `404` is returned, show "State not found" message
- If `boundary.geometry` is null, show state outline placeholder or error message
- If `lon`, `lat`, or `zoom` are null, use default map center and zoom

---

### GUI-2: Get Counties for Detailed State (EAVS Geographic Units)

**Endpoint:** `GET /api/geo/states/{stateId}/counties`

**Use Case:** Display internal boundary lines for EAVS geographic units (counties) on detailed state maps. This endpoint should only be called for "detailed states" where county boundaries need to be shown. For non-detailed states, only display the state boundary from GUI-20.

**Example Request:** `GET /api/geo/states/5/counties` (California counties)

**Response:** Array of CountyGeoDto objects

**Example Response:**

```json
[
  {
    "countyName": "Los Angeles County",
    "boundary": {
      "properties": {
        "COUNTY_NAME": "Los Angeles County",
        "GEO_ID": "CA-037"
      },
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [-118.668176, 33.704538],
            [-118.155389, 33.704538],
            [-118.155389, 34.823807],
            [-118.668176, 34.823807],
            [-118.668176, 33.704538]
          ]
        ]
      }
    }
  },
  {
    "countyName": "San Diego County",
    "boundary": {
      "properties": {
        "COUNTY_NAME": "San Diego County",
        "GEO_ID": "CA-073"
      },
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [-117.599083, 32.534156],
            [-116.095444, 32.534156],
            [-116.095444, 33.507889],
            [-117.599083, 33.507889],
            [-117.599083, 32.534156]
          ]
        ]
      }
    }
  }
  // ... more counties
]
```

**Field Descriptions:**

- `countyName` (String): Name of the county
- `boundary` (Object): Geographic boundary data containing the county's geometry
  - `properties` (Object): Metadata about the county
    - `COUNTY_NAME` (String): County name
    - `GEO_ID` (String): Geographic identifier for the county
  - `geometry` (Object): The actual geographic shape
    - `type` (String): Usually "Polygon" for counties
    - `coordinates` (Array): Array of coordinate pairs [longitude, latitude]

**Frontend Usage:**

1. Check if current state is a "detailed state" before calling this endpoint
2. Overlay county boundaries on top of the state boundary from GUI-20
3. Use different styling for county lines (e.g., thinner lines, different color)
4. Enable interactivity (hover, click) on counties for additional data display
5. Label counties using `countyName` at the centroid of each boundary

**When to Use:**

- **Detailed States**: Call this endpoint to show EAVS geographic unit boundaries
- **Non-Detailed States**: Skip this endpoint, only show state boundary

---

### GUI-2: Get Political Party CVAP Percentages

**Endpoint:** `GET /api/facts/states/{stateId}/political-party-cvap`

**Use Case:** Display the percentage of CVAP (Citizen Voting Age Population) voters eligible to vote for Political Party detailed states (Florida and Illinois only). This is calculated as: (2024 EAVS registered voters) / (2023 ACS CVAP).

**Example Request:** `GET /api/facts/states/12/political-party-cvap` (Florida, assuming state_id=12)

**Response:** Array of PoliticalPartyCvapDto objects

**Example Response:**

```json
[
  {
    "stateId": 12,
    "name": "Democratic",
    "cvapPercent": 36.2
  },
  {
    "stateId": 12,
    "name": "Republican",
    "cvapPercent": 35.8
  },
  {
    "stateId": 12,
    "name": "Independent",
    "cvapPercent": 23.4
  },
  {
    "stateId": 12,
    "name": "Other",
    "cvapPercent": 4.6
  }
]
```

**Example Response (Empty for Non-Political Party States):**

```json
[]
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier
- `name` (String): Political party name (e.g., "Democratic", "Republican", "Independent")
- `cvapPercent` (Double): Percentage of CVAP for this party (0-100)

**Frontend Usage:**

1. Check if current state is a "Political Party detailed state" (Florida or Illinois)
2. If yes, call this endpoint and display the CVAP percentages
3. Display as:
   - Bar chart showing party breakdown
   - Text summary: "XX% of CVAP voters are registered Democrats, YY% Republican..."
   - Pie chart showing party distribution
4. If empty array returned, don't show CVAP data section

**Calculation:**

The percentage represents: `(Registered Voters for Party / Total CVAP) × 100`

- Numerator: 2024 EAVS registered voters by party
- Denominator: 2023 ACS Citizen Voting Age Population

**States Applicable:**

- Florida (state_id TBD)
- Illinois (state_id TBD)
- Returns empty array for all other states

---

### GUI-3: Get Provisional Ballot Categories by State

**Endpoint:** `GET /api/facts/states/{stateId}/provisional`

**Use Case:** Display provisional ballot data broken down by category for a bar chart on the state detail page. Shows the various reasons why provisional ballots were cast (E2a-E2i categories) plus an "other reasons" category.

**Example Request:** `GET /api/facts/states/5/provisional` (California)

**Response:** Single ProvisionalByStateDto object

**Example Response:**

```json
{
  "stateId": 5,
  "voterNotOnList": 12450,
  "voterLackedId": 8234,
  "electionOfficialChallengedEligibility": 1523,
  "anotherPersonChallengedEligibility": 892,
  "voterNotResident": 2103,
  "voterRegistrationNotUpdated": 5678,
  "voterDidNotSurrenderMailBallot": 3421,
  "judgeExtendedVotingHours": 1092,
  "voterUsedSdr": 4567,
  "otherReasons": 2890
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier
- `voterNotOnList` (Integer): Count of provisional ballots because voter not on registration list
- `voterLackedId` (Integer): Count of provisional ballots because voter lacked proper ID
- `electionOfficialChallengedEligibility` (Integer): Count where election official challenged voter eligibility
- `anotherPersonChallengedEligibility` (Integer): Count where another person challenged voter eligibility
- `voterNotResident` (Integer): Count of provisional ballots because voter not a resident
- `voterRegistrationNotUpdated` (Integer): Count because voter registration was not updated
- `voterDidNotSurrenderMailBallot` (Integer): Count because voter didn't surrender mail ballot
- `judgeExtendedVotingHours` (Integer): Count when judge extended voting hours
- `voterUsedSdr` (Integer): Count when voter used same-day registration
- `otherReasons` (Integer): Count of provisional ballots for other/miscellaneous reasons

**Frontend Usage (Bar Chart):**

1. Create a bar chart with 10 categories
2. X-axis labels (shortened for display):
   - "Not on List"
   - "Lacked ID"
   - "Official Challenge"
   - "Person Challenge"
   - "Not Resident"
   - "Registration Not Updated"
   - "Didn't Surrender Mail Ballot"
   - "Extended Hours"
   - "Same-Day Registration"
   - "Other"
3. Y-axis: Count of provisional ballots
4. Each bar represents the count for that category
5. Include legends/annotations to help users understand categories
6. Consider showing percentages or tooltips with full category names

**Data Interpretation:**

- High "Not on List" values may indicate registration issues
- High "Lacked ID" values may indicate voter ID requirement impacts
- "Same-Day Registration" shows SDR usage
- Total provisional ballots = sum of all categories

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions E2a through E2i:

- E2a: Voter not on registration list
- E2b: Voter lacked required ID
- E2c: Election official challenged eligibility
- E2d: Another person challenged eligibility
- E2e: Voter not a resident
- E2f: Voter registration not updated
- E2g: Voter did not surrender mail ballot
- E2h: Judge extended voting hours
- E2i: Voter used same-day registration
- Other: Any reasons not covered above

---

### GUI-4: Get Provisional Ballot Categories by Region

**Endpoint:** `GET /api/facts/states/{stateId}/regions/provisional`

**Use Case:** Display provisional ballot data broken down by category and EAVS geographic unit (county/region) for a detailed table on the state detail page. Each row represents an EAVS geographic unit with columns for each provisional ballot category, enabling users to see regional variations in provisional ballot usage.

**Example Request:** `GET /api/facts/states/5/regions/provisional` (California regions)

**Response:** Array of ProvisionalByRegionDto objects

**Example Response:**

```json
[
  {
    "regionName": "Los Angeles County",
    "voterNotOnList": 3421,
    "voterLackedId": 2156,
    "electionOfficialChallengedEligibility": 423,
    "anotherPersonChallengedEligibility": 189,
    "voterNotResident": 567,
    "voterRegistrationNotUpdated": 1234,
    "voterDidNotSurrenderMailBallot": 876,
    "judgeExtendedVotingHours": 234,
    "voterUsedSdr": 1543
  },
  {
    "regionName": "San Diego County",
    "voterNotOnList": 1876,
    "voterLackedId": 1234,
    "electionOfficialChallengedEligibility": 234,
    "anotherPersonChallengedEligibility": 98,
    "voterNotResident": 345,
    "voterRegistrationNotUpdated": 678,
    "voterDidNotSurrenderMailBallot": 456,
    "judgeExtendedVotingHours": 123,
    "voterUsedSdr": 890
  },
  {
    "regionName": "Orange County",
    "voterNotOnList": 1543,
    "voterLackedId": 987,
    "electionOfficialChallengedEligibility": 176,
    "anotherPersonChallengedEligibility": 67,
    "voterNotResident": 234,
    "voterRegistrationNotUpdated": 543,
    "voterDidNotSurrenderMailBallot": 321,
    "judgeExtendedVotingHours": 89,
    "voterUsedSdr": 765
  }
  // ... more regions
]
```

**Example Response (Empty for States with No Data):**

```json
[]
```

**Field Descriptions:**

- `regionName` (String): Name of the EAVS geographic unit (county, town, or other region)
- `voterNotOnList` (Integer): Count of provisional ballots because voter not on registration list
- `voterLackedId` (Integer): Count of provisional ballots because voter lacked proper ID
- `electionOfficialChallengedEligibility` (Integer): Count where election official challenged voter eligibility
- `anotherPersonChallengedEligibility` (Integer): Count where another person challenged voter eligibility
- `voterNotResident` (Integer): Count of provisional ballots because voter not a resident
- `voterRegistrationNotUpdated` (Integer): Count because voter registration was not updated
- `voterDidNotSurrenderMailBallot` (Integer): Count because voter didn't surrender mail ballot
- `judgeExtendedVotingHours` (Integer): Count when judge extended voting hours
- `voterUsedSdr` (Integer): Count when voter used same-day registration

**Frontend Usage (Data Table):**

1. **Table Structure:**

   - Rows: One row per EAVS geographic unit (region/county)
   - Columns: Region name + each provisional ballot category + total column
   - Fixed headers (should remain visible during scrolling)
   - Paginated or scrollable content

2. **Column Headers:**

   - "Region/County"
   - "Not on List"
   - "Lacked ID"
   - "Official Challenge"
   - "Person Challenge"
   - "Not Resident"
   - "Registration Not Updated"
   - "Didn't Surrender Mail Ballot"
   - "Extended Hours"
   - "Same-Day Registration"
   - "Total" (calculated)

3. **Calculated Fields:**

   - **Row Total**: Sum all categories for each region
   - **Column Totals**: Sum each category across all regions
   - **Grand Total**: Sum of all provisional ballots in the state

4. **Display Features:**

   - Sort by any column (ascending/descending)
   - Filter/search by region name
   - Highlight regions with unusually high values
   - Show percentages in tooltips (e.g., "23% of state total")
   - Export to CSV functionality

5. **Pagination (Preferred):**

   - Show 10-25 regions per page
   - Include page navigation controls
   - Display "Showing X-Y of Z regions"

6. **Alternative Scrolling:**
   - Fixed header row during vertical scrolling
   - Fixed region name column during horizontal scrolling
   - Sticky totals row at bottom

**Data Interpretation:**

- **Regional Patterns**: Compare values across regions to identify geographic disparities
- **High "Not on List"**: May indicate voter registration database issues in that region
- **High "Lacked ID"**: Shows impact of voter ID requirements by region
- **"Same-Day Registration"**: Shows SDR availability and usage by region
- **Urban vs Rural**: Large counties (Los Angeles) vs small counties show different patterns

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions E2a through E2i, reported at the geographic unit level:

- E2a: Voter not on registration list
- E2b: Voter lacked required ID
- E2c: Election official challenged eligibility
- E2d: Another person challenged eligibility
- E2e: Voter not a resident
- E2f: Voter registration not updated
- E2g: Voter did not surrender mail ballot
- E2h: Judge extended voting hours
- E2i: Voter used same-day registration

**Note:** Unlike GUI-3 (state-level), this endpoint does NOT include an "otherReasons" field. The regional data focuses on the nine specific EAVS categories only.

**Relationship to Other GUI Components:**

- **GUI-3 (Bar Chart)**: Shows state-wide totals
- **GUI-4 (This Table)**: Shows breakdown by region
- **GUI-5 (Choropleth Map)**: Shows geographic distribution of total provisional ballots

Together these three components provide comprehensive views of provisional ballot data at different levels of granularity.

---

### GUI-7: Get Active Voters Data by State

**Endpoint:** `GET /api/facts/states/{stateId}/active-voters`

**Use Case:** Display active and inactive voter registration data for a state in a bar chart on the state detail page. Shows the breakdown of active registered voters, inactive registered voters, and total registered voters. This is the default view when "Active Voters" is selected from the EAVS data category drop-down menu.

**Example Request:** `GET /api/facts/states/5/active-voters` (California)

**Response:** Single ActiveVotersByStateDto object

**Example Response:**

```json
{
  "stateId": 5,
  "activeRegisteredVoter": 22047448,
  "inactiveRegisteredVoter": 1523890,
  "totalRegisteredVoter": 23571338
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier
- `activeRegisteredVoter` (Integer): Number of active registered voters (EAVS A1)
- `inactiveRegisteredVoter` (Integer): Number of inactive registered voters (EAVS A2)
- `totalRegisteredVoter` (Integer): Total registered voters (calculated as active + inactive)

**Frontend Usage (Bar Chart):**

1. **Bar Chart Structure:**

   - Three bars showing: Active, Inactive, Total
   - Y-axis: Number of registered voters
   - X-axis labels: "Active Voters", "Inactive Voters", "Total Voters"

2. **Visualization Options:**

   - **Option A (Simple)**: Three separate bars
   - **Option B (Stacked)**: Stacked bar showing active (bottom) and inactive (top)
   - **Option C (Comparison)**: Active vs Inactive side-by-side, with total as reference line

3. **Display Features:**

   - Format numbers with comma separators (e.g., "22,047,448")
   - Show percentages: "93.5% Active, 6.5% Inactive"
   - Tooltips with exact counts
   - Color coding: Green for active, Orange for inactive, Blue for total

4. **Additional Metrics:**
   - Calculate active voter percentage: `(active / total) × 100`
   - Show text summary: "XX.X% of registered voters are active"

**EAVS Data Mapping:**

- `activeRegisteredVoter`: EAVS Question A1 - Active registered voters
- `inactiveRegisteredVoter`: EAVS Question A2 - Inactive registered voters
- `totalRegisteredVoter`: A1 + A2

**Data Interpretation:**

- **High Active Percentage (>90%)**: Indicates good voter list maintenance
- **High Inactive Percentage (>15%)**: May indicate outdated voter rolls
- **Comparison with CVAP**: Can compare total registered with Citizen Voting Age Population

---

### GUI-7: Get Active Voters Data by Region

**Endpoint:** `GET /api/facts/states/{stateId}/regions/active-voters`

**Use Case:** Display active and inactive voter registration data broken down by EAVS geographic unit (county/region) for a detailed table on the state detail page. Enables users to see regional variations in voter registration status and supports choropleth map visualization showing the percentage of active voters by region.

**Example Request:** `GET /api/facts/states/5/regions/active-voters` (California regions)

**Response:** Array of ActiveVotersByRegionDto objects

**Example Response:**

```json
[
  {
    "regionName": "Los Angeles County",
    "activeRegisteredVoter": 5654234,
    "inactiveRegisteredVoter": 423156,
    "totalRegisteredVoter": 6077390
  },
  {
    "regionName": "San Diego County",
    "activeRegisteredVoter": 1987543,
    "inactiveRegisteredVoter": 134567,
    "totalRegisteredVoter": 2122110
  },
  {
    "regionName": "Orange County",
    "activeRegisteredVoter": 1765432,
    "inactiveRegisteredVoter": 98765,
    "totalRegisteredVoter": 1864197
  },
  {
    "regionName": "Riverside County",
    "activeRegisteredVoter": 1234567,
    "inactiveRegisteredVoter": 87654,
    "totalRegisteredVoter": 1322221
  },
  {
    "regionName": "San Bernardino County",
    "activeRegisteredVoter": 1098765,
    "inactiveRegisteredVoter": 76543,
    "totalRegisteredVoter": 1175308
  }
  // ... more regions
]
```

**Example Response (Empty for States with No Data):**

```json
[]
```

**Field Descriptions:**

- `regionName` (String): Name of the EAVS geographic unit (county, town, or other region)
- `activeRegisteredVoter` (Integer): Number of active registered voters in this region
- `inactiveRegisteredVoter` (Integer): Number of inactive registered voters in this region
- `totalRegisteredVoter` (Integer): Total registered voters in this region (active + inactive)

**Frontend Usage (Data Table):**

1. **Table Structure:**

   - Rows: One row per EAVS geographic unit
   - Columns: Region Name, Active Voters, Inactive Voters, Total Voters, % Active
   - Fixed headers during scrolling
   - Paginated or scrollable content

2. **Column Headers:**

   - "Region/County"
   - "Active Voters"
   - "Inactive Voters"
   - "Total Voters"
   - "% Active" (calculated)

3. **Calculated Fields:**

   - **% Active**: `(activeRegisteredVoter / totalRegisteredVoter) × 100`
   - **Row Totals**: Already provided in `totalRegisteredVoter`
   - **Column Totals**: Sum each column across all regions
   - **State Average**: Average percentage of active voters across regions

4. **Display Features:**

   - Sort by any column (ascending/descending)
   - Filter/search by region name
   - Highlight regions with low active voter percentages (<85%)
   - Format numbers with comma separators
   - Color-code cells based on % active (gradient from red to green)
   - Export to CSV functionality

5. **Pagination (Preferred):**
   - Show 10-25 regions per page
   - Include page navigation controls
   - Display "Showing X-Y of Z regions"

**Frontend Usage (Choropleth Map - Detailed States Only):**

1. **Map Configuration:**

   - Use region boundaries from GUI-2 counties endpoint
   - Color scale based on percentage of active voters
   - Legend showing color bins (e.g., <85%, 85-90%, 90-93%, 93-95%, >95%)

2. **Color Scale:**

   - **Red/Orange (< 85%)**: Low active voter percentage
   - **Yellow (85-90%)**: Below average
   - **Light Green (90-93%)**: Average
   - **Green (93-95%)**: Above average
   - **Dark Green (> 95%)**: Excellent voter list maintenance

3. **Interactivity:**

   - Hover: Show tooltip with region name and exact percentages
   - Click: Highlight region in table and show detailed stats
   - Legend: Allow filtering by percentage range

4. **Calculation for Choropleth:**
   ```javascript
   percentActive = (activeRegisteredVoter / totalRegisteredVoter) * 100;
   ```

**Data Interpretation:**

- **Regional Patterns**: Urban counties may show different patterns than rural counties
- **Low Active %**: May indicate need for voter roll maintenance in that region
- **High Inactive %**: Could indicate population mobility, deceased voters not removed, or other data quality issues
- **Comparison**: Compare regions within the state to identify outliers

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions reported at the geographic unit level:

- A1: Active registered voters (by jurisdiction)
- A2: Inactive registered voters (by jurisdiction)

**Relationship to Other GUI Components:**

- **Bar Chart (State-Level)**: Shows overall state totals
- **Table (This Endpoint)**: Shows breakdown by region with calculated percentages
- **Choropleth Map (Detailed States)**: Visual representation of % active voters by region

Together these three views provide comprehensive understanding of voter registration status at different levels of granularity.

**Performance Note:**

For states with many counties (e.g., Texas with 254 counties), the response may contain a large number of regions. Consider implementing pagination on the backend if needed, or use frontend pagination/virtualization for large datasets.

---

### GUI-8: Get Poll Book Deletion Data by State

**Endpoint:** `GET /api/facts/states/{stateId}/pollbook-deletions`

**Use Case:** Display poll book deletion (voter roll maintenance) data for a state in a bar chart. Shows the various reasons why voters were removed from registration rolls (A12b-A12h categories) such as moved, death, felony, etc.

**Example Request:** `GET /api/facts/states/5/pollbook-deletions` (California)

**Response:** Single PollBookDeletionByStateDto object

**Example Response:**

```json
{
  "stateId": 5,
  "moved": 145230,
  "death": 89450,
  "felony": 12340,
  "failResponse": 34560,
  "incompetentToVote": 5670,
  "voterRequest": 23450,
  "duplicateRecord": 18900
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier
- `moved` (Integer): Count of deletions because voter moved
- `death` (Integer): Count of deletions due to voter death
- `felony` (Integer): Count of deletions due to felony conviction
- `failResponse` (Integer): Count of deletions due to failed response to notices
- `incompetentToVote` (Integer): Count of deletions due to mental incompetence
- `voterRequest` (Integer): Count of deletions by voter request
- `duplicateRecord` (Integer): Count of deletions due to duplicate registration

**Frontend Usage (Bar Chart):**

1. **Bar Chart Structure:**

   - Seven bars showing each deletion category
   - Y-axis: Number of deletions
   - X-axis labels: "Moved", "Death", "Felony", "Failed Response", "Incompetent", "Voter Request", "Duplicate"

2. **Display Features:**
   - Format numbers with comma separators
   - Show percentages of total deletions
   - Tooltips with exact counts
   - Color coding by reason severity

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions A12b through A12h regarding voter roll maintenance.

---

### GUI-8: Get Poll Book Deletion Data by Region

**Endpoint:** `GET /api/facts/states/{stateId}/regions/pollbook-deletions`

**Use Case:** Display poll book deletion data broken down by EAVS geographic unit (county/region) for a detailed table. Enables users to see regional variations in voter roll maintenance practices.

**Example Request:** `GET /api/facts/states/5/regions/pollbook-deletions` (California regions)

**Response:** Array of PollBookDeletionByRegionDto objects

**Example Response:**

```json
[
  {
    "regionName": "Los Angeles County",
    "moved": 34520,
    "death": 21340,
    "felony": 2890,
    "failResponse": 8760,
    "incompetentToVote": 1230,
    "voterRequest": 5430,
    "duplicateRecord": 4560
  },
  {
    "regionName": "San Diego County",
    "moved": 18970,
    "death": 11230,
    "felony": 1450,
    "failResponse": 4320,
    "incompetentToVote": 670,
    "voterRequest": 2890,
    "duplicateRecord": 2340
  },
  {
    "regionName": "Orange County",
    "moved": 15430,
    "death": 9870,
    "felony": 1120,
    "failResponse": 3540,
    "incompetentToVote": 540,
    "voterRequest": 2340,
    "duplicateRecord": 1980
  }
  // ... more regions
]
```

**Example Response (Empty for States with No Data):**

```json
[]
```

**Field Descriptions:**

- `regionName` (String): Name of the EAVS geographic unit (county, town, or other region)
- `moved` (Integer): Count of deletions because voter moved
- `death` (Integer): Count of deletions due to voter death
- `felony` (Integer): Count of deletions due to felony conviction
- `failResponse` (Integer): Count of deletions due to failed response to notices
- `incompetentToVote` (Integer): Count of deletions due to mental incompetence
- `voterRequest` (Integer): Count of deletions by voter request
- `duplicateRecord` (Integer): Count of deletions due to duplicate registration

**Frontend Usage (Data Table):**

1. **Table Structure:**

   - Rows: One row per EAVS geographic unit
   - Columns: Region Name + each deletion category + total column
   - Fixed headers during scrolling
   - Paginated or scrollable content

2. **Column Headers:**

   - "Region/County"
   - "Moved"
   - "Death"
   - "Felony"
   - "Failed Response"
   - "Incompetent"
   - "Voter Request"
   - "Duplicate"
   - "Total" (calculated)

3. **Calculated Fields:**

   - **Row Total**: Sum all categories for each region
   - **Column Totals**: Sum each category across all regions
   - **Percentage of Total**: Calculate each region's contribution to state total

4. **Display Features:**
   - Sort by any column
   - Filter/search by region name
   - Highlight regions with unusual patterns
   - Export to CSV functionality

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions A12b through A12h, reported at the geographic unit level.

---

### GUI-9: Get Mail Ballot Rejection Data by State

**Endpoint:** `GET /api/facts/states/{stateId}/mail-ballot-rejections`

**Use Case:** Display mail ballot rejection data for a state in a bar chart. Shows the various reasons why mail-in ballots were rejected (C9b-C9q categories) such as late arrival, missing signature, non-matching signature, etc.

**Example Request:** `GET /api/facts/states/5/mail-ballot-rejections` (California)

**Response:** Single MailBallotRejectionByStateDto object

**Example Response:**

```json
{
  "stateId": 5,
  "late": 45230,
  "missingVoterSignature": 38450,
  "missingWitnessSignature": 12340,
  "nonMatchingVoterSignature": 54320,
  "unofficialEnvelope": 8760,
  "ballotMissingFromEnvelope": 15430,
  "noSecrecyEnvelope": 6780,
  "multipleBallotsInOneEnvelope": 3450,
  "envelopeNotSealed": 9870,
  "noPostmark": 23450,
  "noResidentAddressOnEnvelope": 11230,
  "voterDeceased": 7890,
  "voterAlreadyVoted": 5670,
  "missingDocumentation": 18900,
  "voterNotEligible": 9450,
  "noBallotApplication": 14560
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier
- `late` (Integer): Count of ballots rejected for late arrival
- `missingVoterSignature` (Integer): Count of ballots rejected for missing voter signature
- `missingWitnessSignature` (Integer): Count of ballots rejected for missing witness signature
- `nonMatchingVoterSignature` (Integer): Count of ballots rejected for non-matching voter signature
- `unofficialEnvelope` (Integer): Count of ballots rejected for unofficial envelope
- `ballotMissingFromEnvelope` (Integer): Count of ballots rejected because ballot was missing from envelope
- `noSecrecyEnvelope` (Integer): Count of ballots rejected for no secrecy envelope
- `multipleBallotsInOneEnvelope` (Integer): Count of ballots rejected for multiple ballots in one envelope
- `envelopeNotSealed` (Integer): Count of ballots rejected because envelope was not sealed
- `noPostmark` (Integer): Count of ballots rejected for no postmark
- `noResidentAddressOnEnvelope` (Integer): Count of ballots rejected for no resident address on envelope
- `voterDeceased` (Integer): Count of ballots rejected because voter was deceased
- `voterAlreadyVoted` (Integer): Count of ballots rejected because voter already voted
- `missingDocumentation` (Integer): Count of ballots rejected for missing documentation
- `voterNotEligible` (Integer): Count of ballots rejected because voter was not eligible
- `noBallotApplication` (Integer): Count of ballots rejected because no ballot application was on file

**Frontend Usage (Bar Chart):**

1. **Bar Chart Structure:**

   - 16 bars showing each rejection category
   - Y-axis: Number of rejected ballots
   - X-axis labels: Shortened category names
   - Consider grouping related categories (signature issues, envelope issues, voter eligibility)

2. **Display Features:**

   - Format numbers with comma separators
   - Show percentages of total rejections
   - Tooltips with full category names and exact counts
   - Color coding by rejection type (technical vs eligibility)

3. **Data Interpretation:**
   - High "Late" values may indicate delivery issues
   - High signature mismatch may indicate verification strictness
   - Compare across states to identify policy differences

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions C9b through C9q regarding mail ballot rejection reasons.

---

### GUI-9: Get Mail Ballot Rejection Data by Region

**Endpoint:** `GET /api/facts/states/{stateId}/regions/mail-ballot-rejections`

**Use Case:** Display mail ballot rejection data broken down by EAVS geographic unit (county/region) for a detailed table. Enables users to see regional variations in mail ballot rejection patterns and identify counties with unusually high rejection rates.

**Example Request:** `GET /api/facts/states/5/regions/mail-ballot-rejections` (California regions)

**Response:** Array of MailBallotRejectionByRegionDto objects

**Example Response:**

```json
[
  {
    "regionName": "Los Angeles County",
    "late": 10520,
    "missingVoterSignature": 8940,
    "missingWitnessSignature": 2870,
    "nonMatchingVoterSignature": 12650,
    "unofficialEnvelope": 2030,
    "ballotMissingFromEnvelope": 3590,
    "noSecrecyEnvelope": 1580,
    "multipleBallotsInOneEnvelope": 800,
    "envelopeNotSealed": 2290,
    "noPostmark": 5450,
    "noResidentAddressOnEnvelope": 2610,
    "voterDeceased": 1830,
    "voterAlreadyVoted": 1320,
    "missingDocumentation": 4400,
    "voterNotEligible": 2200,
    "noBallotApplication": 3380
  },
  {
    "regionName": "San Diego County",
    "late": 5670,
    "missingVoterSignature": 4820,
    "missingWitnessSignature": 1550,
    "nonMatchingVoterSignature": 6830,
    "unofficialEnvelope": 1090,
    "ballotMissingFromEnvelope": 1940,
    "noSecrecyEnvelope": 850,
    "multipleBallotsInOneEnvelope": 430,
    "envelopeNotSealed": 1240,
    "noPostmark": 2940,
    "noResidentAddressOnEnvelope": 1410,
    "voterDeceased": 990,
    "voterAlreadyVoted": 710,
    "missingDocumentation": 2370,
    "voterNotEligible": 1190,
    "noBallotApplication": 1820
  }
  // ... more regions
]
```

**Example Response (Empty for States with No Data):**

```json
[]
```

**Field Descriptions:**

- `regionName` (String): Name of the EAVS geographic unit (county, town, or other region)
- All other fields same as state-level endpoint (see above)

**Frontend Usage (Data Table):**

1. **Table Structure:**

   - Rows: One row per EAVS geographic unit
   - Columns: Region Name + each rejection category + total column
   - Fixed headers during scrolling
   - Horizontal scrolling for many columns

2. **Column Headers:**

   - "Region/County"
   - "Late"
   - "Missing Sig"
   - "Missing Witness"
   - "Non-Match Sig"
   - "Unofficial Env"
   - "Ballot Missing"
   - "No Secrecy"
   - "Multiple Ballots"
   - "Not Sealed"
   - "No Postmark"
   - "No Address"
   - "Deceased"
   - "Already Voted"
   - "Missing Docs"
   - "Not Eligible"
   - "No Application"
   - "Total" (calculated)

3. **Calculated Fields:**

   - **Row Total**: Sum all categories for each region
   - **Rejection Rate**: Total rejections / total mail ballots sent (if available)
   - **Column Totals**: Sum each category across all regions

4. **Display Features:**

   - Sort by any column
   - Filter/search by region name
   - Highlight regions with high rejection rates
   - Color-code cells based on rejection counts (heat map)
   - Export to CSV functionality

5. **Data Interpretation:**
   - Compare rejection patterns across regions
   - Identify counties with unusually high/low rejection rates
   - Look for regional patterns (urban vs rural, etc.)

**EAVS Data Mapping:**

These fields correspond to EAVS survey questions C9b through C9q, reported at the geographic unit level.

**Performance Note:**

With 16 fields per region and potentially hundreds of regions, this response can be large. Consider pagination or limiting to top N regions with highest rejection counts.

---

### GUI-11: Get Average Equipment Age for All States

**Endpoint:** `GET /api/facts/states/equipment-age`

**Use Case:** Display a choropleth map on the splash page showing the relative age of voting equipment for all states. The map uses color saturation to indicate equipment age, with darker colors representing older equipment. This helps users identify states with outdated voting infrastructure at a glance.

**Example Request:** `GET /api/facts/states/equipment-age`

**Response:** Array of AverageEquipmentAgeByStateDto objects (48 states)

**Example Response:**

```json
[
  {
    "stateId": 1,
    "averageAgeOfEquipment": 5
  },
  {
    "stateId": 5,
    "averageAgeOfEquipment": 8
  },
  {
    "stateId": 12,
    "averageAgeOfEquipment": 3
  },
  {
    "stateId": 17,
    "averageAgeOfEquipment": 12
  },
  {
    "stateId": 43,
    "averageAgeOfEquipment": 7
  }
  // ... all 48 mainland states
]
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier (1-56)
- `averageAgeOfEquipment` (Integer): Average age of voting equipment in years (weighted by number of devices)

**Frontend Usage (Choropleth Map):**

1. **Map Configuration:**

   - Display on splash page (same component as GUI-1 state boundaries)
   - Overlay choropleth colors on state boundaries
   - Legend showing age bins and colors

2. **Age Bins and Colors:**

   The use case specifies bins from 1 year through 10 years, plus >10 years:

   - **Bin 1-2 years**: Light color (newest equipment)
   - **Bin 3-4 years**: Slightly darker
   - **Bin 5-6 years**: Medium saturation
   - **Bin 7-8 years**: Higher saturation
   - **Bin 9-10 years**: Very saturated
   - **Bin >10 years**: Darkest/most saturated (outdated equipment)

3. **Color Scheme Example (Single Hue):**

   ```javascript
   // Example using blue hue with increasing saturation
   1-2 years:   #E3F2FD (lightest blue)
   3-4 years:   #BBDEFB
   5-6 years:   #64B5F6
   7-8 years:   #2196F3
   9-10 years:  #1976D2
   >10 years:   #0D47A1 (darkest blue)
   ```

4. **Interactivity:**

   - Hover: Show tooltip with state name and exact average age
   - Click: Navigate to state detail page (same as GUI-1)
   - Legend: Show age ranges and color mappings

5. **Display Logic:**
   ```javascript
   function getColorForAge(age) {
     if (age <= 2) return "#E3F2FD";
     if (age <= 4) return "#BBDEFB";
     if (age <= 6) return "#64B5F6";
     if (age <= 8) return "#2196F3";
     if (age <= 10) return "#1976D2";
     return "#0D47A1"; // > 10 years
   }
   ```

**Calculation Method:**

The average age is calculated as a weighted average based on the number of devices:

```
Average Age = SUM(quantity × age) / SUM(quantity)
```

For example, if a state has:

- 100 devices at 3 years old
- 50 devices at 8 years old
- Average = (100×3 + 50×8) / (100+50) = (300 + 400) / 150 = 4.67 ≈ 5 years

**Data Interpretation:**

- **Low Age (1-4 years)**: State has recently updated voting equipment
- **Medium Age (5-8 years)**: Equipment is in mid-lifecycle
- **High Age (>10 years)**: Equipment may be outdated, potentially needs replacement
- **Security Concern**: Older equipment may have outdated certification or OS vulnerabilities

**Performance Note:**

This endpoint returns all 48 states in a single response (~2-5KB). Consider caching this on the frontend as equipment age data doesn't change frequently.

**Relationship to Other GUI Components:**

- **GUI-1**: Uses same US map component, but GUI-11 adds choropleth layer
- **GUI-12**: Shows equipment counts by type
- **GUI-13**: Shows detailed equipment summary
- **GUI-14**: Shows equipment history over time

Together these provide comprehensive views of voting equipment across the nation.

---

### GUI-12: Get Voting Equipment Totals for All States

**Endpoint:** `GET /api/facts/states/equipment-totals`

**Use Case:** Display a table on the splash page showing voting equipment counts by category for all states. Each row represents a state, and each column shows the number of devices in that category (DRE no VVPAT, DRE with VVPAT, ballot marking device, scanner). This allows users to compare equipment types across states.

**Example Request:** `GET /api/facts/states/equipment-totals`

**Response:** Array of StateEquipmentTotalDto objects (48 states)

**Example Response:**

```json
[
  {
    "stateId": 1,
    "stateName": "Alabama",
    "dreNoVvpat": 0,
    "dreWithVvpat": 125,
    "ballotMarkingDevice": 450,
    "scanner": 1200
  },
  {
    "stateId": 5,
    "stateName": "California",
    "dreNoVvpat": 0,
    "dreWithVvpat": 0,
    "ballotMarkingDevice": 2500,
    "scanner": 8900
  },
  {
    "stateId": 12,
    "stateName": "Florida",
    "dreNoVvpat": 50,
    "dreWithVvpat": 340,
    "ballotMarkingDevice": 1800,
    "scanner": 6700
  },
  {
    "stateId": 43,
    "stateName": "Texas",
    "dreNoVvpat": 0,
    "dreWithVvpat": 0,
    "ballotMarkingDevice": 3200,
    "scanner": 12500
  }
  // ... all 48 states
]
```

**Field Descriptions:**

- `stateId` (Integer): The state identifier (1-56)
- `stateName` (String): Full name of the state (for display in table rows)
- `dreNoVvpat` (Integer): Count of Direct Recording Electronic (DRE) devices without Voter Verified Paper Audit Trail
- `dreWithVvpat` (Integer): Count of DRE devices with VVPAT
- `ballotMarkingDevice` (Integer): Count of ballot marking devices
- `scanner` (Integer): Count of optical/ballot scanners

**Frontend Usage (Data Table):**

1. **Table Structure:**

   ```
   | State      | DRE (No VVPAT) | DRE (With VVPAT) | Ballot Marking | Scanner | Total |
   |------------|----------------|------------------|----------------|---------|-------|
   | Alabama    | 0              | 125              | 450            | 1200    | 1775  |
   | California | 0              | 0                | 2500           | 8900    | 11400 |
   | Florida    | 50             | 340              | 1800           | 6700    | 8890  |
   ```

2. **Column Headers:**

   - "State"
   - "DRE (No VVPAT)"
   - "DRE (With VVPAT)"
   - "Ballot Marking Device"
   - "Scanner"
   - "Total Devices" (calculated)

3. **Calculated Fields:**

   - **Row Total**: Sum all four categories for each state
   - **Column Totals**: Sum each category across all states
   - **Grand Total**: Total devices across all states

4. **Display Features:**

   - Sort by any column (ascending/descending)
   - Filter/search by state name
   - Highlight states still using DRE without VVPAT (security concern)
   - Color-code cells based on equipment type prevalence
   - Export to CSV functionality

5. **Visual Enhancements:**
   - **Warning icon** for states with `dreNoVvpat > 0` (outdated, insecure equipment)
   - **Heat map coloring** showing which equipment type is most common per state
   - **Percentage view toggle** to show % of total instead of counts

**Data Interpretation:**

- **High DRE No VVPAT**: Security concern - these devices lack paper audit trail
- **High Scanner**: Most secure and auditable voting method
- **Mixed Equipment**: States using multiple types may indicate transition period
- **Zero in All Categories**: State may not have data or uses different classification

**Equipment Type Explanations:**

1. **DRE No VVPAT**:

   - Direct Recording Electronic voting machines without paper trail
   - Security concern - no way to audit votes
   - Many states have phased these out

2. **DRE With VVPAT**:

   - DRE machines that produce voter-verifiable paper audit trail
   - More secure than DRE without VVPAT
   - Still being phased out in many states

3. **Ballot Marking Device**:

   - Assist voters in marking ballots (especially accessibility)
   - Produces paper ballot that is scanned
   - Increasingly popular for accessibility

4. **Scanner**:
   - Optical scan machines that read paper ballots
   - Most common and secure voting method
   - Paper ballot provides audit trail

**Performance Note:**

This endpoint returns all 48 states in a single response (~3-8KB). Response is lightweight and suitable for caching.

**Relationship to Other GUI Components:**

- **GUI-11**: Shows average equipment age by state (choropleth)
- **GUI-12**: Shows equipment counts by type (this table)
- **GUI-13**: Shows detailed equipment summary by model
- **GUI-14**: Shows equipment history over time

---

### GUI-14: Get Voting Equipment History for State

**Endpoint:** `GET /api/facts/states/{stateId}/equipment-history`

**Use Case:** Display bar graphs showing voting equipment trends over time for a specific state. After a user selects a state in the GUI-12 equipment totals table, this endpoint provides historical data showing how equipment quantities changed across federal election years (2016, 2018, 2020, 2022, 2024). One bar graph is displayed for each equipment category.

**Example Request:** `GET /api/facts/states/5/equipment-history` (California)

**Response:** Single VotingEquipmentHistoryByStateDto object

**Example Response:**

```json
{
  "stateId": "5",
  "years": [
    {
      "year": 2016,
      "dreNoVvpat": 50,
      "dreWithVvpat": 100,
      "ballotMarkingDevice": 200,
      "scanner": 500
    },
    {
      "year": 2018,
      "dreNoVvpat": 40,
      "dreWithVvpat": 120,
      "ballotMarkingDevice": 250,
      "scanner": 600
    },
    {
      "year": 2020,
      "dreNoVvpat": 30,
      "dreWithVvpat": 150,
      "ballotMarkingDevice": 300,
      "scanner": 700
    },
    {
      "year": 2022,
      "dreNoVvpat": 20,
      "dreWithVvpat": 180,
      "ballotMarkingDevice": 350,
      "scanner": 800
    },
    {
      "year": 2024,
      "dreNoVvpat": 0,
      "dreWithVvpat": 200,
      "ballotMarkingDevice": 400,
      "scanner": 900
    }
  ]
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- `stateId` (String): The state identifier
- `years` (Array): List of year objects, each containing equipment counts for that year
  - `year` (Integer): Federal election year (e.g., 2016, 2018, 2020, 2022, 2024)
  - `dreNoVvpat` (Integer): Count of DRE devices without VVPAT for that year
  - `dreWithVvpat` (Integer): Count of DRE devices with VVPAT for that year
  - `ballotMarkingDevice` (Integer): Count of ballot marking devices for that year
  - `scanner` (Integer): Count of optical scanners for that year

**Frontend Usage (Bar Graphs):**

1. **Create 4 Separate Bar Graphs** (one per equipment category):

   **Graph 1: DRE without VVPAT Trend**

   ```javascript
   {
     type: 'bar',
     data: {
       labels: data.years.map(y => y.year.toString()),
       datasets: [{
         label: 'DRE without VVPAT',
         data: data.years.map(y => y.dreNoVvpat),
         backgroundColor: '#e74c3c' // Red - security concern
       }]
     }
   }
   ```

   **Graph 2: DRE with VVPAT Trend**

   ```javascript
   {
     labels: data.years.map(y => y.year.toString()),
     datasets: [{
       label: 'DRE with VVPAT',
       data: data.years.map(y => y.dreWithVvpat),
       backgroundColor: '#f39c12' // Orange
     }]
   }
   ```

   **Graph 3: Ballot Marking Device Trend**

   ```javascript
   {
     labels: data.years.map(y => y.year.toString()),
     datasets: [{
       label: 'Ballot Marking Device',
       data: data.years.map(y => y.ballotMarkingDevice),
       backgroundColor: '#3498db' // Blue
     }]
   }
   ```

   **Graph 4: Scanner Trend**

   ```javascript
   {
     labels: data.years.map(y => y.year.toString()),
     datasets: [{
       label: 'Scanner',
       data: data.years.map(y => y.scanner),
       backgroundColor: '#2ecc71' // Green - most secure
     }]
   }
   ```

2. **Display Features:**

   - **Layout**: Show all 4 bar graphs in a grid (2×2 or 4 vertically)
   - **Axes**:
     - X-axis: Years (2016, 2018, 2020, 2022, 2024)
     - Y-axis: Quantity of devices
   - **Tooltips**: Show exact count on hover
   - **Annotations**: Label each graph clearly with equipment type
   - **Colors**: Use consistent color coding
     - Red: DRE without VVPAT (security concern)
     - Orange: DRE with VVPAT (being phased out)
     - Blue: Ballot marking devices (accessibility)
     - Green: Scanners (most secure, paper trail)

3. **Alternative: Combined Line Graph**

   ```javascript
   {
     type: 'line',
     data: {
       labels: data.years.map(y => y.year.toString()),
       datasets: [
         {
           label: 'DRE No VVPAT',
           data: data.years.map(y => y.dreNoVvpat),
           borderColor: '#e74c3c',
           fill: false
         },
         {
           label: 'DRE With VVPAT',
           data: data.years.map(y => y.dreWithVvpat),
           borderColor: '#f39c12',
           fill: false
         },
         {
           label: 'Ballot Marking Device',
           data: data.years.map(y => y.ballotMarkingDevice),
           borderColor: '#3498db',
           fill: false
         },
         {
           label: 'Scanner',
           data: data.years.map(y => y.scanner),
           borderColor: '#2ecc71',
           fill: false
         }
       ]
     }
   }
   ```

**Data Interpretation:**

- **Decreasing DRE without VVPAT**: Good trend - phasing out insecure equipment
- **Increasing Scanners**: Good trend - moving to more secure, auditable voting
- **Increasing Ballot Marking Devices**: Improving accessibility for voters with disabilities
- **Stable or increasing DRE with VVPAT**: Some states still using, but better than no VVPAT

**Trend Analysis Examples:**

1. **Security Improvement**: DRE without VVPAT decreasing over time
2. **Equipment Modernization**: Newer equipment types (BMD, scanners) increasing
3. **Paper Trail Adoption**: Movement away from paperless voting
4. **Accessibility Investment**: Ballot marking device growth shows commitment to accessibility

**Use Case Requirement (GUI-14):**

This endpoint supports the requirement to display voting equipment history for a state over federal election years from 2016 to 2024. The user can select a state from the GUI-13 national equipment table, and the system displays bar graphs showing quantity trends for each equipment category.

**Implementation Architecture:**

This endpoint demonstrates **flexible data design** that mirrors normalized storage:

- **Database Storage (Normalized)**:

  ```
  | state_id | year | dre_no_vvpat | dre_with_vvpat | ballot_marking_device | scanner |
  |----------|------|--------------|----------------|-----------------------|---------|
  | 5        | 2016 | 50           | 100            | 200                   | 500     |
  | 5        | 2018 | 40           | 120            | 250                   | 600     |
  | 5        | 2020 | 30           | 150            | 300                   | 700     |
  | 5        | 2022 | 20           | 180            | 350                   | 800     |
  | 5        | 2024 | 0            | 200            | 400                   | 900     |
  ```

  N rows (one per year), easy to extend with new years

- **DTO Response (Array of Year Objects)**:

  ```json
  {
    "stateId": "5",
    "years": [
      { "year": 2016, "dreNoVvpat": 50, ... },
      { "year": 2018, "dreNoVvpat": 40, ... },
      ...
    ]
  }
  ```

  Flexible array structure, automatically supports new years

- **Transformation (Service Layer)**:
  - Query returns N entity objects (rows)
  - Service maps each row directly to year object (1:1)
  - No pivoting needed - structure matches database
  - Returns DTO with array of year objects

**Benefits of Flexible Design:**

1. **Extensibility**: Add 2026 data by inserting new rows (no DTO changes needed)
2. **Frontend Flexibility**: Can easily iterate over years array
3. **Future-Proof**: Works with any number of years automatically
4. **Clean Code**: No hardcoded year-specific fields
5. **Database Aligned**: DTO structure mirrors normalized database design

**Relationship to Other Endpoints:**

- **GUI-6**: Shows current equipment details for a state
- **GUI-11**: Shows average equipment age by state
- **GUI-12**: Shows current equipment totals (user clicks state to see GUI-14)
- **GUI-13**: Shows national equipment overview
- **GUI-14**: Shows historical trend for selected state

**Performance Note:**

- Single query fetches 5 rows (one per year)
- Transformation happens in-memory (very fast)
- Response size: ~500-800 bytes (lightweight)

---

### GUI-15: Get State Comparison Data

**Endpoint:** `GET /api/facts/states/{stateId}/comparison`

**Use Case:** Compare Republican and Democratic states (Illinois and Iowa) across comprehensive voting policies and metrics. This endpoint provides data for displaying side-by-side comparison tables showing how different state policies may impact voter participation and election administration.

**Example Request:** `GET /api/facts/states/17/comparison` (Illinois, assuming state_id=17)

**Response:** Single StateComparisonDto object

**Example Response:**

```json
{
  "stateId": "17",
  "felonyVotingRights": "Automatic restoration upon release from prison",
  "earlyVotingPeriodDays": 40,
  "voterIdRequirement": "No photo ID required",
  "sameDayRegistration": "Yes - Available during early voting and election day",
  "absenteeRequestDeadline": "5 days before election",
  "automaticVoterRegistration": "Yes - Implemented 2017",
  "noExcuseAbsenteeVoting": true,
  "percentMailBallot": 28.5,
  "percentDropbox": 8.3,
  "turnoutRate": 68.2
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

- `stateId` (String): The state identifier
- `felonyVotingRights` (String): Description of felony voting rights restoration policy
  - Categories: No denial, Automatic upon release, After parole/probation, Appeal required
- `earlyVotingPeriodDays` (Integer): Number of days for early voting period
- `voterIdRequirement` (String): Description of voter ID requirements
- `sameDayRegistration` (String): Availability and details of same-day registration
- `absenteeRequestDeadline` (String): Deadline for requesting absentee ballot
- `automaticVoterRegistration` (String): Status and implementation year of automatic voter registration
- `noExcuseAbsenteeVoting` (Boolean): Whether no-excuse absentee voting is available
- `percentMailBallot` (Double): Percentage of votes cast by mail (0-100)
- `percentDropbox` (Double): Percentage of votes cast via drop box (0-100)
- `turnoutRate` (Double): Voter turnout rate percentage (0-100)

**Frontend Usage (Comparison Table):**

1. **Table Structure:**

   ```
   | Metric                        | Illinois (Dem) | Iowa (Rep)    |
   |-------------------------------|----------------|---------------|
   | Felony Voting Rights          | Automatic      | After parole  |
   | Early Voting Period           | 40 days        | 29 days       |
   | Voter ID Required             | No             | Yes           |
   | Same-Day Registration         | Yes            | Yes           |
   | Absentee Request Deadline     | 5 days before  | 10 days before|
   | Automatic Voter Registration  | Yes (2017)     | No            |
   | No-Excuse Absentee            | Yes            | No            |
   | Mail Ballot Percentage        | 28.5%          | 45.2%         |
   | Drop Box Percentage           | 8.3%           | 2.1%          |
   | Turnout Rate                  | 68.2%          | 71.5%         |
   ```

2. **Visualization Options:**

   - **Side-by-side comparison table**: Main view
   - **Bar charts**: Compare numeric values (early voting days, percentages)
   - **Icon indicators**: Visual representation of Yes/No policies
   - **Color coding**: Highlight policy differences

3. **Implementation Example:**

   ```javascript
   async function compareStates(illinoisId, iowaId) {
     const [illinois, iowa] = await Promise.all([
       fetch(`/api/facts/states/${illinoisId}/comparison`).then((r) =>
         r.json()
       ),
       fetch(`/api/facts/states/${iowaId}/comparison`).then((r) => r.json()),
     ]);

     // Display comparison table
     displayComparisonTable(illinois, iowa);
   }
   ```

**Policy Field Interpretations:**

1. **Felony Voting Rights Categories:**

   - "No denial of voting" - No restrictions
   - "Automatic restoration upon release from prison" - Rights restored immediately
   - "Restoration after completing parole and probation" - Delayed restoration
   - "Additional action required" - Must apply for restoration

2. **Voter ID Requirement Examples:**

   - "No photo ID required" - Can use utility bill, bank statement, etc.
   - "Photo ID required" - Must show government-issued photo ID
   - "Strict photo ID" - No alternatives accepted

3. **Same-Day Registration:**

   - "Yes - Available during early voting and election day"
   - "Yes - Election day only"
   - "No - Registration deadline X days before election"

**Data Interpretation:**

- **Higher Early Voting Period**: More accessibility for voters
- **No-Excuse Absentee**: Increases mail voting accessibility
- **Automatic Voter Registration**: Increases registration rates
- **Turnout Rate**: Overall voter participation metric
- **Mail/Dropbox Percentages**: Indicates voting method preferences

**Use Case Requirement (GUI-15):**

This endpoint supports the requirement to compare Republican-dominated and Democratic-dominated states across key voting policies including felony voting rights, registration requirements, and voting method statistics to identify policy impacts on voter participation.

**Applicable States:**

- Illinois (Democratic-dominated)
- Iowa (Republican-dominated)
- Returns 404 for states without comparison data

**Relationship to Other Endpoints:**

- **GUI-21**: Opt-in vs opt-out comparison (uses some same fields)
- **GUI-22**: Rep/Dem registration comparison (uses turnout data)
- **GUI-23**: Early voting comparison (uses early voting metrics)

---

### GUI-23: Get Early Voting Data for State Comparison

**Endpoint:** `GET /api/facts/states/{stateId}/early-voting`

**Use Case:** Compare early voting patterns between Republican and Democratic states (Iowa and Illinois). This endpoint provides comprehensive data about all early voting methods including in-person, mail, drop box, UOCAVA, and provisional voting, with both raw counts and calculated percentages.

**Example Request:** `GET /api/facts/states/17/early-voting` (Illinois)

**Response:** Single StateEarlyVotingDto object

**Example Response:**

```json
{
  "stateId": "17",
  "totalVotesCast": 5845123,
  "inPersonEarly": 2156789,
  "mailAccepted": 1665432,
  "dropboxAccepted": 485321,
  "uocavaAccepted": 45678,
  "provisionalCounted": 123456,
  "totalEarly": 4476676,
  "percentInPersonEarly": 36.9,
  "percentMail": 28.5,
  "percentDropbox": 8.3,
  "percentUocava": 0.8,
  "percentProvisional": 2.1,
  "percentTotalEarly": 76.6
}
```

**Example Response (State Not Found):**

```
HTTP Status: 404 Not Found
Body: (empty)
```

**Field Descriptions:**

**Raw Counts:**

- `stateId` (String): The state identifier
- `totalVotesCast` (Integer): Total votes cast in the election (from vote_turnout_by_state)
- `inPersonEarly` (Integer): Number of in-person early votes
- `mailAccepted` (Integer): Number of mail/absentee ballots accepted and counted
- `dropboxAccepted` (Integer): Number of drop box ballots accepted and counted
- `uocavaAccepted` (Integer): Number of UOCAVA (military/overseas) ballots accepted
- `provisionalCounted` (Integer): Number of provisional ballots counted
- `totalEarly` (Integer): Total early votes (calculated: sum of all early voting methods)

**Calculated Percentages (relative to total_votes_cast):**

- `percentInPersonEarly` (Double): In-person early voting percentage (0-100)
- `percentMail` (Double): Mail ballot percentage (0-100)
- `percentDropbox` (Double): Drop box voting percentage (0-100)
- `percentUocava` (Double): UOCAVA ballot percentage (0-100)
- `percentProvisional` (Double): Provisional ballot percentage (0-100)
- `percentTotalEarly` (Double): Total early voting percentage (0-100)

**Percentage Calculation:**

All percentages are calculated using the formula:

```
Percentage = (method_count / total_votes_cast) × 100
```

For example:

```
percent_mail = (mail_accepted / total_votes_cast) × 100
              = (1,665,432 / 5,845,123) × 100
              = 28.5%
```

**Frontend Usage (Comparison Table):**

1. **Two-State Comparison Table:**

   ```
   | Voting Method          | Illinois      | Iowa          |
   |------------------------|---------------|---------------|
   | Total Votes Cast       | 5,845,123     | 1,654,321     |
   | In-Person Early        | 2,156,789     | 456,789       |
   | In-Person Early %      | 36.9%         | 27.6%         |
   | Mail/Absentee          | 1,665,432     | 748,123       |
   | Mail/Absentee %        | 28.5%         | 45.2%         |
   | Drop Box               | 485,321       | 34,567        |
   | Drop Box %             | 8.3%          | 2.1%          |
   | UOCAVA                 | 45,678        | 12,345        |
   | UOCAVA %               | 0.8%          | 0.7%          |
   | Provisional            | 123,456       | 56,789        |
   | Provisional %          | 2.1%          | 3.4%          |
   | **Total Early**        | **4,476,676** | **1,308,613** |
   | **Total Early %**      | **76.6%**     | **79.1%**     |
   ```

2. **Visualization Options:**

   **Stacked Bar Chart:**

   ```javascript
   function createStackedBarChart(illinoisData, iowaData) {
     return {
       type: "bar",
       data: {
         labels: ["Illinois", "Iowa"],
         datasets: [
           {
             label: "In-Person Early",
             data: [
               illinoisData.percentInPersonEarly,
               iowaData.percentInPersonEarly,
             ],
             backgroundColor: "#3498db",
           },
           {
             label: "Mail",
             data: [illinoisData.percentMail, iowaData.percentMail],
             backgroundColor: "#2ecc71",
           },
           {
             label: "Drop Box",
             data: [illinoisData.percentDropbox, iowaData.percentDropbox],
             backgroundColor: "#f39c12",
           },
           {
             label: "UOCAVA",
             data: [illinoisData.percentUocava, iowaData.percentUocava],
             backgroundColor: "#9b59b6",
           },
           {
             label: "Provisional",
             data: [
               illinoisData.percentProvisional,
               iowaData.percentProvisional,
             ],
             backgroundColor: "#e74c3c",
           },
         ],
       },
       options: {
         scales: {
           x: { stacked: true },
           y: { stacked: true, max: 100 },
         },
       },
     };
   }
   ```

   **Pie Charts (per state):**

   - Show breakdown of voting methods
   - Each slice represents percentage of total votes

3. **Data Display Features:**

   - Show both absolute numbers and percentages
   - Highlight differences between states
   - Calculate and display difference percentages
   - Sortable columns
   - Export to CSV functionality

**Data Interpretation:**

1. **High Early Voting Percentage (>70%)**:

   - Indicates strong early voting culture
   - May reflect state policies encouraging early voting
   - Reduces election day congestion

2. **High Mail Voting Percentage**:

   - May indicate no-excuse absentee policy
   - Could reflect COVID-19 pandemic impact
   - Shows voter preference for mail-in voting

3. **Low Drop Box Percentage**:

   - May indicate fewer drop box locations
   - Possible policy restrictions on drop boxes
   - Voter preference for other methods

4. **Provisional Ballot Percentage**:
   - Higher percentages may indicate:
     - Voter registration issues
     - Same-day registration availability
     - ID verification problems

**Comparison Insights:**

```javascript
function calculateDifferences(illinois, iowa) {
  return {
    inPersonEarlyDiff:
      illinois.percentInPersonEarly - iowa.percentInPersonEarly,
    mailDiff: illinois.percentMail - iowa.percentMail,
    dropboxDiff: illinois.percentDropbox - iowa.percentDropbox,
    totalEarlyDiff: illinois.percentTotalEarly - iowa.percentTotalEarly,
  };
}

// Example output:
// {
//   inPersonEarlyDiff: +9.3%  (Illinois has more in-person early voting)
//   mailDiff: -16.7%          (Iowa has more mail voting)
//   dropboxDiff: +6.2%        (Illinois has more drop box voting)
//   totalEarlyDiff: -2.5%     (Iowa has slightly more total early voting)
// }
```

**Use Case Requirement (GUI-23):**

This endpoint supports the requirement to compare early voting data between Republican-dominated and Democratic-dominated states, showing percentages and absolute numbers for each early voting category to identify policy impacts on voting method preferences.

**Data Sources:**

- Early voting counts: `early_vote_by_state` table
- Total votes cast: `vote_turnout_by_state` table (JOIN)
- Percentages: Calculated on-the-fly in backend

**Technical Notes:**

1. **JOIN Operation**: This endpoint joins two tables:

   - `early_vote_by_state` (main data)
   - `vote_turnout_by_state` (for total_votes_cast)

2. **On-the-Fly Calculations**:

   - `totalEarly` = sum of all early voting methods
   - All percentages calculated dynamically
   - Ensures consistency with turnout data

3. **Edge Case Handling**:
   - If `total_votes_cast` = 0, all percentages return 0.0
   - If either table missing data for state, returns 404

**Applicable States:**

- Illinois (Democratic-dominated)
- Iowa (Republican-dominated)
- Can be extended to other states with data

**Relationship to Other Endpoints:**

- **GUI-15**: Provides additional policy context for early voting
- **GUI-21**: Opt-in/opt-out states may show different patterns
- **GUI-22**: Rep/Dem comparison uses similar comparison methodology

---

### GUI-24: Get Drop Box Voting Bubble Chart Data by Region

**Endpoint:** `GET /api/facts/states/{stateId}/regions/dropbox-voting`

**Use Case:** Display a bubble chart showing the relationship between political party preference and drop box voting usage across EAVS geographic units within Republican-dominated and Democratic-dominated states. Each bubble represents a county/region with position and color determined by voting patterns.

**Example Request:** `GET /api/facts/states/17/regions/dropbox-voting` (Illinois)

**Response:** Array of DropboxVotingByRegionDto objects

**Example Response:**

```json
[
  {
    "regionId": "17_31",
    "regionName": "Cook County",
    "republicanVotes2024": 875432,
    "democratVotes2024": 1654321,
    "percentRepublican": 34.6,
    "percentDropbox": 18.5
  },
  {
    "regionId": "17_43",
    "regionName": "DuPage County",
    "republicanVotes2024": 234567,
    "democratVotes2024": 198765,
    "percentRepublican": 54.1,
    "percentDropbox": 12.3
  },
  {
    "regionId": "17_89",
    "regionName": "Lake County",
    "republicanVotes2024": 156789,
    "democratVotes2024": 187654,
    "percentRepublican": 45.5,
    "percentDropbox": 15.7
  }
  // ... more regions
]
```

**Field Descriptions:**

- `regionId` (String): Unique identifier for the EAVS geographic unit (format: "stateId_countyId")
- `regionName` (String): Name of the county/region
- `republicanVotes2024` (Integer): Number of Republican votes in 2024 Presidential election
- `democratVotes2024` (Integer): Number of Democratic votes in 2024 Presidential election
- `percentRepublican` (Double): Percentage of Republican votes in the region (0-100)
- `percentDropbox` (Double): Percentage of drop box voting in the region (0-100)

**Frontend Usage (Bubble Chart):**

1. **Bubble Chart Configuration:**

   - Each bubble represents one EAVS geographic unit (county/region)
   - X-axis: `percentRepublican` (0-100%)
   - Y-axis: `percentDropbox` (0-100%)
   - Bubble color: Determined by which party has more votes
     - Red: `republicanVotes2024 > democratVotes2024`
     - Blue: `democratVotes2024 > republicanVotes2024`

2. **Visualization Details:**

   ```javascript
   function getBubbleColor(data) {
     return data.republicanVotes2024 > data.democratVotes2024 ? "red" : "blue";
   }

   function plotBubble(data) {
     return {
       x: data.percentRepublican,
       y: data.percentDropbox,
       label: data.regionName,
       color: getBubbleColor(data),
     };
   }
   ```

3. **Interactivity:**

   - Hover: Show tooltip with region name, exact percentages, and vote counts
   - Click: Highlight bubble and show detailed statistics
   - Legend: Explain red vs blue color coding

4. **Chart Annotations:**
   - X-axis label: "Republican Vote Percentage (%)"
   - Y-axis label: "Drop Box Voting Percentage (%)"
   - Title: "Drop Box Voting by Political Party Preference"

**Data Interpretation:**

- **High Y, High X**: Republican regions with high drop box usage
- **High Y, Low X**: Democratic regions with high drop box usage
- **Low Y**: Regions with low drop box adoption regardless of party
- **Patterns**: Look for correlation between party preference and drop box usage

**Use Case Requirement (GUI-24):**

This endpoint supports the requirement to display a drop box voting bubble chart for Republican-dominated and Democratic-dominated states, showing the relationship between political party dominance and drop box voting rates at the county level.

**Note:** Returns all regional dropbox entries for the specified state in a single query. Some states may not use drop boxes, resulting in zero or very low percentages.

---

### GUI-25: Get Equipment Quality vs Rejected Ballots Bubble Chart Data by Region

**Endpoint:** `GET /api/facts/states/{stateId}/regions/equipment-quality-rejection`

**Use Case:** Display a bubble chart associating voting equipment quality with the percentage of rejected ballots at the EAVS geographic unit level. This visualization helps identify potential correlations between equipment quality and ballot rejection rates, which may indicate equipment-related voting issues.

**Example Request:** `GET /api/facts/states/5/regions/equipment-quality-rejection` (California)

**Response:** Array of EquipmentQualityRejectionByRegionDto objects

**Example Response:**

```json
[
  {
    "regionId": "5_37",
    "regionName": "Los Angeles County",
    "equipmentQualityScore": 85,
    "percentRejectedBallots": 1.2
  },
  {
    "regionId": "5_73",
    "regionName": "San Diego County",
    "equipmentQualityScore": 92,
    "percentRejectedBallots": 0.8
  },
  {
    "regionId": "5_59",
    "regionName": "Orange County",
    "equipmentQualityScore": 78,
    "percentRejectedBallots": 2.1
  },
  {
    "regionId": "5_1",
    "regionName": "Alameda County",
    "equipmentQualityScore": 88,
    "percentRejectedBallots": 1.0
  }
  // ... more regions
]
```

**Field Descriptions:**

- `regionId` (String): Unique identifier for the EAVS geographic unit (format: "stateId_countyId")
- `regionName` (String): Name of the county/region
- `equipmentQualityScore` (Integer): Equipment quality score (0-100 scale, higher is better)
- `percentRejectedBallots` (Double): Percentage of rejected ballots in the region (0-100)

**Frontend Usage (Bubble Chart):**

1. **Bubble Chart Configuration:**

   - Each bubble represents one EAVS geographic unit (county/region)
   - X-axis: `equipmentQualityScore` (0-100)
   - Y-axis: `percentRejectedBallots` (0-100%)
   - Bubble size: Can be uniform or proportional to region population/ballot count

2. **Visualization Details:**

   ```javascript
   function plotBubble(data) {
     return {
       x: data.equipmentQualityScore,
       y: data.percentRejectedBallots,
       label: data.regionName,
       // Optional: color by quality level
       color: getQualityColor(data.equipmentQualityScore),
     };
   }

   function getQualityColor(score) {
     if (score >= 90) return "#2ecc71"; // Excellent - green
     if (score >= 75) return "#f39c12"; // Good - orange
     if (score >= 60) return "#e74c3c"; // Fair - red
     return "#c0392b"; // Poor - dark red
   }
   ```

3. **Interactivity:**

   - Hover: Show tooltip with region name, quality score, and rejection percentage
   - Click: Highlight bubble and show detailed equipment breakdown
   - Trend line: Display regression line showing correlation

4. **Chart Annotations:**
   - X-axis label: "Equipment Quality Score (0-100)"
   - Y-axis label: "Rejected Ballots (%)"
   - Title: "Equipment Quality vs Ballot Rejection Rate"

**Equipment Quality Score Calculation:**

The quality score (0-100) is calculated based on:

- **Age**: Newer equipment scores higher
- **Certification**: VVSG 2.0 certified scores highest
- **Operating System**: Modern OS scores higher
- **Scan Rate**: Higher scan rates score better
- **Error Rate**: Lower error rates score higher
- **Reliability**: Higher reliability scores better

A score of:

- **90-100**: Excellent equipment (VVSG 2.0 certified, <3 years old)
- **75-89**: Good equipment (VVSG 1.1+ certified, <7 years old)
- **60-74**: Fair equipment (older or less certified)
- **<60**: Poor equipment (outdated, no certification, or high error rates)

**Rejected Ballots Calculation:**

The percentage of rejected ballots is calculated as:

```
Rejected % = (Total Rejected Ballots / Total Ballots Cast) × 100

Where:
- Total Rejected = Mail-in rejected + Provisional rejected + UOCAVA rejected
- Total Ballots = Absentee counted + Early in-person + Election day + Provisional counted
```

**Data Interpretation:**

- **High Quality, Low Rejection**: Ideal scenario - good equipment, few rejections
- **High Quality, High Rejection**: Rejections likely due to voter error, not equipment
- **Low Quality, High Rejection**: Strong correlation - equipment may be causing rejections
- **Low Quality, Low Rejection**: Equipment quality may not be primary factor

**Use Case Requirement (GUI-25):**

This endpoint supports the requirement to display a bubble chart showing the relationship between voting equipment quality and rejected ballot percentages, helping identify equipment-related issues that may impact voter participation.

**Relationship to Other Endpoints:**

- **GUI-26**: Provides regression coefficients for this data at the state level
- **GUI-6**: Provides detailed equipment summary by make/model

---

### GUI-27: Get Gingles Chart Data for Precincts

**Endpoint:** `GET /api/facts/states/{stateId}/precincts/gingles-chart`

**Use Case:** Display Gingles chart visualization for the preclearance state (Alabama) showing racially polarized voting patterns. The chart displays two bubbles per precinct (one for Democratic votes, one for Republican votes) plotted against demographic percentages to identify voting rights concerns.

**Example Request:** `GET /api/facts/states/1/precincts/gingles-chart` (Alabama, state_id=1)

**Response:** Array of GinglesChartPrecinctDto objects

**Example Response:**

```json
[
  {
    "precinctId": "1001",
    "precinctName": "Birmingham Precinct 1",
    "stateId": "1",
    "percentWhite": 35.2,
    "percentBlack": 58.7,
    "percentHispanic": 4.1,
    "percentDem": 72.3,
    "percentRep": 25.8
  },
  {
    "precinctId": "1002",
    "precinctName": "Birmingham Precinct 2",
    "stateId": "1",
    "percentWhite": 82.5,
    "percentBlack": 12.3,
    "percentHispanic": 3.2,
    "percentDem": 38.7,
    "percentRep": 59.4
  },
  {
    "precinctId": "1003",
    "precinctName": "Montgomery Precinct 1",
    "stateId": "1",
    "percentWhite": 25.8,
    "percentBlack": 68.9,
    "percentHispanic": 3.5,
    "percentDem": 78.2,
    "percentRep": 20.1
  },
  {
    "precinctId": "1004",
    "precinctName": "Mobile Precinct 1",
    "stateId": "1",
    "percentWhite": 65.4,
    "percentBlack": 28.7,
    "percentHispanic": 4.2,
    "percentDem": 45.6,
    "percentRep": 52.3
  }
  // ... more precincts
]
```

**Field Descriptions:**

- `precinctId` (String): Unique identifier for the precinct
- `precinctName` (String): Name of the precinct
- `stateId` (String): State identifier (Alabama only for preclearance analysis)
- `percentWhite` (Double): Percentage of white population in precinct (0-100)
- `percentBlack` (Double): Percentage of Black/African American population in precinct (0-100)
- `percentHispanic` (Double): Percentage of Hispanic/Latino population in precinct (0-100)
- `percentDem` (Double): Percentage of Democratic votes in 2024 Presidential election (0-100)
- `percentRep` (Double): Percentage of Republican votes in 2024 Presidential election (0-100)

**Frontend Usage (Gingles Chart):**

1. **Chart Configuration:**

   - **TWO bubbles per precinct**:
     - Blue bubble: Y-axis = `percentDem`
     - Red bubble: Y-axis = `percentRep`
   - **X-axis**: User-selectable demographic group
     - Option 1: `percentWhite`
     - Option 2: `percentBlack`
     - Option 3: `percentHispanic`

2. **Visualization Implementation:**

   ```javascript
   function createGinglesChart(data, selectedDemographic) {
     const bubbles = [];

     data.forEach((precinct) => {
       // Determine X value based on selected demographic
       let xValue;
       switch (selectedDemographic) {
         case "white":
           xValue = precinct.percentWhite;
           break;
         case "black":
           xValue = precinct.percentBlack;
           break;
         case "hispanic":
           xValue = precinct.percentHispanic;
           break;
       }

       // Add Democratic bubble (blue)
       bubbles.push({
         x: xValue,
         y: precinct.percentDem,
         color: "blue",
         label: `${precinct.precinctName} (Dem)`,
       });

       // Add Republican bubble (red)
       bubbles.push({
         x: xValue,
         y: precinct.percentRep,
         color: "red",
         label: `${precinct.precinctName} (Rep)`,
       });
     });

     return bubbles;
   }
   ```

3. **GUI Controls:**

   - Dropdown/radio buttons to select demographic group (White, Black, Hispanic)
   - Checkbox to show/hide Democratic bubbles
   - Checkbox to show/hide Republican bubbles
   - Toggle to show/hide regression lines

4. **Chart Features:**

   - **Regression Lines**: Display non-linear regression lines for both parties
   - **Clustering**: Identify clusters indicating racially polarized voting
   - **Tooltips**: Show precinct name, demographic %, and voting % on hover
   - **Zoom/Pan**: Allow users to focus on specific areas

5. **Chart Annotations:**
   - X-axis label: "Percentage of [Selected Demographic] (%)"
   - Y-axis label: "Vote Percentage (%)"
   - Title: "Gingles Chart - Racially Polarized Voting Analysis"
   - Legend: Blue dots (Democratic), Red dots (Republican)

**Data Interpretation:**

The Gingles chart is used to identify racially polarized voting as required by the Voting Rights Act. Key patterns to look for:

1. **Racial Polarization**:

   - If Democratic votes increase as Black % increases → Black voters prefer Democratic candidates
   - If Republican votes increase as White % increases → White voters prefer Republican candidates
   - Strong opposite trends indicate racially polarized voting

2. **Gingles Preconditions** (for vote dilution claims):

   - Minority group is sufficiently large and geographically compact
   - Minority group is politically cohesive
   - Majority votes as a bloc to defeat minority-preferred candidates

3. **Pattern Examples**:
   - **Strong Polarization**: Clear diagonal trend lines in opposite directions
   - **Weak Polarization**: Scattered bubbles with no clear trend
   - **Crossover Voting**: Significant overlap between party preferences

**Use Case Requirement (GUI-27):**

This endpoint supports the Voting Rights Act preclearance analysis requirement, providing data to visualize racially polarized voting patterns through the Gingles chart methodology. The chart helps identify potential voting rights violations in the preclearance state (Alabama).

**Applicable State:**

- **Primary**: Alabama (preclearance state)
- Returns empty array for states without precinct-level data

**Relationship to Other Endpoints:**

- **GUI-28**: Ecological Inference analysis for equipment access by demographic
- **GUI-29**: Ecological Inference analysis for ballot rejection by demographic

**Legal Context:**

The Gingles chart is named after _Thornburg v. Gingles_ (1986), the landmark Supreme Court case establishing the three-precondition test for proving vote dilution under Section 2 of the Voting Rights Act. This visualization helps demonstrate the first two Gingles preconditions through empirical data.

---

### GUI-6: Get Equipment Summary for Specific State

**Endpoint:** `GET /api/facts/states/{stateId}/equipment-summary`

**Use Case:** Display a detailed equipment summary table on the state detail page showing all voting equipment used in a specific state. For each unique voting device (make and model), the table shows quantity, equipment type, short description, age, underlying OS, certification level, scan rate, error rate, and reliability. Discontinued equipment should be highlighted (e.g., make and model displayed in red).

**Example Request:** `GET /api/facts/states/5/equipment-summary` (California)

**Response:** Array of EquipmentSummaryByStateDto objects

**Example Response:**

```json
[
  {
    "stateId": "5",
    "make": "ES&S",
    "model": "DS200",
    "quantity": "3500",
    "equipmentType": "scanner",
    "age": 6,
    "operatingSystem": "Windows 7 Embedded",
    "description": "Precinct-based optical scanner for paper ballots",
    "cert": "VVSG 1.1 certified",
    "scanRate": 100.0,
    "errorRate": 0.0015,
    "reliability": 98.5,
    "discontinued": false
  },
  {
    "stateId": "5",
    "make": "Dominion Voting",
    "model": "ImageCast Evolution",
    "quantity": "2800",
    "equipmentType": "ballot_marking_device",
    "age": 4,
    "operatingSystem": "Linux",
    "description": "Accessible ballot marking device with audio interface",
    "cert": "VVSG 2.0 certified",
    "scanRate": null,
    "errorRate": 0.0008,
    "reliability": 99.2,
    "discontinued": false
  },
  {
    "stateId": "5",
    "make": "Sequoia Voting Systems",
    "model": "AVC Edge",
    "quantity": "150",
    "equipmentType": "dre_with_vvpat",
    "age": 14,
    "operatingSystem": "Windows CE",
    "description": "DRE with voter-verified paper audit trail",
    "cert": "VVSG 1.0 certified",
    "scanRate": null,
    "errorRate": 0.0045,
    "reliability": 92.1,
    "discontinued": true
  }
]
```

**Field Descriptions:**

- `stateId` (String): The state identifier
- `make` (String): Equipment manufacturer (e.g., "ES&S", "Dominion Voting")
- `model` (String): Equipment model name
- `quantity` (String): Number of devices of this make/model in the state
- `equipmentType` (String): Category of equipment
  - "dre_no_vvpat": DRE without voter-verified paper audit trail
  - "dre_with_vvpat": DRE with VVPAT
  - "ballot_marking_device": Ballot marking device (often for accessibility)
  - "scanner": Optical/ballot scanner
- `age` (Integer): Average age of this equipment in years
- `operatingSystem` (String): Underlying operating system (e.g., "Windows 7 Embedded", "Linux")
- `description` (String): Short description of the equipment
- `cert` (String): Certification level
  - "VVSG 2.0 certified": Latest certification standard
  - "VVSG 2.0 applied": Applied for 2.0 certification
  - "VVSG 1.1 certified": Version 1.1 certified
  - "VVSG 1.0 certified": Version 1.0 certified
  - "not certified": No certification
- `scanRate` (Double): Scan rate in ballots per minute (null for non-scanner equipment)
- `errorRate` (Double): Error rate as a decimal (e.g., 0.0015 = 0.15%)
- `reliability` (Double): Reliability rating as percentage (0-100)
- `discontinued` (Boolean): Whether equipment is discontinued by manufacturer

**Frontend Usage (Paginated Table):**

1. **Table Structure:**

   ```
   | Make      | Model           | Quantity | Type    | Age | OS              | Description       | Cert         | Scan Rate | Error Rate | Reliability | Discontinued |
   |-----------|-----------------|----------|---------|-----|-----------------|-------------------|--------------|-----------|------------|-------------|--------------|
   | ES&S      | DS200           | 3500     | Scanner | 6   | Windows 7 Embed | Precinct scanner  | VVSG 1.1     | 100.0     | 0.15%      | 98.5%       | No           |
   | Dominion  | ImageCast Evo   | 2800     | BMD     | 4   | Linux           | Accessible BMD    | VVSG 2.0     | -         | 0.08%      | 99.2%       | No           |
   | Sequoia   | AVC Edge        | 150      | DRE     | 14  | Windows CE      | DRE with VVPAT    | VVSG 1.0     | -         | 0.45%      | 92.1%       | Yes          |
   ```

2. **Display Features:**

   - **Pagination**: Show 10-25 devices per page
   - **Sorting**: Allow sorting by any column
   - **Filtering**: Filter by equipment type, certification level
   - **Search**: Search by make or model name
   - **Highlighting**:
     - Display discontinued equipment in **red** (entire row or make/model columns)
     - Highlight old equipment (age > 10 years) in **orange**
     - Highlight uncertified equipment in **yellow**
   - **Total Row**: Show total quantity across all equipment

3. **Formatting:**

   - Format error rate as percentage (multiply by 100)
   - Show "-" for null scan rates (non-scanner equipment)
   - Format reliability with one decimal place
   - Show "Yes/No" or icon for discontinued flag

**Data Interpretation:**

- **High Age (>10 years)**: Equipment may need replacement
- **Discontinued = true**: Manufacturer no longer supports, may have security/maintenance issues
- **Low Certification**: VVSG 1.0 or uncertified equipment is outdated
- **High Error Rate**: May indicate equipment quality issues
- **DRE without VVPAT**: Security concern, no paper audit trail

**Use Case Requirement (GUI-6):**

This endpoint supports the requirement to display a comprehensive equipment summary table on the state detail page, allowing users to understand the voting equipment landscape in each state, identify security concerns (discontinued or uncertified equipment), and track equipment age and performance metrics.

**Implementation Architecture:**

This endpoint demonstrates **clean repository separation** with **optimized performance**:

- **Entity**: `EquipmentSummaryByStateEntity` in `facts/state/entity` (owns state-level inventory data)
- **Repository**: `EquipmentSummaryByStateRepository` in `facts/state/repository`
  - Performs **repository-level JOIN** with `voting_equipment` table
  - Single SQL query eliminates N+1 query problem
  - Performance: O(1) query instead of O(1+N) queries
- **Service**: `StateFactsService` maps JOIN results to DTOs
  - No additional database calls needed
  - All data fetched in single query
- **DTO**: `EquipmentSummaryByStateDto` in `facts/state/dto` (state-specific response)
- **Controller**: Endpoint in `FactsController` under `/api/facts/states/{stateId}/...`

**Performance Optimization:**

```sql
-- Single efficient query with JOIN (executed in repository)
SELECT ess.*, ve.make, ve.model, ve.equipment_type, ...
FROM equipment_summary_by_state ess
JOIN voting_equipment ve ON ess.equipment_id = ve.id
WHERE ess.state_id = :stateId
ORDER BY ve.make, ve.model
```

This architecture provides:

- **Optimal performance**: Single database query (no N+1 problem)
- **Clean separation**: State package owns its data
- **Clear responsibility**: Repository handles JOIN, service handles mapping

**Relationship to Other Endpoints:**

- **GUI-11**: Shows average equipment age by state (choropleth map)
- **GUI-12**: Shows equipment totals by category for all states
- **GUI-13**: Shows national equipment overview (all states aggregated)
- **GUI-14**: Shows equipment history for a state over time

---

## Equipment API - Voting Equipment Endpoints

### GUI-13: Get National Voting Equipment Overview

**Endpoint:** `GET /api/equipment/overview`

**Use Case:** Display a table of equipment used in 2024 across all states (national level). The table is row-ordered by equipment provider (make) with sub-ordering by model. Columns show quantity of devices, age, underlying OS, certification, scan rate, error rate, reliability, and a composite quality measure. This provides a comprehensive national view of voting equipment inventory and quality.

**Example Request:** `GET /api/equipment/overview`

**Response:** Array of VotingEquipmentOverviewDto objects

**Example Response:**

```json
[
  {
    "make": "Dominion Voting",
    "model": "ImageCast Evolution",
    "quantity": "45000",
    "age": 4,
    "operatingSystem": "Linux",
    "cert": "VVSG 2.0 certified",
    "scanRate": 85.5,
    "errorRate": 0.0008,
    "reliability": 99.2,
    "quality": 94.5,
    "discontinued": false
  },
  {
    "make": "Dominion Voting",
    "model": "ImageCast X",
    "quantity": "12000",
    "age": 3,
    "operatingSystem": "Linux",
    "cert": "VVSG 2.0 certified",
    "scanRate": null,
    "errorRate": 0.0012,
    "reliability": 98.8,
    "quality": 95.2,
    "discontinued": false
  },
  {
    "make": "ES&S",
    "model": "DS200",
    "quantity": "78000",
    "age": 6,
    "operatingSystem": "Windows 7 Embedded",
    "cert": "VVSG 1.1 certified",
    "scanRate": 100.0,
    "errorRate": 0.0015,
    "reliability": 98.5,
    "quality": 87.3,
    "discontinued": false
  },
  {
    "make": "ES&S",
    "model": "ExpressVote",
    "quantity": "35000",
    "age": 5,
    "operatingSystem": "Windows Embedded Standard",
    "cert": "VVSG 1.1 certified",
    "scanRate": null,
    "errorRate": 0.0018,
    "reliability": 97.9,
    "quality": 88.1,
    "discontinued": false
  },
  {
    "make": "Hart InterCivic",
    "model": "Verity Scan",
    "quantity": "18000",
    "age": 5,
    "operatingSystem": "Linux",
    "cert": "VVSG 1.1 certified",
    "scanRate": 95.0,
    "errorRate": 0.0014,
    "reliability": 98.3,
    "quality": 89.2,
    "discontinued": false
  },
  {
    "make": "Sequoia Voting Systems",
    "model": "AVC Edge",
    "quantity": "3500",
    "age": 14,
    "operatingSystem": "Windows CE",
    "cert": "VVSG 1.0 certified",
    "scanRate": null,
    "errorRate": 0.0045,
    "reliability": 92.1,
    "quality": 58.3,
    "discontinued": true
  }
]
```

**Field Descriptions:**

- `make` (String): Equipment manufacturer/provider (e.g., "ES&S", "Dominion Voting", "Hart InterCivic")
- `model` (String): Equipment model name
- `quantity` (String): Total number of devices of this make/model across all states
- `age` (Integer): Average age of this equipment in years (nationwide)
- `operatingSystem` (String): Underlying operating system
  - Examples: "Linux", "Windows 7 Embedded", "Windows Embedded Standard", "Windows CE"
- `cert` (String): Certification level
  - "VVSG 2.0 certified": Latest certification standard (best)
  - "VVSG 2.0 applied": Applied for 2.0 certification
  - "VVSG 1.1 certified": Version 1.1 certified
  - "VVSG 1.0 certified": Version 1.0 certified (outdated)
  - "not certified": No federal certification (concerning)
- `scanRate` (Double): Observed scan rate in ballots per minute (null for non-scanner equipment like ballot marking devices)
- `errorRate` (Double): Observed error rate as a decimal (e.g., 0.0015 = 0.15%)
- `reliability` (Double): Observed reliability rating as percentage (0-100)
- `quality` (Double): Composite quality measure score (0-100 scale)
  - Calculated based on: age, underlying OS, certification, scan rate, error rate, reliability
  - Higher scores = better equipment (newer, certified, reliable, high-performing)
  - Lower scores = problematic equipment (old, uncertified, unreliable, high error rates)
- `discontinued` (Boolean): Whether equipment is discontinued by manufacturer

**Frontend Usage (Data Table):**

1. **Table Structure:**

   ```
   | Provider        | Model             | Quantity | Age | OS               | Cert       | Scan Rate | Error Rate | Reliability | Quality | Discontinued |
   |-----------------|-------------------|----------|-----|------------------|------------|-----------|------------|-------------|---------|--------------|
   | Dominion Voting | ImageCast Evo     | 45,000   | 4   | Linux            | VVSG 2.0   | 85.5      | 0.08%      | 99.2%       | 94.5    | No           |
   | ES&S            | DS200             | 78,000   | 6   | Win 7 Embedded   | VVSG 1.1   | 100.0     | 0.15%      | 98.5%       | 87.3    | No           |
   | Sequoia         | AVC Edge          | 3,500    | 14  | Windows CE       | VVSG 1.0   | -         | 0.45%      | 92.1%       | 58.3    | Yes          |
   ```

2. **Display Features:**

   - **Row Ordering**: By equipment provider (make) with sub-ordering by model
   - **Sorting**: Allow sorting by any column (quantity, age, quality, etc.)
   - **Filtering**: Filter by certification level, discontinued status
   - **Search**: Search by make or model name
   - **Highlighting**:
     - Display discontinued equipment in **red** (entire row or make/model columns)
     - Highlight low quality scores (<60) in **orange**
     - Highlight uncertified equipment in **yellow**
     - Highlight VVSG 2.0 certified equipment in **green** (best)
   - **Totals**: Show total quantity across all equipment at bottom

3. **Formatting:**

   - Format quantity with comma separators (e.g., "78,000")
   - Format error rate as percentage (multiply by 100, add %)
   - Format reliability with one decimal place and % symbol
   - Format quality score with one decimal place
   - Show "-" for null scan rates (non-scanner equipment)
   - Display "Yes/No" or icons for discontinued flag

4. **Quality Score Color Coding:**

   ```javascript
   function getQualityColor(score) {
     if (score >= 90) return "#2ecc71"; // Excellent - green
     if (score >= 75) return "#f39c12"; // Good - orange
     if (score >= 60) return "#e74c3c"; // Fair - red
     return "#c0392b"; // Poor - dark red
   }
   ```

**Data Interpretation:**

- **High Quality Score (>90)**: Excellent equipment - newer, certified, reliable
- **Medium Quality Score (75-89)**: Good equipment - meets standards, some age
- **Low Quality Score (60-74)**: Fair equipment - older or less certified
- **Very Low Quality Score (<60)**: Poor equipment - needs replacement
- **Discontinued = true**: Manufacturer no longer supports - security/maintenance risk
- **High Error Rate (>0.003)**: Equipment may have technical issues
- **Low Reliability (<95%)**: Equipment experiences frequent failures

**Quality Measure Calculation:**

The quality score (0-100) is a composite metric based on:

1. **Age** (weight: 25%)

   - Newer equipment scores higher
   - 0-3 years: 100 points
   - 4-6 years: 80 points
   - 7-10 years: 50 points
   - > 10 years: 20 points

2. **Certification** (weight: 30%)

   - VVSG 2.0 certified: 100 points
   - VVSG 2.0 applied: 85 points
   - VVSG 1.1 certified: 70 points
   - VVSG 1.0 certified: 50 points
   - Not certified: 0 points

3. **Operating System** (weight: 15%)

   - Modern OS (Linux, Windows 10+): 100 points
   - Embedded OS (Win 7 Embedded): 70 points
   - Legacy OS (Windows CE, XP): 30 points

4. **Performance Metrics** (weight: 30%)
   - Error rate: Lower is better (inverse scoring)
   - Reliability: Higher is better (direct scoring)
   - Scan rate: Higher is better for scanners

**Use Case Requirement (GUI-13):**

This endpoint supports the requirement to display a comprehensive national voting equipment summary table on the splash page (accessed via button click), allowing users to understand the voting equipment landscape across all states, identify widespread security concerns, and compare equipment quality across different manufacturers and models.

**Applicable States:**

- All states: Data is aggregated nationwide
- Equipment counts represent total devices across all 48 mainland states

**Relationship to Other Endpoints:**

- **GUI-6**: Shows equipment summary for a specific state (state-level detail)
- **GUI-11**: Shows average equipment age by state (choropleth map)
- **GUI-12**: Shows equipment totals by category for all states
- **GUI-14**: Shows equipment history for a state over time (2016-2024)

**Performance Note:**

- Response size: Typically 30-50 equipment models across all manufacturers
- Lightweight response (~5-15KB)
- Suitable for caching as equipment overview data doesn't change frequently

---

## Notes

### GeoJSON Format

All geographic boundaries follow the GeoJSON specification:

- **Polygon**: Single continuous boundary `[[[lon, lat], [lon, lat], ...]]`
- **MultiPolygon**: Multiple separate areas (e.g., for states with islands) `[[[[lon, lat], ...]], [[[lon, lat], ...]]]`

### Coordinate Format

- Coordinates are always `[longitude, latitude]` (NOT latitude, longitude)
- Longitude: -180 to 180 (negative = West, positive = East)
- Latitude: -90 to 90 (negative = South, positive = North)

### Map Libraries Compatibility

These responses are compatible with:

- Leaflet: Use `L.geoJSON()` to render boundaries
- Mapbox GL JS: Use `addSource()` with GeoJSON type
- Google Maps: Use `Data` layer with `addGeoJson()`
- D3.js: Use `d3.geoPath()` for custom visualizations

### Performance Considerations

- GUI-1 returns all 48 states (~5-10MB of data depending on geometry simplification)
- Consider caching this response on the frontend
- GUI-20 returns a single state (~100-500KB per state)
- Geometry coordinates may be simplified for better performance vs precision tradeoff

---

## Changelog

### Version 1.0 (Current - December 2025)

- GUI-1: Get all states with boundaries for splash page map - DONE
- GUI-2: Get counties (EAVS geographic units) for detailed states - DONE
- GUI-2: Get political party CVAP percentages (Florida and Illinois)
- GUI-3: Get provisional ballot categories by state (bar chart) - PROPRO
- GUI-4: Get provisional ballot categories by region (table) - PROPRO
- GUI-6: Get equipment summary for specific state (state-level equipment detail)
- GUI-7: Get active voters data by state (bar chart)
- GUI-7: Get active voters data by region (table and choropleth)
- GUI-8: Get poll book deletion data by state (bar chart) - PROPRO
- GUI-8: Get poll book deletion data by region (table) - REMOVE
- GUI-9: Get mail ballot rejection data by state (bar chart) - PROPRO
- GUI-9: Get mail ballot rejection data by region (table) - PROPRO
- GUI-10: - PROPRO
- GUI-11: Get average equipment age for all states (choropleth map)
- GUI-12: Get voting equipment totals by category for all states (table) - PROPRO
- GUI-13: Get national voting equipment overview (comprehensive quality metrics)
- GUI-14: Get voting equipment history for a state (trend analysis 2016-2024) - PROPRO
- GUI-15: Get state comparison data (Political Party states - Illinois and Iowa) - PROPRO
- GUI-17: - PROPRO
- GUI-19: - PROPRO
- GUI-20: Get specific state geographic data with center coordinates - DONE
- GUI-21: - PROPRO - ONE SINGLE TABLE
- GUI-22: - PROPRO - ONE SINGLE TABLE
- GUI-23: Get early voting data for state comparison (Iowa and Illinois) - PROPRO
- GUI-24: Get drop box voting bubble chart data by region - PROPRO
- GUI-25: Get equipment quality vs rejected ballots bubble chart by region
- GUI-27: Get Gingles chart data for precincts (preclearance state - Alabama)

---

## Unimplemented GUI Use Cases

The following GUI use cases from the requirements have not yet been implemented:

### Required Use Cases (Not Yet Implemented)

**GUI-5: Provisional ballot choropleth map**

- Endpoint: Not implemented
- Description: Display choropleth map showing total provisional ballots cast (E1a) by EAVS geographic unit for detailed states
- Data: Uses provisional ballot data by region with bins (5-10 bins) and monochromatic colors

**GUI-10: Display type of voting equipment** (Preferred)

- Endpoint: Not implemented
- Description: For detailed states, color EAVS geographic units on map based on voting equipment type used
- Categories: DRE no VVPAT, DRE with VVPAT, ballot marking device, scanner
- Display: Different colors for each type, stripe/blend for mixed equipment

**GUI-16: Compare changes in voter registration** (Preferred)

- Endpoint: Not implemented
- Description: For every EAVS state, sort geographic units by registered voters (2024), plot corresponding 2020 and 2016 numbers as line graphs
- Display: Three line graphs showing registration trends

**GUI-17: Display voter registration data** (Required)

- Endpoint: Not implemented
- Description: Display voter registration data for states with voter registration data
- Components: Choropleth map (% registered voters), table (registration by party, completeness data)
- Data: Number of registered voters, Democratic/Republican/unaffiliated breakdown

**GUI-18: Display voter registration bubble chart** (Preferred)

- Endpoint: Not implemented
- Description: Bubble chart showing dominant political party in each census block
- Display: Red/blue bubbles overlaid on state map at census block center points
- Data: Requires census block level registration data (Prepro-9)

**GUI-19: Display registered voters** (Required)

- Endpoint: Not implemented
- Description: Click on EAVS geographic unit to display names of all registered voters
- Features: Filter by political party (Republican/Democratic), pagination
- Data: Individual voter names by geographic unit

**GUI-21: Compare voter registration data for opt-in and opt-out** (Required)

- Endpoint: Not implemented
- Description: Compare three states: opt-in, opt-out with same-day registration, opt-out without same-day registration
- Data: 2024 EAVS registration rates, turnout rates (absolute numbers and percentages)

**GUI-22: Compare Republican and Democratic states** (Required)

- Endpoint: Not implemented
- Description: Compare registration data for Republican and Democratic states
- Data: 2024 EAVS voter registration rates and turnout rates (absolute numbers and percentages)
- Note: Different from GUI-15 which compares policies; this focuses on registration/turnout

**GUI-26: Bubble chart regression line** (Preferred)

- Endpoint: Not implemented
- Description: Calculate and display non-linear regression lines for equipment quality vs rejected ballots bubble chart
- Display: Two regression lines (one for Republican bubbles, one for Democratic bubbles)
- Data: Regression coefficients transmitted from server

**GUI-28: Ecological Inference analysis of voting equipment** (Required)

- Endpoint: Not implemented
- Description: Display EI probability curve showing probability of demographic group accessing quality equipment
- Display: X-axis = quality measure, Y-axis = relative probability
- Data: Probability curves for selected demographic groups (white, Hispanic, African American)

**GUI-29: Ecological Inference analysis of rejected ballots** (Required)

- Endpoint: Not implemented
- Description: Display EI probability curve showing probability of demographic group having ballot rejected
- Display: X-axis = rejection probability, Y-axis = relative probability
- Data: Probability curves for selected demographic groups

**GUI-30: Reset page** (Preferred)

- Implementation: Frontend only (no backend endpoint needed)
- Description: Reset button to return GUI to initial state before state selection

---

### Summary of Unimplemented Use Cases

**Total Unimplemented: 11 use cases**

**By Priority:**

- **Required**: 7 use cases (GUI-5, GUI-17, GUI-19, GUI-21, GUI-22, GUI-28, GUI-29)
- **Preferred**: 4 use cases (GUI-10, GUI-16, GUI-18, GUI-26)

**By Data Requirements:**

- **Needs voter registration data**: GUI-17, GUI-18, GUI-19, GUI-21, GUI-22
- **Needs choropleth/map logic**: GUI-5, GUI-10, GUI-17, GUI-18
- **Needs EI analysis**: GUI-28, GUI-29
- **Needs regression analysis**: GUI-26
- **Needs historical EAVS data**: GUI-16

**Implementation Dependencies:**

GUI-17, GUI-18, GUI-19 require:

- Voter registration data preprocessing (Prepro-7)
- Census block mapping (Prepro-9)
- EAVS region mapping (Prepro-10)

GUI-28, GUI-29 require:

- Ecological inference statistical analysis
- Demographic data by precinct/region
- Probability curve calculation

GUI-21, GUI-22 require:

- Additional state comparison data
- Opt-in/opt-out state identification

---

-- Flyway V1: Recreate schema from updated ERD and reconcile with existing schema
-- Goal: Implement tables/views to match the new ERD while retaining indispensable parts
--       of the current schema. We'll fill this file incrementally, section by section.
--
-- Sections (to be implemented step-by-step):
-- 1) Geo
-- 2) Normalized jurisdiction dimensions
-- 3) Equipment dimensions and inventory
-- 4) Region-level fact tables
-- 5) State-level fact tables
-- 6) Equipment facts
--
-- Note: Fresh-create style (no ALTER / IF NOT EXISTS); order across sections matters.


-- ============================================================================
-- 1) Geo
--    - State geometry (GeoJSON) with properties
--    - State centers for map presets
--    - County and Region geometry tables (for FK links from dims)
-- ============================================================================

-- State geometry (retain 'properties' JSONB from prior schema)
CREATE TABLE us_state_geo (
    state_id   INTEGER PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    properties JSONB        NOT NULL,
    geometry   JSONB        NOT NULL
);
CREATE INDEX idx_us_state_geo_name ON us_state_geo (name);

-- County geometry
CREATE TABLE county_geo (
	geo_id     INTEGER PRIMARY KEY,
	name       VARCHAR(255) NOT NULL,
	properties JSONB        NOT NULL,
	geometry   JSONB        NOT NULL
);
CREATE INDEX idx_county_geo_name ON county_geo (name);

-- ============================================================================
-- 2) Normalized jurisdiction dimensions (State -> County -> Region/Precinct)
--    - State remains the top-level administrative unit
--    - County belongs to State
--    - Region (or precinct) belongs to County
--    - Geometry foreign keys (geo_id) are added here; constraints will be
--      wired in the Geo section to avoid dependency ordering issues.
-- ============================================================================

-- State
CREATE TABLE state (
    state_id   INTEGER PRIMARY KEY REFERENCES us_state_geo(state_id),
    name       VARCHAR(255) NOT NULL UNIQUE
);

-- State centers (now that state exists, add FK inline)
CREATE TABLE state_center (
	state_id   INTEGER PRIMARY KEY REFERENCES state(state_id),
	lon        DOUBLE PRECISION NOT NULL,
	lat        DOUBLE PRECISION NOT NULL,
	zoom       DOUBLE PRECISION NOT NULL
);

-- County (or county-equivalent)
CREATE TABLE county (
    geo_id     INTEGER NOT NULL UNIQUE REFERENCES county_geo(geo_id),
    name       VARCHAR(255) NOT NULL,
    state_id   INTEGER NOT NULL REFERENCES state(state_id)
);
CREATE INDEX idx_county_state ON county (state_id);

-- EAVS Region table: geo_id is concat of state_id and county_id
CREATE TABLE eavs_region (
	region_id INTEGER PRIMARY KEY,
	state_id  INTEGER NOT NULL REFERENCES state(state_id),
	name      VARCHAR(255) NOT NULL,
	geo_id    INTEGER NOT NULL REFERENCES county(geo_id)
);

-- Census Block (jurisdiction level for voter registration visualizations)
CREATE TABLE census_block (
	block_id INTEGER PRIMARY KEY,
	state_id INTEGER NOT NULL REFERENCES state(state_id),
	name     VARCHAR(255) NOT NULL
);
CREATE INDEX idx_census_block_state ON census_block (state_id);


-- ============================================================================
-- 3) Equipment dimensions and inventory
--    - Model-level table holds stable attributes (type, description, OS, cert)
--    - Inventory table captures state/year specifics (quantity, age, observed metrics)
-- ============================================================================

-- Voting equipment overview (national level)
-- Matches: 13_Voting_Equipment_Overview.csv
CREATE TABLE voting_equipment (
	id                       BIGSERIAL PRIMARY KEY,
	make                     VARCHAR(128) NOT NULL,
	type                     VARCHAR(512),
	model                    VARCHAR(256) NOT NULL,
	age                      NUMERIC(5,2),
	operating_system         VARCHAR(128),
	cert                     VARCHAR(128),
	scan_rate                VARCHAR(128),
	error_rate               VARCHAR(128),
	reliability              NUMERIC(5,2),
	quality                  NUMERIC(5,2),
	discontinued             BOOLEAN NOT NULL DEFAULT FALSE,
	quantity                 INTEGER NOT NULL DEFAULT 0,
	CONSTRAINT uq_equipment_model UNIQUE (make, model)
);


-- ============================================================================
-- 4) Region-level fact tables (wide per ERD)
--    region_id (concat of state_id and county_id) references eavs_region(region_id).
-- ============================================================================

CREATE TABLE active_voters_by_region (
	region_id                    INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	total_registered_voters      INTEGER NOT NULL DEFAULT 0,
	active_registered_voters     INTEGER NOT NULL DEFAULT 0,
	inactive_registered_voters   INTEGER NOT NULL DEFAULT 0,
	active_percentage            DOUBLE PRECISION
);

-- Mail Ballot Rejections by Region (wide)
CREATE TABLE mail_ballot_rejection_by_region (
	region_id                         INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	total_mail_ballot                 INTEGER NOT NULL DEFAULT 0,
	total_rejected_ballots            INTEGER NOT NULL DEFAULT 0,
	rejection_percentage              DOUBLE PRECISION,
	late                              INTEGER NOT NULL DEFAULT 0,
	missing_voter_signature           INTEGER NOT NULL DEFAULT 0,
	missing_witness_signature         INTEGER NOT NULL DEFAULT 0,
	non_matching_voter_signature      INTEGER NOT NULL DEFAULT 0,
	unofficial_envelope               INTEGER NOT NULL DEFAULT 0,
	ballot_missing_from_envelope      INTEGER NOT NULL DEFAULT 0,
	no_secrecy_envelope               INTEGER NOT NULL DEFAULT 0,
	multiple_ballots_one_envelope     INTEGER NOT NULL DEFAULT 0,
	envelope_not_sealed               INTEGER NOT NULL DEFAULT 0,
	no_postmark                       INTEGER NOT NULL DEFAULT 0,
	no_resident_address_on_envelope   INTEGER NOT NULL DEFAULT 0,
	voter_deceased                    INTEGER NOT NULL DEFAULT 0,
	voter_already_voted               INTEGER NOT NULL DEFAULT 0,
	missing_documentation             INTEGER NOT NULL DEFAULT 0,
	voter_not_eligible                INTEGER NOT NULL DEFAULT 0,
	no_ballot_application             INTEGER NOT NULL DEFAULT 0
);

-- Poll Book Deletions by Region (wide)
CREATE TABLE poll_book_deletion_by_region (
	region_id               INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	total_registered_user   INTEGER NOT NULL DEFAULT 0,
	moved                   INTEGER NOT NULL DEFAULT 0,
	death                   INTEGER NOT NULL DEFAULT 0,
	felony                  INTEGER NOT NULL DEFAULT 0,
	fail_response           INTEGER NOT NULL DEFAULT 0,
	incompetent_to_vote     INTEGER NOT NULL DEFAULT 0,
	voter_request           INTEGER NOT NULL DEFAULT 0,
	duplicate_record        INTEGER NOT NULL DEFAULT 0
);

-- Provisional ballots by Region (wide)
CREATE TABLE provisional_by_region (
	region_id                               INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	voter_not_on_list                       INTEGER NOT NULL DEFAULT 0,
	voter_lacked_id                         INTEGER NOT NULL DEFAULT 0,
	election_official_challenged_eligibility INTEGER NOT NULL DEFAULT 0,
	another_person_challenged_eligibility   INTEGER NOT NULL DEFAULT 0,
	voter_not_resident                      INTEGER NOT NULL DEFAULT 0,
	voter_registration_not_updated          INTEGER NOT NULL DEFAULT 0,
	voter_did_not_surrender_mail_ballot     INTEGER NOT NULL DEFAULT 0,
	judge_extended_voting_hours             INTEGER NOT NULL DEFAULT 0,
	voter_used_sdr                          INTEGER NOT NULL DEFAULT 0,
	other_reason                            INTEGER NOT NULL DEFAULT 0,
	total_provisional                       INTEGER NOT NULL DEFAULT 0
);

-- Drop box voting bubble chart metrics by region (GUI-24)
CREATE TABLE dropbox_voting_by_region (
	region_id                     INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	republican_votes_2024         INTEGER NOT NULL DEFAULT 0,
	democrat_votes_2024           INTEGER NOT NULL DEFAULT 0,
	total_presidential_votes_2024 INTEGER NOT NULL DEFAULT 0,
	mail_in_ballots               INTEGER NOT NULL DEFAULT 0,
	total_ballots                 INTEGER NOT NULL DEFAULT 0,
	percent_republican            DOUBLE PRECISION,
	percent_mail_in_ballots       DOUBLE PRECISION
);

-- Equipment quality vs rejected ballots by region (GUI-25)
CREATE TABLE equipment_quality_rejection_by_region (
	region_id                 INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	equipment_quality_score   NUMERIC(5,2) NOT NULL DEFAULT 0,
	mail_rejected             INTEGER NOT NULL DEFAULT 0,
	provisional_rejected      INTEGER NOT NULL DEFAULT 0,
	uocava_rejected           INTEGER NOT NULL DEFAULT 0,
	total_rejected_ballots    INTEGER NOT NULL DEFAULT 0,
	absentee_ballots_counted  INTEGER NOT NULL DEFAULT 0,
	early_in_person_ballots  INTEGER NOT NULL DEFAULT 0,
	election_day_ballots     INTEGER NOT NULL DEFAULT 0,
	provisional_ballots_counted INTEGER NOT NULL DEFAULT 0,
	total_ballots             INTEGER NOT NULL DEFAULT 0,
	percent_rejected_ballots  DOUBLE PRECISION
);

-- Display type of voting equipment by region (GUI-10)
-- Stores array of equipment types used in each region
CREATE TABLE display_type_of_voting_equipment_by_region (
	region_id                INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	type_of_equipment_used   TEXT[] NOT NULL
);

-- Voter registration summary by region (party breakdown and completeness metrics)
CREATE TABLE voter_registration_summary_by_region (
	region_id                    INTEGER PRIMARY KEY REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	geo_id                       INTEGER NOT NULL REFERENCES county(geo_id),
	total_num_registered_voters  INTEGER NOT NULL DEFAULT 0,
	total_num_republican         INTEGER NOT NULL DEFAULT 0,
	total_num_democratic         INTEGER NOT NULL DEFAULT 0,
	total_num_unaffiliated       INTEGER NOT NULL DEFAULT 0,
	total_cvap                   INTEGER NOT NULL DEFAULT 0,
	registered_voter_percentage  DOUBLE PRECISION,
	completeness                 DOUBLE PRECISION,
	contact_completeness         DOUBLE PRECISION
);


-- ============================================================================
-- 5) State-level fact tables (wide per ERD)
--    state_id references state(state_id). No year column for now.
-- ============================================================================

-- Equipment Summary by State (denormalized, persisted)
-- Matches: 6_Equipment_Summary_by_State_with_short_description.csv
CREATE TABLE equipment_summary_by_state (
	id                       BIGSERIAL PRIMARY KEY,
	state_id                 INTEGER NOT NULL REFERENCES state(state_id),
	manufacturer             VARCHAR(128) NOT NULL,
	model_name               VARCHAR(256) NOT NULL,
	quantity                 INTEGER NOT NULL DEFAULT 0,
	equipment_type           VARCHAR(64),
	age                      NUMERIC(5,2),
	os                       VARCHAR(128),
	certification_level      VARCHAR(128),
	scanning_rate            VARCHAR(128),
	error_rate               VARCHAR(128),
	reliability              NUMERIC(5,2),
	quality                  NUMERIC(5,2),
	short_description        VARCHAR(512)
);
CREATE INDEX idx_equipment_summary_by_state ON equipment_summary_by_state (state_id);

-- Active Voters by State
CREATE TABLE active_voters_by_state (
	state_id      INTEGER PRIMARY KEY REFERENCES state(state_id),
	total_registered_voters  INTEGER NOT NULL DEFAULT 0,
	active_registered_voters INTEGER NOT NULL DEFAULT 0
);

-- Registration and Turnout Metrics by State
CREATE TABLE registration_metrics_by_state (
	state_id           INTEGER PRIMARY KEY REFERENCES state(state_id),
	total_votes_cast   INTEGER NOT NULL DEFAULT 0,
	total_cvap         INTEGER NOT NULL DEFAULT 0,
	registration_rate  DOUBLE PRECISION,
	turnout_rate       DOUBLE PRECISION
);

-- Vote Turnout by State
CREATE TABLE vote_turnout_by_state (
	state_id                  INTEGER PRIMARY KEY REFERENCES state(state_id),
	total_registered_voter    INTEGER NOT NULL DEFAULT 0,
	total_votes_cast          INTEGER NOT NULL DEFAULT 0,
	turn_out_rate             DOUBLE PRECISION
);

-- Early Vote by State (GUI-23)
CREATE TABLE early_vote_by_state (
	state_id                  INTEGER PRIMARY KEY REFERENCES state(state_id),
	total_votes_cast          INTEGER NOT NULL DEFAULT 0,
	uocava_accepted           INTEGER NOT NULL DEFAULT 0,
	mail_accepted             INTEGER NOT NULL DEFAULT 0,
	in_person_early           INTEGER NOT NULL DEFAULT 0,
	provisional_counted       INTEGER NOT NULL DEFAULT 0,
	dropbox_accepted          INTEGER NOT NULL DEFAULT 0,
	total_early               INTEGER NOT NULL DEFAULT 0,
	percent_in_person_early   DOUBLE PRECISION,
	percent_mail              DOUBLE PRECISION,
	percent_dropbox           DOUBLE PRECISION,
	percent_uocava            DOUBLE PRECISION,
	percent_provisional       DOUBLE PRECISION,
	percent_total_early       DOUBLE PRECISION,
	mail_votes_in_vbm_jurisdiction INTEGER NOT NULL DEFAULT 0
);

-- Poll Book Deletions by State (wide)
CREATE TABLE poll_book_deletion_by_state (
	state_id               INTEGER PRIMARY KEY REFERENCES state(state_id),
	moved                  INTEGER NOT NULL DEFAULT 0,
	death                  INTEGER NOT NULL DEFAULT 0,
	felony                 INTEGER NOT NULL DEFAULT 0,
	fail_response          INTEGER NOT NULL DEFAULT 0,
	incompetent_to_vote    INTEGER NOT NULL DEFAULT 0,
	voter_request          INTEGER NOT NULL DEFAULT 0,
	duplicate_record       INTEGER NOT NULL DEFAULT 0
);

-- Provisional ballots by State (wide)
CREATE TABLE provisional_by_state (
	state_id                                 INTEGER PRIMARY KEY REFERENCES state(state_id),
	voter_not_on_list                        INTEGER NOT NULL DEFAULT 0,
	voter_lacked_id                          INTEGER NOT NULL DEFAULT 0,
	election_official_challenged_eligibility INTEGER NOT NULL DEFAULT 0,
	another_person_challenged_eligibility    INTEGER NOT NULL DEFAULT 0,
	voter_not_resident                       INTEGER NOT NULL DEFAULT 0,
	voter_registration_not_updated           INTEGER NOT NULL DEFAULT 0,
	voter_did_not_surrender_mail_ballot      INTEGER NOT NULL DEFAULT 0,
	judge_extended_voting_hours              INTEGER NOT NULL DEFAULT 0,
	voter_used_sdr                           INTEGER NOT NULL DEFAULT 0,
	other_reason                             INTEGER NOT NULL DEFAULT 0
);

-- Mail Ballot Rejections by State (wide)
CREATE TABLE mail_ballot_rejection_by_state (
	state_id                          INTEGER PRIMARY KEY REFERENCES state(state_id),
	late                               INTEGER NOT NULL DEFAULT 0,
	total_mail_ballot                  INTEGER NOT NULL DEFAULT 0,
	total_rejected_ballots             INTEGER NOT NULL DEFAULT 0,
	missing_voter_signature            INTEGER NOT NULL DEFAULT 0,
	missing_witness_signature          INTEGER NOT NULL DEFAULT 0,
	non_matching_voter_signature       INTEGER NOT NULL DEFAULT 0,
	unofficial_envelope                INTEGER NOT NULL DEFAULT 0,
	ballot_missing_from_envelope       INTEGER NOT NULL DEFAULT 0,
	no_secrecy_envelope                INTEGER NOT NULL DEFAULT 0,
	multiple_ballots_one_envelope      INTEGER NOT NULL DEFAULT 0,
	envelope_not_sealed                INTEGER NOT NULL DEFAULT 0,
	no_postmark                        INTEGER NOT NULL DEFAULT 0,
	no_resident_address_on_envelope    INTEGER NOT NULL DEFAULT 0,
	voter_deceased                     INTEGER NOT NULL DEFAULT 0,
	voter_already_voted                INTEGER NOT NULL DEFAULT 0,
	missing_documentation              INTEGER NOT NULL DEFAULT 0,
	voter_not_eligible                 INTEGER NOT NULL DEFAULT 0,
	no_ballot_application              INTEGER NOT NULL DEFAULT 0
);

-- GUI-15: State mail / dropbox comparison facts
CREATE TABLE mail_and_dropbox_by_state (
	state_id                      INTEGER PRIMARY KEY REFERENCES state(state_id),
	total_votes_cast              INTEGER NOT NULL DEFAULT 0,
	total_mail_ballot             INTEGER NOT NULL DEFAULT 0,
	dropbox_votes                 INTEGER NOT NULL DEFAULT 0,
	percent_mail_ballot           DOUBLE PRECISION,
	percent_dropbox               DOUBLE PRECISION,
	total_registered_voter        INTEGER NOT NULL DEFAULT 0,
	turn_out_rate                 DOUBLE PRECISION,
	felony_voting_rights          VARCHAR(128),
	early_voting_period_days      DOUBLE PRECISION,
	voter_id_requirement          VARCHAR(128),
	same_day_registration         BOOLEAN,
	absentee_request_deadline     VARCHAR(128),
	automatic_voter_registration  BOOLEAN,
	no_excuse_absentee_voting     BOOLEAN
);

-- EAVS Data Quality Score by State
CREATE TABLE eavs_data_quality_by_state (
	state_id            INTEGER PRIMARY KEY REFERENCES state(state_id),
	data_quality_score  DOUBLE PRECISION NOT NULL
);

-- Political Party CVAP share by State (narrow; one row per party per state)
CREATE TABLE political_party_cvap_by_state (
	state_id   INTEGER PRIMARY KEY NOT NULL REFERENCES state(state_id),
	name       VARCHAR(128) NOT NULL,
	cvap_pct   DOUBLE PRECISION
);

-- State Equipment History (GUI-14: Equipment counts by category across federal election years)
-- Normalized design: One row per state per year (easily extensible for future years)
-- Frontend receives data transformed into wide format (years as columns)
CREATE TABLE state_equipment_history (
	state_id                  INTEGER NOT NULL REFERENCES state(state_id),
	year                      INTEGER NOT NULL,
	dre_no_vvpat              INTEGER NOT NULL DEFAULT 0,
	dre_with_vvpat            INTEGER NOT NULL DEFAULT 0,
	ballot_marking_device     INTEGER NOT NULL DEFAULT 0,
	scanner                   INTEGER NOT NULL DEFAULT 0,
	
	PRIMARY KEY (state_id, year),
	CONSTRAINT chk_year_valid CHECK (year IN (2016, 2018, 2020, 2022, 2024))
);
CREATE INDEX idx_state_equipment_history_year ON state_equipment_history (year);

CREATE TABLE state_equipment_total (
	state_id                 INTEGER PRIMARY KEY REFERENCES state(state_id),
	dre_no_vvpat             INTEGER NOT NULL DEFAULT 0,
	dre_with_vvpat           INTEGER NOT NULL DEFAULT 0,
	ballot_marking_device    INTEGER NOT NULL DEFAULT 0,
	scanner                  INTEGER NOT NULL DEFAULT 0
);

-- GUI-11: Average age of voting equipment by state (for choropleth map)
CREATE TABLE average_equipment_age_by_state (
	state_id                 INTEGER PRIMARY KEY REFERENCES state(state_id),
	average_age_of_equipment INTEGER NOT NULL DEFAULT 0
);

-- GUI-26: Regression coefficients for equipment quality vs rejected ballots
CREATE TABLE equipment_rejection_regression_coef (
	state_id        INTEGER NOT NULL REFERENCES state(state_id),
	political_party VARCHAR(32) NOT NULL,
	term_order      INTEGER NOT NULL,
	coefficient     NUMERIC(12,6) NOT NULL,
	r_squared       NUMERIC(6,4),
	PRIMARY KEY (state_id, political_party, term_order)
);

-- GUI-27: Precinct demographic regression coefficients
CREATE TABLE precinct_demographic_regression_coef (
	id              BIGSERIAL PRIMARY KEY,
	state_id        INTEGER NOT NULL REFERENCES state(state_id),
	political_party VARCHAR(32) NOT NULL,
	demographic     VARCHAR(64) NOT NULL,
	coef_a          NUMERIC(12,6) NOT NULL,
	coef_b          NUMERIC(12,6) NOT NULL,
	coef_c          NUMERIC(12,6) NOT NULL
);

-- GUI-28: Ecological inference curve data (state-wide per demographic & quality)
CREATE TABLE equipment_ei_curve_point (
	id                    BIGSERIAL PRIMARY KEY,
	state_id              INTEGER NOT NULL REFERENCES state(state_id),
	demographic           VARCHAR(64) NOT NULL,
	equipment_quality     NUMERIC(5,4) NOT NULL,
	relative_probability  NUMERIC(6,4) NOT NULL,
	probability_density   NUMERIC(10,4) NOT NULL
);

-- GUI-29: Rejected ballot ecological inference curve data (state-wide per demographic)
CREATE TABLE rejected_ballot_ei_curve_point (
	id                      BIGSERIAL PRIMARY KEY,
	state_id                INTEGER NOT NULL REFERENCES state(state_id),
	demographic             VARCHAR(64) NOT NULL,
	rejection_probability   NUMERIC(6,4) NOT NULL,
	probability_density     NUMERIC(10,4) NOT NULL,
	relative_probability    NUMERIC(6,4) NOT NULL
);

-- ============================================================================
-- 6) Equipment facts
--    - voting_equipment table now contains national overview data (with quantity, quality, etc.)
--    - No separate equipment_overview table needed
-- ============================================================================



-- ============================================================================
-- 7) Precinct-level facts (Gingles analysis for Texas)
-- ============================================================================

-- Gingles chart data per precinct (GUI-27)
-- Note: Data has multiple rows per precinct (one for each political party)
CREATE TABLE gingles_chart_precinct_data (
	id                    BIGSERIAL PRIMARY KEY,
	state_id              INTEGER NOT NULL REFERENCES state(state_id),
	county_fips           INTEGER NOT NULL REFERENCES county(geo_id),
	precinct_name         VARCHAR(255),
	precinct_id           VARCHAR(255),
	total_votes           INTEGER,
	white_pct             DOUBLE PRECISION,
	hispanic_pct          DOUBLE PRECISION,
	african_american_pct  DOUBLE PRECISION,
	party                 VARCHAR(32),
	vote_percent          DOUBLE PRECISION,
	votes                 INTEGER
);
CREATE INDEX idx_gingles_state ON gingles_chart_precinct_data (state_id);
CREATE INDEX idx_gingles_county ON gingles_chart_precinct_data (county_fips);


-- ============================================================================
-- 8) Voter registration analytics (census-block level)
-- ============================================================================

-- GUI-18: Voter registration bubble chart points per census block
CREATE TABLE voter_registration_bubble_point (
	block_id              INTEGER PRIMARY KEY REFERENCES census_block(block_id) ON DELETE CASCADE,
	state_id              INTEGER NOT NULL REFERENCES state(state_id),
	dominant_party        VARCHAR(32) NOT NULL,
	republican_registered INTEGER NOT NULL DEFAULT 0,
	democrat_registered   INTEGER NOT NULL DEFAULT 0,
	total_registered      INTEGER NOT NULL DEFAULT 0,
	latitude              DOUBLE PRECISION NOT NULL,
	longitude             DOUBLE PRECISION NOT NULL
);


-- ============================================================================
-- 9) Individual voter records
--    - Stores individual voter information by region
--    - Used for detailed voter analysis and queries
-- ============================================================================

-- Individual voter list by region
CREATE TABLE voter_list (
	id         BIGSERIAL PRIMARY KEY,
	region_id  INTEGER NOT NULL REFERENCES eavs_region(region_id) ON DELETE CASCADE,
	name_full  VARCHAR(255),
	party      VARCHAR(32) NOT NULL
);
CREATE INDEX idx_voter_list_region ON voter_list (region_id);
CREATE INDEX idx_voter_list_party ON voter_list (party);
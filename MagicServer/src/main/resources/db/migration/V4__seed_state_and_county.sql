-- Populate state table from us_state_geo
INSERT INTO state (state_id, name)
SELECT state_id, name
FROM us_state_geo;

-- Populate county table from county_geo
-- Extract state_id from geo_id (geo_id format: last 5 chars of GEO_ID where first 2 are state)
-- Example: geo_id 2261 → state_id = 2 (2261 / 1000 = 2)
-- Only insert counties for states that exist in the state table
INSERT INTO county (geo_id, name, state_id)
SELECT 
    cg.geo_id,
    cg.name,
    cg.geo_id / 1000 AS state_id
FROM county_geo cg
WHERE EXISTS (
    SELECT 1 FROM state s WHERE s.state_id = cg.geo_id / 1000
);


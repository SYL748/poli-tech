#!/bin/bash

# Script to load CSV data into PostgreSQL voter_list table
# This uses COPY which is much faster than individual INSERT statements

CSV_FILE="19_fl_voter_list.csv"
DB_NAME="magic"
DB_USER="postgres"
DB_PASSWORD="postgres"
CONTAINER_NAME="magic-postgres"
TABLE_NAME="voter_list"

echo "Loading voter data from CSV into PostgreSQL..."

# Copy CSV file into the container
docker cp "$CSV_FILE" "$CONTAINER_NAME:/tmp/voter_data.csv"

# Execute COPY command to load data
docker exec -i "$CONTAINER_NAME" psql -U "$DB_USER" -d "$DB_NAME" <<EOF
-- Create table if it doesn't exist
CREATE TABLE IF NOT EXISTS $TABLE_NAME (
    region_id INTEGER,
    name_full VARCHAR(255),
    party VARCHAR(50)
);

-- Truncate table if you want to reload (optional - comment out if you want to append)
-- TRUNCATE TABLE $TABLE_NAME;

-- Load data from CSV using COPY (skip header row)
COPY $TABLE_NAME(region_id, name_full, party) FROM '/tmp/voter_data.csv' WITH (FORMAT csv, HEADER true, DELIMITER ',');

-- Show count of loaded records
SELECT COUNT(*) as total_records FROM $TABLE_NAME;
EOF

# Clean up the temporary file in container
docker exec "$CONTAINER_NAME" rm /tmp/voter_data.csv

echo "Data loading complete!"


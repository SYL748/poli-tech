## MagicServer

Spring Boot backend powering dashboards and maps with normalized datasets for states, regions, counties, voters, equipment, provisional ballots, mail ballot rejections, and pollbook deletions. PostgreSQL schema is managed and seeded by Flyway migrations.

### Requirements

- **JDK**: Java 25 (configured in `pom.xml`). Consider switching to 21/17 LTS if preferred.
- **Maven**: Not required (Maven Wrapper provided: `mvnw`, `mvnw.cmd`).
- **PostgreSQL**: 16+ running locally. Database `magic` with user/password from properties or env.
- **Port**: 8080 free (configurable via `src/main/resources/application.properties`).

### Project Structure

- `pom.xml`: Build, dependencies (Spring Web, Spring Data JPA, Flyway, PostgreSQL), Java version.
- `src/main/java/com/magic/MagicServer/MagicServerApplication.java`: Spring Boot entry point.
- `src/main/java/com/magic/MagicServer/config/AppConfig.java`: Templates for CORS/Jackson/MVC/Tomcat/Security (commented out by default).
- `src/main/java/com/magic/MagicServer/controller`: REST controllers.
- `src/main/java/com/magic/MagicServer/service`: Business logic and response shaping.
- `src/main/java/com/magic/MagicServer/repository`: Spring Data JPA repositories.
- `src/main/java/com/magic/MagicServer/entity`: JPA entities mapping to DB tables.
- `src/main/java/com/magic/MagicServer/model`: DTOs for Geo and other typed responses.
- `src/main/resources/db/migration`: Flyway migrations (schema and seed data).

### Endpoints
in postman_collection.csv

### Sample requests

```bash
curl -s "http://localhost:8080/api/geo/states" | jq '.type, (.features | length)'
curl -s "http://localhost:8080/api/equipment/summary?stateId=12&year=2024" | jq '.[0]'
curl -s "http://localhost:8080/api/provisional/summary?stateId=12&year=2024" | jq '.[0]'
curl -s "http://localhost:8080/api/mail-rejections/regions?stateId=12&year=2024" | jq '.[0]'
curl -s "http://localhost:8080/api/pollbook-deletions/by-county?stateId=12&year=2024" | jq '.[0]'
curl -s "http://localhost:8080/api/voters/active/summary?stateId=12&year=2024"
```

### Database & Migrations

- DB: PostgreSQL 16+. Connection defaults (override via env):
  - `spring.datasource.url=jdbc:postgresql://localhost:5432/magic`
  - `spring.datasource.username=${DB_USERNAME:postgres}`
  - `spring.datasource.password=${DB_PASSWORD:postgres}`
- JPA/Hibernate: `spring.jpa.hibernate.ddl-auto=validate`, SQL logging enabled in dev.
- Flyway: enabled, runs on startup. Key migrations:
  - `V3__recreate_schema_for_frontend.sql`: Creates normalized schema
    - Geo: `us_state_geometry` (JSONB), `state_center`, `state`, `region`, `county`
    - Reason dims: `provisional_reason`, `pollbook_deletion_reason`, `mail_rejection_reason`
    - Equipment dims: `equipment_make`, `equipment_model`
    - Facts: `voter_roll`, `pollbook_deletion_count`, `pollbook_deletion_summary`, `equipment_inventory`, `equipment_quality_vs_rejections`, `provisional_count`, `mail_rejection_count`
  - `V4__seed_us_state_geometry.sql`: Seeds `us_state_geometry` with US state features (large file).
  - `V5__seed_state_center.sql`: Seeds `state_center` lat/lon/zoom for each state.
  - `V6__seed_dimensions.sql`: Seeds states from geometry + demo regions/counties for state `'12'`; seeds reason and equipment dimensions.
  - `V7__seed_facts.sql`: Seeds 2024 facts for state `'12'` (provisional, mail rejections, voter roll, equipment).
  - etc...

### Architecture

Controller → Service → Repository → Entity → DB

1. Controllers receive HTTP requests and delegate to Services.
2. Services implement the aggregations/joins and shape API responses (often `List<Map<String,Object>>` for chart/table friendliness).
3. Repositories are Spring Data JPA interfaces backed by derived queries.
4. Entities map tables and composite keys (embeddable IDs) exactly; Geo JSONB columns are parsed in services for output.

### Configuration & CORS

- Main configuration in `src/main/resources/application.properties`.
- To enable CORS for a frontend during development, uncomment and adjust the CORS section in `config/AppConfig.java` (or configure via properties) to allow your dev origin.

### Troubleshooting

- Port already in use (8080): find and kill the process or run with `--server.port=8081`.
- Schema validation errors: ensure Flyway applied all migrations; DB should be on version 7 per logs.

# Poli-Tech Data Analytics Platform

An end-to-end poli-tech project that collects, preprocesses, stores, and visualizes election- and civics-related datasets (e.g., **EAVS**, **CVAP**, Florida voter file, and more).  
This is a multi-stage pipeline: **Python preprocessing → PostgreSQL (seeded via Flyway) → Spring Boot APIs → React/TypeScript visualizations (D3 + Leaflet)**.

---

## What This Project Does

This project analyzes multiple election and civics-related datasets of 2024 (including but not limited to):
- **EAVS** (Election Administration and Voting Survey)
- **CVAP** (Citizen Voting-Age Population)
- **Florida voter file** (name file / voter list)

---

## Tech Stack

### Frontend
- React
- TypeScript
- D3.js
- Leaflet

### Backend
- Spring Boot
- PostgreSQL
- Flyway (schema + seed migrations)

### Preprocessing
- Python (cleaning, normalization, CSV outputs consumed by backend)
---
## Project Flow

1. **Data acquisition**
   - Find datasets online or obtain them through special requests to state governments.
2. **Preprocessing (Python)**
   - Clean, normalize, and organize raw datasets.
   - Output a set of ready-to-ingest **CSV files** for the backend.
3. **Database seeding (PostgreSQL + Flyway)**
   - Backend seeds tables using the preprocessed outputs (schema + seed managed by Flyway migrations/scripts).
4. **API layer (Spring Boot)**
   - Spring Boot reads from Postgres and exposes APIs for the frontend.
5. **Visualization layer (React + D3 + Leaflet)**
   - Frontend fetches data from Spring Boot and renders interactive dashboards, charts, and maps.

---

## Requirements

- **Java (JDK)**: **25**  
  Configured via `<java.version>25</java.version>` in `MagicServer/pom.xml`.  
  Make sure `java -version` shows 25 and your `JAVA_HOME` points to JDK 25.

- **Node.js + npm**: Required for the frontend (`npm install`, `npm run dev`).

- **Docker**: Required for running PostgreSQL via container (recommended setup). <br>
Make sure nothing (e.g. local psql) is running on the same port:5432 as DOCKER

- **PostgreSQL / psql**:
  - **No local `psql` required** if you use the provided Docker commands (they run `psql` *inside* the container via `docker exec`).

---

## Start the Project

### Frontend Start
run these commands in `frontend/`
```bash
cd frontend
npm install
npm run dev
```

Frontend runs at: **http://localhost:5173/**

### Backend Start

Run these commands from the backend folder (e.g., `MagicServer/`) <br>
Make sure nothing (e.g. local psql) is running on the same port:5432 as DOCKER

#### 1) Start PostgreSQL (Docker)

```bash
# build docker container at port 5432
docker run -d \
  --name magic-postgres \
  -e POSTGRES_DB=magic \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16
```

#### 2) Reset DB (and build tables)

```bash
# reinitialize and reseed the database if any changes were made to the database
./mvnw clean
docker exec magic-postgres psql -U postgres -c "DROP DATABASE IF EXISTS magic;"
docker exec magic-postgres psql -U postgres -c "CREATE DATABASE magic;"
```

> **Note:** `./mvnw clean` cleans Maven build artifacts.  
> Flyway migrations run when Spring Boot starts.

#### 3) Start Spring Boot

```bash
# start springboot
./mvnw spring-boot:run
```

---

## Seed Florida Voter Name File

Some features require the Florida voter name file.

1. Download the Florida voter file from Google Drive:
   - https://drive.google.com/drive/folders/1XWjmDua2RzkoKH4cL_ohRaq9aczKronp?usp=drive_link

2. Move the file to:
   ```
   MagicServer/script-to-load-fl-voter/
   ```

3. Run:
   ```bash
   cd MagicServer/script-to-load-fl-voter
   bash ./load_19_fl_voter.sh
   ```

---

## Preprocessing Notes

All preprocessing details (data cleaning, dataset-specific rules, and how CSVs are produced for the backend) are documented here:

**`preprocess/README.md`**

---

### Spring Boot fails to start due to Java version

```bash
java -version
```

Ensure you are using JDK 25.

### Database errors / schema validation errors

Reset the DB (optional section above) and restart Spring Boot so Flyway can re-apply migrations.
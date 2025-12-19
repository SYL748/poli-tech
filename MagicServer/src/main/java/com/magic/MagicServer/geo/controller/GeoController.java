package com.magic.MagicServer.geo.controller;

import com.magic.MagicServer.geo.dto.FeatureCollectionDto;
import com.magic.MagicServer.geo.dto.StateCenterDto;
import com.magic.MagicServer.geo.service.GeoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for geographic data
 * Provides endpoints for state and county geographic data in GeoJSON FeatureCollection format
 */
@RestController
@RequestMapping("/api/geo")
public class GeoController {

	private final GeoService geoService;

	public GeoController(GeoService geoService) {
		this.geoService = geoService;
	}

	/**
	 * Get all states with geographic data as GeoJSON FeatureCollection
	 * 
	 * Endpoint: GET /api/geo/states
	 * 
	 * Returns a GeoJSON FeatureCollection containing all mainland states with:
	 * - Feature type: "Feature"
	 * - Properties: All state properties from the properties JSONB column
	 * - Geometry: State boundary geometry for drawing on map
	 * 
	 * Frontend can use this data to:
	 * - Render the US map with state boundaries
	 * - Access state properties for display
	 * - Enable click interactions to select states
	 * 
	 * @return FeatureCollectionDto containing all state features
	 */
	@GetMapping("/states")
	public FeatureCollectionDto getAllStates() {
		return geoService.getAllStatesGeoData();
	}

	/**
	 * Get geographic data for a specific state as GeoJSON FeatureCollection
	 * 
	 * Endpoint: GET /api/geo/states/{stateId}
	 * 
	 * Returns a GeoJSON FeatureCollection (with single feature) for the selected state including:
	 * - Properties: State properties from the properties JSONB column
	 * - Geometry: State boundary geometry for display
	 * 
	 * This endpoint supports the state detail page where the state
	 * boundary should be displayed on the map.
	 * 
	 * @param stateId The state identifier (INTEGER per schema, values 1-56)
	 * @return FeatureCollectionDto if found, 404 if not found
	 */
	@GetMapping("/states/{stateId}")
	public ResponseEntity<FeatureCollectionDto> getStateById(@PathVariable Integer stateId) {
		return geoService.getStateGeoData(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Get all counties for a state as GeoJSON FeatureCollection
	 * 
	 * Endpoint: GET /api/geo/states/{stateId}/counties
	 * 
	 * Returns a GeoJSON FeatureCollection containing county boundaries for displaying 
	 * EAVS geographic unit boundaries on the state map. Each feature contains:
	 * - Properties: County properties from the properties JSONB column
	 * - Geometry: County boundary geometry
	 * 
	 * This endpoint should only be called for "detailed states"
	 * where internal boundaries need to be shown.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return FeatureCollectionDto with county features
	 */
	@GetMapping("/states/{stateId}/counties")
	public FeatureCollectionDto getCountiesByState(@PathVariable Integer stateId) {
		return geoService.getCountiesByState(stateId);
	}

	/**
	 * Get all state centers with coordinates and zoom levels
	 * 
	 * Endpoint: GET /api/geo/state-centers
	 * 
	 * Returns a list of all state center data including:
	 * - State ID and name
	 * - Center coordinates (lon, lat)
	 * - Zoom level for appropriate map display
	 * 
	 * This endpoint is useful for:
	 * - Getting map view settings for all states
	 * - Centering the map on a specific state
	 * - Setting appropriate zoom levels
	 * 
	 * @return List of StateCenterDto containing center data for all states
	 */
	@GetMapping("/state-centers")
	public List<StateCenterDto> getAllStateCenters() {
		return geoService.getAllStateCenters();
	}

	/**
	 * Get state center data for a specific state
	 * 
	 * Endpoint: GET /api/geo/state-centers/{stateId}
	 * 
	 * Returns center coordinates and zoom level for the selected state.
	 * Used to center the map on a specific state at the correct zoom level.
	 * 
	 * @param stateId The state identifier (INTEGER, values 1-56)
	 * @return StateCenterDto if found, 404 if not found
	 */
	@GetMapping("/state-centers/{stateId}")
	public ResponseEntity<StateCenterDto> getStateCenterById(@PathVariable Integer stateId) {
		return geoService.getStateCenter(stateId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}

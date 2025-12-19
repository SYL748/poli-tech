package com.magic.MagicServer.geo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magic.MagicServer.geo.dto.FeatureCollectionDto;
import com.magic.MagicServer.geo.dto.FeatureDto;
import com.magic.MagicServer.geo.dto.StateCenterDto;
import com.magic.MagicServer.geo.entity.CountyGeoEntity;
import com.magic.MagicServer.geo.entity.StateCenterEntity;
import com.magic.MagicServer.geo.entity.UsStateGeometryEntity;
import com.magic.MagicServer.geo.repository.CountyGeoRepository;
import com.magic.MagicServer.geo.repository.StateCenterRepository;
import com.magic.MagicServer.geo.repository.UsStateGeometryRepository;
import com.magic.MagicServer.jurisdiction.entity.CountyEntity;
import com.magic.MagicServer.jurisdiction.repository.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for geographic data
 * Handles business logic and DTO mapping for state and county geographic data
 * Returns data in GeoJSON FeatureCollection format
 */
@Service
public class GeoService {

	private final UsStateGeometryRepository usStateGeometryRepository;
	private final StateCenterRepository stateCenterRepository;
	private final CountyRepository countyRepository;
	private final CountyGeoRepository countyGeoRepository;
	private final ObjectMapper objectMapper;

	public GeoService(
			UsStateGeometryRepository usStateGeometryRepository,
			StateCenterRepository stateCenterRepository,
			CountyRepository countyRepository,
			CountyGeoRepository countyGeoRepository,
			ObjectMapper objectMapper
	) {
		this.usStateGeometryRepository = usStateGeometryRepository;
		this.stateCenterRepository = stateCenterRepository;
		this.countyRepository = countyRepository;
		this.countyGeoRepository = countyGeoRepository;
		this.objectMapper = objectMapper;
	}

	/**
	 * Get all states with geographic data as a GeoJSON FeatureCollection
	 * @return FeatureCollectionDto containing all state features
	 */
	public FeatureCollectionDto getAllStatesGeoData() {
		List<FeatureDto> features = usStateGeometryRepository.findAll().stream()
				.map(this::toFeatureDto)
				.collect(Collectors.toList());

		FeatureCollectionDto featureCollection = new FeatureCollectionDto();
		featureCollection.setFeatures(features);
		return featureCollection;
	}

	/**
	 * Get geographic data for a specific state as a GeoJSON FeatureCollection
	 * @param stateId The state identifier (INTEGER from schema, values 1-56)
	 * @return Optional containing FeatureCollectionDto with single state feature if found
	 */
	public Optional<FeatureCollectionDto> getStateGeoData(Integer stateId) {
		return usStateGeometryRepository.findByStateId(stateId)
				.map(entity -> {
					FeatureDto feature = toFeatureDto(entity);
					FeatureCollectionDto featureCollection = new FeatureCollectionDto();
					featureCollection.setFeatures(List.of(feature));
					return featureCollection;
				});
	}

	/**
	 * Convert UsStateGeometryEntity to GeoJSON FeatureDto
	 */
	private FeatureDto toFeatureDto(UsStateGeometryEntity entity) {
		FeatureDto feature = new FeatureDto();

		try {
			// Parse properties JSON string
			JsonNode properties = objectMapper.readTree(entity.getProperties());
			feature.setProperties(properties);

			// Parse geometry JSON string
			JsonNode geometry = objectMapper.readTree(entity.getGeometry());
			feature.setGeometry(geometry);
		} catch (Exception ex) {
			// If parsing fails, set nulls
			feature.setProperties(null);
			feature.setGeometry(null);
		}

		return feature;
	}

	/**
	 * Get all counties for a state as a GeoJSON FeatureCollection
	 * Returns county boundaries for displaying EAVS geographic units on state map
	 * @param stateId The state identifier
	 * @return FeatureCollectionDto containing county features
	 */
	public FeatureCollectionDto getCountiesByState(Integer stateId) {
		// Get all counties for this state from county repository
		List<CountyEntity> counties = countyRepository.findByStateId(stateId);

		// Map each county to FeatureDto with geometry
		List<FeatureDto> features = counties.stream()
				.map(this::toCountyFeatureDto)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

		FeatureCollectionDto featureCollection = new FeatureCollectionDto();
		featureCollection.setFeatures(features);
		return featureCollection;
	}

	/**
	 * Convert CountyEntity to GeoJSON FeatureDto
	 */
	private Optional<FeatureDto> toCountyFeatureDto(CountyEntity county) {
		// Fetch county geometry using geo_id
		return countyGeoRepository.findByGeoId(county.getGeoId())
				.map(countyGeo -> {
					FeatureDto feature = new FeatureDto();

					try {
						// Parse properties JSON string
						JsonNode properties = objectMapper.readTree(countyGeo.getProperties());
						feature.setProperties(properties);

						// Parse geometry JSON string
						JsonNode geometry = objectMapper.readTree(countyGeo.getGeometry());
						feature.setGeometry(geometry);
					} catch (Exception ex) {
						feature.setProperties(null);
						feature.setGeometry(null);
					}

					return feature;
				});
	}

	/**
	 * Get all state centers with their zoom levels
	 * @return List of StateCenterDto containing center coordinates and zoom for all states
	 */
	public List<StateCenterDto> getAllStateCenters() {
		return stateCenterRepository.findAll().stream()
				.map(this::toStateCenterDto)
				.collect(Collectors.toList());
	}

	/**
	 * Get state center data for a specific state
	 * @param stateId The state identifier
	 * @return Optional containing StateCenterDto if found
	 */
	public Optional<StateCenterDto> getStateCenter(Integer stateId) {
		return stateCenterRepository.findByStateId(stateId)
				.map(this::toStateCenterDto);
	}

	/**
	 * Convert StateCenterEntity to StateCenterDto
	 */
	private StateCenterDto toStateCenterDto(StateCenterEntity entity) {
		StateCenterDto dto = new StateCenterDto();
		dto.setStateId(entity.getStateId());
		
		// Get state name from us_state_geo table
		usStateGeometryRepository.findByStateId(entity.getStateId())
				.ifPresent(stateGeo -> dto.setName(stateGeo.getName()));
		
		dto.setLon(entity.getLon());
		dto.setLat(entity.getLat());
		dto.setZoom(entity.getZoom());
		return dto;
	}
}

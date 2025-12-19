package com.magic.MagicServer.geo.dto;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a GeoJSON Feature
 * A Feature object represents a spatially bounded entity with properties and geometry
 */
public class FeatureDto {
	private String type = "Feature";
	private JsonNode properties;
	private JsonNode geometry;

	public FeatureDto() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonNode getProperties() {
		return properties;
	}

	public void setProperties(JsonNode properties) {
		this.properties = properties;
	}

	public JsonNode getGeometry() {
		return geometry;
	}

	public void setGeometry(JsonNode geometry) {
		this.geometry = geometry;
	}
}


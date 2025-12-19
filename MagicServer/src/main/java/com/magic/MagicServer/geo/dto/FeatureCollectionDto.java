package com.magic.MagicServer.geo.dto;

import java.util.List;

/**
 * Represents a GeoJSON FeatureCollection
 * A FeatureCollection contains an array of Feature objects
 * This is the standard format for returning geographic data to the frontend
 */
public class FeatureCollectionDto {
	private String type = "FeatureCollection";
	private List<FeatureDto> features;

	public FeatureCollectionDto() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FeatureDto> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureDto> features) {
		this.features = features;
	}
}


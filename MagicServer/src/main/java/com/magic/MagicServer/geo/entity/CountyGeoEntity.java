package com.magic.MagicServer.geo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "county_geo")
public class CountyGeoEntity {

	@Id
	@Column(name = "geo_id", nullable = false)
	private Integer geoId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "properties", columnDefinition = "jsonb", nullable = false)
	private String properties;

	@Column(name = "geometry", columnDefinition = "jsonb", nullable = false)
	private String geometry;

	public CountyGeoEntity() {
	}

	public Integer getGeoId() {
		return geoId;
	}

	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
}


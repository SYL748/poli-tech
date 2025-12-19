package com.magic.MagicServer.geo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "us_state_geo")
public class UsStateGeometryEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "properties", columnDefinition = "jsonb", nullable = false)
	private String properties;

	@Column(name = "geometry", columnDefinition = "jsonb", nullable = false)
	private String geometry;

	public UsStateGeometryEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

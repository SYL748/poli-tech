package com.magic.MagicServer.jurisdiction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "county")
public class CountyEntity {

	@Id
	@Column(name = "geo_id", nullable = false)
	private Integer geoId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	public CountyEntity() {
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

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
}


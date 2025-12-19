package com.magic.MagicServer.jurisdiction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "eavs_region")
public class EavsRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "geo_id", nullable = false)
	private Integer geoId;

	public EavsRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
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

	public Integer getGeoId() {
		return geoId;
	}

	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}
}


package com.magic.MagicServer.geo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "state_center")
public class StateCenterEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "lon", nullable = false)
	private Double lon;

	@Column(name = "lat", nullable = false)
	private Double lat;

	@Column(name = "zoom", nullable = false)
	private Double zoom;

	public StateCenterEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getZoom() {
		return zoom;
	}

	public void setZoom(Double zoom) {
		this.zoom = zoom;
	}
}

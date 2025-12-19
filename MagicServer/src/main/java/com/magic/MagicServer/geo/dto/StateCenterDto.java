package com.magic.MagicServer.geo.dto;

/**
 * DTO for state center data
 * Contains center point coordinates and zoom level for centering map on a state
 */
public class StateCenterDto {
	private Integer stateId;
	private String name;
	private Double lon;
	private Double lat;
	private Double zoom;

	public StateCenterDto() {
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


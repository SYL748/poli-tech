package com.magic.MagicServer.facts.region.dto;

import java.util.List;

/**
 * DTO for GUI-5, GUI-7, GUI-8, GUI-9, GUI-10: County Choropleth Info
 * Aggregates data from multiple region tables for detailed states
 * Used to display county-level metrics on choropleth maps
 */
public class CountyChoroplethInfoDto {
	private Integer regionId;
	private String countyName;
	private Integer totalProvisional;
	private Double activeVoterPercentage;
	private Double pollbookDeletionPercentage;
	private Double rejectionPercentage;
	private List<String> typeOfEquipmentUsed;

	public CountyChoroplethInfoDto() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getTotalProvisional() {
		return totalProvisional;
	}

	public void setTotalProvisional(Integer totalProvisional) {
		this.totalProvisional = totalProvisional;
	}

	public Double getActiveVoterPercentage() {
		return activeVoterPercentage;
	}

	public void setActiveVoterPercentage(Double activeVoterPercentage) {
		this.activeVoterPercentage = activeVoterPercentage;
	}

	public Double getPollbookDeletionPercentage() {
		return pollbookDeletionPercentage;
	}

	public void setPollbookDeletionPercentage(Double pollbookDeletionPercentage) {
		this.pollbookDeletionPercentage = pollbookDeletionPercentage;
	}

	public Double getRejectionPercentage() {
		return rejectionPercentage;
	}

	public void setRejectionPercentage(Double rejectionPercentage) {
		this.rejectionPercentage = rejectionPercentage;
	}

	public List<String> getTypeOfEquipmentUsed() {
		return typeOfEquipmentUsed;
	}

	public void setTypeOfEquipmentUsed(List<String> typeOfEquipmentUsed) {
		this.typeOfEquipmentUsed = typeOfEquipmentUsed;
	}
}


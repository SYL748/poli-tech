package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-11: Average equipment age by state
 * Used to display choropleth map showing relative age of voting equipment
 */
public class AverageEquipmentAgeByStateDto {
	private Integer stateId;
	private Integer averageAgeOfEquipment;

	public AverageEquipmentAgeByStateDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getAverageAgeOfEquipment() {
		return averageAgeOfEquipment;
	}

	public void setAverageAgeOfEquipment(Integer averageAgeOfEquipment) {
		this.averageAgeOfEquipment = averageAgeOfEquipment;
	}
}


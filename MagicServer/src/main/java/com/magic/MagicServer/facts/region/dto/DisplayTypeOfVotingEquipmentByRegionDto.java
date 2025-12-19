package com.magic.MagicServer.facts.region.dto;

import java.util.List;

/**
 * DTO for GUI-10: Display type of voting equipment by region
 * Used to display table of equipment types used per EAVS geographic unit
 */
public class DisplayTypeOfVotingEquipmentByRegionDto {
	private String regionName;
	private List<String> typeOfEquipmentUsed;

	public DisplayTypeOfVotingEquipmentByRegionDto() {
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<String> getTypeOfEquipmentUsed() {
		return typeOfEquipmentUsed;
	}

	public void setTypeOfEquipmentUsed(List<String> typeOfEquipmentUsed) {
		this.typeOfEquipmentUsed = typeOfEquipmentUsed;
	}
}


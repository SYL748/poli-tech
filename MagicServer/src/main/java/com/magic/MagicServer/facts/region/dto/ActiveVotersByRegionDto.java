package com.magic.MagicServer.facts.region.dto;

/**
 * DTO for GUI-7 and GUI-8: Active voters data by region
 * Used to display table of active, inactive, and total registered voters per EAVS geographic unit
 */
public class ActiveVotersByRegionDto {
	private String regionName;
	private Integer totalRegisteredVoter;
	private Integer activeRegisteredVoter;
	private Integer inactiveRegisteredVoter;
	private Double activePercentage;

	public ActiveVotersByRegionDto() {
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getTotalRegisteredVoter() {
		return totalRegisteredVoter;
	}

	public void setTotalRegisteredVoter(Integer totalRegisteredVoter) {
		this.totalRegisteredVoter = totalRegisteredVoter;
	}

	public Integer getActiveRegisteredVoter() {
		return activeRegisteredVoter;
	}

	public void setActiveRegisteredVoter(Integer activeRegisteredVoter) {
		this.activeRegisteredVoter = activeRegisteredVoter;
	}

	public Integer getInactiveRegisteredVoter() {
		return inactiveRegisteredVoter;
	}

	public void setInactiveRegisteredVoter(Integer inactiveRegisteredVoter) {
		this.inactiveRegisteredVoter = inactiveRegisteredVoter;
	}

	public Double getActivePercentage() {
		return activePercentage;
	}

	public void setActivePercentage(Double activePercentage) {
		this.activePercentage = activePercentage;
	}
}


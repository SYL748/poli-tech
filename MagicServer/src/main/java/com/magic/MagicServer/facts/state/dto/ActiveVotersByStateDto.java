package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-7: Active voters data by state
 * Used to display bar chart of active, inactive, and total registered voters
 */
public class ActiveVotersByStateDto {
	private Integer stateId;
	private Integer totalRegisteredVoters;
	private Integer activeRegisteredVoters;
	private Integer inactiveRegisteredVoters;

	public ActiveVotersByStateDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getTotalRegisteredVoters() {
		return totalRegisteredVoters;
	}

	public void setTotalRegisteredVoters(Integer totalRegisteredVoters) {
		this.totalRegisteredVoters = totalRegisteredVoters;
	}

	public Integer getActiveRegisteredVoters() {
		return activeRegisteredVoters;
	}

	public void setActiveRegisteredVoters(Integer activeRegisteredVoters) {
		this.activeRegisteredVoters = activeRegisteredVoters;
	}

	public Integer getInactiveRegisteredVoters() {
		return inactiveRegisteredVoters;
	}

	public void setInactiveRegisteredVoters(Integer inactiveRegisteredVoters) {
		this.inactiveRegisteredVoters = inactiveRegisteredVoters;
	}
}


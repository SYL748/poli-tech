package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-2: Political Party CVAP percentage for Political Party states
 * Used to display voter eligibility percentage (Florida and Illinois only)
 */
public class PoliticalPartyCvapDto {
	private Integer stateId;
	private String name;
	private Double cvapPercent;

	public PoliticalPartyCvapDto() {
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

	public Double getCvapPercent() {
		return cvapPercent;
	}

	public void setCvapPercent(Double cvapPercent) {
		this.cvapPercent = cvapPercent;
	}
}


package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for registration and turnout metrics by state
 * Combines data from registration_metrics_by_state and active_voters_by_state
 * Used to display voter registration rates, CVAP, votes cast, turnout percentages, and voter counts
 */
public class RegistrationMetricsByStateDto {
	private Integer stateId;
	private Integer totalVotesCast;
	private Integer totalCvap;
	private Double registrationRate;
	private Double turnoutRate;
	private Integer totalRegisteredVoters;
	private Integer activeRegisteredVoters;

	public RegistrationMetricsByStateDto() {
	}

	// Constructor for query projection
	public RegistrationMetricsByStateDto(Integer stateId, Integer totalVotesCast, Integer totalCvap, 
	                                     Double registrationRate, Double turnoutRate, 
	                                     Integer totalRegisteredVoters, Integer activeRegisteredVoters) {
		this.stateId = stateId;
		this.totalVotesCast = totalVotesCast;
		this.totalCvap = totalCvap;
		this.registrationRate = registrationRate;
		this.turnoutRate = turnoutRate;
		this.totalRegisteredVoters = totalRegisteredVoters;
		this.activeRegisteredVoters = activeRegisteredVoters;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getTotalVotesCast() {
		return totalVotesCast;
	}

	public void setTotalVotesCast(Integer totalVotesCast) {
		this.totalVotesCast = totalVotesCast;
	}

	public Integer getTotalCvap() {
		return totalCvap;
	}

	public void setTotalCvap(Integer totalCvap) {
		this.totalCvap = totalCvap;
	}

	public Double getRegistrationRate() {
		return registrationRate;
	}

	public void setRegistrationRate(Double registrationRate) {
		this.registrationRate = registrationRate;
	}

	public Double getTurnoutRate() {
		return turnoutRate;
	}

	public void setTurnoutRate(Double turnoutRate) {
		this.turnoutRate = turnoutRate;
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
}


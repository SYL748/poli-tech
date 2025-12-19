package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-15: State comparison data for Political States (Illinois and Iowa)
 * Used for comparing Republican and Democratic states across various voting metrics
 * and policy attributes.
 */
public class StateComparisonDto {

	private String stateId;
	private String felonyVotingRights;
	private Integer earlyVotingPeriodDays;
	private String voterIdRequirement;
	private String sameDayRegistration;
	private String absenteeRequestDeadline;
	private String automaticVoterRegistration;
	private Boolean noExcuseAbsenteeVoting;
	private Double percentMailBallot;
	private Double percentDropbox;
	private Double turnoutRate;

	public StateComparisonDto() {
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getFelonyVotingRights() {
		return felonyVotingRights;
	}

	public void setFelonyVotingRights(String felonyVotingRights) {
		this.felonyVotingRights = felonyVotingRights;
	}

	public Integer getEarlyVotingPeriodDays() {
		return earlyVotingPeriodDays;
	}

	public void setEarlyVotingPeriodDays(Integer earlyVotingPeriodDays) {
		this.earlyVotingPeriodDays = earlyVotingPeriodDays;
	}

	public String getVoterIdRequirement() {
		return voterIdRequirement;
	}

	public void setVoterIdRequirement(String voterIdRequirement) {
		this.voterIdRequirement = voterIdRequirement;
	}

	public String getSameDayRegistration() {
		return sameDayRegistration;
	}

	public void setSameDayRegistration(String sameDayRegistration) {
		this.sameDayRegistration = sameDayRegistration;
	}

	public String getAbsenteeRequestDeadline() {
		return absenteeRequestDeadline;
	}

	public void setAbsenteeRequestDeadline(String absenteeRequestDeadline) {
		this.absenteeRequestDeadline = absenteeRequestDeadline;
	}

	public String getAutomaticVoterRegistration() {
		return automaticVoterRegistration;
	}

	public void setAutomaticVoterRegistration(String automaticVoterRegistration) {
		this.automaticVoterRegistration = automaticVoterRegistration;
	}

	public Boolean getNoExcuseAbsenteeVoting() {
		return noExcuseAbsenteeVoting;
	}

	public void setNoExcuseAbsenteeVoting(Boolean noExcuseAbsenteeVoting) {
		this.noExcuseAbsenteeVoting = noExcuseAbsenteeVoting;
	}

	public Double getPercentMailBallot() {
		return percentMailBallot;
	}

	public void setPercentMailBallot(Double percentMailBallot) {
		this.percentMailBallot = percentMailBallot;
	}

	public Double getPercentDropbox() {
		return percentDropbox;
	}

	public void setPercentDropbox(Double percentDropbox) {
		this.percentDropbox = percentDropbox;
	}

	public Double getTurnoutRate() {
		return turnoutRate;
	}

	public void setTurnoutRate(Double turnoutRate) {
		this.turnoutRate = turnoutRate;
	}
}


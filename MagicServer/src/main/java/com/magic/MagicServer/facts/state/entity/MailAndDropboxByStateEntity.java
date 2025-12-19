package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for mail_and_dropbox_by_state table (GUI-15)
 * Stores comprehensive state comparison data including voting policies and metrics
 * Used for comparing Republican and Democratic states
 */
@Entity
@Table(name = "mail_and_dropbox_by_state")
public class MailAndDropboxByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "total_votes_cast", nullable = false)
	private Integer totalVotesCast = 0;

	@Column(name = "total_mail_ballot", nullable = false)
	private Integer totalMailBallot = 0;

	@Column(name = "dropbox_votes", nullable = false)
	private Integer dropboxVotes = 0;

	@Column(name = "percent_mail_ballot")
	private Double percentMailBallot;

	@Column(name = "percent_dropbox")
	private Double percentDropbox;

	@Column(name = "total_registered_voter", nullable = false)
	private Integer totalRegisteredVoter = 0;

	@Column(name = "turn_out_rate")
	private Double turnOutRate;

	@Column(name = "felony_voting_rights", length = 128)
	private String felonyVotingRights;

	@Column(name = "early_voting_period_days")
	private Double earlyVotingPeriodDays;

	@Column(name = "voter_id_requirement", length = 128)
	private String voterIdRequirement;

	@Column(name = "same_day_registration")
	private Boolean sameDayRegistration;

	@Column(name = "absentee_request_deadline", length = 128)
	private String absenteeRequestDeadline;

	@Column(name = "automatic_voter_registration")
	private Boolean automaticVoterRegistration;

	@Column(name = "no_excuse_absentee_voting")
	private Boolean noExcuseAbsenteeVoting;

	public MailAndDropboxByStateEntity() {
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

	public Integer getTotalMailBallot() {
		return totalMailBallot;
	}

	public void setTotalMailBallot(Integer totalMailBallot) {
		this.totalMailBallot = totalMailBallot;
	}

	public Integer getDropboxVotes() {
		return dropboxVotes;
	}

	public void setDropboxVotes(Integer dropboxVotes) {
		this.dropboxVotes = dropboxVotes;
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

	public Integer getTotalRegisteredVoter() {
		return totalRegisteredVoter;
	}

	public void setTotalRegisteredVoter(Integer totalRegisteredVoter) {
		this.totalRegisteredVoter = totalRegisteredVoter;
	}

	public Double getTurnOutRate() {
		return turnOutRate;
	}

	public void setTurnOutRate(Double turnOutRate) {
		this.turnOutRate = turnOutRate;
	}

	public String getFelonyVotingRights() {
		return felonyVotingRights;
	}

	public void setFelonyVotingRights(String felonyVotingRights) {
		this.felonyVotingRights = felonyVotingRights;
	}

	public Double getEarlyVotingPeriodDays() {
		return earlyVotingPeriodDays;
	}

	public void setEarlyVotingPeriodDays(Double earlyVotingPeriodDays) {
		this.earlyVotingPeriodDays = earlyVotingPeriodDays;
	}

	public String getVoterIdRequirement() {
		return voterIdRequirement;
	}

	public void setVoterIdRequirement(String voterIdRequirement) {
		this.voterIdRequirement = voterIdRequirement;
	}

	public Boolean getSameDayRegistration() {
		return sameDayRegistration;
	}

	public void setSameDayRegistration(Boolean sameDayRegistration) {
		this.sameDayRegistration = sameDayRegistration;
	}

	public String getAbsenteeRequestDeadline() {
		return absenteeRequestDeadline;
	}

	public void setAbsenteeRequestDeadline(String absenteeRequestDeadline) {
		this.absenteeRequestDeadline = absenteeRequestDeadline;
	}

	public Boolean getAutomaticVoterRegistration() {
		return automaticVoterRegistration;
	}

	public void setAutomaticVoterRegistration(Boolean automaticVoterRegistration) {
		this.automaticVoterRegistration = automaticVoterRegistration;
	}

	public Boolean getNoExcuseAbsenteeVoting() {
		return noExcuseAbsenteeVoting;
	}

	public void setNoExcuseAbsenteeVoting(Boolean noExcuseAbsenteeVoting) {
		this.noExcuseAbsenteeVoting = noExcuseAbsenteeVoting;
	}
}


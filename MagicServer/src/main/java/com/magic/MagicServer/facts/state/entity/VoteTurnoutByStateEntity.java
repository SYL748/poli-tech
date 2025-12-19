package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for vote_turnout_by_state table
 * Stores voter turnout data by state including total votes cast and turnout rate
 */
@Entity
@Table(name = "vote_turnout_by_state")
public class VoteTurnoutByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "total_registered_voter", nullable = false)
	private Integer totalRegisteredVoter = 0;

	@Column(name = "total_votes_cast", nullable = false)
	private Integer totalVotesCast = 0;

	@Column(name = "turn_out_rate")
	private Double turnOutRate;

	public VoteTurnoutByStateEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getTotalRegisteredVoter() {
		return totalRegisteredVoter;
	}

	public void setTotalRegisteredVoter(Integer totalRegisteredVoter) {
		this.totalRegisteredVoter = totalRegisteredVoter;
	}

	public Integer getTotalVotesCast() {
		return totalVotesCast;
	}

	public void setTotalVotesCast(Integer totalVotesCast) {
		this.totalVotesCast = totalVotesCast;
	}

	public Double getTurnOutRate() {
		return turnOutRate;
	}

	public void setTurnOutRate(Double turnOutRate) {
		this.turnOutRate = turnOutRate;
	}
}


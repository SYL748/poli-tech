package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registration_metrics_by_state")
public class RegistrationMetricsByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "total_votes_cast", nullable = false)
	private Integer totalVotesCast;

	@Column(name = "total_cvap", nullable = false)
	private Integer totalCvap;

	@Column(name = "registration_rate")
	private Double registrationRate;

	@Column(name = "turnout_rate")
	private Double turnoutRate;

	public RegistrationMetricsByStateEntity() {
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
}


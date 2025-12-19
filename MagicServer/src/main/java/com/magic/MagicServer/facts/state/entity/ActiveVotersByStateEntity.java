package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "active_voters_by_state")
public class ActiveVotersByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "total_registered_voters", nullable = false)
	private Integer totalRegisteredVoters;

	@Column(name = "active_registered_voters", nullable = false)
	private Integer activeRegisteredVoters;

	public ActiveVotersByStateEntity() {
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
}


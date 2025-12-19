package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "active_voters_by_region")
public class ActiveVotersByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "total_registered_voters", nullable = false)
	private Integer totalRegisteredVoters;

	@Column(name = "active_registered_voters", nullable = false)
	private Integer activeRegisteredVoters;

	@Column(name = "inactive_registered_voters", nullable = false)
	private Integer inactiveRegisteredVoters;

	@Column(name = "active_percentage")
	private Double activePercentage;

	public ActiveVotersByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
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

	public Double getActivePercentage() {
		return activePercentage;
	}

	public void setActivePercentage(Double activePercentage) {
		this.activePercentage = activePercentage;
	}
}


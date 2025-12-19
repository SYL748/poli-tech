package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for state equipment totals by category
 * Maps to state_equipment_total table
 */
@Entity
@Table(name = "state_equipment_total")
public class StateEquipmentTotalEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "dre_no_vvpat", nullable = false)
	private Integer dreNoVvpat;

	@Column(name = "dre_with_vvpat", nullable = false)
	private Integer dreWithVvpat;

	@Column(name = "ballot_marking_device", nullable = false)
	private Integer ballotMarkingDevice;

	@Column(name = "scanner", nullable = false)
	private Integer scanner;

	public StateEquipmentTotalEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getDreNoVvpat() {
		return dreNoVvpat;
	}

	public void setDreNoVvpat(Integer dreNoVvpat) {
		this.dreNoVvpat = dreNoVvpat;
	}

	public Integer getDreWithVvpat() {
		return dreWithVvpat;
	}

	public void setDreWithVvpat(Integer dreWithVvpat) {
		this.dreWithVvpat = dreWithVvpat;
	}

	public Integer getBallotMarkingDevice() {
		return ballotMarkingDevice;
	}

	public void setBallotMarkingDevice(Integer ballotMarkingDevice) {
		this.ballotMarkingDevice = ballotMarkingDevice;
	}

	public Integer getScanner() {
		return scanner;
	}

	public void setScanner(Integer scanner) {
		this.scanner = scanner;
	}
}


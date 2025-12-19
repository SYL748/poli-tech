package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-12: State equipment totals by category
 * Used to display table showing equipment counts by type for all states
 */
public class StateEquipmentTotalDto {
	private Integer stateId;
	private String stateName;
	private Integer dreNoVvpat;
	private Integer dreWithVvpat;
	private Integer ballotMarkingDevice;
	private Integer scanner;

	public StateEquipmentTotalDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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


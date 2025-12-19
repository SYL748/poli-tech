package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Entity for state_equipment_history table
 * Normalized design: One row per state per year
 * Tracks equipment counts by category across federal election years (2016-2024)
 */
@Entity
@Table(name = "state_equipment_history")
@IdClass(StateEquipmentHistoryEntity.StateEquipmentHistoryId.class)
public class StateEquipmentHistoryEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Id
	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "dre_no_vvpat", nullable = false)
	private Integer dreNoVvpat;

	@Column(name = "dre_with_vvpat", nullable = false)
	private Integer dreWithVvpat;

	@Column(name = "ballot_marking_device", nullable = false)
	private Integer ballotMarkingDevice;

	@Column(name = "scanner", nullable = false)
	private Integer scanner;

	public StateEquipmentHistoryEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	/**
	 * Composite key class for state_equipment_history
	 * Required for @IdClass annotation (state_id, year)
	 */
	public static class StateEquipmentHistoryId implements Serializable {
		private Integer stateId;
		private Integer year;

		public StateEquipmentHistoryId() {
		}

		public StateEquipmentHistoryId(Integer stateId, Integer year) {
			this.stateId = stateId;
			this.year = year;
		}

		public Integer getStateId() {
			return stateId;
		}

		public void setStateId(Integer stateId) {
			this.stateId = stateId;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			StateEquipmentHistoryId that = (StateEquipmentHistoryId) o;
			return stateId.equals(that.stateId) && year.equals(that.year);
		}

		@Override
		public int hashCode() {
			return 31 * stateId.hashCode() + year.hashCode();
		}
	}
}


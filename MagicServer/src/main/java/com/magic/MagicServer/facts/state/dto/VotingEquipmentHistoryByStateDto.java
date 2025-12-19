package com.magic.MagicServer.facts.state.dto;

import java.util.List;

/**
 * DTO for GUI-14: Voting Equipment History by State
 * Used to display bar graphs showing equipment counts over time
 * Flexible structure: supports any years without DTO changes
 */
public class VotingEquipmentHistoryByStateDto {
	private String stateId;
	private List<YearEquipmentData> years;

	public VotingEquipmentHistoryByStateDto() {
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public List<YearEquipmentData> getYears() {
		return years;
	}

	public void setYears(List<YearEquipmentData> years) {
		this.years = years;
	}

	/**
	 * Nested class representing equipment counts for a single year
	 */
	public static class YearEquipmentData {
		private Integer year;
		private Integer dreNoVvpat;
		private Integer dreWithVvpat;
		private Integer ballotMarkingDevice;
		private Integer scanner;

		public YearEquipmentData() {
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
	}
}

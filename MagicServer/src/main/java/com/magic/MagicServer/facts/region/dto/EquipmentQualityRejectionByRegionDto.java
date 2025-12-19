package com.magic.MagicServer.facts.region.dto;

/**
 * DTO for GUI-25: Equipment quality vs rejected ballots bubble chart by region
 * Used for displaying bubble chart showing relationship between voting equipment quality
 * and rejected ballot percentages for EAVS geographic units within a state.
 */
public class EquipmentQualityRejectionByRegionDto {

	private Integer regionId;
	private String regionName;
	private Double equipmentQualityScore;
	private Double percentRejectedBallots;
	private Integer totalBallots;

	public EquipmentQualityRejectionByRegionDto() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Double getEquipmentQualityScore() {
		return equipmentQualityScore;
	}

	public void setEquipmentQualityScore(Double equipmentQualityScore) {
		this.equipmentQualityScore = equipmentQualityScore;
	}

	public Double getPercentRejectedBallots() {
		return percentRejectedBallots;
	}

	public void setPercentRejectedBallots(Double percentRejectedBallots) {
		this.percentRejectedBallots = percentRejectedBallots;
	}

	public Integer getTotalBallots() {
		return totalBallots;
	}

	public void setTotalBallots(Integer totalBallots) {
		this.totalBallots = totalBallots;
	}
}


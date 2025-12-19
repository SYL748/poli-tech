package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for EAVS data quality score by state
 * Used to display data quality metrics from EAVS Missing Data Metric
 */
public class EavsDataQualityByStateDto {
	private Integer stateId;
	private Double dataQualityScore;

	public EavsDataQualityByStateDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Double getDataQualityScore() {
		return dataQualityScore;
	}

	public void setDataQualityScore(Double dataQualityScore) {
		this.dataQualityScore = dataQualityScore;
	}
}


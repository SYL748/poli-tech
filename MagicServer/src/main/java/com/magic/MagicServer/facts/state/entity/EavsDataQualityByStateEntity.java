package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for EAVS data quality score by state
 * Maps to eavs_data_quality_by_state table
 */
@Entity
@Table(name = "eavs_data_quality_by_state")
public class EavsDataQualityByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "data_quality_score", nullable = false)
	private Double dataQualityScore;

	public EavsDataQualityByStateEntity() {
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


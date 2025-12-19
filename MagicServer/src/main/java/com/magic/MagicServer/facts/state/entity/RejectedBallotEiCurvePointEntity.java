package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Entity for rejected ballot ecological inference curve point data
 * Maps to rejected_ballot_ei_curve_point table
 * Used for GUI-29: Rejected Ballot EI KDE density visualization
 */
@Entity
@Table(name = "rejected_ballot_ei_curve_point")
public class RejectedBallotEiCurvePointEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "demographic", nullable = false, length = 64)
	private String demographic;

	@Column(name = "rejection_probability", nullable = false, precision = 6, scale = 4)
	private BigDecimal rejectionProbability;

	@Column(name = "probability_density", nullable = false, precision = 10, scale = 4)
	private BigDecimal probabilityDensity;

	@Column(name = "relative_probability", nullable = false, precision = 6, scale = 4)
	private BigDecimal relativeProbability;

	public RejectedBallotEiCurvePointEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getDemographic() {
		return demographic;
	}

	public void setDemographic(String demographic) {
		this.demographic = demographic;
	}

	public BigDecimal getRejectionProbability() {
		return rejectionProbability;
	}

	public void setRejectionProbability(BigDecimal rejectionProbability) {
		this.rejectionProbability = rejectionProbability;
	}

	public BigDecimal getProbabilityDensity() {
		return probabilityDensity;
	}

	public void setProbabilityDensity(BigDecimal probabilityDensity) {
		this.probabilityDensity = probabilityDensity;
	}

	public BigDecimal getRelativeProbability() {
		return relativeProbability;
	}

	public void setRelativeProbability(BigDecimal relativeProbability) {
		this.relativeProbability = relativeProbability;
	}
}


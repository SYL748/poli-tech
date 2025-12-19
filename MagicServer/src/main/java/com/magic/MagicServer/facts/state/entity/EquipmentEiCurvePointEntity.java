package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Entity for equipment ecological inference curve point data
 * Maps to equipment_ei_curve_point table
 * Used for GUI-28: Equipment Quality EI KDE density visualization
 */
@Entity
@Table(name = "equipment_ei_curve_point")
public class EquipmentEiCurvePointEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "demographic", nullable = false, length = 64)
	private String demographic;

	@Column(name = "equipment_quality", nullable = false, precision = 5, scale = 4)
	private BigDecimal equipmentQuality;

	@Column(name = "relative_probability", nullable = false, precision = 6, scale = 4)
	private BigDecimal relativeProbability;

	@Column(name = "probability_density", nullable = false, precision = 10, scale = 4)
	private BigDecimal probabilityDensity;

	public EquipmentEiCurvePointEntity() {
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

	public BigDecimal getEquipmentQuality() {
		return equipmentQuality;
	}

	public void setEquipmentQuality(BigDecimal equipmentQuality) {
		this.equipmentQuality = equipmentQuality;
	}

	public BigDecimal getRelativeProbability() {
		return relativeProbability;
	}

	public void setRelativeProbability(BigDecimal relativeProbability) {
		this.relativeProbability = relativeProbability;
	}

	public BigDecimal getProbabilityDensity() {
		return probabilityDensity;
	}

	public void setProbabilityDensity(BigDecimal probabilityDensity) {
		this.probabilityDensity = probabilityDensity;
	}
}


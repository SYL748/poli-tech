package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Entity for precinct demographic regression coefficients
 * Maps to precinct_demographic_regression_coef table
 * Used for GUI-27: Precinct demographic regression analysis
 */
@Entity
@Table(name = "precinct_demographic_regression_coef")
public class PrecinctDemographicRegressionCoefEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "political_party", nullable = false, length = 32)
	private String politicalParty;

	@Column(name = "demographic", nullable = false, length = 64)
	private String demographic;

	@Column(name = "coef_a", nullable = false, precision = 12, scale = 6)
	private BigDecimal coefA;

	@Column(name = "coef_b", nullable = false, precision = 12, scale = 6)
	private BigDecimal coefB;

	@Column(name = "coef_c", nullable = false, precision = 12, scale = 6)
	private BigDecimal coefC;

	public PrecinctDemographicRegressionCoefEntity() {
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

	public String getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(String politicalParty) {
		this.politicalParty = politicalParty;
	}

	public String getDemographic() {
		return demographic;
	}

	public void setDemographic(String demographic) {
		this.demographic = demographic;
	}

	public BigDecimal getCoefA() {
		return coefA;
	}

	public void setCoefA(BigDecimal coefA) {
		this.coefA = coefA;
	}

	public BigDecimal getCoefB() {
		return coefB;
	}

	public void setCoefB(BigDecimal coefB) {
		this.coefB = coefB;
	}

	public BigDecimal getCoefC() {
		return coefC;
	}

	public void setCoefC(BigDecimal coefC) {
		this.coefC = coefC;
	}
}


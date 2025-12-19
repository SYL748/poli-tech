package com.magic.MagicServer.facts.state.dto;

import java.math.BigDecimal;

/**
 * DTO for precinct demographic regression coefficients
 * Used for GUI-27: Precinct demographic regression analysis
 * 
 * Response format matches Precinct_Demographic_Regression.json:
 * [
 *   {
 *     "state": "Alabama",
 *     "party": "Democratic",
 *     "demographic": "White",
 *     "coef_a": 0.000345,
 *     "coef_b": -0.949716,
 *     "coef_c": 95.698004
 *   },
 *   ...
 * ]
 */
public class PrecinctDemographicRegressionCoefDto {
	private String state;
	private String party;
	private String demographic;
	private BigDecimal coefA;
	private BigDecimal coefB;
	private BigDecimal coefC;

	public PrecinctDemographicRegressionCoefDto() {
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
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


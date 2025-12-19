package com.magic.MagicServer.facts.state.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DTO for rejected ballot ecological inference curve point data
 * Used for GUI-29: Rejected Ballot EI KDE density visualization
 * 
 * Response format matches Rejected_Ballots_EI_KDE_Density.json:
 * {
 *   "stateId": {
 *     "demographic1": [
 *       { "rejection_probability": 0.0, "probability_density": 0.0 },
 *       ...
 *     ],
 *     "demographic2": [...],
 *     ...
 *   }
 * }
 */
public class RejectedBallotEiCurvePointDto {
	
	/**
	 * State ID as string key for JSON response
	 */
	private Integer stateId;
	
	/**
	 * Map of demographic groups to their curve data points
	 * Key: demographic name (e.g., "White", "Hispanic", "African American")
	 * Value: List of data points for that demographic
	 */
	private Map<String, List<CurveDataPoint>> demographicData;

	public RejectedBallotEiCurvePointDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Map<String, List<CurveDataPoint>> getDemographicData() {
		return demographicData;
	}

	public void setDemographicData(Map<String, List<CurveDataPoint>> demographicData) {
		this.demographicData = demographicData;
	}

	/**
	 * Inner class representing a single point on the EI curve
	 * Contains rejection probability and corresponding probability density
	 */
	public static class CurveDataPoint {
		private BigDecimal rejectionProbability;
		private BigDecimal probabilityDensity;

		public CurveDataPoint() {
		}

		public CurveDataPoint(BigDecimal rejectionProbability, BigDecimal probabilityDensity) {
			this.rejectionProbability = rejectionProbability;
			this.probabilityDensity = probabilityDensity;
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
	}
}


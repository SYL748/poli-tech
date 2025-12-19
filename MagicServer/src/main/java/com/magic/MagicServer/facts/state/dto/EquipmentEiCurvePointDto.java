package com.magic.MagicServer.facts.state.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DTO for equipment ecological inference curve point data
 * Used for GUI-28: Equipment Quality EI KDE density visualization
 * 
 * Response format matches Equipment_Quality_EI_KDE_density.json:
 * {
 *   "stateId": {
 *     "demographic1": [
 *       { "equipment_quality": 0.0, "probability_density": 0.0 },
 *       ...
 *     ],
 *     "demographic2": [...],
 *     ...
 *   }
 * }
 */
public class EquipmentEiCurvePointDto {
	
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

	public EquipmentEiCurvePointDto() {
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
	 * Contains equipment quality score and corresponding probability density
	 */
	public static class CurveDataPoint {
		private BigDecimal equipmentQuality;
		private BigDecimal probabilityDensity;

		public CurveDataPoint() {
		}

		public CurveDataPoint(BigDecimal equipmentQuality, BigDecimal probabilityDensity) {
			this.equipmentQuality = equipmentQuality;
			this.probabilityDensity = probabilityDensity;
		}

		public BigDecimal getEquipmentQuality() {
			return equipmentQuality;
		}

		public void setEquipmentQuality(BigDecimal equipmentQuality) {
			this.equipmentQuality = equipmentQuality;
		}

		public BigDecimal getProbabilityDensity() {
			return probabilityDensity;
		}

		public void setProbabilityDensity(BigDecimal probabilityDensity) {
			this.probabilityDensity = probabilityDensity;
		}
	}
}


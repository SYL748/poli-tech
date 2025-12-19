package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-6: Equipment Summary by State
 * Used to display table of voting equipment for a specific state
 * Matches: 6_Equipment_Summary_by_State_with_short_description.csv
 */
public class EquipmentSummaryByStateDto {
	private String stateId;
	private String manufacturer;
	private String modelName;
	private Integer quantity;
	private String equipmentType;
	private Double age;
	private String os;
	private String certificationLevel;
	private String scanningRate;
	private String errorRate;
	private Double reliability;
	private Double quality;
	private String shortDescription;

	public EquipmentSummaryByStateDto() {
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCertificationLevel() {
		return certificationLevel;
	}

	public void setCertificationLevel(String certificationLevel) {
		this.certificationLevel = certificationLevel;
	}

	public String getScanningRate() {
		return scanningRate;
	}

	public void setScanningRate(String scanningRate) {
		this.scanningRate = scanningRate;
	}

	public String getErrorRate() {
		return errorRate;
	}

	public void setErrorRate(String errorRate) {
		this.errorRate = errorRate;
	}

	public Double getReliability() {
		return reliability;
	}

	public void setReliability(Double reliability) {
		this.reliability = reliability;
	}

	public Double getQuality() {
		return quality;
	}

	public void setQuality(Double quality) {
		this.quality = quality;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}


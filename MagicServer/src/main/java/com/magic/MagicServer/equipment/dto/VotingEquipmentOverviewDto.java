package com.magic.MagicServer.equipment.dto;

/**
 * DTO for GUI-13: Voting Equipment Overview (National)
 * Used to display table of equipment used in 2024 across all states
 * Row ordered by equipment provider (make) with sub-ordering by model
 * Matches: 13_Voting_Equipment_Overview.csv
 */
public class VotingEquipmentOverviewDto {
	private String make;
	private String type;
	private String model;
	private Double age;
	private String operatingSystem;
	private String cert;
	private String scanRate;
	private String errorRate;
	private Double reliability;
	private Double quality;
	private Boolean discontinued;
	private Integer quantity;

	public VotingEquipmentOverviewDto() {
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getScanRate() {
		return scanRate;
	}

	public void setScanRate(String scanRate) {
		this.scanRate = scanRate;
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

	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}


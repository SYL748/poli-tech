package com.magic.MagicServer.equipment.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Entity for voting_equipment table
 * Represents voting equipment overview with national statistics
 * Matches: 13_Voting_Equipment_Overview.csv
 */
@Entity
@Table(name = "voting_equipment")
public class VotingEquipmentOverviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "make", nullable = false, length = 128)
	private String make;

	@Column(name = "type", length = 512)
	private String type;

	@Column(name = "model", nullable = false, length = 256)
	private String model;

	@Column(name = "age")
	private BigDecimal age;

	@Column(name = "operating_system", length = 128)
	private String operatingSystem;

	@Column(name = "cert", length = 128)
	private String cert;

	@Column(name = "scan_rate", length = 128)
	private String scanRate;

	@Column(name = "error_rate", length = 128)
	private String errorRate;

	@Column(name = "reliability")
	private BigDecimal reliability;

	@Column(name = "quality")
	private BigDecimal quality;

	@Column(name = "discontinued", nullable = false)
	private Boolean discontinued = false;

	@Column(name = "quantity", nullable = false)
	private Integer quantity = 0;

	public VotingEquipmentOverviewEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
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

	public BigDecimal getReliability() {
		return reliability;
	}

	public void setReliability(BigDecimal reliability) {
		this.reliability = reliability;
	}

	public BigDecimal getQuality() {
		return quality;
	}

	public void setQuality(BigDecimal quality) {
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


package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Entity for equipment_summary_by_state table
 * Represents voting equipment inventory and metrics at the state level
 * Denormalized table with all equipment details included
 * Matches: 6_Equipment_Summary_by_State_with_short_description.csv
 */
@Entity
@Table(name = "equipment_summary_by_state")
public class EquipmentSummaryByStateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "manufacturer", nullable = false, length = 128)
	private String manufacturer;

	@Column(name = "model_name", nullable = false, length = 256)
	private String modelName;

	@Column(name = "quantity", nullable = false)
	private Integer quantity = 0;

	@Column(name = "equipment_type", length = 64)
	private String equipmentType;

	@Column(name = "age")
	private BigDecimal age;

	@Column(name = "os", length = 128)
	private String os;

	@Column(name = "certification_level", length = 128)
	private String certificationLevel;

	@Column(name = "scanning_rate", length = 128)
	private String scanningRate;

	@Column(name = "error_rate", length = 128)
	private String errorRate;

	@Column(name = "reliability")
	private BigDecimal reliability;

	@Column(name = "quality")
	private BigDecimal quality;

	@Column(name = "short_description", length = 512)
	private String shortDescription;

	public EquipmentSummaryByStateEntity() {
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

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}


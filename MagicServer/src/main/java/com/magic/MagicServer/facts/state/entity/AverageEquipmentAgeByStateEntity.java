package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for average equipment age by state
 * Maps to average_equipment_age_by_state table
 */
@Entity
@Table(name = "average_equipment_age_by_state")
public class AverageEquipmentAgeByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "average_age_of_equipment", nullable = false)
	private Integer averageAgeOfEquipment;

	public AverageEquipmentAgeByStateEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getAverageAgeOfEquipment() {
		return averageAgeOfEquipment;
	}

	public void setAverageAgeOfEquipment(Integer averageAgeOfEquipment) {
		this.averageAgeOfEquipment = averageAgeOfEquipment;
	}
}


package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.util.List;

/**
 * Entity for display_type_of_voting_equipment_by_region table (GUI-10)
 * Stores array of equipment types used in each region
 */
@Entity
@Table(name = "display_type_of_voting_equipment_by_region")
public class DisplayTypeOfVotingEquipmentByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "type_of_equipment_used", nullable = false, columnDefinition = "TEXT[]")
	private List<String> typeOfEquipmentUsed;

	public DisplayTypeOfVotingEquipmentByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public List<String> getTypeOfEquipmentUsed() {
		return typeOfEquipmentUsed;
	}

	public void setTypeOfEquipmentUsed(List<String> typeOfEquipmentUsed) {
		this.typeOfEquipmentUsed = typeOfEquipmentUsed;
	}
}


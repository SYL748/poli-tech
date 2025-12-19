package com.magic.MagicServer.voter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for individual voter records
 * Maps to voter_list table which contains detailed voter information by region
 */
@Entity
@Table(name = "voter_list")
public class VoterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "name_full", length = 255)
	private String nameFull;

	@Column(name = "party", nullable = false, length = 32)
	private String party;

	public VoterEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getNameFull() {
		return nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
}


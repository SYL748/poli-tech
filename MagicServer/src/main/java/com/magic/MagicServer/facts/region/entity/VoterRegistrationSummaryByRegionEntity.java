package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for voter registration summary by region
 * Maps to voter_registration_summary_by_region table
 * Contains party breakdown and data completeness metrics
 */
@Entity
@Table(name = "voter_registration_summary_by_region")
public class VoterRegistrationSummaryByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "geo_id", nullable = false)
	private Integer geoId;

	@Column(name = "total_num_registered_voters", nullable = false)
	private Integer totalNumRegisteredVoters;

	@Column(name = "total_num_republican", nullable = false)
	private Integer totalNumRepublican;

	@Column(name = "total_num_democratic", nullable = false)
	private Integer totalNumDemocratic;

	@Column(name = "total_num_unaffiliated", nullable = false)
	private Integer totalNumUnaffiliated;

	@Column(name = "total_cvap", nullable = false)
	private Integer totalCvap;

	@Column(name = "registered_voter_percentage")
	private Double registeredVoterPercentage;

	@Column(name = "completeness")
	private Double completeness;

	@Column(name = "contact_completeness")
	private Double contactCompleteness;

	public VoterRegistrationSummaryByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getGeoId() {
		return geoId;
	}

	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}

	public Integer getTotalNumRegisteredVoters() {
		return totalNumRegisteredVoters;
	}

	public void setTotalNumRegisteredVoters(Integer totalNumRegisteredVoters) {
		this.totalNumRegisteredVoters = totalNumRegisteredVoters;
	}

	public Integer getTotalNumRepublican() {
		return totalNumRepublican;
	}

	public void setTotalNumRepublican(Integer totalNumRepublican) {
		this.totalNumRepublican = totalNumRepublican;
	}

	public Integer getTotalNumDemocratic() {
		return totalNumDemocratic;
	}

	public void setTotalNumDemocratic(Integer totalNumDemocratic) {
		this.totalNumDemocratic = totalNumDemocratic;
	}

	public Integer getTotalNumUnaffiliated() {
		return totalNumUnaffiliated;
	}

	public void setTotalNumUnaffiliated(Integer totalNumUnaffiliated) {
		this.totalNumUnaffiliated = totalNumUnaffiliated;
	}

	public Integer getTotalCvap() {
		return totalCvap;
	}

	public void setTotalCvap(Integer totalCvap) {
		this.totalCvap = totalCvap;
	}

	public Double getRegisteredVoterPercentage() {
		return registeredVoterPercentage;
	}

	public void setRegisteredVoterPercentage(Double registeredVoterPercentage) {
		this.registeredVoterPercentage = registeredVoterPercentage;
	}

	public Double getCompleteness() {
		return completeness;
	}

	public void setCompleteness(Double completeness) {
		this.completeness = completeness;
	}

	public Double getContactCompleteness() {
		return contactCompleteness;
	}

	public void setContactCompleteness(Double contactCompleteness) {
		this.contactCompleteness = contactCompleteness;
	}
}


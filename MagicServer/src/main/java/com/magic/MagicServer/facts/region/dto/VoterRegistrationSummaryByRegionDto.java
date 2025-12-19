package com.magic.MagicServer.facts.region.dto;

/**
 * DTO for voter registration summary by region
 * Contains party breakdown and data completeness metrics
 */
public class VoterRegistrationSummaryByRegionDto {
	private Integer regionId;
	private String regionName;
	private Integer geoId;
	private Integer totalNumRegisteredVoters;
	private Integer totalNumRepublican;
	private Integer totalNumDemocratic;
	private Integer totalNumUnaffiliated;
	private Integer totalCvap;
	private Double registeredVoterPercentage;
	private Double completeness;
	private Double contactCompleteness;

	public VoterRegistrationSummaryByRegionDto() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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


package com.magic.MagicServer.voter.dto;

/**
 * DTO for individual voter record
 * Used in paginated voter list responses
 */
public class VoterDto {
	private Long id;
	private Integer regionId;
	private String regionName;
	private String nameFull;
	private String party;

	public VoterDto() {
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

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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


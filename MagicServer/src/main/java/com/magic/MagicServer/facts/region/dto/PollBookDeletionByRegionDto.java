package com.magic.MagicServer.facts.region.dto;

/**
 * DTO for GUI-8: Poll book deletion data by region
 * Used to display table of voter roll deletions by reason per EAVS geographic unit
 */
public class PollBookDeletionByRegionDto {
	private String regionName;
	private Integer moved;
	private Integer death;
	private Integer felony;
	private Integer failResponse;
	private Integer incompetentToVote;
	private Integer voterRequest;
	private Integer duplicateRecord;

	public PollBookDeletionByRegionDto() {
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getMoved() {
		return moved;
	}

	public void setMoved(Integer moved) {
		this.moved = moved;
	}

	public Integer getDeath() {
		return death;
	}

	public void setDeath(Integer death) {
		this.death = death;
	}

	public Integer getFelony() {
		return felony;
	}

	public void setFelony(Integer felony) {
		this.felony = felony;
	}

	public Integer getFailResponse() {
		return failResponse;
	}

	public void setFailResponse(Integer failResponse) {
		this.failResponse = failResponse;
	}

	public Integer getIncompetentToVote() {
		return incompetentToVote;
	}

	public void setIncompetentToVote(Integer incompetentToVote) {
		this.incompetentToVote = incompetentToVote;
	}

	public Integer getVoterRequest() {
		return voterRequest;
	}

	public void setVoterRequest(Integer voterRequest) {
		this.voterRequest = voterRequest;
	}

	public Integer getDuplicateRecord() {
		return duplicateRecord;
	}

	public void setDuplicateRecord(Integer duplicateRecord) {
		this.duplicateRecord = duplicateRecord;
	}
}


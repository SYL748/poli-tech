package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-8: Poll book deletion data by state
 * Used to display bar chart of voter roll deletions by reason
 */
public class PollBookDeletionByStateDto {
	private Integer stateId;
	private Integer moved;
	private Integer death;
	private Integer felony;
	private Integer failResponse;
	private Integer incompetentToVote;
	private Integer voterRequest;
	private Integer duplicateRecord;

	public PollBookDeletionByStateDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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


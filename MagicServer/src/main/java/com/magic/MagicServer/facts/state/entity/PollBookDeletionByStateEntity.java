package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for poll book deletion data by state
 * Maps to poll_book_deletion_by_state table
 */
@Entity
@Table(name = "poll_book_deletion_by_state")
public class PollBookDeletionByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "moved", nullable = false)
	private Integer moved;

	@Column(name = "death", nullable = false)
	private Integer death;

	@Column(name = "felony", nullable = false)
	private Integer felony;

	@Column(name = "fail_response", nullable = false)
	private Integer failResponse;

	@Column(name = "incompetent_to_vote", nullable = false)
	private Integer incompetentToVote;

	@Column(name = "voter_request", nullable = false)
	private Integer voterRequest;

	@Column(name = "duplicate_record", nullable = false)
	private Integer duplicateRecord;

	public PollBookDeletionByStateEntity() {
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


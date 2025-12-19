package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for poll book deletion data by region
 * Maps to poll_book_deletion_by_region table
 */
@Entity
@Table(name = "poll_book_deletion_by_region")
public class PollBookDeletionByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "total_registered_user", nullable = false)
	private Integer totalRegisteredUser;

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

	public PollBookDeletionByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getTotalRegisteredUser() {
		return totalRegisteredUser;
	}

	public void setTotalRegisteredUser(Integer totalRegisteredUser) {
		this.totalRegisteredUser = totalRegisteredUser;
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


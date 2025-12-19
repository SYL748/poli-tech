package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "provisional_by_region")
public class ProvisionalByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "voter_not_on_list", nullable = false)
	private Integer voterNotOnList;

	@Column(name = "voter_lacked_id", nullable = false)
	private Integer voterLackedId;

	@Column(name = "election_official_challenged_eligibility", nullable = false)
	private Integer electionOfficialChallengedEligibility;

	@Column(name = "another_person_challenged_eligibility", nullable = false)
	private Integer anotherPersonChallengedEligibility;

	@Column(name = "voter_not_resident", nullable = false)
	private Integer voterNotResident;

	@Column(name = "voter_registration_not_updated", nullable = false)
	private Integer voterRegistrationNotUpdated;

	@Column(name = "voter_did_not_surrender_mail_ballot", nullable = false)
	private Integer voterDidNotSurrenderMailBallot;

	@Column(name = "judge_extended_voting_hours", nullable = false)
	private Integer judgeExtendedVotingHours;

	@Column(name = "voter_used_sdr", nullable = false)
	private Integer voterUsedSdr;

	@Column(name = "other_reason", nullable = false)
	private Integer otherReason;

	@Column(name = "total_provisional", nullable = false)
	private Integer totalProvisional;

	public ProvisionalByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getVoterNotOnList() {
		return voterNotOnList;
	}

	public void setVoterNotOnList(Integer voterNotOnList) {
		this.voterNotOnList = voterNotOnList;
	}

	public Integer getVoterLackedId() {
		return voterLackedId;
	}

	public void setVoterLackedId(Integer voterLackedId) {
		this.voterLackedId = voterLackedId;
	}

	public Integer getElectionOfficialChallengedEligibility() {
		return electionOfficialChallengedEligibility;
	}

	public void setElectionOfficialChallengedEligibility(Integer electionOfficialChallengedEligibility) {
		this.electionOfficialChallengedEligibility = electionOfficialChallengedEligibility;
	}

	public Integer getAnotherPersonChallengedEligibility() {
		return anotherPersonChallengedEligibility;
	}

	public void setAnotherPersonChallengedEligibility(Integer anotherPersonChallengedEligibility) {
		this.anotherPersonChallengedEligibility = anotherPersonChallengedEligibility;
	}

	public Integer getVoterNotResident() {
		return voterNotResident;
	}

	public void setVoterNotResident(Integer voterNotResident) {
		this.voterNotResident = voterNotResident;
	}

	public Integer getVoterRegistrationNotUpdated() {
		return voterRegistrationNotUpdated;
	}

	public void setVoterRegistrationNotUpdated(Integer voterRegistrationNotUpdated) {
		this.voterRegistrationNotUpdated = voterRegistrationNotUpdated;
	}

	public Integer getVoterDidNotSurrenderMailBallot() {
		return voterDidNotSurrenderMailBallot;
	}

	public void setVoterDidNotSurrenderMailBallot(Integer voterDidNotSurrenderMailBallot) {
		this.voterDidNotSurrenderMailBallot = voterDidNotSurrenderMailBallot;
	}

	public Integer getJudgeExtendedVotingHours() {
		return judgeExtendedVotingHours;
	}

	public void setJudgeExtendedVotingHours(Integer judgeExtendedVotingHours) {
		this.judgeExtendedVotingHours = judgeExtendedVotingHours;
	}

	public Integer getVoterUsedSdr() {
		return voterUsedSdr;
	}

	public void setVoterUsedSdr(Integer voterUsedSdr) {
		this.voterUsedSdr = voterUsedSdr;
	}

	public Integer getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(Integer otherReason) {
		this.otherReason = otherReason;
	}

	public Integer getTotalProvisional() {
		return totalProvisional;
	}

	public void setTotalProvisional(Integer totalProvisional) {
		this.totalProvisional = totalProvisional;
	}
}


package com.magic.MagicServer.facts.region.dto;

/**
 * DTO for GUI-4: Provisional ballot categories by region
 * Used to display table of provisional ballot reasons per EAVS geographic unit
 */
public class ProvisionalByRegionDto {
	private String regionName;
	private Integer voterNotOnList;
	private Integer voterLackedId;
	private Integer electionOfficialChallengedEligibility;
	private Integer anotherPersonChallengedEligibility;
	private Integer voterNotResident;
	private Integer voterRegistrationNotUpdated;
	private Integer voterDidNotSurrenderMailBallot;
	private Integer judgeExtendedVotingHours;
	private Integer voterUsedSdr;
	private Integer otherReason;
	private Integer totalProvisional;

	public ProvisionalByRegionDto() {
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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


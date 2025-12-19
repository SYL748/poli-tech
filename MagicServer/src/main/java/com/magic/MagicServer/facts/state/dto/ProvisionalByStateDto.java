package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-3: Provisional ballot categories by state
 * Used to display bar chart of provisional ballot reasons
 */
public class ProvisionalByStateDto {
	private Integer stateId;
	private Integer voterNotOnList;
	private Integer voterLackedId;
	private Integer electionOfficialChallengedEligibility;
	private Integer anotherPersonChallengedEligibility;
	private Integer voterNotResident;
	private Integer voterRegistrationNotUpdated;
	private Integer voterDidNotSurrenderMailBallot;
	private Integer judgeExtendedVotingHours;
	private Integer voterUsedSdr;
	private Integer otherReasons;

	public ProvisionalByStateDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

	public Integer getOtherReasons() {
		return otherReasons;
	}

	public void setOtherReasons(Integer otherReasons) {
		this.otherReasons = otherReasons;
	}
}


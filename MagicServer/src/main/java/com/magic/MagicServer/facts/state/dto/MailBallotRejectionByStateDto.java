package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-9: Mail ballot rejection data by state
 * Used to display bar chart of mail ballot rejections by reason
 */
public class MailBallotRejectionByStateDto {
	private Integer stateId;
	private Integer late;
	private Integer totalMailBallot;
	private Integer totalRejectedBallots;
	private Double rejectionPercentage;
	private Integer missingVoterSignature;
	private Integer missingWitnessSignature;
	private Integer nonMatchingVoterSignature;
	private Integer unofficialEnvelope;
	private Integer ballotMissingFromEnvelope;
	private Integer noSecrecyEnvelope;
	private Integer multipleBallotsInOneEnvelope;
	private Integer envelopeNotSealed;
	private Integer noPostmark;
	private Integer noResidentAddressOnEnvelope;
	private Integer voterDeceased;
	private Integer voterAlreadyVoted;
	private Integer missingDocumentation;
	private Integer voterNotEligible;
	private Integer noBallotApplication;

	public MailBallotRejectionByStateDto() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getLate() {
		return late;
	}

	public void setLate(Integer late) {
		this.late = late;
	}

	public Integer getTotalMailBallot() {
		return totalMailBallot;
	}

	public void setTotalMailBallot(Integer totalMailBallot) {
		this.totalMailBallot = totalMailBallot;
	}

	public Integer getTotalRejectedBallots() {
		return totalRejectedBallots;
	}

	public void setTotalRejectedBallots(Integer totalRejectedBallots) {
		this.totalRejectedBallots = totalRejectedBallots;
	}

	public Double getRejectionPercentage() {
		return rejectionPercentage;
	}

	public void setRejectionPercentage(Double rejectionPercentage) {
		this.rejectionPercentage = rejectionPercentage;
	}

	public Integer getMissingVoterSignature() {
		return missingVoterSignature;
	}

	public void setMissingVoterSignature(Integer missingVoterSignature) {
		this.missingVoterSignature = missingVoterSignature;
	}

	public Integer getMissingWitnessSignature() {
		return missingWitnessSignature;
	}

	public void setMissingWitnessSignature(Integer missingWitnessSignature) {
		this.missingWitnessSignature = missingWitnessSignature;
	}

	public Integer getNonMatchingVoterSignature() {
		return nonMatchingVoterSignature;
	}

	public void setNonMatchingVoterSignature(Integer nonMatchingVoterSignature) {
		this.nonMatchingVoterSignature = nonMatchingVoterSignature;
	}

	public Integer getUnofficialEnvelope() {
		return unofficialEnvelope;
	}

	public void setUnofficialEnvelope(Integer unofficialEnvelope) {
		this.unofficialEnvelope = unofficialEnvelope;
	}

	public Integer getBallotMissingFromEnvelope() {
		return ballotMissingFromEnvelope;
	}

	public void setBallotMissingFromEnvelope(Integer ballotMissingFromEnvelope) {
		this.ballotMissingFromEnvelope = ballotMissingFromEnvelope;
	}

	public Integer getNoSecrecyEnvelope() {
		return noSecrecyEnvelope;
	}

	public void setNoSecrecyEnvelope(Integer noSecrecyEnvelope) {
		this.noSecrecyEnvelope = noSecrecyEnvelope;
	}

	public Integer getMultipleBallotsInOneEnvelope() {
		return multipleBallotsInOneEnvelope;
	}

	public void setMultipleBallotsInOneEnvelope(Integer multipleBallotsInOneEnvelope) {
		this.multipleBallotsInOneEnvelope = multipleBallotsInOneEnvelope;
	}

	public Integer getEnvelopeNotSealed() {
		return envelopeNotSealed;
	}

	public void setEnvelopeNotSealed(Integer envelopeNotSealed) {
		this.envelopeNotSealed = envelopeNotSealed;
	}

	public Integer getNoPostmark() {
		return noPostmark;
	}

	public void setNoPostmark(Integer noPostmark) {
		this.noPostmark = noPostmark;
	}

	public Integer getNoResidentAddressOnEnvelope() {
		return noResidentAddressOnEnvelope;
	}

	public void setNoResidentAddressOnEnvelope(Integer noResidentAddressOnEnvelope) {
		this.noResidentAddressOnEnvelope = noResidentAddressOnEnvelope;
	}

	public Integer getVoterDeceased() {
		return voterDeceased;
	}

	public void setVoterDeceased(Integer voterDeceased) {
		this.voterDeceased = voterDeceased;
	}

	public Integer getVoterAlreadyVoted() {
		return voterAlreadyVoted;
	}

	public void setVoterAlreadyVoted(Integer voterAlreadyVoted) {
		this.voterAlreadyVoted = voterAlreadyVoted;
	}

	public Integer getMissingDocumentation() {
		return missingDocumentation;
	}

	public void setMissingDocumentation(Integer missingDocumentation) {
		this.missingDocumentation = missingDocumentation;
	}

	public Integer getVoterNotEligible() {
		return voterNotEligible;
	}

	public void setVoterNotEligible(Integer voterNotEligible) {
		this.voterNotEligible = voterNotEligible;
	}

	public Integer getNoBallotApplication() {
		return noBallotApplication;
	}

	public void setNoBallotApplication(Integer noBallotApplication) {
		this.noBallotApplication = noBallotApplication;
	}
}


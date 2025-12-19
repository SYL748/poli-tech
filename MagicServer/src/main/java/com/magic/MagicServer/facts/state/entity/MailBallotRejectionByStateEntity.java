package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for mail ballot rejection data by state
 * Maps to mail_ballot_rejection_by_state table
 */
@Entity
@Table(name = "mail_ballot_rejection_by_state")
public class MailBallotRejectionByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "late", nullable = false)
	private Integer late;

	@Column(name = "total_mail_ballot", nullable = false)
	private Integer totalMailBallot;

	@Column(name = "total_rejected_ballots", nullable = false)
	private Integer totalRejectedBallots;

	@Column(name = "missing_voter_signature", nullable = false)
	private Integer missingVoterSignature;

	@Column(name = "missing_witness_signature", nullable = false)
	private Integer missingWitnessSignature;

	@Column(name = "non_matching_voter_signature", nullable = false)
	private Integer nonMatchingVoterSignature;

	@Column(name = "unofficial_envelope", nullable = false)
	private Integer unofficialEnvelope;

	@Column(name = "ballot_missing_from_envelope", nullable = false)
	private Integer ballotMissingFromEnvelope;

	@Column(name = "no_secrecy_envelope", nullable = false)
	private Integer noSecrecyEnvelope;

	@Column(name = "multiple_ballots_one_envelope", nullable = false)
	private Integer multipleBallotsOneEnvelope;

	@Column(name = "envelope_not_sealed", nullable = false)
	private Integer envelopeNotSealed;

	@Column(name = "no_postmark", nullable = false)
	private Integer noPostmark;

	@Column(name = "no_resident_address_on_envelope", nullable = false)
	private Integer noResidentAddressOnEnvelope;

	@Column(name = "voter_deceased", nullable = false)
	private Integer voterDeceased;

	@Column(name = "voter_already_voted", nullable = false)
	private Integer voterAlreadyVoted;

	@Column(name = "missing_documentation", nullable = false)
	private Integer missingDocumentation;

	@Column(name = "voter_not_eligible", nullable = false)
	private Integer voterNotEligible;

	@Column(name = "no_ballot_application", nullable = false)
	private Integer noBallotApplication;

	public MailBallotRejectionByStateEntity() {
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

	public Integer getMultipleBallotsOneEnvelope() {
		return multipleBallotsOneEnvelope;
	}

	public void setMultipleBallotsOneEnvelope(Integer multipleBallotsOneEnvelope) {
		this.multipleBallotsOneEnvelope = multipleBallotsOneEnvelope;
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


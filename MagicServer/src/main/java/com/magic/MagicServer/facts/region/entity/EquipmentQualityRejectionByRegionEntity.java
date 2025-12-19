package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * Entity for equipment_quality_rejection_by_region table (GUI-25)
 * Stores equipment quality scores and rejected ballot percentages by EAVS geographic region
 * Used for bubble chart visualization analyzing relationship between equipment quality and ballot rejection rates
 */
@Entity
@Table(name = "equipment_quality_rejection_by_region")
public class EquipmentQualityRejectionByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "equipment_quality_score", nullable = false, precision = 5, scale = 2)
	private BigDecimal equipmentQualityScore = BigDecimal.ZERO;

	@Column(name = "mail_rejected", nullable = false)
	private Integer mailRejected = 0;

	@Column(name = "provisional_rejected", nullable = false)
	private Integer provisionalRejected = 0;

	@Column(name = "uocava_rejected", nullable = false)
	private Integer uocavaRejected = 0;

	@Column(name = "total_rejected_ballots", nullable = false)
	private Integer totalRejectedBallots = 0;

	@Column(name = "absentee_ballots_counted", nullable = false)
	private Integer absenteeBallotsCounted = 0;

	@Column(name = "early_in_person_ballots", nullable = false)
	private Integer earlyInPersonBallots = 0;

	@Column(name = "election_day_ballots", nullable = false)
	private Integer electionDayBallots = 0;

	@Column(name = "provisional_ballots_counted", nullable = false)
	private Integer provisionalBallotsCounted = 0;

	@Column(name = "total_ballots", nullable = false)
	private Integer totalBallots = 0;

	@Column(name = "percent_rejected_ballots")
	private Double percentRejectedBallots;

	public EquipmentQualityRejectionByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public BigDecimal getEquipmentQualityScore() {
		return equipmentQualityScore;
	}

	public void setEquipmentQualityScore(BigDecimal equipmentQualityScore) {
		this.equipmentQualityScore = equipmentQualityScore;
	}

	public Integer getMailRejected() {
		return mailRejected;
	}

	public void setMailRejected(Integer mailRejected) {
		this.mailRejected = mailRejected;
	}

	public Integer getProvisionalRejected() {
		return provisionalRejected;
	}

	public void setProvisionalRejected(Integer provisionalRejected) {
		this.provisionalRejected = provisionalRejected;
	}

	public Integer getUocavaRejected() {
		return uocavaRejected;
	}

	public void setUocavaRejected(Integer uocavaRejected) {
		this.uocavaRejected = uocavaRejected;
	}

	public Integer getTotalRejectedBallots() {
		return totalRejectedBallots;
	}

	public void setTotalRejectedBallots(Integer totalRejectedBallots) {
		this.totalRejectedBallots = totalRejectedBallots;
	}

	public Integer getAbsenteeBallotsCounted() {
		return absenteeBallotsCounted;
	}

	public void setAbsenteeBallotsCounted(Integer absenteeBallotsCounted) {
		this.absenteeBallotsCounted = absenteeBallotsCounted;
	}

	public Integer getEarlyInPersonBallots() {
		return earlyInPersonBallots;
	}

	public void setEarlyInPersonBallots(Integer earlyInPersonBallots) {
		this.earlyInPersonBallots = earlyInPersonBallots;
	}

	public Integer getElectionDayBallots() {
		return electionDayBallots;
	}

	public void setElectionDayBallots(Integer electionDayBallots) {
		this.electionDayBallots = electionDayBallots;
	}

	public Integer getProvisionalBallotsCounted() {
		return provisionalBallotsCounted;
	}

	public void setProvisionalBallotsCounted(Integer provisionalBallotsCounted) {
		this.provisionalBallotsCounted = provisionalBallotsCounted;
	}

	public Integer getTotalBallots() {
		return totalBallots;
	}

	public void setTotalBallots(Integer totalBallots) {
		this.totalBallots = totalBallots;
	}

	public Double getPercentRejectedBallots() {
		return percentRejectedBallots;
	}

	public void setPercentRejectedBallots(Double percentRejectedBallots) {
		this.percentRejectedBallots = percentRejectedBallots;
	}
}


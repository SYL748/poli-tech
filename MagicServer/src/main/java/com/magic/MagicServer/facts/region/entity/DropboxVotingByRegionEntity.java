package com.magic.MagicServer.facts.region.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for dropbox_voting_by_region table (GUI-24)
 * Stores drop box voting metrics by EAVS geographic region for bubble chart visualization
 */
@Entity
@Table(name = "dropbox_voting_by_region")
public class DropboxVotingByRegionEntity {

	@Id
	@Column(name = "region_id", nullable = false)
	private Integer regionId;

	@Column(name = "republican_votes_2024", nullable = false)
	private Integer republicanVotes2024 = 0;

	@Column(name = "democrat_votes_2024", nullable = false)
	private Integer democratVotes2024 = 0;

	@Column(name = "total_presidential_votes_2024", nullable = false)
	private Integer totalPresidentialVotes2024 = 0;

	@Column(name = "mail_in_ballots", nullable = false)
	private Integer mailInBallots = 0;

	@Column(name = "total_ballots", nullable = false)
	private Integer totalBallots = 0;

	@Column(name = "percent_republican")
	private Double percentRepublican;

	@Column(name = "percent_mail_in_ballots")
	private Double percentMailInBallots;

	public DropboxVotingByRegionEntity() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getRepublicanVotes2024() {
		return republicanVotes2024;
	}

	public void setRepublicanVotes2024(Integer republicanVotes2024) {
		this.republicanVotes2024 = republicanVotes2024;
	}

	public Integer getDemocratVotes2024() {
		return democratVotes2024;
	}

	public void setDemocratVotes2024(Integer democratVotes2024) {
		this.democratVotes2024 = democratVotes2024;
	}

	public Integer getTotalPresidentialVotes2024() {
		return totalPresidentialVotes2024;
	}

	public void setTotalPresidentialVotes2024(Integer totalPresidentialVotes2024) {
		this.totalPresidentialVotes2024 = totalPresidentialVotes2024;
	}

	public Integer getMailInBallots() {
		return mailInBallots;
	}

	public void setMailInBallots(Integer mailInBallots) {
		this.mailInBallots = mailInBallots;
	}

	public Integer getTotalBallots() {
		return totalBallots;
	}

	public void setTotalBallots(Integer totalBallots) {
		this.totalBallots = totalBallots;
	}

	public Double getPercentRepublican() {
		return percentRepublican;
	}

	public void setPercentRepublican(Double percentRepublican) {
		this.percentRepublican = percentRepublican;
	}

	public Double getPercentMailInBallots() {
		return percentMailInBallots;
	}

	public void setPercentMailInBallots(Double percentMailInBallots) {
		this.percentMailInBallots = percentMailInBallots;
	}
}


package com.magic.MagicServer.facts.region.dto;

/**
 * DTO for GUI-24: Drop box voting bubble chart data by region
 * Used for displaying bubble chart showing relationship between Republican votes
 * and drop box voting percentages for EAVS geographic units within a state.
 */
public class DropboxVotingByRegionDto {

	private Integer regionId;
	private String regionName;
	private Integer republicanVotes2024;
	private Integer democratVotes2024;
	private Integer totalPresidentialVotes2024;
	private Integer mailInBallots;
	private Integer totalBallots;
	private Double percentRepublican;
	private Double percentMailInBallots;

	public DropboxVotingByRegionDto() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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


package com.magic.MagicServer.facts.precinct.dto;

/**
 * DTO for GUI-27: Gingles Chart precinct data
 * Used for displaying Gingles chart visualization showing racially polarized voting patterns.
 * Each row represents one party's results for a precinct (multiple rows per precinct).
 * Matches the structure expected by the frontend from Texas_Precinct_Results.json
 */
public class GinglesChartPrecinctDto {

	private String county;
	private String countyFips;
	private String precinctName;
	private String precinctId;
	private Integer totalVotes;
	private Double whitePct;
	private Double hispanicPct;
	private Double africanAmericanPct;
	private String party;
	private Double votePercent;
	private Integer votes;

	public GinglesChartPrecinctDto() {
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountyFips() {
		return countyFips;
	}

	public void setCountyFips(String countyFips) {
		this.countyFips = countyFips;
	}

	public String getPrecinctName() {
		return precinctName;
	}

	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}

	public String getPrecinctId() {
		return precinctId;
	}

	public void setPrecinctId(String precinctId) {
		this.precinctId = precinctId;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Double getWhitePct() {
		return whitePct;
	}

	public void setWhitePct(Double whitePct) {
		this.whitePct = whitePct;
	}

	public Double getHispanicPct() {
		return hispanicPct;
	}

	public void setHispanicPct(Double hispanicPct) {
		this.hispanicPct = hispanicPct;
	}

	public Double getAfricanAmericanPct() {
		return africanAmericanPct;
	}

	public void setAfricanAmericanPct(Double africanAmericanPct) {
		this.africanAmericanPct = africanAmericanPct;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Double getVotePercent() {
		return votePercent;
	}

	public void setVotePercent(Double votePercent) {
		this.votePercent = votePercent;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
}


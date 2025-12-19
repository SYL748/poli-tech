package com.magic.MagicServer.facts.precinct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for gingles_chart_precinct_data table (GUI-27)
 * Stores demographic and voting data by precinct and party for Gingles chart visualization
 * Used to analyze racially polarized voting patterns in Texas (state_id=48)
 * Multiple rows per precinct (one for each political party)
 */
@Entity
@Table(name = "gingles_chart_precinct_data")
public class GinglesChartPrecinctEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "county_fips", nullable = false)
	private Integer countyFips;

	@Column(name = "precinct_name", length = 255)
	private String precinctName;

	@Column(name = "precinct_id", length = 255)
	private String precinctId;

	@Column(name = "total_votes")
	private Integer totalVotes;

	@Column(name = "white_pct")
	private Double whitePct;

	@Column(name = "hispanic_pct")
	private Double hispanicPct;

	@Column(name = "african_american_pct")
	private Double africanAmericanPct;

	@Column(name = "party", length = 32)
	private String party;

	@Column(name = "vote_percent")
	private Double votePercent;

	@Column(name = "votes")
	private Integer votes;

	public GinglesChartPrecinctEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCountyFips() {
		return countyFips;
	}

	public void setCountyFips(Integer countyFips) {
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


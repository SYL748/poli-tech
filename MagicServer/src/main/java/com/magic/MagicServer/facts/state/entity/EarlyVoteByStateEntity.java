package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity for early_vote_by_state table (GUI-23)
 * Stores early voting counts by state with all totals and percentages.
 */
@Entity
@Table(name = "early_vote_by_state")
public class EarlyVoteByStateEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Column(name = "total_votes_cast", nullable = false)
	private Integer totalVotesCast = 0;

	@Column(name = "uocava_accepted", nullable = false)
	private Integer uocavaAccepted = 0;

	@Column(name = "mail_accepted", nullable = false)
	private Integer mailAccepted = 0;

	@Column(name = "in_person_early", nullable = false)
	private Integer inPersonEarly = 0;

	@Column(name = "provisional_counted", nullable = false)
	private Integer provisionalCounted = 0;

	@Column(name = "dropbox_accepted", nullable = false)
	private Integer dropboxAccepted = 0;

	@Column(name = "total_early", nullable = false)
	private Integer totalEarly = 0;

	@Column(name = "percent_in_person_early")
	private Double percentInPersonEarly;

	@Column(name = "percent_mail")
	private Double percentMail;

	@Column(name = "percent_dropbox")
	private Double percentDropbox;

	@Column(name = "percent_uocava")
	private Double percentUocava;

	@Column(name = "percent_provisional")
	private Double percentProvisional;

	@Column(name = "percent_total_early")
	private Double percentTotalEarly;

	@Column(name = "mail_votes_in_vbm_jurisdiction", nullable = false)
	private Integer mailVotesInVbmJurisdiction = 0;

	public EarlyVoteByStateEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getTotalVotesCast() {
		return totalVotesCast;
	}

	public void setTotalVotesCast(Integer totalVotesCast) {
		this.totalVotesCast = totalVotesCast;
	}

	public Integer getUocavaAccepted() {
		return uocavaAccepted;
	}

	public void setUocavaAccepted(Integer uocavaAccepted) {
		this.uocavaAccepted = uocavaAccepted;
	}

	public Integer getMailAccepted() {
		return mailAccepted;
	}

	public void setMailAccepted(Integer mailAccepted) {
		this.mailAccepted = mailAccepted;
	}

	public Integer getInPersonEarly() {
		return inPersonEarly;
	}

	public void setInPersonEarly(Integer inPersonEarly) {
		this.inPersonEarly = inPersonEarly;
	}

	public Integer getProvisionalCounted() {
		return provisionalCounted;
	}

	public void setProvisionalCounted(Integer provisionalCounted) {
		this.provisionalCounted = provisionalCounted;
	}

	public Integer getDropboxAccepted() {
		return dropboxAccepted;
	}

	public void setDropboxAccepted(Integer dropboxAccepted) {
		this.dropboxAccepted = dropboxAccepted;
	}

	public Integer getTotalEarly() {
		return totalEarly;
	}

	public void setTotalEarly(Integer totalEarly) {
		this.totalEarly = totalEarly;
	}

	public Double getPercentInPersonEarly() {
		return percentInPersonEarly;
	}

	public void setPercentInPersonEarly(Double percentInPersonEarly) {
		this.percentInPersonEarly = percentInPersonEarly;
	}

	public Double getPercentMail() {
		return percentMail;
	}

	public void setPercentMail(Double percentMail) {
		this.percentMail = percentMail;
	}

	public Double getPercentDropbox() {
		return percentDropbox;
	}

	public void setPercentDropbox(Double percentDropbox) {
		this.percentDropbox = percentDropbox;
	}

	public Double getPercentUocava() {
		return percentUocava;
	}

	public void setPercentUocava(Double percentUocava) {
		this.percentUocava = percentUocava;
	}

	public Double getPercentProvisional() {
		return percentProvisional;
	}

	public void setPercentProvisional(Double percentProvisional) {
		this.percentProvisional = percentProvisional;
	}

	public Double getPercentTotalEarly() {
		return percentTotalEarly;
	}

	public void setPercentTotalEarly(Double percentTotalEarly) {
		this.percentTotalEarly = percentTotalEarly;
	}

	public Integer getMailVotesInVbmJurisdiction() {
		return mailVotesInVbmJurisdiction;
	}

	public void setMailVotesInVbmJurisdiction(Integer mailVotesInVbmJurisdiction) {
		this.mailVotesInVbmJurisdiction = mailVotesInVbmJurisdiction;
	}
}


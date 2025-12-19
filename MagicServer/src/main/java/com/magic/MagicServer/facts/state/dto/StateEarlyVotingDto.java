package com.magic.MagicServer.facts.state.dto;

/**
 * DTO for GUI-23: State early voting comparison data (Iowa and Illinois)
 * Used for comparing early voting patterns between Republican and Democratic states.
 * All percentages are calculated on-the-fly in the service layer.
 */
public class StateEarlyVotingDto {

	private String stateId;
	private Integer totalVotesCast;
	private Integer inPersonEarly;
	private Integer mailAccepted;
	private Integer dropboxAccepted;
	private Integer uocavaAccepted;
	private Integer provisionalCounted;
	private Integer totalEarly;
	private Double percentInPersonEarly;
	private Double percentMail;
	private Double percentDropbox;
	private Double percentUocava;
	private Double percentProvisional;
	private Double percentTotalEarly;

	public StateEarlyVotingDto() {
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public Integer getTotalVotesCast() {
		return totalVotesCast;
	}

	public void setTotalVotesCast(Integer totalVotesCast) {
		this.totalVotesCast = totalVotesCast;
	}

	public Integer getInPersonEarly() {
		return inPersonEarly;
	}

	public void setInPersonEarly(Integer inPersonEarly) {
		this.inPersonEarly = inPersonEarly;
	}

	public Integer getMailAccepted() {
		return mailAccepted;
	}

	public void setMailAccepted(Integer mailAccepted) {
		this.mailAccepted = mailAccepted;
	}

	public Integer getDropboxAccepted() {
		return dropboxAccepted;
	}

	public void setDropboxAccepted(Integer dropboxAccepted) {
		this.dropboxAccepted = dropboxAccepted;
	}

	public Integer getUocavaAccepted() {
		return uocavaAccepted;
	}

	public void setUocavaAccepted(Integer uocavaAccepted) {
		this.uocavaAccepted = uocavaAccepted;
	}

	public Integer getProvisionalCounted() {
		return provisionalCounted;
	}

	public void setProvisionalCounted(Integer provisionalCounted) {
		this.provisionalCounted = provisionalCounted;
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
}


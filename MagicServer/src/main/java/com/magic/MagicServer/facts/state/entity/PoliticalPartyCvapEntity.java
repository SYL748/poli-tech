package com.magic.MagicServer.facts.state.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "political_party_cvap_by_state")
@IdClass(PoliticalPartyCvapEntity.PoliticalPartyCvapId.class)
public class PoliticalPartyCvapEntity {

	@Id
	@Column(name = "state_id", nullable = false)
	private Integer stateId;

	@Id
	@Column(name = "name", nullable = false, length = 128)
	private String name;

	@Column(name = "cvap_pct")
	private Double cvapPct;

	public PoliticalPartyCvapEntity() {
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCvapPct() {
		return cvapPct;
	}

	public void setCvapPct(Double cvapPct) {
		this.cvapPct = cvapPct;
	}

	// Composite key class
	public static class PoliticalPartyCvapId implements Serializable {
		private Integer stateId;
		private String name;

		public PoliticalPartyCvapId() {
		}

		public PoliticalPartyCvapId(Integer stateId, String name) {
			this.stateId = stateId;
			this.name = name;
		}

		public Integer getStateId() {
			return stateId;
		}

		public void setStateId(Integer stateId) {
			this.stateId = stateId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			PoliticalPartyCvapId that = (PoliticalPartyCvapId) o;
			return stateId.equals(that.stateId) && name.equals(that.name);
		}

		@Override
		public int hashCode() {
			return 31 * stateId.hashCode() + name.hashCode();
		}
	}
}


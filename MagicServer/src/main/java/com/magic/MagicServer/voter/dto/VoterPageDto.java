package com.magic.MagicServer.voter.dto;

import java.util.List;

/**
 * DTO for paginated voter list response
 * Contains list of voters and pagination metadata
 * 
 * Page size is fixed at 7 voters per page
 */
public class VoterPageDto {
	private List<VoterDto> voters;
	private Integer currentPage;
	private Integer totalPages;
	private Long totalElements;
	private Boolean hasNext;
	private Boolean hasPrevious;
	private Integer pageSize;

	public VoterPageDto() {
	}

	public List<VoterDto> getVoters() {
		return voters;
	}

	public void setVoters(List<VoterDto> voters) {
		this.voters = voters;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}


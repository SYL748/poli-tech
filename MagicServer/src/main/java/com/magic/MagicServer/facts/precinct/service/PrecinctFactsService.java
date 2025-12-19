package com.magic.MagicServer.facts.precinct.service;

import com.magic.MagicServer.facts.precinct.dto.GinglesChartPrecinctDto;
import com.magic.MagicServer.facts.precinct.repository.GinglesChartPrecinctRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for precinct-level facts data
 * Handles business logic for precinct metrics and analytics
 */
@Service
public class PrecinctFactsService {

	private final GinglesChartPrecinctRepository ginglesChartPrecinctRepository;

	public PrecinctFactsService(GinglesChartPrecinctRepository ginglesChartPrecinctRepository) {
		this.ginglesChartPrecinctRepository = ginglesChartPrecinctRepository;
	}

	/**
	 * Get Gingles chart data for all precincts in a state (GUI-27)
	 * Used for displaying racially polarized voting visualization
	 * Note: Frontend is built for Texas (state_id=48) only
	 * 
	 * @param stateId The state identifier (should be Texas, state_id=48)
	 * @return List of GinglesChartPrecinctDto with demographic and voting data
	 */
	public List<GinglesChartPrecinctDto> getGinglesChartData(Integer stateId) {
		return ginglesChartPrecinctRepository.findByStateIdWithCountyName(stateId).stream()
				.map(this::toGinglesChartPrecinctDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map Object[] from native JOIN query to GinglesChartPrecinctDto (GUI-27)
	 * 
	 * Query result order (from repository JOIN):
	 * [0] county.name (String)
	 * [1] g.countyFips (Integer)
	 * [2] g.precinctName (String)
	 * [3] g.precinctId (String)
	 * [4] g.totalVotes (Integer)
	 * [5] g.whitePct (Double)
	 * [6] g.hispanicPct (Double)
	 * [7] g.africanAmericanPct (Double)
	 * [8] g.party (String)
	 * [9] g.votePercent (Double)
	 * [10] g.votes (Integer)
	 */
	private GinglesChartPrecinctDto toGinglesChartPrecinctDto(Object[] row) {
		GinglesChartPrecinctDto dto = new GinglesChartPrecinctDto();
		
		dto.setCounty((String) row[0]);
		dto.setCountyFips(row[1] != null ? row[1].toString() : null);
		dto.setPrecinctName((String) row[2]);
		dto.setPrecinctId((String) row[3]);
		dto.setTotalVotes((Integer) row[4]);
		dto.setWhitePct((Double) row[5]);
		dto.setHispanicPct((Double) row[6]);
		dto.setAfricanAmericanPct((Double) row[7]);
		dto.setParty((String) row[8]);
		dto.setVotePercent((Double) row[9]);
		dto.setVotes((Integer) row[10]);
		
		return dto;
	}
}


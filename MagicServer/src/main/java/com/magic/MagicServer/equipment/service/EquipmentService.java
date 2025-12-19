package com.magic.MagicServer.equipment.service;

import com.magic.MagicServer.equipment.dto.VotingEquipmentOverviewDto;
import com.magic.MagicServer.equipment.entity.VotingEquipmentOverviewEntity;
import com.magic.MagicServer.equipment.repository.VotingEquipmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for equipment-related data
 * Handles business logic for voting equipment analytics and overview
 * Matches: 13_Voting_Equipment_Overview.csv
 */
@Service
public class EquipmentService {

	private final VotingEquipmentRepository votingEquipmentRepository;

	public EquipmentService(VotingEquipmentRepository votingEquipmentRepository) {
		this.votingEquipmentRepository = votingEquipmentRepository;
	}

	/**
	 * Get national voting equipment overview (GUI-13)
	 * Returns equipment data from voting_equipment table
	 * Ordered by equipment provider (make) with sub-ordering by model
	 * 
	 * @return List of VotingEquipmentOverviewDto with equipment details
	 */
	public List<VotingEquipmentOverviewDto> getVotingEquipmentOverview() {
		return votingEquipmentRepository.findAllByOrderByMakeAscModelAsc().stream()
				.map(this::toVotingEquipmentOverviewDto)
				.collect(Collectors.toList());
	}

	/**
	 * Map VotingEquipmentOverviewEntity to VotingEquipmentOverviewDto (GUI-13)
	 * Converts database entity to frontend-compatible DTO format
	 * 
	 * @param entity VotingEquipmentOverviewEntity from database
	 * @return VotingEquipmentOverviewDto for frontend
	 */
	private VotingEquipmentOverviewDto toVotingEquipmentOverviewDto(VotingEquipmentOverviewEntity entity) {
		VotingEquipmentOverviewDto dto = new VotingEquipmentOverviewDto();
		
		// Set equipment details from voting_equipment table
		dto.setMake(entity.getMake());
		dto.setType(entity.getType());
		dto.setModel(entity.getModel());
		
		// Convert BigDecimal age to Double
		dto.setAge(entity.getAge() != null ? entity.getAge().doubleValue() : null);
		
		dto.setOperatingSystem(entity.getOperatingSystem());
		dto.setCert(entity.getCert());
		
		// Scan rate and error rate are already strings in the entity
		dto.setScanRate(entity.getScanRate());
		dto.setErrorRate(entity.getErrorRate());
		
		// Convert BigDecimal to Double for metrics
		dto.setReliability(entity.getReliability() != null ? entity.getReliability().doubleValue() : null);
		dto.setQuality(entity.getQuality() != null ? entity.getQuality().doubleValue() : null);
		
		dto.setDiscontinued(entity.getDiscontinued());
		dto.setQuantity(entity.getQuantity());
		
		return dto;
	}
}


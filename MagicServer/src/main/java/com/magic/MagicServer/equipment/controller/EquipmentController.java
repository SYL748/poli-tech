package com.magic.MagicServer.equipment.controller;

import com.magic.MagicServer.equipment.dto.VotingEquipmentOverviewDto;
import com.magic.MagicServer.equipment.service.EquipmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for equipment-related endpoints
 * Handles national voting equipment overview and analytics
 */
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

	private final EquipmentService equipmentService;

	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	/**
	 * GUI-13: Get voting equipment overview for all states (national level)
	 * 
	 * Endpoint: GET /api/equipment/overview
	 * 
	 * Returns a table of equipment used in 2024 across all states. The table is row-ordered
	 * by equipment provider (make) with sub-ordering by model. Columns include:
	 * - Equipment details (make, model, quantity)
	 * - Age and specifications (average age, underlying OS, certification)
	 * - Performance metrics (scan rate, error rate, reliability)
	 * - Quality measure (composite quality score)
	 * - Availability status (discontinued flag)
	 * 
	 * This endpoint aggregates equipment data from all states to provide a national view
	 * of voting equipment inventory and quality.
	 * 
	 * Quality Measure (0-100 scale):
	 * - Calculated based on: age, underlying OS, certification, scan rate, error rate, reliability
	 * - Higher scores indicate better equipment (newer, certified, reliable)
	 * - Lower scores indicate outdated or problematic equipment
	 * 
	 * @return List of VotingEquipmentOverviewDto ordered by make and model
	 */
	@GetMapping("/overview")
	public List<VotingEquipmentOverviewDto> getVotingEquipmentOverview() {
		return equipmentService.getVotingEquipmentOverview();
	}
}


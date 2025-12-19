package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.EquipmentEiCurvePointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for equipment ecological inference curve point data
 * Handles queries for GUI-28: Equipment Quality EI KDE density visualization
 */
public interface EquipmentEiCurvePointRepository extends JpaRepository<EquipmentEiCurvePointEntity, Long> {

	/**
	 * Find all EI curve data points for a specific state
	 * Returns all demographic groups and their associated curve data
	 * Used to generate the complete EI KDE density visualization
	 * 
	 * @param stateId The state identifier
	 * @return List of all curve point entities for the state
	 */
	List<EquipmentEiCurvePointEntity> findByStateId(Integer stateId);

	/**
	 * Find EI curve data points for a specific state and demographic group
	 * Useful for filtering visualization by specific demographic
	 * 
	 * @param stateId The state identifier
	 * @param demographic The demographic group name (e.g., "White", "Hispanic", "African American")
	 * @return List of curve point entities for the state and demographic
	 */
	List<EquipmentEiCurvePointEntity> findByStateIdAndDemographic(Integer stateId, String demographic);
}


package com.magic.MagicServer.equipment.repository;

import com.magic.MagicServer.equipment.entity.VotingEquipmentOverviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for voting_equipment table
 * Provides queries for equipment specifications and details
 * Matches: 13_Voting_Equipment_Overview.csv
 */
@Repository
public interface VotingEquipmentRepository extends JpaRepository<VotingEquipmentOverviewEntity, Long> {

	/**
	 * Find all equipment records ordered by make and model
	 * Used for GUI-13: Display of US voting equipment summary
	 * 
	 * @return List of VotingEquipmentOverviewEntity ordered by make and model
	 */
	List<VotingEquipmentOverviewEntity> findAllByOrderByMakeAscModelAsc();
}


package com.magic.MagicServer.jurisdiction.repository;

import com.magic.MagicServer.jurisdiction.entity.EavsRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for EAVS region dimensional data
 * Handles queries for EAVS region entities
 */
public interface EavsRegionRepository extends JpaRepository<EavsRegionEntity, Integer> {

	/**
	 * Find an EAVS region by its region_id
	 * @param regionId The region identifier
	 * @return Optional containing the EAVS region if found
	 */
	Optional<EavsRegionEntity> findByRegionId(Integer regionId);

	/**
	 * Find all EAVS regions for a specific state
	 * @param stateId The state identifier
	 * @return List of EAVS regions in the state
	 */
	List<EavsRegionEntity> findByStateId(Integer stateId);

	/**
	 * Find an EAVS region by its name
	 * @param name The region name (county name)
	 * @return Optional containing the EAVS region if found
	 */
	Optional<EavsRegionEntity> findByName(String name);
}


package com.magic.MagicServer.geo.repository;

import com.magic.MagicServer.geo.entity.StateCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateCenterRepository extends JpaRepository<StateCenterEntity, Integer> {

	Optional<StateCenterEntity> findByStateId(Integer stateId);
}


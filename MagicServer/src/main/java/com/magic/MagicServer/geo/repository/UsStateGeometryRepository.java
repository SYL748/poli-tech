package com.magic.MagicServer.geo.repository;

import com.magic.MagicServer.geo.entity.UsStateGeometryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsStateGeometryRepository extends JpaRepository<UsStateGeometryEntity, Integer> {

	Optional<UsStateGeometryEntity> findByStateId(Integer stateId);
}


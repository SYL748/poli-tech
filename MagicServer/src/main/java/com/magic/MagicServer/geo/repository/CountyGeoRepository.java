package com.magic.MagicServer.geo.repository;

import com.magic.MagicServer.geo.entity.CountyGeoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountyGeoRepository extends JpaRepository<CountyGeoEntity, Integer> {

	Optional<CountyGeoEntity> findByGeoId(Integer geoId);
}


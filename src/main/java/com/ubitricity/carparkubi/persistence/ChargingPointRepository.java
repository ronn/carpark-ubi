package com.ubitricity.carparkubi.persistence;

import com.ubitricity.carparkubi.persistence.model.ChargingPointDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingPointRepository extends JpaRepository<ChargingPointDTO, String> {
}
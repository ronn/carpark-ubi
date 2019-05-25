package com.ubitricity.carparkubi.service;

import com.ubitricity.carparkubi.api.model.ChargingPointRecord;
import com.ubitricity.carparkubi.domain.ChargingPoint;

import java.util.List;

public interface ChargingPointService {

    void save(ChargingPoint chargingPoint);

    List<ChargingPointRecord> getAll();
}
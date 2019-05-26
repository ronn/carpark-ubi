package com.ubitricity.carparkubi.service;

import com.ubitricity.carparkubi.api.model.ChargingPointRecord;
import com.ubitricity.carparkubi.domain.ChargingPoint;

import java.util.List;
import java.util.stream.Stream;

public interface ChargingPointService {

    void save(ChargingPoint chargingPoint);

    Stream<ChargingPoint> getChargingPoint();

    List<ChargingPointRecord> getAll();

    Integer getTotalUsedAmperes();

    void plug(String chargingPointId);
}
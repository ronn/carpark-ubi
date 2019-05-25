package com.ubitricity.carparkubi.api.model;

import java.util.List;

public class Report {

    private final List<ChargingPointRecord> chargingPoints;

    public Report(List<ChargingPointRecord> chargingPoints) {
        this.chargingPoints = chargingPoints;
    }

    public List<ChargingPointRecord> getChargingPoints() {
        return chargingPoints;
    }
}
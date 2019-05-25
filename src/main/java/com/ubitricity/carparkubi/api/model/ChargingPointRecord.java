package com.ubitricity.carparkubi.api.model;

import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingType;

public class ChargingPointRecord {

    private final String id;
    private final String state;
    private final Integer amperes;

    public ChargingPointRecord(String id, String state, Integer amperes) {
        this.id = id;
        this.state = state;
        this.amperes = amperes;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Integer getAmperes() {
        return amperes;
    }

    public static ChargingPointRecord from(ChargingPoint cp){
        return new ChargingPointRecord(
                cp.getId().name(),
                cp.getState().name(),
                cp.getChargingType().map(ChargingType::getAmperes).orElse(null)
        );
    }
}
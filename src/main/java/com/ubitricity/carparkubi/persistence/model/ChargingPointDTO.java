package com.ubitricity.carparkubi.persistence.model;

import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChargingPointDTO {

    @Id
    private String id;
    private String state;
    private String chargingType;

    public ChargingPointDTO() {
    }

    public ChargingPointDTO(String id, String state, String chargingType) {
        this.id = id;
        this.state = state;
        this.chargingType = chargingType;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getChargingType() {
        return chargingType;
    }

    public static ChargingPointDTO from(ChargingPoint cp){
        return new ChargingPointDTO(
                cp.getId().name(),
                cp.getState().name(),
                cp.getChargingType().map(ChargingType::getName).orElse(null)
        );
    }
}
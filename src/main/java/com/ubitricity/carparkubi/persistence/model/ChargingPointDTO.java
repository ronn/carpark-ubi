package com.ubitricity.carparkubi.persistence.model;

import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ChargingPointDTO {

    @Id
    private String id;
    private String state;
    private String chargingType;
    private LocalDateTime plugTime;

    public ChargingPointDTO() {
    }

    private ChargingPointDTO(String id, String state, String chargingType, LocalDateTime plugTime) {
        this.id = id;
        this.state = state;
        this.chargingType = chargingType;
        this.plugTime = plugTime;
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

    public LocalDateTime getPlugTime() {
        return plugTime;
    }

    public static ChargingPointDTO from(ChargingPoint cp){
        return new ChargingPointDTO(
                cp.getId().name(),
                cp.getState().name(),
                cp.getChargingType().map(ChargingType::getName).orElse(null),
                cp.getPlugTime().orElse(null)
        );
    }
}
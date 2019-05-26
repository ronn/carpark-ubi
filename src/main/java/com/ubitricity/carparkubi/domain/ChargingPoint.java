package com.ubitricity.carparkubi.domain;

import com.ubitricity.carparkubi.persistence.model.ChargingPointDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public class ChargingPoint {

    private final ChargingPointId id;
    private final State state;
    private final Optional<ChargingType> chargingType;
    private final Optional<LocalDateTime> plugTime;

    private ChargingPoint(ChargingPointId id, State state, Optional<ChargingType> chargingType, Optional<LocalDateTime> plugTime) {
        this.id = id;
        this.state = state;
        this.chargingType = chargingType;
        this.plugTime = plugTime;
    }

    public ChargingPoint(ChargingPointId id) {
        this.id = id;
        this.state = State.AVAILABLE;
        this.chargingType = Optional.empty();
        this.plugTime = Optional.empty();
    }

    public ChargingPointId getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Optional<ChargingType> getChargingType() {
        return chargingType;
    }

    public Optional<LocalDateTime> getPlugTime() {
        return plugTime;
    }

    public ChargingPoint withState (State state){
        return new ChargingPoint(
                this.id,
                state,
                this.chargingType,
                this.plugTime);
    }

    public ChargingPoint withChargingType (ChargingType chargingType){
        return new ChargingPoint(
                this.id,
                this.state,
                Optional.ofNullable(chargingType),
                this.plugTime);
    }

    public ChargingPoint withPlugTime (LocalDateTime plugTime){
        return new ChargingPoint(
                this.id,
                this.state,
                this.chargingType,
                Optional.ofNullable(plugTime)
        );
    }

    public static ChargingPoint from(ChargingPointDTO dto){
        return new ChargingPoint(
                ChargingPointId.valueOf(dto.getId()),
                State.valueOf(dto.getState()),
                ChargingType.getChargingType(dto.getChargingType()),
                Optional.ofNullable(dto.getPlugTime())
        );
    }
}
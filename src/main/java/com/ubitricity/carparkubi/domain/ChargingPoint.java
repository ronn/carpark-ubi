package com.ubitricity.carparkubi.domain;

import com.ubitricity.carparkubi.api.model.ChargingPointRecord;
import com.ubitricity.carparkubi.persistence.model.ChargingPointDTO;

import java.util.Optional;

public class ChargingPoint {

    private final ChargingPointId id;
    private final State state;
    private final Optional<ChargingType> chargingType;

    private ChargingPoint(ChargingPointId id, State state, Optional<ChargingType> chargingType) {
        this.id = id;
        this.state = state;
        this.chargingType = chargingType;
    }

    public ChargingPoint(ChargingPointId id) {
        this.id = id;
        this.state = State.AVAILABLE;
        this.chargingType = Optional.empty();
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

    public ChargingPoint withState (State state){
        return new ChargingPoint(
                this.id,
                state,
                this.chargingType
        );
    }

    public ChargingPoint withChargingType (ChargingType chargingType){
        return new ChargingPoint(
                this.id,
                this.state,
                Optional.ofNullable(chargingType)
        );
    }

    public static ChargingPoint from(ChargingPointDTO dto){
        return new ChargingPoint(
                ChargingPointId.valueOf(dto.getId()),
                State.valueOf(dto.getState()),
                ChargingType.getChargingType(dto.getChargingType())
        );
    }

    public static ChargingPoint from(ChargingPointRecord record){
        return new ChargingPoint(
                ChargingPointId.valueOf(record.getId()),
                State.valueOf(record.getState()),
                ChargingType.getChargingType(record.getAmperes())
        );
    }
}
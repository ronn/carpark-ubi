package com.ubitricity.carparkubi.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum ChargingType {

    SLOW_CHARGING("Slow charging", 10),
    FAST_CHARGING("Fast charging", 20);

    private final String name;
    private final Integer amperes;

    ChargingType(String name, Integer amperes) {
        this.name = name;
        this.amperes = amperes;
    }

    public static Optional<ChargingType> getChargingType(String name){
        return getFirst(chargingType -> chargingType.name.equalsIgnoreCase(name));
    }

    public static Optional<ChargingType> getChargingType(Integer amperes){
        return getFirst(chargingType -> chargingType.amperes.equals(amperes));
    }

    private static Optional<ChargingType> getFirst(Predicate<ChargingType> chargingTypePredicate){
        return Arrays.stream(ChargingType.values())
                .filter(chargingTypePredicate)
                .findFirst();
    }

    public String getName() {
        return name;
    }

    public Integer getAmperes() {
        return amperes;
    }
}
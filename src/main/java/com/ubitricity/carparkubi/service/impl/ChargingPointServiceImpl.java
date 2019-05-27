package com.ubitricity.carparkubi.service.impl;

import com.ubitricity.carparkubi.api.model.ChargingPointRecord;
import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingType;
import com.ubitricity.carparkubi.domain.State;
import com.ubitricity.carparkubi.persistence.ChargingPointRepository;
import com.ubitricity.carparkubi.persistence.model.ChargingPointDTO;
import com.ubitricity.carparkubi.service.ChargingPointService;
import com.ubitricity.carparkubi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class ChargingPointServiceImpl implements ChargingPointService {

    private final ChargingPointRepository chargingPointRepo;

    @Autowired
    public ChargingPointServiceImpl(ChargingPointRepository chargingPointRepo) {
        this.chargingPointRepo = chargingPointRepo;
    }

    @Override
    public void save(ChargingPoint chargingPoint) {
        chargingPointRepo.save(ChargingPointDTO.from(chargingPoint));
    }

    @Override
    public List<ChargingPointRecord> getAll() {
        return getChargingPoint()
                .map(ChargingPointRecord::from)
                .collect(toList());
    }

    @Override
    public Stream<ChargingPoint> getChargingPoint() {
        return chargingPointRepo.findAll()
                .stream()
                .map(ChargingPoint::from);
    }

    private boolean allPointsWillBeInUse() {
        return getChargingPoint()
                .filter(chargingPoint -> chargingPoint.getState().equals(State.OCCUPIED))
                .count() >= 9;
    }

    @Override
    public void plug(String chargingPointId) {
        chargingPointRepo
                .findById(chargingPointId)
                .map(ChargingPoint::from)
                .ifPresent(this::manageNewPlug);
    }

    private void manageNewPlug(ChargingPoint chargingPoint) {
        ChargingPoint cp = disconectIfNecessary(chargingPoint);

        boolean distributingWasNeeded = distributeAmperes();

        save(cp.withState(State.OCCUPIED)
                .withChargingType(getPropperChargingType())
                .withPlugTime(LocalDateTime.now())
        );

        distributeIfNotFull(distributingWasNeeded);
    }

    private ChargingType getPropperChargingType() {
        return allPointsWillBeInUse()
                ? ChargingType.SLOW_CHARGING
                : ChargingType.FAST_CHARGING;
    }

    private boolean distributeAmperes() {
        if (getTotalUsedAmperes() > Constants.MAXIMUM_AMPERES_WITHOUT_DISTRIBUTING ){
            getChargingPoint()
                    .filter(cp -> cp.getChargingType().orElse(ChargingType.SLOW_CHARGING).equals(ChargingType.FAST_CHARGING))
                    .sorted(Comparator.comparing(a -> a.getPlugTime().orElse(LocalDateTime.now())))
                    .limit(Constants.POINTS_TO_DECREASE)
                    .forEach(cp -> chargingPointRepo.save(ChargingPointDTO.from(cp.withChargingType(ChargingType.SLOW_CHARGING))));

            return true;
        }

        return false;
    }

    private void distributeIfNotFull(boolean distributingWasNeeded) {
        if (distributingWasNeeded && getTotalUsedAmperes() < Constants.OVERALL_CURRENT_INPUT){
            getChargingPoint()
                    .filter(cp -> ChargingType.SLOW_CHARGING.equals(cp.getChargingType().orElse(ChargingType.FAST_CHARGING)))
                    .max(Comparator.comparing(a -> a.getPlugTime().orElse(LocalDateTime.now())))
                    .map(cp -> cp.withChargingType(ChargingType.FAST_CHARGING))
                    .ifPresent(this::save);
        }
    }

    private ChargingPoint disconectIfNecessary(ChargingPoint chargingPoint) {
        return chargingPoint.getState().equals(State.OCCUPIED)
                ? ChargingPoint.from(chargingPointRepo.save(ChargingPointDTO.from(
                        chargingPoint.withPlugTime(null)
                                .withChargingType(null)
                                .withState(State.AVAILABLE)
                )))
                : chargingPoint;
    }

    public Integer getTotalUsedAmperes() {
        return getChargingPoint()
                .mapToInt(x -> x.getChargingType()
                        .map(ChargingType::getAmperes)
                        .orElse(0)
                ).sum();
    }
}
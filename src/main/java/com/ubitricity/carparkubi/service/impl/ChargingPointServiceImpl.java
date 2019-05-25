package com.ubitricity.carparkubi.service.impl;

import com.ubitricity.carparkubi.api.model.ChargingPointRecord;
import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.persistence.ChargingPointRepository;
import com.ubitricity.carparkubi.persistence.model.ChargingPointDTO;
import com.ubitricity.carparkubi.service.ChargingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return chargingPointRepo.findAll()
                .stream()
                .map(ChargingPoint::from)
                .map(ChargingPointRecord::from)
                .collect(toList());
    }
}
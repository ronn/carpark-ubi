package com.ubitricity.carparkubi.api;

import com.ubitricity.carparkubi.api.model.ChargingPointRecord;
import com.ubitricity.carparkubi.api.model.Report;
import com.ubitricity.carparkubi.service.ChargingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarparkUbiController {

    private final ChargingPointService cpService;

    @Autowired
    public CarparkUbiController(ChargingPointService cpService) {
        this.cpService = cpService;
    }

    @RequestMapping("/report")
    public Report getReport(){
        return new Report(cpService.getAll());
    }

    @RequestMapping("/plug")
    public Report plug(ChargingPointRecord record){
        return new Report(cpService.getAll());
    }
}
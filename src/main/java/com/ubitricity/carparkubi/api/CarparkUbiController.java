package com.ubitricity.carparkubi.api;

import com.ubitricity.carparkubi.api.model.Report;
import com.ubitricity.carparkubi.service.ChargingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarparkUbiController {

    private final ChargingPointService service;

    @Autowired
    public CarparkUbiController(ChargingPointService service) {
        this.service = service;
    }

    @RequestMapping("/report")
    public ResponseEntity<Report> getReport(){
        return ResponseEntity.ok(new Report(service.getAll()));
    }

    @PutMapping("/plug/{cpId}")
    public ResponseEntity<Report> plug(@PathVariable String cpId){
        return service.getChargingPoint()
                .filter(chargingPoint -> cpId.equals(chargingPoint.getId().name()))
                .findFirst()
                .map(chargingPoint -> {
                    service.plug(cpId);
                    return ResponseEntity.ok(new Report(service.getAll()));
                }).orElse(ResponseEntity.badRequest().build());
    }
}
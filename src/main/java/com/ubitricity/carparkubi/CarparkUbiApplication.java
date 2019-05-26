package com.ubitricity.carparkubi;

import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingPointId;
import com.ubitricity.carparkubi.service.ChargingPointService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CarparkUbiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarparkUbiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ChargingPointService cPService){
		return args -> Arrays.asList(
				new ChargingPoint(ChargingPointId.CP1),
				new ChargingPoint(ChargingPointId.CP2),
				new ChargingPoint(ChargingPointId.CP3),
				new ChargingPoint(ChargingPointId.CP4),
				new ChargingPoint(ChargingPointId.CP5),
				new ChargingPoint(ChargingPointId.CP6),
				new ChargingPoint(ChargingPointId.CP7),
				new ChargingPoint(ChargingPointId.CP8),
				new ChargingPoint(ChargingPointId.CP9),
				new ChargingPoint(ChargingPointId.CP10)
		).forEach(cPService::save);
	}
}
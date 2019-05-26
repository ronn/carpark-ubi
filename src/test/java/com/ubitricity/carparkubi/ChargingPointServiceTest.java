package com.ubitricity.carparkubi;

import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingPointId;
import com.ubitricity.carparkubi.service.ChargingPointService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ChargingPointServiceTest {

    @Mock
    private ChargingPointService service;

    @Before
    public void init(){
        service = Mockito.mock(ChargingPointService.class, Mockito.RETURNS_MOCKS);
        Mockito.when(service.getChargingPointStream())
                .thenReturn(Stream.of(
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
                ));
    }

    @Test
    public void getTotalUsedAmperes(){
        assertEquals(0, service.getTotalUsedAmperes().intValue());
    }
}
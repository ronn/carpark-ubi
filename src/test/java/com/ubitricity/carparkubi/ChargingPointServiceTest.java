package com.ubitricity.carparkubi;

import com.ubitricity.carparkubi.domain.ChargingPoint;
import com.ubitricity.carparkubi.domain.ChargingPointId;
import com.ubitricity.carparkubi.persistence.ChargingPointRepository;
import com.ubitricity.carparkubi.persistence.model.ChargingPointDTO;
import com.ubitricity.carparkubi.service.ChargingPointService;
import com.ubitricity.carparkubi.service.impl.ChargingPointServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ChargingPointServiceTest {

    private ChargingPointService service;

    @Mock
    private ChargingPointRepository repo;

    @Before
    public void init(){
        repo = Mockito.mock(ChargingPointRepository.class, Mockito.RETURNS_MOCKS);

        Mockito.when(repo.findAll())
                .thenReturn(Arrays.asList(
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP1)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP2)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP3)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP4)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP5)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP6)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP7)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP8)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP9)),
                        ChargingPointDTO.from(new ChargingPoint(ChargingPointId.CP10))
                ));

        service = new ChargingPointServiceImpl(repo);
    }

    @Test
    public void getTotalUsedAmperesTest(){
        assertEquals(0, service.getTotalUsedAmperes().intValue());
    }
}
package com.blandygbc.sdw24.application;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blandygbc.sdw24.domain.model.ChampionRec;

@SpringBootTest
public class ListChamptionsUseCaseTest {

    @Autowired
    private ListChampionsUseCase listChamptionsUseCase;

    @Test
    void testFindAll() {
        List<ChampionRec> champs = listChamptionsUseCase.findAll();
        Assertions.assertEquals(12, champs.size());
    }
}

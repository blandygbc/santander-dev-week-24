package com.blandygbc.sdw24.application;

import java.util.List;

import com.blandygbc.sdw24.domain.model.ChampionRec;
import com.blandygbc.sdw24.domain.ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository repository) {
    public List<ChampionRec> findAll() {
        return repository.findAll();
    }
}

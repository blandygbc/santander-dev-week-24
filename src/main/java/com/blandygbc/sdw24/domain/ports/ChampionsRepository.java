package com.blandygbc.sdw24.domain.ports;

import java.util.List;
import java.util.Optional;

import com.blandygbc.sdw24.domain.model.ChampionRec;

public interface ChampionsRepository {
    List<ChampionRec> findAll();

    Optional<ChampionRec> findById(Long id);
}

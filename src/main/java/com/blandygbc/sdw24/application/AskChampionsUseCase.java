package com.blandygbc.sdw24.application;

import com.blandygbc.sdw24.domain.exception.ChampionNotFoundException;
import com.blandygbc.sdw24.domain.model.ChampionRec;
import com.blandygbc.sdw24.domain.ports.ChampionsRepository;

public record AskChampionsUseCase(ChampionsRepository repository) {
    public String askChampion(Long championId, String question) {

        ChampionRec champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String championContext = champion.generateContextByQuestion(question);

        return championContext;
    }
}

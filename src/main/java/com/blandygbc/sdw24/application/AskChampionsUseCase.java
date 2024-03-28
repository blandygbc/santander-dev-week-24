package com.blandygbc.sdw24.application;

import com.blandygbc.sdw24.domain.exception.ChampionNotFoundException;
import com.blandygbc.sdw24.domain.model.ChampionRec;
import com.blandygbc.sdw24.domain.ports.ChampionsRepository;
import com.blandygbc.sdw24.domain.ports.GenerativeAiService;

public record AskChampionsUseCase(ChampionsRepository repository, GenerativeAiService generativeAiApi) {
    public String askChampion(Long championId, String question) {

        ChampionRec champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String championContext = champion.generateContextByQuestion(question);

        String objective = """
                Atue como um assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
                Responsa perguntas incorporando a personalidade e estilo de um determinado Campeão.
                Segue a pergunta, o nome do Campeão e sua respectiva lore (história):

                """;

        return generativeAiApi.generateContent(objective, championContext);
    }
}

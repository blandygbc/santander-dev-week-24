package com.blandygbc.sdw24.adapters.in;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.sdw24.application.ListChampionsUseCase;
import com.blandygbc.sdw24.domain.model.ChampionRec;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Campeões", description = "Endpoints do dominio de Campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping
    public List<ChampionRec> findAllChampions() {
        return useCase.findAll();
    }

}

package com.blandygbc.sdw24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blandygbc.sdw24.application.AskChampionsUseCase;
import com.blandygbc.sdw24.domain.ports.ChampionsRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public AskChampionsUseCase provideListChamptionsUseCase(ChampionsRepository repository) {
		return new AskChampionsUseCase(repository);
	}

	@Bean
	public AskChampionsUseCase provideAskChampionUseCase(ChampionsRepository repository) {
		return new AskChampionsUseCase(repository);
	}

}

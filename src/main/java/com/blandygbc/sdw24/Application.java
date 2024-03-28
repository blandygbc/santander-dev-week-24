package com.blandygbc.sdw24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.blandygbc.sdw24.application.AskChampionsUseCase;
import com.blandygbc.sdw24.application.ListChampionsUseCase;
import com.blandygbc.sdw24.domain.ports.ChampionsRepository;
import com.blandygbc.sdw24.domain.ports.GenerativeAiService;

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChamptionsUseCase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}

	@Bean
	public AskChampionsUseCase provideAskChampionUseCase(ChampionsRepository repository,
			GenerativeAiService genAiService) {
		return new AskChampionsUseCase(repository, genAiService);
	}

}

package com.blandygbc.sdw24.adapters.out;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import com.blandygbc.sdw24.domain.ports.GenerativeAiService;

import feign.FeignException;
import feign.RequestInterceptor;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "OPENAI", matchIfMissing = true)
@FeignClient(name = "openAiChatApi", url = "${openai.base-url}", configuration = OpenAiChatApi.Config.class)
public interface OpenAiChatApi extends GenerativeAiService {

    static final Logger logger = LoggerFactory.getLogger(OpenAiChatApi.class);

    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionReq req);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user", context));
        OpenAiChatCompletionReq req = new OpenAiChatCompletionReq(model, messages);
        try {
            OpenAiChatCompletionResp resp = chatCompletion(req);
            return resp.choises().getFirst().message().content();
        } catch (FeignException httpErrors) {
            logger.error(
                    "Deu ruim! Erro de comunicação com a API da OpenAI.",
                    httpErrors);
            return "Deu ruim! Erro de comunicação com a API da OpenAI.";
        } catch (Exception unexpectedError) {
            logger.error(
                    "Deu mais ruim ainda! O retorno da API da OpenAI não contem os dados esperados.",
                    unexpectedError);
            return "Deu mais ruim ainda! O retorno da API da OpenAI não contem os dados esperados.";
        }
    }

    record OpenAiChatCompletionReq(String model, List<Message> messages) {
    }

    record Message(String role, String content) {
    }

    record OpenAiChatCompletionResp(List<Choise> choises) {
    }

    record Choise(Message message) {
    }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(
                    HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
        }
    }
}

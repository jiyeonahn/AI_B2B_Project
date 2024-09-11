package com.three_iii.company.config;

import com.three_iii.company.application.service.AiInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class AiConfig {

    @Bean
    public RestTemplate geminiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors()
            .add(((request, body, execution) -> execution.execute(request, body)));
        return restTemplate;
    }

    @Bean
    public RestClient geminiRestClient(@Value("${gemini.api.url}") String baseUrl,
        @Value("${gemini.api.key}") String apiKey) {
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("x-goog-api-key", apiKey)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Accept", "application/json")
            .build();
    }

    @Bean
    public AiInterface geminiInterface(@Qualifier("geminiRestClient") RestClient client) {
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AiInterface.class);
    }
}

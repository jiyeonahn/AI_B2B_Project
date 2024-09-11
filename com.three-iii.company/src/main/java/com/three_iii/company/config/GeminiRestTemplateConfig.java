package com.three_iii.company.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class GeminiRestTemplateConfig {

    @Bean
    public RestTemplate geminiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors()
            .add(((request, body, execution) -> execution.execute(request, body)));
        return restTemplate;
    }
}

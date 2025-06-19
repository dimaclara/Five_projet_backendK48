package com.marieteck.gestionstock_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Configuration
public class PexelConfiguration {

    @Value("${pexels.apiKey}")
    private String apiKey;

    @Bean
    public WebClient pexelsWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.pexels.com/v1/")
                .defaultHeader(HttpHeaders.AUTHORIZATION, apiKey)
                .build();
    }


}





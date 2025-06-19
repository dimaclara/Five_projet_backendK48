package com.marieteck.gestionstock_backend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marieteck.gestionstock_backend.service.PexelService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;



@Service

@RequiredArgsConstructor
public class PexelServiceImpl implements PexelService {


    @Value("${pexels.api.key}")
    private String apiKey;

    private WebClient webClient;


    @Override

    public String savePhoto(String keyword) {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("search")
                        .queryParam("query",keyword)
                        .queryParam("per_page",1)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response == null){
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode photos = jsonNode.get("photos");
            if (!photos.isArray() || photos.isEmpty()) {
                return null;
            }
            JsonNode firstPhoto = photos.get(0);
            return firstPhoto.path("src").path("medium").asText(null);


        }catch (Exception e){
            e.printStackTrace();
            return null;

        }







//
    }
}


package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.service.PexelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.InputStream;

@Service
@Slf4j
public class PexelServiceImpl implements PexelService {

    @Value("${pexels.apiKey}")
    private String apiKey;

    private WebClient webClient;


    @Override
    public String savePhoto(String keyword) {
        String response = pexelsWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("search")
                        .queryParam("query", keyword)
                        .queryParam("per_page", 1)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); //

        if (response != null) {
            JSONObject json = new JSONObject(response);
            JSONArray photos = json.getJSONArray("photos");
            if (!photos.isEmpty()) {
                JSONObject photo = photos.getJSONObject(0);
                JSONObject src = photo.getJSONObject("src");
                return src.getString("medium"); // URL image taille moyenne
            }
        }

        return null;
    }
    }
}

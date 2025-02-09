package com.huggingFace.AI.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;

@Service
public class HuggingFaceService {

    @Value("${huggingface.api.key}")
    private String apiKey;

    private final String url = "https://api-inference.huggingface.co/models/gpt-4";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getPrediction(String inputText) {
        String requestBody = String.format("{\"inputs\": \"%s\"}", inputText);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}

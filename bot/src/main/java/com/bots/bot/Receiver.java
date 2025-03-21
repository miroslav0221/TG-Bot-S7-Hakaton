package com.bots.bot;

import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Receiver {
    private static final String URL = "http://localhost:8081/get";
    private Map<Integer, Integer> dataStore = new ConcurrentHashMap<>();

    public void getData() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map response = restTemplate.getForObject(URL, Map.class);

            if (response != null) {
                dataStore.putAll(response);
            }

            System.out.println("Received data: " + response);
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
}

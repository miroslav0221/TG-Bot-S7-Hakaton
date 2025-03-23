package com.bots.bot;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Receiver {
    private static final String URL = "http://localhost:8081/data";
    public Map<String, String> getData(String airport) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> data = new HashMap<>();
        try {
            data = restTemplate.getForObject(URL + "?airport={airport}", Map.class, airport);
        }
        catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        return data;
    }
}

package com.four_boys.server;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Receiver {
    private static final String URL = "http://localhost:8080/get";

    public static void main(String[] args) {
        getData();
    }

    public static void getData() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map response = restTemplate.getForObject(URL, Map.class);
            System.out.println("Received data: " + response);
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
}
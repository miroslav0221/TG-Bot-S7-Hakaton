package com.four_boys.server;

import org.springframework.web.client.RestTemplate;
import java.util.Timer;
import java.util.TimerTask;

public class Sender {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/update";

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String data = "New Data " + System.currentTimeMillis();
                restTemplate.postForObject(url, data, String.class);
                System.out.println("Sent: " + data);
            }
        }, 0, 5000); // Каждые 5 секунд
    }
}
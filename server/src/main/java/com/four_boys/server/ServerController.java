package com.four_boys.server;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Airport {
    public String nameAirport;
    public Map<String, String> reseption = new HashMap<>();
    public Airport(String nameAirport, Map<String, String> reseption) {
        this.nameAirport = nameAirport;
        this.reseption = reseption;
    }
    public String getNameAirport() {
        return nameAirport;
    }
    public Map<String, String> getReseption() {
        return reseption;
    }
}

@RestController
public class ServerController {
    private final Map<String, Airport> data = new HashMap<>();
    @PostMapping("/update")
    public String updateData(@RequestBody Map<String, Map<String, String>> payload) {
        if (payload == null || payload.isEmpty()) {
            return "Payload is empty";
        }
        data.clear();
        for (Map.Entry<String, Map<String, String>> entry : payload.entrySet()) {
            String airportName = entry.getKey();
            Map<String, String> receptionData = entry.getValue();
            Airport airport = new Airport(airportName, receptionData);
            data.put(airportName, airport);
        }
        for (Map.Entry<String, Airport> entry : data.entrySet()) {
            String airportName = entry.getKey();
            Airport airport = entry.getValue();
            System.out.println("Аэропорт: " + airportName);
            for (Map.Entry<String, String> receptionEntry : airport.getReseption().entrySet()) {
                System.out.println("  Стойка " + receptionEntry.getKey() + ": " + receptionEntry.getValue() + " человек");
            }
        }
        return "Data updated successfully";
    }

    @GetMapping("/data")
    public Map<String, String> getData(@RequestParam String airport) {
        return data.get(airport).getReseption();
    }
}
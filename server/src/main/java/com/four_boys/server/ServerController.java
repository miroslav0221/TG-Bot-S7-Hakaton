package com.four_boys.server;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ServerController {
    private final Map<Integer, Integer> dataStore = new ConcurrentHashMap<>();

    @PostMapping("/update")
    public String updateData(@RequestBody Map<String, Integer> payload) {

        /// __________________________________________________________
        /// Нужно согласовать с отправителем json, как называются поля
        /// __________________________________________________________
        Integer number_register_desk = payload.get("номер_стойки");
        Integer count_people = payload.get("количество_человек");

        if (number_register_desk != null && count_people != null) {
            dataStore.put(number_register_desk, count_people);
            return "Data updated for стойка " + number_register_desk;
        }
        return "Invalid data";
    }

    @GetMapping("/get")
    public Map<Integer, Integer> getData() {
        return dataStore;
    }
}
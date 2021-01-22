package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.NoSuchElementException;

public class WeirdJsonProcessor {

    private KittyEvents kittyEvents;

    public WeirdJsonProcessor(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kittyEvents = objectMapper.readValue(data, KittyEvents.class);
    }

    public KittyEvents getKittyEvents() {
        return kittyEvents;
    }

    public String getEnergyLevelsByKittyId(int i) throws NoSuchElementException {
        if (kittyEvents.getEvents().stream().noneMatch(event -> event.get(0).equals(i))) {
            throw new NoSuchElementException();
        }

        return "{}";
    }
}

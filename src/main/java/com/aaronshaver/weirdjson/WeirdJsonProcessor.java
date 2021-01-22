package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeirdJsonProcessor {

    private KittyEvents kittyEvents;

    public WeirdJsonProcessor(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kittyEvents = objectMapper.readValue(data, KittyEvents.class);
    }

    public KittyEvents getKittyEvents() {
        return kittyEvents;
    }
}

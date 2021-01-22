package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toUnmodifiableList;

public class WeirdJsonProcessor {

    private KittyEvents kittyEvents;

    public WeirdJsonProcessor(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kittyEvents = objectMapper.readValue(data, KittyEvents.class);
    }

    public KittyEvents getKittyEvents() {
        return kittyEvents;
    }

    public String getEnergyLevelsByKittyId(int id) throws NoSuchElementException, JsonProcessingException {
        if (kittyEvents.getEvents().stream().noneMatch(event -> event.get(0).equals(id))) {
            throw new NoSuchElementException();
        }

        final int energyLevel = (int) kittyEvents.getEvents().stream()
                .filter(event -> event.get(0).equals(id))
                .collect(toUnmodifiableList()).get(0).get(1);

        KittyResponse kittyResponse = new KittyResponse();
        kittyResponse.setId(id);
        kittyResponse.setStartingEnergy(energyLevel);
        kittyResponse.setEndingEnergy(energyLevel);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(kittyResponse);
    }
}

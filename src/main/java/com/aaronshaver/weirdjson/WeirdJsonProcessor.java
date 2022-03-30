package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
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

        final List<List<Object>> singleKittyEvents = kittyEvents.getEvents().stream()
                .filter(event -> event.get(0).equals(id))
                .collect(toUnmodifiableList());

        // ************************************************************************************************
        // Retrospective note: To refactor this, to reduce constant type conversions and to use more
        // built-in and modern code, I would extract all the plain Object/plain String type data
        // into a new class like KittyEvent and, while doing so, populate it with types like LocalDateTime.
        // These KittyEvent objects would live in a single (not nested) list, so we could avoid this
        // .get(x).get(y) nonsense.
        //
        // *Then* I could do something like:
        // Collections.sort(listOfEvents, Comparator.comparing(KittyEvent::getDateTime));
        //
        // And then simply grab the energy level from the first and last items of the sorted list
        // ************************************************************************************************
        
        LocalDateTime earliest = LocalDateTime.parse((String) singleKittyEvents.get(0).get(3));
        LocalDateTime latest = LocalDateTime.parse((String) singleKittyEvents.get(0).get(3));
        for (int i = 0; i < singleKittyEvents.size(); i++) {
            if (LocalDateTime.parse((String) singleKittyEvents.get(i).get(3)).isBefore(earliest)) {
                earliest = LocalDateTime.parse((String) singleKittyEvents.get(i).get(3));
            }
            if (LocalDateTime.parse((String) singleKittyEvents.get(i).get(3)).isAfter(latest)) {
                latest = LocalDateTime.parse((String) singleKittyEvents.get(i).get(3));
            }
        }

        int startingEnergy = -1;
        int endingEnergy = -1;
        // 2022-03-30 note upon a re-read of this code: you don't have to go through the data set twice;
        // you can just grab the energy level while you grab it above; it will naturally overwrite/get updated
        // if the conditional is satisfied
        for (int i = 0; i < singleKittyEvents.size(); i++) {
            LocalDateTime localDateTime = LocalDateTime.parse((String) singleKittyEvents.get(i).get(3));
            int energyLevel = (int) singleKittyEvents.get(i).get(1);
            if (localDateTime.equals(earliest)) {
                startingEnergy = energyLevel;
            }

            if (localDateTime.equals(latest)) {
                endingEnergy = energyLevel;
            }
        }

        KittyResponse kittyResponse = new KittyResponse();
        kittyResponse.setId(id);
        kittyResponse.setStartingEnergy(startingEnergy);
        kittyResponse.setEndingEnergy(endingEnergy);

        ObjectMapper objectMapper = new ObjectMapper();

        // 2022-03-30 note upon a re-read of this code: we don't need to instantiate a fresh objectMapper,
        // particularly since we're not doing anything exotic with it; just DRY this up by using a class-level objectMapper
        return objectMapper.writeValueAsString(kittyResponse);
    }
}

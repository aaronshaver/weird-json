package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class KittyEvents {
    @JsonProperty("events")
    private ArrayList<ArrayList<Object>> events;
    @JsonProperty("keys")
    private ArrayList<String> keys;

    public KittyEvents() {
    }

    public ArrayList<ArrayList<Object>> getEvents() {
        return events;
    }

    private void setEvents(ArrayList<ArrayList<Object>> events) {
        this.events = events;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }
}

package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KittyResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("starting_energy")
    private int startingEnergy;

    @JsonProperty("ending_energy")
    private int endingEnergy;

    public KittyResponse() {}

    public int getEndingEnergy() {
        return endingEnergy;
    }

    public void setEndingEnergy(int endingEnergy) {
        this.endingEnergy = endingEnergy;
    }

    public int getStartingEnergy() {
        return startingEnergy;
    }

    public void setStartingEnergy(int startingEnergy) {
        this.startingEnergy = startingEnergy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

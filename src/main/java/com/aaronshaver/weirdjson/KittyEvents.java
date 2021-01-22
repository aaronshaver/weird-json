package com.aaronshaver.weirdjson;

import java.util.ArrayList;

public class KittyEvents {
    private ArrayList<String> keys;

    public KittyEvents(ArrayList<String> keys) {
        setKeys(keys);
    }
    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }
}

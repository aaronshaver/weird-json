package com.aaronshaver.weirdjson;

import java.util.ArrayList;

public class KittyEvents {

    final ArrayList<String> keys;

    public KittyEvents() {
        this.keys = new ArrayList<>();
        keys.add("id");
        keys.add("energy_level");
        keys.add("name");
        keys.add("timestamp");
    }
}

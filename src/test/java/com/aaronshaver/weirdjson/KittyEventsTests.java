package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KittyEventsTests {

	@Test
	void mapKittyEventsObjectToJsonString() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		ArrayList<String> keys = new ArrayList<>();
		keys.add("id");
		keys.add("energy_level");
		keys.add("name");
		keys.add("timestamp");
		KittyEvents kittyEvents = new KittyEvents(keys);

		final String eventsAsString = objectMapper.writeValueAsString(kittyEvents);

		assertEquals(eventsAsString, "{\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}");
	}

}

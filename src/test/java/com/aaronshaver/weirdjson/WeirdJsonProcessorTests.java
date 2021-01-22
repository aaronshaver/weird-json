package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeirdJsonProcessorTests {

	@Test
	void processorConstructorPassesWithFullDataSet() throws JsonProcessingException {
		final String data = "{\"events\":[[0,75,\"Captain Fluff\",\"2021-01-01T13:11:51.141Z\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";

		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);
		ArrayList<String> keys = weirdJsonProcessor.getKittyEvents().getKeys();
		ArrayList<ArrayList<Object>> events = weirdJsonProcessor.getKittyEvents().getEvents();

		assertTrue(events.get(0).contains(75));
		assertTrue(keys.contains("id"));
	}

	@Test
	void processorConstructorPasses() throws JsonProcessingException {
		final String data = "{}";
		new WeirdJsonProcessor(data);
	}

}

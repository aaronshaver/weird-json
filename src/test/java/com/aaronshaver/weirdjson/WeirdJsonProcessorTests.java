package com.aaronshaver.weirdjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeirdJsonProcessorTests {

	@Test
	void getEnergyLevelsByKittyIdMultipleKitties() throws NoSuchElementException, JsonProcessingException {
		final String data = "{\"events\":[[1,44,\"Floofers\",\"2021-01-09T13:11:51.141\"],[0,75,\"Captain Fluff\",\"2021-01-02T13:11:51.141\"],[0,65,\"Captain Fluff\",\"2021-01-01T13:11:51.141\"],[0,85,\"Captain Fluff\",\"2021-01-03T13:11:51.141\"],[2,85,\"Mr. Pickles\",\"2021-01-03T13:11:51.141\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";
		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);
		assertEquals("{\"id\":0,\"starting_energy\":65,\"ending_energy\":85}",
				weirdJsonProcessor.getEnergyLevelsByKittyId(0));
	}

	@Test
	void getEnergyLevelsByKittyIdMultipleEvents() throws NoSuchElementException, JsonProcessingException {
		final String data = "{\"events\":[[0,75,\"Captain Fluff\",\"2021-01-02T13:11:51.141\"],[0,65,\"Captain Fluff\",\"2021-01-01T13:11:51.141\"],[0,85,\"Captain Fluff\",\"2021-01-03T13:11:51.141\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";
		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);
		assertEquals("{\"id\":0,\"starting_energy\":65,\"ending_energy\":85}",
				weirdJsonProcessor.getEnergyLevelsByKittyId(0));
	}

	@Test
	void getEnergyLevelsByKittyIdOneEventCaseReturnsExpected() throws NoSuchElementException, JsonProcessingException {
		final String data = "{\"events\":[[3,11,\"Sir Sleepsalot\",\"2021-01-05T13:11:51.141\"],[0,75,\"Captain Fluff\",\"2021-01-01T13:11:51.141\"],[1,33,\"Floofers\",\"2021-01-02T13:11:51.141\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";
		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);
		assertEquals("{\"id\":0,\"starting_energy\":75,\"ending_energy\":75}",
				weirdJsonProcessor.getEnergyLevelsByKittyId(0));
	}

	@Test
	void processorConstructorPassesWithFullDataSet() throws JsonProcessingException {
		final String data = "{\"events\":[[0,75,\"Captain Fluff\",\"2021-01-01T13:11:51.141\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";

		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);
		ArrayList<String> keys = weirdJsonProcessor.getKittyEvents().getKeys();
		ArrayList<ArrayList<Object>> events = weirdJsonProcessor.getKittyEvents().getEvents();

		assertTrue(events.get(0).contains(75));
		assertTrue(keys.contains("id"));
	}

	@Test
	void getExistingKittyDoesNotThrowException() throws NoSuchElementException, JsonProcessingException {
		final String data = "{\"events\":[[0,75,\"Captain Fluff\",\"2021-01-01T13:11:51.141\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";
		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);
		weirdJsonProcessor.getEnergyLevelsByKittyId(0);
	}

	@Test
	void getNonexistentKittyThrowsException() throws NoSuchElementException, JsonProcessingException {
		final String data = "{\"events\":[[0,75,\"Captain Fluff\",\"2021-01-01T13:11:51.141\"]],\"keys\":[\"id\",\"energy_level\",\"name\",\"timestamp\"]}";
		WeirdJsonProcessor weirdJsonProcessor = new WeirdJsonProcessor(data);

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			weirdJsonProcessor.getEnergyLevelsByKittyId(99);
		});
	}

	@Test
	void processorConstructorPasses() throws JsonProcessingException {
		final String data = "{}";
		new WeirdJsonProcessor(data);
	}

}

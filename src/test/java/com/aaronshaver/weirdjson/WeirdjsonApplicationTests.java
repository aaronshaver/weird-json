package com.aaronshaver.weirdjson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WeirdjsonApplicationTests {

	@Test
	void canCreateKittyEventsObject() {
		KittyEvents kittyEvents = new KittyEvents();
		assertEquals(kittyEvents.keys.get(0), "id");
	}

}

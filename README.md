# weird-json

This is a small code challenge of my own devising to refresh my memory on JSON processing with Jackson.

You've been hired as a consultant to write code for a Kitty Energy Levels company. Your task is to report the starting and ending energy level from a set of events in a JSON file when querying for a particular kitty by ID. Make a processor class with constructor that accepts a JSON string, and create a method in the class to get a JSON string response object showing kitty ID, starting energy level, and ending energy level like `{ "kitty_id": 1, "starting_energy": 40, "ending_energy": 80 }` when you pass it an Integer ID. Throw a custom exception if the kitty doesn't exist.

Complicating matters is that the company chose a bizarre JSON format where keys and their values are separated instead of logically connected (they probably should have used a .CSV file instead, or stuck with JSON-appropriate "key":"value" syntax).

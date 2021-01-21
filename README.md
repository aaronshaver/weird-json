# weird-json

This is a small code challenge of my own devising to refresh my memory on JSON processing with Jackson.

You've been hired as a consultant to write a function for a Kitty Energy Levels company. Your task is to report the starting and ending energy level from a set of events in a JSON file when querying for a particular kitty. Complicating this is that the company chose a bizarre JSON format where keys and their values are separated instead of logically connected (they probbably should have used a .CSV file instead).

The function should take a kitty unique ID integer parameter and then return a JSON response string like `{ "kitty_id": 1, "starting_energy": 40, "ending_energy": 80 }`.

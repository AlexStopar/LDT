package com.example.ldt;

import java.util.HashMap;
import java.util.Map;

import android.location.Location;

public class LocationHistory {
	private Map<Long, Location> history = new HashMap<Long, Location>();
	
	public void AddToHistory() {
		//Once we can grab a location, we can add it based on HoursStopwatch's current time
	}
}

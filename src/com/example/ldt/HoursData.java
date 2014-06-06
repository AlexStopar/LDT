package com.example.ldt;

public class HoursData {
	private static long hours = 3;

	public static long getHours() {
		return hours;
	}
	
	public long getMinutes() {
		return hours * 60;
	}

	public static void setHours(long hours) {
		HoursData.hours = hours;
	}
	
	
}

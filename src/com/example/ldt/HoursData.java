package com.example.ldt;

public class HoursData {
	private static int hours;

	public static int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return hours * 60;
	}

	public void setHours(int hours) {
		HoursData.hours = hours;
	}
	
	
}

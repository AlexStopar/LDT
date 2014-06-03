package com.example.ldt;

public class HoursData {
	private int hours;

	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return hours * 60;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	
}

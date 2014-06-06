package com.example.ldt;

public class BeepData {
	
	private static long beepVolume = 5;
	
	private static long beepDuration = 5;
	
	public static long getVolume() {
		return beepVolume;
	}
	public static void setVolume(long volume) {
		BeepData.beepVolume = volume;
	}
	public static long getDuration() {
		return beepDuration;
	}
	public static void setDuration(long duration) {
		BeepData.beepDuration = duration;
	}
}

package com.example.ldt;

public class BeepData {
	
	private static int beepVolume = 5;
	
	private static int beepDuration = 5;
	
	public static int getVolume() {
		return beepVolume;
	}
	public void setVolume(int volume) {
		BeepData.beepVolume = volume;
	}
	public static int getDuration() {
		return beepDuration;
	}
	public void setDuration(int duration) {
		BeepData.beepDuration = duration;
	}
}

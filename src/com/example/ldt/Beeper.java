package com.example.ldt;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class Beeper {
	private static ToneGenerator beeper;
	private static int beepDuration;
	
	
	public static void updateBeeper(){
		beepDuration = (int) BeepData.getDuration();
		beeper = new ToneGenerator(AudioManager.STREAM_ALARM, (int) BeepData.getVolume());
	}
	
	public static void Beep(){
		beeper.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, beepDuration * 1000);
	}
	
}

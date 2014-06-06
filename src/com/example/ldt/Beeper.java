package com.example.ldt;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.ToneGenerator;

public class Beeper {
	private static SoundPool beeper;
	private static int beepDuration;
	
	
	public static void updateBeeper(){
		beepDuration = (int) BeepData.getDuration();
		beeper = new SoundPool(3, AudioManager.STREAM_ALARM, 0);
	}
	
	public static void Beep(){
		beeper.play(R.raw.beep, 0.99f, 0.99f, 0, 0, 1);
	}
	
}

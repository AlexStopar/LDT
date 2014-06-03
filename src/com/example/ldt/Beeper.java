package com.example.ldt;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class Beeper {
	private ToneGenerator beeper;
	private int beepDuration;
	
	
	public Beeper(){
		beeper = new ToneGenerator(AudioManager.STREAM_ALARM, BeepData.getVolume());
		beepDuration = BeepData.getDuration();
	}
	
	public void updateBeeper(){
		beepDuration = BeepData.getDuration();
		beeper = new ToneGenerator(AudioManager.STREAM_ALARM, BeepData.getVolume());
	}
	
	public void Beep(){
		beeper.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, beepDuration * 1000);
	}
	
}

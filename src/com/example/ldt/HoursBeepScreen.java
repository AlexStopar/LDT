package com.example.ldt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class HoursBeepScreen extends Activity implements OnClickListener {
	
	HoursBeepDatabase hdb;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_hours_beep_screen);
		
		this.hdb = new HoursBeepDatabase(this);
		
		Beeper.updateBeeper();
		NumberPicker hourPicker = (NumberPicker) findViewById(R.id.hourPicker);
		hourPicker.setMinValue(1);
		hourPicker.setMaxValue(24);
		hourPicker.setWrapSelectorWheel(false);
		hourPicker.setValue((int) HoursData.getHours());
		
		hourPicker.setOnValueChangedListener(new OnValueChangeListener(){
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				HoursData.setHours((long) newVal);
				updateDatabase();
			}
		});
		
		NumberPicker beepDurationPicker = (NumberPicker) findViewById(R.id.beep_durationPicker);
		beepDurationPicker.setMinValue(1);
		beepDurationPicker.setMaxValue(10);
		beepDurationPicker.setWrapSelectorWheel(false);
		beepDurationPicker.setValue((int) BeepData.getDuration());
		
		beepDurationPicker.setOnValueChangedListener(new OnValueChangeListener(){
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				BeepData.setDuration((long) newVal);
				Beeper.updateBeeper();
				updateDatabase();
			}
		});
		
		NumberPicker beepVolumePicker = (NumberPicker) findViewById(R.id.beep_volumePicker);
		beepVolumePicker.setMinValue(1);
		beepVolumePicker.setMaxValue(10);
		beepVolumePicker.setWrapSelectorWheel(false);
		beepVolumePicker.setValue((int) BeepData.getVolume());
		
		beepVolumePicker.setOnValueChangedListener(new OnValueChangeListener(){
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				BeepData.setVolume((long) newVal);
				Beeper.updateBeeper();
				updateDatabase();
			}
		});
		
		
		View testBeep = (Button) findViewById(R.id.test_beep_button);
		testBeep.setOnClickListener(this);
		
		View returnToOptions = (Button) findViewById(R.id.return_button);
		returnToOptions.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.return_button:
			startActivity(new Intent(this, Options.class));
			finish();
			break;
		case R.id.test_beep_button:
			Beeper.Beep();
			break;
		}
	}
	
	private void updateDatabase() {
		hdb.deleteAll();
		hdb.insert(HoursData.getHours(), BeepData.getDuration(), BeepData.getVolume());
	}
}
	
	

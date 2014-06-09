package com.example.ldt;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;

public class Map extends Activity implements OnClickListener {
	
	private SeekBar timeBar = null;
	private int numLocations = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_map);
		
		/* TODO:
		 * Also needs to grab from server/local storage the dictionary of 
		 * times and locations of the phone
		 * 
		 * Set up the seek bar to include a discrete number of locations
		 * along the bar. One for each time the phone was logged.
		 */

		View btnLocate = (Button) findViewById(R.id.locate_button);
		btnLocate.setOnClickListener(this);
		
		timeBar = (SeekBar) findViewByID(R.id.time_seek_bar);
		timeBar.setMax(numLocations);
		
		timeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progressChanged = 0;
			
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				progressChanged = progress;
				/* TODO:
				 * Update what the textView says in the current activity
				 * to the selected time from the seekBar.
				 */
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {

			}
			
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
	}
	
	

	void displayLocation() {
		/* TODO:
		 * This will update the mapview to show on a google maps
		 * where the phone was located at which time. Based on 
		 * the seeker/slider.
		 */
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.locate_button;
			displayLocation();
			break;
		default:
			break;
		}
		
	}

}

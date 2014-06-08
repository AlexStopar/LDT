package com.example.ldt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Options extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_options);
		
		View mapButton = (Button) findViewById(R.id.map_button);
		mapButton.setOnClickListener(this);
		
		View hourBeepButton = (Button) findViewById(R.id.hours_beep_button);
		hourBeepButton.setOnClickListener(this);
		
		View newUserButton = (Button) findViewById(R.id.new_user_button);
		newUserButton.setOnClickListener(this);
		
		View quitButton = (Button) findViewById(R.id.quit_button);
		quitButton.setOnClickListener(this);
		
		View activateButton = (Button) findViewById(R.id.activate_button);
		activateButton.setOnClickListener(this);
		
	}
	

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.map_button:
			break;
		case R.id.hours_beep_button:
			startActivity(new Intent(this, HoursBeepScreen.class));
			break;
		case R.id.new_user_button:
			startActivity(new Intent(this, NewUser.class));
			break;
		case R.id.quit_button:
			finish();
			break;
		case R.id.activate_button:
			break;
		}
		
	}

}

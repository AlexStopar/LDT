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
import android.widget.Button;
import android.widget.EditText;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;


	public class Login extends Activity implements OnClickListener {
		
		private LoginDatabase dh;
		private EditText userNameEditableField;
		private EditText passwordEditableField;
		private final static String OPT_NAME = "name";
		String deviceId;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.fragment_login);
			
			userNameEditableField = (EditText) findViewById(R.id.username_text);
			passwordEditableField = (EditText) findViewById(R.id.password_text);
			View btnLogin = (Button) findViewById(R.id.login_button);
			btnLogin.setOnClickListener(this);
			View btnNewUser = (Button) findViewById(R.id.new_user_button);
			btnNewUser.setOnClickListener(this);
			
			deviceId = Secure.getString(this.getContentResolver(),
	                Secure.ANDROID_ID);
		}

		private void checkLogin() {
			String username = this.userNameEditableField.getText().toString();
			String password = this.passwordEditableField.getText().toString();
			this.dh = new LoginDatabase(this);
			List<String> names = this.dh.selectAll(username, password, deviceId);
		
			if (names.size() > 0) { // Login successful
				// Save username as the name of the player
				SharedPreferences settings = PreferenceManager
						.getDefaultSharedPreferences(this);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(OPT_NAME, username);
				editor.commit();

				// Bring up the GameOptions screen
			    startActivity(new Intent(this, Options.class));
//				 startActivity(new Intent(this, DummyActivity.class));
				finish();
			} else {
				// Try again?
				new AlertDialog.Builder(this)
						.setTitle("Error")
						.setMessage("Login failed")
						.setNeutralButton("Try Again",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
			}
		}

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_button:
				checkLogin();
				break;
			case R.id.new_user_button:

				startActivity(new Intent(this, NewUser.class));
				break;
			}
		}
	}


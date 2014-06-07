package com.example.ldt;

import com.example.ldt.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class NewUser extends Activity implements OnClickListener{
	
	private EditText etUsername;
	private EditText etPassword;
	private EditText etConfirm;
	private LoginDatabase dh;
	String deviceId;
	long deviceIdCount=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_new_user);
		
		etUsername = (EditText) findViewById(R.id.username);
		etPassword = (EditText) findViewById(R.id.password);
		etConfirm = (EditText) findViewById(R.id.password_confirm);
		View btnAdd = (Button) findViewById(R.id.register_button);
		btnAdd.setOnClickListener(this);
		View btnCancel = (Button) findViewById(R.id.cancel_button);
		btnCancel.setOnClickListener(this);
		
	     deviceId = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);
       // Toast.makeText(this, deviceId, Toast.LENGTH_SHORT).show();

	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_button:
			CreateAccount();
			break;
		case R.id.cancel_button:
			finish();
			break;
		}
	}
		
		private void CreateAccount() {
			// this.output = (TextView) this.findViewById(R.id.out_text);
			String username = etUsername.getText().toString();
			String password = etPassword.getText().toString();
			String confirm = etConfirm.getText().toString();
			

			/*if(!(this.dh.getSingleEntry(username)))
			{
				new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage("username already exists")
				.setNeutralButton("Try Again",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						})

				.show();
				return;
			}	*/
			
			//this.dh.getSingleEntry(username,password);
			this.dh = new LoginDatabase(this);
				
				long id =0;
				if ((username.equals("")))
					{
				Toast.makeText(NewUser.this, "Username is empty", Toast.LENGTH_SHORT)
				.show();
				return;
					}else if(password.equals("")
					|| (confirm.equals(""))) {
				Toast.makeText(NewUser.this, "Password field is empty", Toast.LENGTH_SHORT)
						.show();
				return;
			
			} else if (!password.equals(confirm)) {
				new AlertDialog.Builder(this)
						.setTitle("Error")
						.setMessage("passwords do not match")
						.setNeutralButton("Try Again",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								})

						.show();
				return;
			} else 
		    id = dh.getSingleEntry(username, password);
			if (id == 1)
			{
			    /*Toast.makeText(NewUser.this, "username already taken",
			                        Toast.LENGTH_LONG).show();*/
				
				new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage("Username already exists")
				.setNeutralButton("Please try Again with different user name",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						})

				.show();
				etUsername.getText().clear();
				etPassword.getText().clear();
				etConfirm.getText().clear();
				etUsername.requestFocus();
			    return;
			}
			
			deviceIdCount = dh.getdeviceIdDuplicate(deviceId);
			if (deviceIdCount == 1)
			{
				new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage("User already registered")
				.setNeutralButton("only one user allowed per device",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						})

				.show();
				etUsername.requestFocus();
			    return;				
			}
			
			if ((password.equals(confirm)) && (!username.equals(""))
					&& (!password.equals("")) && (!confirm.equals(""))) {
				//this.dh = new LoginDatabase(this);
				this.dh.insert(username, password, deviceId);
				// this.labResult.setText("Added");
				Toast.makeText(NewUser.this, "new record inserted",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		}

}

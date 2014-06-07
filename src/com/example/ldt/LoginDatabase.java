package com.example.ldt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;
import android.content.*;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;


public class LoginDatabase {
	
	private static final String DATABASE_NAME = "LDT.db";
	   private static final int DATABASE_VERSION = 2;
	   private static final String TABLE_NAME = "LoginDetails";
	   private Context context;
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
	   private static final String INSERT = "insert into " + TABLE_NAME + "(name, password, deviceid) values (?, ?, ?)" ;
	   
	   public LoginDatabase(Context context) {
	      this.context = context;
	      LDTOpenHelper openHelper = new LDTOpenHelper(this.context);
	      this.db = openHelper.getWritableDatabase();
	      this.insertStmt = this.db.compileStatement(INSERT);
	   }

	   public long insert(String name, String password, String deviceid) {
	      this.insertStmt.bindString(1, name);
	      this.insertStmt.bindString(2, password);
	      this.insertStmt.bindString(3, deviceid);
	      return this.insertStmt.executeInsert();
	   }
	   public void deleteAll() {

	      this.db.delete(TABLE_NAME, null, null);
	   }
	  
	   public List<String> selectAll(String username, String password,String deviceid) {
	      List<String> list = new ArrayList<String>();
	      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "name", "password", "deviceid" }, "name = '"+ username +"' AND password= '"+ password+"' AND deviceid = '"+ deviceid +"'", null, null, null, "name desc");
	      if (cursor.moveToFirst()) {
	        do {
	        	 list.add(cursor.getString(0));
	        	 list.add(cursor.getString(1));
	        	 list.add(cursor.getString(2));
	         } while (cursor.moveToNext()); 
	      }
	      if (cursor != null && !cursor.isClosed()) {
	         cursor.close();
	      }
	      return list;
	   }
	   
	   private static class LDTOpenHelper extends SQLiteOpenHelper {
		   LDTOpenHelper(Context context) {
	    	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	      }

	      @Override
	      public void onCreate(SQLiteDatabase db) {
	         db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, name TEXT, password TEXT, deviceid INT)");
	      }

	      @Override
	      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
	         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	         onCreate(db);
	      }
	   }
	   
	   public long getSingleEntry(String username,String password)
       {

		  /* Cursor cursor=db.query(TABLE_NAME, null, " USERNAME=?", new String[]{username}, null, null, null);
           if(cursor.getCount()<1) // UserName Not Exist
           {
               cursor.close();
               return "Username already exists";
           }
           cursor.moveToFirst();
           String password= cursor.getString(cursor.getColumnIndex("password"));
           cursor.close();
           return password; */

		   
		  /* ContentValues values = new ContentValues();
		    values.put("name", username);
		    values.put("password", password);

		    return db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);*/
		   
		   Cursor c = db.query(TABLE_NAME, new String[] {"name"},
				   "name"+ " ='" + username + "'", null, null, null, null);
		   
		   if(c.getCount() > 0)
		   {
		      return 1;
		   }
               
           return -1;
       }
	   
	   public long getdeviceIdDuplicate(String deviceid)
	   {
		   Cursor c = db.query(TABLE_NAME, new String[] {"deviceid"},
				   "deviceid"+ " ='" + deviceid + "'", null, null, null, null);
		   
		   if(c.getCount() > 0)
		   {
		      return 1;
		   }
               
           return -1;
	   }
	   
}

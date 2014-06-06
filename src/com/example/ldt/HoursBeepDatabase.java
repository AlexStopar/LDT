package com.example.ldt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class HoursBeepDatabase {
	private static final String DATABASE_NAME = "LDT.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "HoursBeepInfo";
	   private Context context;
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
	   private static final String INSERT = "insert into " + TABLE_NAME + "(hours, beepDuration, beepVolume) values (?, ?, ?)" ;
	   
	   public HoursBeepDatabase(Context context) {
		      this.context = context;
		      LDTOpenHelper openHelper = new LDTOpenHelper(this.context);
		      this.db = openHelper.getWritableDatabase();
		      this.insertStmt = this.db.compileStatement(INSERT);
		   }
	   
	   public long insert(long hours, long beepDuration, long beepVolume) {
		      this.insertStmt.bindLong(1, hours);
		      this.insertStmt.bindLong(2, beepDuration);
		      this.insertStmt.bindLong(3, beepVolume);
		      return this.insertStmt.executeInsert();
		   }
	   
		   public void deleteAll() {

		      this.db.delete(TABLE_NAME, null, null);
		   }
		   
		   public static class LDTOpenHelper extends SQLiteOpenHelper {
			   LDTOpenHelper(Context context) {
		    	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
		      }
			   @Override
			      public void onCreate(SQLiteDatabase db) {
			         db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, hours LONG, beepDuration LONG, beepVolume LONG)");
			      }

			      @Override
			      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
			         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			         onCreate(db);
			      }
		   }
}

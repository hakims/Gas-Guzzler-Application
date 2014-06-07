package com.example.gasguzzler;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;
import android.database.SQLException;

/**
 * This class is used to create a SQLite Database we will interact with to store all the users necessary data
 * i.e. gas price, volume purchased, previous odometer reading.  
 * 
 *@author Hakims
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	/**
	 * Database version - change to upgrade
	 */
	private static final int DB_VERSION = 1;
	/**
	 * Name of database - don't change
	 */
	private static final String DB_NAME = "drivingData";
	/**
	 * Application context
	 */
	protected Context c = null;
	/**
	 * Reference to SQLite database
	 */
	static SQLiteDatabase db = null;
	
	/**
	 * VEHICLE table: Stores Vehicle Driving Data
	 */
	private static final String VEHICLE_TABLE = "vehicle";
	private static final String VEHICLE_COL_REFILLPRICE = "fillup_price";     /// The amount paid for this single trip at the pump
	private static final String VEHICLE_COL_REFILLAMOUNT = "fillup_amount";   /// Amount paid this single trip
	private static final String VEHICLE_COL_PREVODOMETER = "prevodometer";	  /// Previos Odometer Reading
	private static final String VEHICLE_COL_DATE = "Date";					  /// The Date you visited the pump
		
	
	/**
	 * Default constructor for class.  Passes application context to parent and gets reference to database.
	 * 
	 * @param context: (Context) Application context
	 */
	public DatabaseHelper(Context context) {
	
		super (context, DB_NAME, null, DB_VERSION);
		Log.i(getClass().getSimpleName(), "DB Initialized");
		c = context;
		db = this.getWritableDatabase();
		
	}
	
	/**
	 * Creates the database.  It is only called when there is a new version passed into the helper.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		String sql1 = 	"CREATE TABLE " + VEHICLE_TABLE + "(" +
						VEHICLE_COL_REFILLPRICE + " DOUBLE PRIMARY KEY AUTOINCREMENT, " + 
						VEHICLE_COL_REFILLAMOUNT + " DOUBLE NOT NULL, " + 
						VEHICLE_COL_PREVODOMETER + " DOUBLE NOT NULL " + 
						");";
		
		
		
		try {
			db.beginTransaction();
			db.execSQL(sql1);
			Log.i(getClass().getName(), "Table Created:" + VEHICLE_TABLE);
			db.setTransactionSuccessful();
			db.endTransaction();
			
			
			Log.i(getClass().getName(), "Database Created:" + DB_VERSION);
			
		} catch (SQLException e) {
				Log.e(getClass().getName(), "Could not create database");
			}
		
		
	
	}
	
	
	public void insertData (String _name, String _pin) {
		ContentValues values = null;
		
		try {
			db.beginTransaction();
			values = new ContentValues();
			values.put(VEHICLE_COL_USERID, _name);
			values.put(VEHICLE_COL_PIN, _pin);
			db.insert(VEHICLE_TABLE, null, values);
			values.clear();
			db.setTransactionSuccessful();
			db.endTransaction();
			Log.i(getClass().getSimpleName(), _name + " inserted");
		} catch (SQLException e) {
			Log.e(getClass().getSimpleName(), e.getMessage());
		}
	}
	
	public int getRowCount() {
		
		String sql = "SELECT " + VEHICLE_COL_PIN  + " FROM " + VEHICLE_TABLE;
		
		try {
			Cursor allRows = db.rawQuery(sql, null);
			return allRows.getCount();
		} catch (NullPointerException e) {
			return -2;
		} catch (SQLException e) {
			Log.e(getClass().getSimpleName(), "Error processing " + sql + "\t" + e.getMessage());
			return -1;
		} 
		
	}
	
	public String getPin (String _name) {
		
		
		String sql = "SELECT " + VEHICLE_COL_PIN  + " FROM " + VEHICLE_TABLE +  
				" WHERE " + VEHICLE_COL_USERID + " = ?";
		String[] args = {_name};	
		
		//** Never use rawQuery
		try {
			Cursor allRows = db.rawQuery(sql, args);
				
			if (allRows.getCount() <=0) {
				Log.i(getClass().getSimpleName(), sql);
				return null;
			} else {
				allRows.moveToFirst();
				return allRows.getString(0);
			}
			 
		}catch (SQLException e) {
			Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
			return null;
		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
			return null;
		}
			
		
	}

	
	/**
	 * Upgrades the database.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
	//*****************************************************************************************************************
	//** This method is called when there is a new version of the database being used.
	//*****************************************************************************************************************
		
		String dropSQL1 = "DROP TABLE IF EXISTS " + VEHICLE_TABLE + ";";
		
		try {
			db.beginTransaction();
			db.execSQL(dropSQL1);
			Log.i (getClass().getName(),"Table dropped:" + VEHICLE_TABLE);
			
			db.setTransactionSuccessful();
			db.endTransaction();
			onCreate(db);
			
			
		} catch (SQLException e) {
			
			Log.e(getClass().getName(), "Upgrade failed:" + e.getMessage());
		} catch (Exception e) {
			Log.e(getClass().getName(), "Upgrade failed - Uncaught error");
		}

		
		
	}
	
	
}
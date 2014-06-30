package com.app.gasguzzler;


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
 *@author Ali Hakimi
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	/**
	 * Database version - change to upgrade
	 */
	private static final int DB_VERSION = 1;
	/**
	 * Name of database - don't change
	 */
	private static final String DB_NAME = "drivingData.db";
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
	private static final String VEHICLE_COL_PREVODOMETER = "prevodometer";	  /// Previous Odometer Reading
	private static final String VEHICLE_COL_DATE = "date";					  /// The Date you visited the pump
		
	
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
		
	public SQLiteDatabase getDatabase()
	{
		return db;	
	}
	
	/**
	 * Creates the database.  It is only called when there is a new version passed into the helper.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		String sql1 = 	"CREATE TABLE " + VEHICLE_TABLE + "(" +
						VEHICLE_COL_REFILLPRICE + " DOUBLE NOT NULL, " + 
						VEHICLE_COL_REFILLAMOUNT + " DOUBLE NOT NULL, " + 
						VEHICLE_COL_PREVODOMETER + " DOUBLE NOT NULL, " +
						VEHICLE_COL_DATE + " STRING NOT NULL " +
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
	
	/**
	 * Inserts data into the database based off of the input paramters
	 * 
	 */
	
	
	public void insertData (double _price, double _amount, double _odometer, String _date) {
		ContentValues values = null;
		
		try {
			db.beginTransaction();
			values = new ContentValues();
			values.put(VEHICLE_COL_REFILLPRICE, _price);
			values.put(VEHICLE_COL_REFILLAMOUNT, _amount);
			values.put(VEHICLE_COL_PREVODOMETER, _odometer);
			values.put(VEHICLE_COL_DATE, _date);
			db.insert(VEHICLE_TABLE, null, values);
			values.clear();
			db.setTransactionSuccessful();
			db.endTransaction();
			Log.i(getClass().getSimpleName(), _date + " inserted with Price: " + _price);
			Log.i("TEST", "Date: " + _date + " P: " + _price + " V: " + _amount + " O: " + _odometer );
		} catch (SQLException e) {
			Log.e(getClass().getSimpleName(), e.getMessage());
		}
	}
	
	/**
	 * Searches the database for the price payed on a certain date.
	 */
	
	public double getPrice (String _date) {
		
		
		String sql = "SELECT " + VEHICLE_COL_REFILLPRICE  + " FROM " + VEHICLE_TABLE +  
				" WHERE " + VEHICLE_COL_DATE + " = ?";
		String[] args = {_date};	
		
		//** Never use rawQuery
		try {
			Cursor allRows = db.rawQuery(sql, args);
				
			if (allRows.getCount() <=0) {
				Log.i(getClass().getSimpleName(), sql);
				return 0;
			} else {
				allRows.moveToFirst();
				return allRows.getDouble(0);
			}
			 
		}catch (SQLException e) {
			Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
			return 0;
		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
			return 0;
		}
			
		
	}

/**
 * Searches the database for the amount of gas purchased on a certain date.	
 */
	
public double getVolume (String _date) {
		
		
		String sql = "SELECT " + VEHICLE_COL_REFILLAMOUNT  + " FROM " + VEHICLE_TABLE +  
				" WHERE " + VEHICLE_COL_DATE + " = ?";
		String[] args = {_date};	
		
		//** Never use rawQuery
		try {
			Cursor allRows = db.rawQuery(sql, args);
				
			if (allRows.getCount() <=0) {
				Log.i(getClass().getSimpleName(), sql);
				return 0;
			} else {
				allRows.moveToFirst();
				return allRows.getDouble(0);
			}
			 
		}catch (SQLException e) {
			Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
			return 0;
		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
			return 0;
		}
			
		
	}
	
/**
 * Searches the database for the Odometer reading  on a certain date.
 */

public double getOdometer (String _date) {
	
	
	String sql = "SELECT " + VEHICLE_COL_PREVODOMETER  + " FROM " + VEHICLE_TABLE +  
			" WHERE " + VEHICLE_COL_DATE + " = ?";
	String[] args = {_date};	
	
	//** Never use rawQuery
	try {
		Cursor allRows = db.rawQuery(sql, args);
			
		if (allRows.getCount() <=0) {
			Log.i(getClass().getSimpleName(), sql);
			return 0;
		} else {
			allRows.moveToFirst();
			return allRows.getDouble(0);
		}
		 
	}catch (SQLException e) {
		Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
		return 0;
	} catch (Exception e) {
		Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
		return 0;
	}
		
	
}

/**
 * 
 * @return the number of rows in the database. Since we only allow insertion with all 4 entries, it is assumed 
 * that the number of rows will be equivalent across all columns of the database
 */

public int getNumRows()
{
	int rowCount = 0;
	
	String sql = "SELECT " + VEHICLE_COL_DATE + " FROM " + VEHICLE_TABLE;
	
	Cursor cur = db.rawQuery(sql, null);
	cur.moveToFirst();

    while(cur.isAfterLast() == false)
    {
       	rowCount++;
    	cur.moveToNext();
    }
	
	return rowCount;
}

/**
 * Go through the entire database and calculate the average price per gallon.  
 */
public double getAveragePricePerGallon()
{
	double avPrice = 0;
	
	String sql = "SELECT " + VEHICLE_COL_REFILLPRICE + ", " + VEHICLE_COL_REFILLAMOUNT + " FROM " + VEHICLE_TABLE;

	try {
		Cursor allRows = db.rawQuery(sql, null);
		allRows.moveToFirst();
		
		while(allRows.isAfterLast() == false)
		{
		
			if (allRows.getCount() <=0) {
				Log.i(getClass().getSimpleName(), sql);
				return 0;
			} else {
			
				avPrice += allRows.getDouble(0) / allRows.getDouble(1);
				Log.i("Database Debug", "the value of avg is: " + avPrice);
				
				allRows.moveToNext();
			}
		}
		 allRows.close();
		 
	}catch (SQLException e) {
		Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
		return 0;
	} catch (Exception e) {
		Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
		return 0;
	}
	
	
	return avPrice/getNumRows();
	
}

/**
 * 
 * @return the average amount of gas purchased. Sums all volume entries and divides by the number of rows. 
 */
	public double getAverageVolume()
	{
		double total = 0;
		
		String sql = "SELECT " + VEHICLE_COL_REFILLAMOUNT + " FROM " + VEHICLE_TABLE;
		
		try {
			Cursor allRows = db.rawQuery(sql, null);
			allRows.moveToFirst();
			
			while(allRows.isAfterLast() == false)
			{
			
				if (allRows.getCount() <=0) {
					Log.i(getClass().getSimpleName(), sql);
					return 0;
				} else {
				
					total += allRows.getDouble(0);
					Log.i("Database Debug", "the value of total is: " + total);
					allRows.moveToNext();
				}
			}
			 allRows.close();
			 
		}catch (SQLException e) {
			Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
			return 0;
		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
			return 0;
		}
		
		
		return total/getNumRows();
		
	}

	/**
	 * 
	 * @return the calculated average for miles per gallon. This calculation requires at least 2 data 
	 * entries, otherwise it will return 0.
	 */
	
	public double getAverageMPGS()
	{
		String sql = "SELECT " + VEHICLE_COL_REFILLAMOUNT + ", " + VEHICLE_COL_PREVODOMETER + " FROM " + VEHICLE_TABLE;
		double averageMPG = 0;
		
		try {
	        
	        if(getNumRows() <2)
	        {
	        	Log.i("MPGS", "Not enough Entries");
	        }
	        else
	        {
	        	Cursor current = db.rawQuery(sql, null);
	            current.moveToFirst();
	            
	        	while(current.isAfterLast() == false)
	            {
	        		if(current.isFirst())
	        		{
	        			//There are no entries before the first so we can't calculate MPGS
	        			//Do nothing
	        		}	
	        		else
	        		{
	        			current.moveToPrevious();
	                    
	                    double prevMileage = current.getDouble(1);
	                    
	                    Log.i("Database Testing Current", "V: " + current.getDouble(0) + " O: " + current.getDouble(1));
	                    current.moveToNext();
	                	
	                    double currMileage = current.getDouble(1);
	                	double currVolume = current.getDouble(0);

	                    Log.i("Database Testing Current ", "V: " + current.getDouble(0) + " O: " + current.getDouble(1));                	
	                	Log.i("Summary Testing", "PrevMileage: " + prevMileage + " currMileage: " + currMileage + " currVolume: " + currVolume);
	                		                	
	                	averageMPG += (currMileage - prevMileage) / currVolume;
	                	Log.i("Summary Testing", "MPGS: " + averageMPG);
	                	
	                
	        		}
	        		
	        		current.moveToNext();
	        	}
	        }	

			averageMPG = averageMPG / (getNumRows() - 1);
	        
			return averageMPG;
			
			 
		}catch (SQLException e) {
			Log.e(getClass().getSimpleName(), "Unable to process SQL: " + sql);
			return -1;
		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "Unhandled exception SQL:" + sql);
			return -1;
		}
	}
	
	/** Helper function for the OdometerPage class. It is used to make sure 
	 * the users input mileage is not less than the last mileage stored in the database. 
	 * Preserves order.
	 * 
	 */
	public double getLastMileage()
	{
		String sql = "SELECT " + VEHICLE_COL_PREVODOMETER + " FROM " + VEHICLE_TABLE;
		double last;
		
		Cursor cur = db.rawQuery(sql, null);
		cur.moveToLast();
		if(getNumRows() <= 0 )
			last = -1;
		else
			last = cur.getDouble(0);
		
		return last;
		
	}
	
	
	
	/** Calculates MPGS between two refill instances. This assumes that the driver uses ALL the gallons they previously
	 * purchased, which is a poor assumption.
	 * 
	 * In order to use this function you need to initialize previousRefill and currentRefill
	 * using "SELECT fillup_amount, prevodometer FROM vehicle" 
	 * The order is important!
	 * 
	 */
	
	
	public double getMPGS(Cursor previousRefill, Cursor currentRefill)
	{
		double mpgs = 0;
		
		double previousMileage = previousRefill.getDouble(1);
		double currentMileage = currentRefill.getDouble(1);
		double currentVolume = currentRefill.getDouble(0);
		
		mpgs = (currentMileage - previousMileage)/currentVolume; 
		
		return mpgs;
		
		
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
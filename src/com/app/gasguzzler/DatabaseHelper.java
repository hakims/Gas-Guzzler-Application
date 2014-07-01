package com.app.gasguzzler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
	 * @return void
	 * This is used for testing purposes.
	 * 
	 */
	
	public void eraseDBContents()
	{
	    // db.delete(String tableName, String whereClause, String[] whereArgs);
	    // If whereClause is null, it will delete all rows.
	    db.delete(VEHICLE_TABLE, null, null);
	   // db.delete(VEHICLE_COL_REFILLAMOUNT, null, null);
	   // db.delete(VEHICLE_COL_PREVODOMETER, null, null);
	   // db.delete(VEHICLE_COL_DATE, null, null);
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
	
	public Context getContext()
	{
		return c;
	}
	
	/**
	 * Inserts data into the database 
	 * @param _price : Amount Spent at the pump
	 * @param _amount : Amount of Gas Purchased
	 * @param _odomoter : Odometer Reading at that point of purchase
	 * @param _date : Date the user is refilling
	 * 
	 * @return void
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
	 * Inserts Andy Sayler's statistics. This function is only used to show off some of the database functionality.
	 * We did not have prices so those were randomly generated.
	 * 
	 * We are aware that this function is ridiculously large. It was generated at the last second just so we could have some
	 * useful demo values.
	 * 
	 * @return void
	 */
	public void insertDefaults() {
		
			ContentValues values = null;
			TestingTools priceGen = new TestingTools();
			
			try {
				db.beginTransaction();
				db.delete(VEHICLE_TABLE, null, null);				
				db.setTransactionSuccessful();
				db.endTransaction();
				
				db.beginTransaction();
				values = new ContentValues();
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 12.81);
				values.put(VEHICLE_COL_PREVODOMETER, 102890);
				values.put(VEHICLE_COL_DATE, "01-13-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 11.18);
				values.put(VEHICLE_COL_PREVODOMETER, 103156);
				values.put(VEHICLE_COL_DATE, "01-28-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 8.24);
				values.put(VEHICLE_COL_PREVODOMETER, 103377);
				values.put(VEHICLE_COL_DATE, "02-02-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 6.48);
				values.put(VEHICLE_COL_PREVODOMETER, 103556);
				values.put(VEHICLE_COL_DATE, "02-03-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 8.79);
				values.put(VEHICLE_COL_PREVODOMETER, 103783);
				values.put(VEHICLE_COL_DATE, "02-04-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 11.06);
				values.put(VEHICLE_COL_PREVODOMETER, 104070);
				values.put(VEHICLE_COL_DATE, "02-05-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.03);
				values.put(VEHICLE_COL_PREVODOMETER, 104334);
				values.put(VEHICLE_COL_DATE, "02-05-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 2.68);
				values.put(VEHICLE_COL_PREVODOMETER, 104456);
				values.put(VEHICLE_COL_DATE, "02-10-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.18);
				values.put(VEHICLE_COL_PREVODOMETER, 104638);
				values.put(VEHICLE_COL_DATE, "02-16-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 5.29);
				values.put(VEHICLE_COL_PREVODOMETER, 104771);
				values.put(VEHICLE_COL_DATE, "02-18-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 3.89);
				values.put(VEHICLE_COL_PREVODOMETER, 104881);
				values.put(VEHICLE_COL_DATE, "02-19-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 4.38);
				values.put(VEHICLE_COL_PREVODOMETER, 105000);
				values.put(VEHICLE_COL_DATE, "02-19-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.58);
				values.put(VEHICLE_COL_PREVODOMETER, 105280);
				values.put(VEHICLE_COL_DATE, "02-22-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 8.37);
				values.put(VEHICLE_COL_PREVODOMETER, 105687);
				values.put(VEHICLE_COL_DATE, "02-24-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				
				values.clear();
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.09);
				values.put(VEHICLE_COL_PREVODOMETER, 105953);
				values.put(VEHICLE_COL_DATE, "03-03-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 12.03);
				values.put(VEHICLE_COL_PREVODOMETER, 106530);
				values.put(VEHICLE_COL_DATE, "03-23-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 11.10);
				values.put(VEHICLE_COL_PREVODOMETER, 106835);
				values.put(VEHICLE_COL_DATE, "03-30-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 11.73);
				values.put(VEHICLE_COL_PREVODOMETER, 107151);
				values.put(VEHICLE_COL_DATE, "04-07-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 12.59);
				values.put(VEHICLE_COL_PREVODOMETER, 107466);
				values.put(VEHICLE_COL_DATE, "04-26-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 12.45);
				values.put(VEHICLE_COL_PREVODOMETER, 107767);
				values.put(VEHICLE_COL_DATE, "05-11-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.21);
				values.put(VEHICLE_COL_PREVODOMETER, 108046);
				values.put(VEHICLE_COL_DATE, "05-16-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 11.27);
				values.put(VEHICLE_COL_PREVODOMETER, 108358);
				values.put(VEHICLE_COL_DATE, "05-24-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.81);
				values.put(VEHICLE_COL_PREVODOMETER, 108638);
				values.put(VEHICLE_COL_DATE, "06-02-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 11.90);
				values.put(VEHICLE_COL_PREVODOMETER, 108933);
				values.put(VEHICLE_COL_DATE, "06-13-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 9.42);
				values.put(VEHICLE_COL_PREVODOMETER, 109172);
				values.put(VEHICLE_COL_DATE, "06-24-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 7.14);
				values.put(VEHICLE_COL_PREVODOMETER, 109338);
				values.put(VEHICLE_COL_DATE, "07-04-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				
				values.put(VEHICLE_COL_REFILLPRICE, priceGen.randPrice());
				values.put(VEHICLE_COL_REFILLAMOUNT, 10.28);
				values.put(VEHICLE_COL_PREVODOMETER, 109563);
				values.put(VEHICLE_COL_DATE, "07-22-2012 00:00:00");
				db.insert(VEHICLE_TABLE, null, values);
				values.clear();
				db.setTransactionSuccessful();
				db.endTransaction();
			}catch (SQLException e) {
				
				Log.e(getClass().getName(), "Default insertion failed" + e.getMessage());
			} catch (Exception e) {
				Log.e(getClass().getName(), "Default insertion failed - Uncaught error");
			}
	}
	
	/**
	 * @return Price payed on a certain date.
	 * @param _date : Date for which you're searching the price for
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
 * @return Amount of gas purchased on a certain date.
 * @param _date : Date for which you're searching the price for	
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
 * @return Odometer Reading for specified date
 * @param _date : Date for which you're searching the price for
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
 * @return number of rows in the database. Since we only allow insertion with all 4 entries, it is assumed 
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
 * @return Average price per gallon.
 * 
 * 
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
 * @return Average amount of gas purchased. Sums all volume entries and divides by the number of rows. 
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
	 * @return Calculated average for miles per gallon. This calculation requires at least 2 data 
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
	 * @return Last mileage entry in the database
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
	 * @return Miles Per Gallon between two entries
	 * @deprecated
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
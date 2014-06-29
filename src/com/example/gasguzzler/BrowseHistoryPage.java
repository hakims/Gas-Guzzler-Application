package com.example.gasguzzler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BrowseHistoryPage extends Activity{

	DatabaseHelper dbHelper;
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_history_page);
	
		TableLayout table = (TableLayout) findViewById(R.id.browseTable);
		TableRow rowHeader, rowX;
		
		TextView date = new TextView(this);
		date.setText("Date    ");
		TextView price = new TextView(this);
		price.setText("Amount     \nSpent ($)");
		TextView volume = new TextView(this);
		volume.setText("Amount     \nPurchased \n (gallons)");
		TextView odometer = new TextView(this);
		odometer.setText("   Mileage     \n   (Miles)");
		
		rowHeader = new TableRow(this);
		
		rowHeader.addView(date);
		rowHeader.addView(price);
		rowHeader.addView(volume);
		rowHeader.addView(odometer);
		
		TextView empty = new TextView(this);
		empty.setText("\n");
		
		rowX = new TableRow(this);
		rowX.addView(empty);
		table.addView(rowHeader);
		table.addView(rowX);
		
		
		dbHelper = new DatabaseHelper(getApplicationContext());
	    SQLiteDatabase db = dbHelper.getDatabase();
	    
		String sql = "SELECT date FROM vehicle";
		Cursor cur = db.rawQuery(sql, null);
	
		cur.moveToFirst();
	
			while(cur.isAfterLast() == false) 
			{
		    	
				date = new TextView(this);
				price = new TextView(this);
				volume = new TextView(this);
				odometer = new TextView(this);
				
				rowX = new TableRow(this);
				
				String dbDate = cur.getString(0);
		    //	Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
		    	double dbPrice = dbHelper.getPrice(dbDate);
		    	double dbVolume = dbHelper.getVolume(dbDate);
		    	double dbOdo = dbHelper.getOdometer(dbDate);
		    	
			    date.setText(dbDate.subSequence(0, 11) + "     ");
			    price.setText(String.valueOf(dbPrice) + "     ");
			    volume.setText("   " + String.valueOf(dbVolume) + "     ");
			    odometer.setText("     " + String.valueOf(dbOdo) + "     ");
			    
			    rowX.addView(date);
			    rowX.addView(price);
			    rowX.addView(volume);
			    rowX.addView(odometer);
				
			    table.addView(rowX);
			   
			    cur.moveToNext();
			}
		cur.close();
		db.close();
        dbHelper.close();
    }

    @Override
    protected void onResume() {
    	super.onResume();

       // new AsyncCaller(this).execute();

    }
    
    @Override
    protected void onDestroy()
    {
    	super.onDestroy();
    }
    

}

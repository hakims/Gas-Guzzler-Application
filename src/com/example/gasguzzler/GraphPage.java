package com.example.gasguzzler;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class GraphPage extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
    	
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getDatabase();
    	//db = openOrCreateDatabase("drivingData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    
    	String sql = "SELECT date FROM vehicle";
    	//Log.i("CRASH", "Crash 1");
    	Cursor cur = db.rawQuery(sql, null);
    	//Log.i("CRASH", "Crash 2");
    	cur.moveToFirst();
    	//Log.i("CRASH", "Crash 3");
    	
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
        	      
        	});  
        
        while(cur.isAfterLast() == false) {
        	String date = cur.getString(0);
        //	Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
        	double price = dbHelper.getPrice(date);
        	double volume = dbHelper.getVolume(date);
        	double odo = dbHelper.getOdometer(date);
        	
        	String finalOut = "Date: "+ date + " P: " + price + " V: " + volume + " O: " + odo;
        	Log.i("Accessing data from the Database", finalOut);
        	//Toast.makeText(getApplicationContext(), finalOut, Toast.LENGTH_LONG).show();
        	
        	exampleSeries.appendData(new GraphView.GraphViewData(4, 20), true);
        	//exampleSeries.appendData(new GraphView.GraphViewData(7, 50), true);
        	
        	int p = (int) price;
        	//int p = Integer.parseInt(price);
        	exampleSeries.appendData(new GraphView.GraphViewData(9, p), true);
        	cur.moveToNext();
        }
        cur.close();
        
        	GraphView graphView = new LineGraphView(this, "Gas Data");  
        	graphView.addSeries(exampleSeries); // data  
        	  
        	LinearLayout layout = (LinearLayout) findViewById(R.id.graph_pageLayout);  
        	layout.addView(graphView); 
      
    }
}
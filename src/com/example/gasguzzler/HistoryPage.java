package com.example.gasguzzler;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gasguzzler.DatabaseHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class HistoryPage extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
    	
        /*
        SQLiteDatabase db;
    	db = openOrCreateDatabase("drivingData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    	db.setVersion(1);
    	db.setLocale(Locale.getDefault());
    	db.setLockingEnabled(true);
    	Cursor cur = db.query("VEHICLE_TABLE", null, null, null, null, null, null);
    	
    	cur.moveToFirst();
    	*/
    	
    	
    	
    	/*
    	int numberOfDates = 5;
    	String[] hLabel = new String[numberOfDates];
    	GraphView.GraphViewData[] graphViewData = new GraphView.GraphViewData[numberOfDates];
    	
    	for(int i = 0; i < numberOfDates; i++) {
    		
    		graphViewData[i] = new GraphView.GraphViewData(i, 3);
    		
    		//Horizontal Labels
    		SimpleDateFormat sdf = new SimpleDateFormat("EEE");
    		String day = sdf.format("6-05-2014");
    		hLabel[i] = day;
    		
    		
    	}
    	
    	GraphViewSeries mSeries = new GraphViewSeries("", null, graphViewData);
    	GraphView graphView = new LineGraphView(getApplicationContext(), "");
    	graphView.addSeries(mSeries);
    	graphView.setHorizontalLabels(hLabel);
    	graphView.setVerticalLabels(new String[] {"10", "5", "0"});
    	GraphView mLineGraphView = graphView;
    	*/
    	
    	
    	
    	/*EXTROPOLATES DATA FROM DATABASE
    	 * 
    	 * date1
    	 * date2
    	 * date3
    	 * date4
    	 * date5
    	 * date6
    	 * date7
    	 * for (int i = 0; i < Curser.number, i++){
    	 * 
    	 * 		date + "i"= "date "i's values"    	 */
    	
    	
    	//database.getPrice(_date)
    	
        
       
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
        	      
        	});  
        
        //while(cur.isAfterLast() == false) {
        	//String date = cur.getString(1);
        	//Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
        	//String price = database.getPrice(date);
        	//String volume = database.getVolume(date);
        	//String odo = database.getOdometer(date);
        	
        	//String finalOut = "Date: "+ date + " P: " + price + " V: " + volume + " O: " + odo;
        	//Toast.makeText(getApplicationContext(), finalOut, Toast.LENGTH_LONG).show();
        	
        	exampleSeries.appendData(new GraphView.GraphViewData(4, 20), true);
        	exampleSeries.appendData(new GraphView.GraphViewData(7, 50), true);
        	
        	//int p = Integer.parseInt(price);
        	//exampleSeries.appendData(new GraphView.GraphViewData(1, p), true);
        	//cur.moveToNext();
        //}
        //cur.close();
        
        	GraphView graphView = new LineGraphView(  
        	      this // context  
        	      , "Job Status Graph" // heading  
        	);  
        	graphView.addSeries(exampleSeries); // data  
        	  
        	LinearLayout layout = (LinearLayout) findViewById(R.id.layout);  
        	layout.addView(graphView); 
        	
    }
}
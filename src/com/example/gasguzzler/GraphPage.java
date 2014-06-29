package com.example.gasguzzler;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class GraphPage extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.graph_page);
        
    	
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getDatabase();
    	//db = openOrCreateDatabase("drivingData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    
    	String sql = "SELECT date FROM vehicle";
    	//Log.i("CRASH", "Crash 1");
    	Cursor cur = db.rawQuery(sql, null);
    	//Log.i("CRASH", "Crash 2");
    	cur.moveToFirst();
    	
    	
    	
    	LineGraphView mpgGraphView = new LineGraphView(this, "Average Miles per Gallon");
    	mpgGraphView.setVerticalLabels(new String[] { "60", "50", "40", "30",
                "20", "10", "0"});
    	//mpgGraphView.setHorizontalLabels(new String[] { "January", "February", "March", "April",
       //         "May", "June" });
        
        GraphViewSeriesStyle seriesStyle = new GraphViewSeriesStyle();
        GraphViewSeries exampleSeries2 = new GraphViewSeries(
                "Gas Graph",
                seriesStyle,
                new GraphViewData[] 
                	{ 	new GraphViewData(0, 10),
                        new GraphViewData(1, 20),
                        new GraphViewData(2, 35),
                        new GraphViewData(3, 40),
                        new GraphViewData(4, 20),
                	    new GraphViewData(5, 25),
                	    new GraphViewData(6, 30),
                	    new GraphViewData(7, 37)
                    });
        
        mpgGraphView.setCustomLabelFormatter(new CustomLabelFormatter() {
      	  @Override
      	  public String formatLabel(double value, boolean isValueX) {
      	    if (isValueX) {
      	      if (value == 0) {
      	        return "Entry 1";
      	      } else if (value == 1 ) {
      	        return "Entry 2";
      	      } else if (value == 2){
      	        return "Entry 3";
      	      } else if (value == 3){
      	    	  return "Entry 4";
      	      } else if(value == 4){
      	    	  return "Entry 5";
      	      } else if(value == 5){
      	    	  return "Entry 6";
      	      } else if(value == 6){
      	    	  return "Entry 7";
      	      }
      	      
      	    }
      	    return null; // let graphview generate Y-axis label for us
      	  }
      	});
        
        
        mpgGraphView.setManualYAxisBounds(60, 0);
        mpgGraphView.addSeries(exampleSeries2);
        mpgGraphView.setViewPort(0, 6);
        //mpgGraphView.setScrollable(true);
        //mpgGraphView.setScalable(true);
        mpgGraphView.getGraphViewStyle().setGridColor(Color.DKGRAY);
        
        mpgGraphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
        mpgGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
       // graphView.setDrawBackground(true);
       // graphView.setBackgroundColor(Color.BLUE);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph_pageLayout1);  
    	layout.addView(mpgGraphView); 
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//BarGraphView mpgBarGraph = new BarGraphView(this, "Average Miles per Gallon");
    	//mpgGraphView.setVerticalLabels(new String[] { "60", "50", "40", "30",
               // "20", "10", "0"});
    	//mpgGraphView.setHorizontalLabels(new String[] { "January", "February", "March", "April",
               // "May", "June" });
    	
    	
    	
    	
    	/*
       
        GraphViewSeriesStyle barSeriesStyle = new GraphViewSeriesStyle();
        GraphViewSeries barGraphSeries = new GraphViewSeries(
                "Gas Graph",
                barSeriesStyle,
                new GraphViewData[] 
                	{ 	new GraphViewData(0, 1d),
                        //new GraphViewData(2, 4d),
                       // new GraphViewData(2, 30d),
                       // new GraphViewData(.75, 15d)
                    });
       // mpgGraphView.setManualYAxisBounds(60, 0);
        mpgBarGraph.addSeries(barGraphSeries);
        mpgBarGraph.setViewPort(0, 40);
       // mpgBarGraph.setScrollable(true);
       // mpgBarGraph.setScalable(true);
        
        
        
        mpgBarGraph.setCustomLabelFormatter(new CustomLabelFormatter() {
        	  @Override
        	  public String formatLabel(double value, boolean isValueX) {
        	    if (isValueX) {
        	      if (value < 5) {
        	        return "January";
        	      } else if (value < 15) {
        	        return "February";
        	      } else if (value < 25){
        	        return "big";
        	      } else if (value < 35){
        	    	  return "bigger";
        	      }
        	      
        	    }
        	    return null; // let graphview generate Y-axis label for us
        	  }
        	});
        
        
        
        
        
        mpgBarGraph.getGraphViewStyle().setGridColor(Color.DKGRAY);
        mpgBarGraph.getGraphViewStyle().setNumHorizontalLabels(6);
        mpgBarGraph.getGraphViewStyle().setNumVerticalLabels(6);
        mpgBarGraph.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
        mpgBarGraph.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
       // graphView.setDrawBackground(true);
       // graphView.setBackgroundColor(Color.BLUE);
        
        LinearLayout barGraphLayout = (LinearLayout) findViewById(R.id.graph_pageLayout2);  
    	barGraphLayout.addView(mpgBarGraph); 
    	
        */
        
    	/*
    	*/
    	
    	
    	
    	
    	
    	
    	
    	
    	/*
    	
    	
    	LineGraphView mpgGraphView = new LineGraphView(this, "Average Miles per Gallon"); 
    	
    	GraphViewSeriesStyle seriesStyle = new GraphViewSeriesStyle();
        GraphViewSeries exampleSeries2 = new GraphViewSeries(
                "Gas Graph",
                seriesStyle,
                new GraphViewData[] 
                	{ 	//new GraphViewData(0, 10),
                        //new GraphViewData(1, 20),
                        //new GraphViewData(2, 35),
                        //new GraphViewData(3, 40),
                        //new GraphViewData(4, 20),
                	    //new GraphViewData(5, 25),
                	    //new GraphViewData(6, 30),
                	    //new GraphViewData(7, 37)
                    });
        
        while(cur.isAfterLast() == false) {
        	String date = cur.getString(0);
        //	Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
        	double mpg = 0;
        	double price = dbHelper.getPrice(date);
        	double volume = dbHelper.getVolume(date);
        	double odo1 = dbHelper.getOdometer(date);
        	cur.moveToNext();
        	if (cur.isAfterLast() == true){
        		break;
        	} else{
        		double odo2 = dbHelper.getOdometer(date);
        		mpg = (odo2 - odo1) / volume;
            	cur.moveToPrevious();
            	
        	}
        	
        	
        	//String finalOut = "Date: "+ date + " P: " + price + " V: " + volume + " O: " + odo;
        	//Log.i("Accessing data from the Database", finalOut);
        	//Toast.makeText(getApplicationContext(), finalOut, Toast.LENGTH_LONG).show();
        	
        	exampleSeries2.appendData(new GraphView.GraphViewData(4, mpg), true);
        	//exampleSeries.appendData(new GraphView.GraphViewData(7, 50), true);
        	
        	int p = (int) price;
        	//int p = Integer.parseInt(price);
        	exampleSeries2.appendData(new GraphView.GraphViewData(9, p), true);
        	cur.moveToNext();
        }
        cur.close();
        
        	GraphView graphView = new LineGraphView(this, "Gas Data");  
        	graphView.addSeries(exampleSeries2); // data  
        	  
        	
      */
        	
    	db.close();
    	dbHelper.close();
    }
    
    @Override
	public void onDestroy() {
		super.onDestroy();
	}
}
package com.app.gasguzzler;

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


    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.graph_page);
        
    	/// Calls our database helper and begins initial query database querying 
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getDatabase();
    	String sql = "SELECT date FROM vehicle";
    	Cursor cur = db.rawQuery(sql, null);
    	cur.moveToFirst();
    	
    	
    	
    	LineGraphView mpgGraphView = new LineGraphView(this, "Price Per Gallon");
    	mpgGraphView.setVerticalLabels(new String[] { "60", "50", "40", "30",
                "20", "10", "0"});
    	
        
        GraphViewSeriesStyle seriesStyle = new GraphViewSeriesStyle();
        GraphViewSeries exampleSeries2 = new GraphViewSeries(
        		
                "Price Per Gallon",
                seriesStyle,
                new GraphViewData[] 
                	{ 	
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
      	    return null; /// let graphview generate Y-axis label for us
      	  }
      	});
        
        /**
         * This paragraph is in charge of setting all the attributes of the graphView.  It sets bounds on 
         * the Y axis.  Sets the viewport, Gridcolor, label color, and adds it to the layout. 
         */
        mpgGraphView.setManualYAxisBounds(60, 0);
        mpgGraphView.addSeries(exampleSeries2);
        mpgGraphView.setViewPort(0,6);
        mpgGraphView.getGraphViewStyle().setGridColor(Color.DKGRAY);
        mpgGraphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
        mpgGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph_pageLayout1);  
    	layout.addView(mpgGraphView); 
    	
    	/*
    	 * The paragraph below is a loop that appends each new Gas Guzzle entry onto our graph by looping
    	 * through the database using a cursor to keep track of things.  
    	 */
    	
    	double PPG = 0;
    	double price = 0;
    	double volume = 0;
    	double odo1 = 0;  ///unused
    	int i = 0;
    	while(cur.isAfterLast() == false) {
        	String date = cur.getString(0);
        	price = dbHelper.getPrice(date);
        	volume = dbHelper.getVolume(date);
        	odo1 = dbHelper.getOdometer(date);
        	PPG = (price / volume); 
        	exampleSeries2.appendData(new GraphView.GraphViewData(i, PPG ), false);
        	i++;
        	cur.moveToNext();
        }
        cur.close();
        db.close();
    	dbHelper.close();
    	
   }
    
    @Override
	public void onDestroy() {
		super.onDestroy();
	}
}
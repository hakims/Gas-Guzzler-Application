package com.example.gasguzzler;


import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class HistoryPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
        
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
        	      new GraphViewData(1, 40)  
        	      , new GraphViewData(2, 12)  
        	      , new GraphViewData(3, 7)
        	      , new GraphViewData(2, 8)
        	      , new GraphViewData(2, 10)
        	      , new GraphViewData(3, 26)
        	      , new GraphViewData(1, 37)
        	      , new GraphViewData(1, 53)
        	      , new GraphViewData(3, 253)

        	});  
        	  
        	GraphView graphView = new LineGraphView(  
        	      this // context  
        	      , "Job Status Graph" // heading  
        	);  
        	graphView.addSeries(exampleSeries); // data  
        	  
        	LinearLayout layout = (LinearLayout) findViewById(R.id.layout);  
        	layout.addView(graphView);  
    }
}
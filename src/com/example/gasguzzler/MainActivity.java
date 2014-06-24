package com.example.gasguzzler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	DatabaseHelper database;
	Button next;
	Button toHistory;
	Button toGraphs;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.activity_main);
        
        database = new DatabaseHelper (getApplicationContext());


        next = (Button) findViewById(R.id.NewEntryButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GasPricePage.class);
                startActivityForResult(myIntent, 0);

              //  Toast.makeText(getApplicationContext(), "You went to the next page",
                		//   Toast.LENGTH_LONG).show();


              //  Toast.makeText(getApplicationContext(), "You went to the next page",
                		//   Toast.LENGTH_LONG).show();
            }

        });
        
        toGraphs = (Button) findViewById(R.id.b_toGraphs);
        toGraphs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GraphPage.class);
                
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
        
        toHistory = (Button) findViewById(R.id.b_toHistory);
        toHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), BrowseHistoryPage.class);
                
                startActivityForResult(myIntent, 0);
            }
    });
    }
    

    @Override
	public void onDestroy() {
		super.onDestroy();
		database.close();
	}

    
    
}

package com.example.gasguzzler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SummaryPage extends Activity {
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_page);

        Button done = (Button) findViewById(R.id.b_toHome);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(view.getContext(), GasVolumePage.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });
        
        Button viewHistory = (Button) findViewById(R.id.b_ViewHistory);
        viewHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), HistoryPage.class);
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
        
        Button testQuery = (Button) findViewById(R.id.b_toQuery);
        testQuery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), QueryDatabase.class);
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}


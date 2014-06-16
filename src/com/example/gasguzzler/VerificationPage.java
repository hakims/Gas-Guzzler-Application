package com.example.gasguzzler;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class VerificationPage extends MainActivity {
	
	String price;
	String volume;
	String odometer;
	
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_page);

        
       price = getIntent().getStringExtra("STRING_PRICE");
       volume = getIntent().getStringExtra("STRING_VOLUME");
       odometer = getIntent().getStringExtra("STRING_ODOMETER"); 
        
        Log.i("TEST", " P: " + price + " V: " + volume + " O: " + odometer );
        
        Button back = (Button) findViewById(R.id.b_backtoOdometer);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        Button next = (Button) findViewById(R.id.b_toSummary);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	Calendar c = Calendar.getInstance();
            	SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            	String date = df.format(c.getTime());
            	
            	
            	database.insertData(price, volume, odometer, date);
            	
            	database.insertData("3","50","500","06-17-2014 17:00:00");  //Test cases
            	database.insertData("5","60","125","05-05-2015 15:00:00");
            	
                Intent myIntent = new Intent(view.getContext(), SummaryPage.class);
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}

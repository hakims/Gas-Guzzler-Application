package com.app.gasguzzler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Hakims
 * @deprecated
 * This is a depricated Activity that was used to test proper functionality of database storage
 */

public class QueryDatabase extends MainActivity{

	Button showResults;
	Button next;
	EditText etResults;
	
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_database);

        etResults = (EditText) findViewById(R.id.editText_results);
        
        showResults = (Button) findViewById(R.id.b_ShowResults);
        showResults.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	String date = etResults.getText().toString();
            	double outPrice = database.getPrice(date);
            	double outVolume = database.getVolume(date);
            	double outOdo = database.getOdometer(date);
            	
            	String finalOut = "Date: "+ date + " P: " + outPrice + " V: " + outVolume + " O: " + outOdo;
            	Toast.makeText(getApplicationContext(), finalOut, Toast.LENGTH_LONG).show();
            	
            	Log.i("Database Test", finalOut );
            	
               
            }

        });
        
        
    
    
    }
    
}

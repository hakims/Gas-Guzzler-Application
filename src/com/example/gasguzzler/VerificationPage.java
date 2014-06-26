package com.example.gasguzzler;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class VerificationPage extends Activity {
	
	String price;
	String volume;
	String odometer;
	
	Button back;
	Button toSummary;
	EditText etPrice;
	EditText etVolume;
	EditText etOdometer;
	
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.verification_page);

        final DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); 
        
       price = getIntent().getStringExtra("STRING_PRICE");
       volume = getIntent().getStringExtra("STRING_VOLUME");
       odometer = getIntent().getStringExtra("STRING_ODOMETER"); 
       
       etPrice = (EditText) findViewById(R.id.editText_price);
       etVolume = (EditText) findViewById(R.id.editText_volume);
       etOdometer = (EditText) findViewById(R.id.editText_odometer);
       
       etPrice.setText(price);
       etVolume.setText(volume);
       etOdometer.setText(odometer);
       
        Log.i("Before Changing Verifications", " P: " + price + " V: " + volume + " O: " + odometer );
        
        back = (Button) findViewById(R.id.b_backtoOdometer);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        toSummary = (Button) findViewById(R.id.b_toSummary);
        toSummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	if(etPrice.getText().toString().matches("") || etVolume.getText().toString().matches("") || etOdometer.getText().toString().matches(""))
            		Toast.makeText(getApplicationContext(), "One of your input fields is empty!", Toast.LENGTH_LONG).show();
            	else
            	{
            		Calendar c = Calendar.getInstance();
            		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            		String date = df.format(c.getTime());
            	
            		double p = Double.parseDouble(etPrice.getText().toString());
            		double v = Double.parseDouble(etVolume.getText().toString());
            		double o = Double.parseDouble(etOdometer.getText().toString());
            	
            	
            		dbHelper.insertData(p, v, o, date);
            	
            		dbHelper.close();
            		Intent myIntent = new Intent(view.getContext(), SummaryPage.class);
            		startActivityForResult(myIntent, 0);
            	}
            }
    });
    
    }
    
    @Override
	public void onDestroy() {
		super.onDestroy();
	
	}
}

package com.example.gasguzzler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button bAnalyze;
	EditText etPrice;
	EditText etVolume;
	EditText etOdometer;
	DatabaseHelper database;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        database = new DatabaseHelper (getApplicationContext());
        bAnalyze = (Button) findViewById (R.id.button_analyze);
        etPrice = (EditText) findViewById (R.id.editText_cost);
        etVolume = (EditText) findViewById (R.id.editText_volume);
        etOdometer = (EditText) findViewById (R.id.editText_odometer);
        
        final Context c = this;
        
        bAnalyze.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v)
        	{
        	//	Toast.makeText(getApplicationContext(), database.getRowCount(), Toast.LENGTH_LONG).show();
        		
	        	if( (etPrice.getText().toString() != null) && (etVolume.getText().toString() != null) && (etOdometer.getText().toString() != null))	
	        	{	
	        		String price = etPrice.getText().toString();  ///Pull the data from the text fields
	        		String vol = etVolume.getText().toString();
	        		String odo = etOdometer.getText().toString();
	        	
	        		//Toast.makeText(getApplicationContext(), database.getRowCount(), Toast.LENGTH_LONG).show();
	        	
	        		Calendar c = Calendar.getInstance();
	        		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	        		String formattedDate = df.format(c.getTime()); /// formatted date has the current date
	        		
	        		Toast.makeText(getApplicationContext(), "Price: " + price + " Vol: " + vol + " Odo: " + odo + " Date: " + formattedDate, Toast.LENGTH_LONG).show();
	        		//Toast.makeText(getApplicationContext(), database.getRowCount(), Toast.LENGTH_LONG).show();
	        		database.insertData(price, vol, odo, formattedDate);  
	        		
	        		//database.insertData("10", "2", "20000", "05-17-1994 02:03:17");
	        		//database.insertData("20", "5", "50", "06-01-1999 03:45:00");
	        		
	        		String testP = database.getPrice(formattedDate);
	        		String testV = database.getVolume(formattedDate);
	        		String testO = database.getOdometer(formattedDate);
	        		
	        		//String june = database.getPrice("06-01-1999 03:45:00");
	        		
	        		
	        		Log.i("TEST", "TestP: " + testP + " TestV: " + testV + "testO: " + testO );
	        		
	        	}
	        	else
	        		Toast.makeText(getApplicationContext(), "One of your text fields is empty!", Toast.LENGTH_LONG).show();
        	
        	}
        	
        });
        
    }


    @Override
	public void onDestroy() {
		super.onDestroy();
		database.close();
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

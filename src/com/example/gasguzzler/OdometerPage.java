package com.example.gasguzzler;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OdometerPage extends Activity {
	
	Button back;
	Button next;
	EditText etOdometer;

    final DataProcessor inputProcessor = new DataProcessor();
    DatabaseHelper dbHelper;
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.odometer_page);

        dbHelper = new DatabaseHelper(getApplicationContext());
        
        etOdometer = (EditText) findViewById(R.id.editText_odometer);
        etOdometer.setTextColor(Color.BLACK);
        
        back = (Button) findViewById(R.id.b_backtoVolume);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        next = (Button) findViewById(R.id.b_toVerification);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	String input = etOdometer.getText().toString();
            	
            	if(inputProcessor.isInValidInputEmpty(input))
            		Toast.makeText(getApplicationContext(), "You didn't enter anything!", Toast.LENGTH_LONG).show();
            	else if(Double.parseDouble(input) <= dbHelper.getLastMileage())
                	Toast.makeText(getApplicationContext(), "I didn't know Odometers could be turned backwards?", Toast.LENGTH_LONG).show();
                
            	else
            	{	
            		Intent myIntent = new Intent(view.getContext(), VerificationPage.class);
            		myIntent.putExtra("STRING_ODOMETER", etOdometer.getText().toString());
            		myIntent.putExtra("STRING_PRICE", getIntent().getStringExtra("STRING_PRICE"));
            		myIntent.putExtra("STRING_VOLUME", getIntent().getStringExtra("STRING_VOLUME"));
            		startActivityForResult(myIntent, 0);
            	}	
            }
    });
    
    }
    
    @Override
	public void onDestroy() {
		super.onDestroy();
		dbHelper.close();
	}
    
}

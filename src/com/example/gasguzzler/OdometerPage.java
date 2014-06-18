package com.example.gasguzzler;


import android.app.Activity;
import android.content.Intent;
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
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.odometer_page);

        etOdometer = (EditText) findViewById(R.id.editText_odometer);
        
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
            	
            	if(etOdometer.getText().toString().matches(""))
            		Toast.makeText(getApplicationContext(), "You didn't enter anything!", Toast.LENGTH_LONG).show();
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
    
}

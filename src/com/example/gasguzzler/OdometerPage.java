package com.example.gasguzzler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OdometerPage extends Activity {
	
	Button back;
	Button next;
	EditText etOdometer;
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
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
                Intent myIntent = new Intent(view.getContext(), VerificationPage.class);
                //Intent results = new Intent(view.getContext(), VerificationPage.class);
                myIntent.putExtra("STRING_ODOMETER", etOdometer.getText().toString());
                myIntent.putExtra("STRING_PRICE", getIntent().getStringExtra("STRING_PRICE"));
                myIntent.putExtra("STRING_VOLUME", getIntent().getStringExtra("STRING_VOLUME"));
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}

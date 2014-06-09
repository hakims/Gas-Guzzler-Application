package com.example.gasguzzler;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OdometerPage extends MainActivity {
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odometer_page);

        Button back = (Button) findViewById(R.id.b_backtoVolume);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        Button next = (Button) findViewById(R.id.b_toVerification);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), VerificationPage.class);
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}

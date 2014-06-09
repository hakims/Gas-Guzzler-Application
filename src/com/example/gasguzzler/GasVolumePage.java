package com.example.gasguzzler;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GasVolumePage extends MainActivity {
	
	Button back;
	Button next;
	EditText etVolume;
	
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_volume_page);

        etVolume = (EditText) findViewById(R.id.editText_volume);
        
        back = (Button) findViewById(R.id.Back2);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        next = (Button) findViewById(R.id.Next2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), OdometerPage.class);
                myIntent.putExtra("STRING_VOLUME", etVolume.getText().toString());
                myIntent.putExtra("STRING_PRICE", getIntent().getStringExtra("STRING_PRICE"));
                
                
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}



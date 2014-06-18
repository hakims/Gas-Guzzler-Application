package com.example.gasguzzler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GasVolumePage extends Activity {
	
	Button back;
	Button next;
	EditText etVolume;
	
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
            
            	if(etVolume.getText().toString().matches(""))
            		Toast.makeText(getApplicationContext(), "You didn't enter anything!", Toast.LENGTH_LONG).show();
            	else
            	{
            		Intent myIntent = new Intent(view.getContext(), OdometerPage.class);
            		myIntent.putExtra("STRING_VOLUME", etVolume.getText().toString());
            		myIntent.putExtra("STRING_PRICE", getIntent().getStringExtra("STRING_PRICE"));
                
            		startActivityForResult(myIntent, 0);
            	}
            }
    });
    
    }
    
}



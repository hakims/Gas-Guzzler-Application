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

public class GasVolumePage extends Activity {
	
	Button back;
	Button next;
	EditText etVolume;

    final DataProcessor inputProcessor = new DataProcessor();

	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gas_volume_page);
        

        etVolume = (EditText) findViewById(R.id.editText_volume);
        etVolume.setTextColor(Color.BLACK);
        
        back = (Button) findViewById(R.id.b_backToPrice);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        next = (Button) findViewById(R.id.b_toOdo);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            
            	String input = etVolume.getText().toString();
            	
            	if(inputProcessor.isInValidInputEmpty(input))
            		Toast.makeText(getApplicationContext(), "You didn't enter anything!", Toast.LENGTH_LONG).show();
            	else if(inputProcessor.isInValidInputZero(input))
                	Toast.makeText(getApplicationContext(), "Don't get smart with me!", Toast.LENGTH_LONG).show();
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
    
    @Override
	public void onDestroy() {
		super.onDestroy();
	}
}



package com.app.gasguzzler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Hakims
 * Configures the page where users enter the amount of money they spent at the pump.
 * Upon clicking next the activity sends an intent containing the value entered which will be received
 * by the VerificationPage
 */

public class GasPricePage extends Activity {

	Button back;
	Button next;
	EditText etPrice;
	
	final DataProcessor inputProcessor = new DataProcessor();
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gas_price_page);
        
        etPrice = (EditText) findViewById (R.id.editText_price);
        etPrice.setTextColor(Color.BLACK);
        etPrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});
        
        back = (Button) findViewById(R.id.b_toHome);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        next = (Button) findViewById(R.id.b_toVolume);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
       	
            String input = etPrice.getText().toString();
            	
            if(inputProcessor.isInValidInputEmpty(input))	
            	Toast.makeText(getApplicationContext(), "You didn't enter anything!", Toast.LENGTH_LONG).show();
            else if(inputProcessor.isInValidInputZero(input))
            	Toast.makeText(getApplicationContext(), "Gas isn't Free!", Toast.LENGTH_LONG).show();
            else
            {	
            	Intent myIntent = new Intent(view.getContext(), GasVolumePage.class);
            	myIntent.putExtra("STRING_PRICE", etPrice.getText().toString());   ///send the Price in the intent
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

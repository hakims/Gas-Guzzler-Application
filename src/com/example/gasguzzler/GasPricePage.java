package com.example.gasguzzler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GasPricePage extends MainActivity {

	Button back;
	Button next;
	EditText etPrice;
	
	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_price_page);

        etPrice = (EditText) findViewById (R.id.editText_price);
        
        back = (Button) findViewById(R.id.Back1);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        next = (Button) findViewById(R.id.Next1);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GasVolumePage.class);
                myIntent.putExtra("STRING_PRICE", etPrice.getText().toString());   ///send the Price in the intent
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}

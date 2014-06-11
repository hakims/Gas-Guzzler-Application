package com.example.gasguzzler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	DatabaseHelper database;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        database = new DatabaseHelper (getApplicationContext());


        Button next = (Button) findViewById(R.id.NewEntryButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GasPricePage.class);
                startActivityForResult(myIntent, 0);

              //  Toast.makeText(getApplicationContext(), "You went to the next page",
                		//   Toast.LENGTH_LONG).show();


              //  Toast.makeText(getApplicationContext(), "You went to the next page",
                		//   Toast.LENGTH_LONG).show();
            }

        });
    }
    

    @Override
	public void onDestroy() {
		super.onDestroy();
		database.close();
	}

    
    
}

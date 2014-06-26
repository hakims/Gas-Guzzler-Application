package com.example.gasguzzler;


import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryPage extends Activity {
	/** Called when the activity is first created. */
	
	Button viewGraphs;
	Button done;
	Button viewHistory;
	TextView tvPPG;
	TextView tvMPG;
	TextView tvAvgVol;
	double avgPrice=0, totalVolume=0;
	int rowCount=0;
	double ppg=0, mpg=0, avgVol=0;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.summary_page);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
    
       rowCount = dbHelper.getNumRows(); 
       totalVolume = dbHelper.getTotalVolume();
        
        //Toast.makeText(getApplicationContext(), "What is: " + dbHelper.getTotalVolume() + " What should be: " + totalVolume,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), avgPrice + " = " + dbHelper.getAveragePrice(),Toast.LENGTH_LONG).show();
       //Toast.makeText(getApplicationContext(), rowCount + " = " + dbHelper.getNumRows(),Toast.LENGTH_LONG).show();
       
        
        ppg = dbHelper.getAveragePrice()/rowCount;
       // ppg = avgPrice/rowCount;
       // mpg = implement me
        avgVol = totalVolume/rowCount;
        

        dbHelper.close();
        
        
        tvPPG = (TextView) findViewById(R.id.tv_ppgDisplay);
        tvMPG = (TextView) findViewById(R.id.tv_mpgDisplay);
        tvAvgVol = (TextView) findViewById(R.id.tv_averageVolumeDisplay);
        
        DecimalFormat twoDform = new DecimalFormat("#########.##");
        
        String strPPG = String.valueOf(Double.valueOf(twoDform.format(ppg)));
        String strMPG = String.valueOf(Double.valueOf(twoDform.format(mpg)));
        String strAvgVol = String.valueOf(Double.valueOf(twoDform.format(avgVol)));
        
        tvPPG.setText(strPPG);
        tvMPG.setText(strMPG);
        tvAvgVol.setText(strAvgVol);
        
        done = (Button) findViewById(R.id.b_toHome);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });
        
        viewGraphs = (Button) findViewById(R.id.b_toGraphs);
        viewGraphs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GraphPage.class);
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",
                		   //Toast.LENGTH_LONG).show();
            }
    });
        
        viewHistory = (Button) findViewById(R.id.b_toHistory);
        viewHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), BrowseHistoryPage.class);
                startActivityForResult(myIntent, 0);
                //Toast.makeText(getApplicationContext(), "You went to the next page",Toast.LENGTH_LONG).show();
            }
    });
    
    }
    
}


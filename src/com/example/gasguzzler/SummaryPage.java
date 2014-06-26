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
	Button toHome;
	Button viewHistory;
	TextView tvPPG;
	TextView tvMPG;
	TextView tvAvgVol;
	
	double avgPrice=0, totalVolume=0;
	double ppg=0, mpg=0, avgVol=0;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.summary_page);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
    
        
        totalVolume = dbHelper.getTotalVolume();
        
        ppg = dbHelper.getAveragePrice()/dbHelper.getNumRows();
       
        // mpg = implement me
        avgVol = totalVolume/dbHelper.getNumRows();
        
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
    
    
        ///Initialize the button that sends an intent, pushing the user back to the Main page. 
        
        toHome = (Button) findViewById(R.id.b_toHome);
        toHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

         });
        
         ///Initialize the button that sends an intent, pushing the user to the GraphPage.
        
         viewGraphs = (Button) findViewById(R.id.b_toGraphs);
         viewGraphs.setOnClickListener(new View.OnClickListener() {
         
        	 public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GraphPage.class);
                startActivityForResult(myIntent, 0);
            }
        });
      
        ///Initialize the button that sends an intent, pushing the user to the HistoryPage. 
        
        viewHistory = (Button) findViewById(R.id.b_toHistory);
        viewHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), BrowseHistoryPage.class);
                startActivityForResult(myIntent, 0);
            }
    });
    
    }
    
    ///Override the default hardware back button because leaving it allows database mishaps. Send the user to MainPage
    
    @Override
    public void onBackPressed()
    {
    	Intent myIntent = new Intent(this ,MainActivity.class);
        startActivityForResult(myIntent, 0);
    }
    
}


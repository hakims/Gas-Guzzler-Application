package com.app.gasguzzler;


import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Hakims
 *
 * Page that displays all of the database calculations to the user.
 * Provides buttons to see graphs and history.
 */
public class SummaryPage extends Activity {
	/** Called when the activity is first created. */
	
	Button viewGraphs;
	Button toHome;
	Button viewHistory;
	TextView tvPPG;
	TextView tvMPG;
	TextView tvAvgVol;
	double ppg, mpg, avgVol;
	int numRows;
	DatabaseHelper dbHelper;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.summary_page);

        dbHelper = new DatabaseHelper(getApplicationContext());
        numRows = dbHelper.getNumRows();
        
        ///Access the DB and perform necessary calculations
        ppg = dbHelper.getAveragePricePerGallon();
        avgVol = dbHelper.getAverageVolume();
        mpg = dbHelper.getAverageMPGS();
   
        dbHelper.close();
        
        tvPPG = (TextView) findViewById(R.id.tv_ppgDisplay);
        tvMPG = (TextView) findViewById(R.id.tv_mpgDisplay);
        tvAvgVol = (TextView) findViewById(R.id.tv_averageVolumeDisplay);
        
        
        ///Use DecimalFormat to control input types to only 2 decimal places
        DecimalFormat twoDform = new DecimalFormat("#########.##");
        
        String strPPG = String.valueOf(Double.valueOf(twoDform.format(ppg)));
        String strMPG;
        if( mpg >0)
        	strMPG = String.valueOf(Double.valueOf(twoDform.format(mpg)));
        else
        	strMPG = "Need more data!";
        String strAvgVol = String.valueOf(Double.valueOf(twoDform.format(avgVol)));
        
        strPPG = "Average PPG:  $" + strPPG;
        strMPG = "Average MPG:  " + strMPG ;
        strAvgVol = "Average Volume:  " + strAvgVol + " gallons";
        
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
        		 if(numRows <=1)
             	{
             		Toast.makeText(getApplicationContext(), "You don't have enough data points yet, Start Guzzling!", Toast.LENGTH_LONG).show();
             	}
             	else
             	{
             		Intent myIntent = new Intent(view.getContext(), GraphPage.class);
             		startActivityForResult(myIntent, 0);
             	}
            }
        });
      
        ///Initialize the button that sends an intent, pushing the user to the HistoryPage. 
        
        viewHistory = (Button) findViewById(R.id.b_toHistory);
        viewHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // Intent myIntent = new Intent(view.getContext(), BrowseHistoryPage.class);
               // startActivityForResult(myIntent, 0);
                
                new AsyncCaller(SummaryPage.this).execute();
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
    
    
    @Override
	public void onDestroy() {
		super.onDestroy();
	
	}
}


package com.example.gasguzzler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	DatabaseHelper database;
	Button next;
	Button toHistory;
	Button toGraphs;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
    	//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.activity_main);
        
        database = new DatabaseHelper (getApplicationContext());


        next = (Button) findViewById(R.id.NewEntryButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GasPricePage.class);
                startActivityForResult(myIntent, 0);
            }

        });
        
        toGraphs = (Button) findViewById(R.id.b_toGraphs);
        toGraphs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GraphPage.class);
                
                startActivityForResult(myIntent, 0);
            }
    });
        
        toHistory = (Button) findViewById(R.id.b_toHistory);
        toHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            	if(database.getNumRows() <=0)
            		Toast.makeText(getApplicationContext(), "You don't have a history yet, Start Guzzling!", Toast.LENGTH_LONG).show();
            	else
            	{
            		Intent myIntent = new Intent(view.getContext(), BrowseHistoryPage.class);
            		startActivityForResult(myIntent, 0);
            	}
            }
    });
    }
    

    /*
     * Create options menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	getMenuInflater().inflate(R.menu.main_menu, menu);
    	
    	return true;
    }
    
    
    /*
     * What to do when a menu item is selected
     * */
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	Intent intent;
    	try
    	{
    	
    		switch(item.getItemId())
    		{
    		case R.id.action_aboutGG:
    			intent = new Intent(this.getApplicationContext(), AboutGG.class);
    			startActivity(intent);
    			return super.onOptionsItemSelected(item);
    			
    		default:
    			return super.onOptionsItemSelected(item);
    		
    		}
    	} catch (Exception e)
    	{
    		Log.e (getClass().getSimpleName(), "Unable to process menu command");
    		Toast.makeText(getApplicationContext(), "Unable to process menu command", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	
    }
    
    @Override
    public void onBackPressed()
    {
    	
    }
    
    @Override
	public void onDestroy() {
		super.onDestroy();
		database.close();
	}

    
    
}

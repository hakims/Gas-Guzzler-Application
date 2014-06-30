package com.app.gasguzzler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;


/**
 * @author Hakims
 * Called whenever a user tries to access the BrowseHistory Page. Sets up a new AsyncCaller with the 
 * activity being called from, displays a waiting ProgressDialog (loading wheel), starts the BrowseHistoryPage 
 * and stops showing the loading wheel when BrowseHistory has finished loading everything from the database. 
 * 
 ***/

public class AsyncCaller extends AsyncTask<Void, Void, Void>
{
    ProgressDialog pdLoading;
    public Activity activity;
    
    
    /**
     * 
     * @param a : the activity being passed in. This should be the same activity that you are calling from. Instantiate this 
     * by doing AsyncCaller(this)
     * 
     */
    public AsyncCaller(Activity a)
    {
    	this.activity = a;
    	this.pdLoading = new ProgressDialog(a);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread
        pdLoading.setTitle("Loading Database");
        pdLoading.setMessage("\tPlease Wait While Loading...");
        pdLoading.show();
    }
    @Override
    protected Void doInBackground(Void... params) {

        //this method will be running on background thread so don't update UI frome here
        //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

    	activity.startActivity(new Intent(activity, BrowseHistoryPage.class));
    	return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        //activity.startActivity(new Intent(activity, BrowseHistoryPage.class));
       // Intent myIntent = new Intent(activity.view.getContext(), GraphPage.class);
        
        //activity.startActivityForResult(myIntent, 0);
        //this method will be running on UI thread

        pdLoading.dismiss();
    }

    }

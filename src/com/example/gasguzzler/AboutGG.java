package com.example.gasguzzler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AboutGG extends Activity {

	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.about_gg);
    	
    }
	
	
}

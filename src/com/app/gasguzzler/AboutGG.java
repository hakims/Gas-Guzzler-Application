package com.app.gasguzzler;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.widget.TextView;

/**
 * @author Hakims
 * This Activity is called to display information about the app and its developers
 * **/

public class AboutGG extends Activity {

	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.about_gg);
    	TextView t2 = (TextView) findViewById(R.id.bug_page_link);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    	
    }
	
	
}

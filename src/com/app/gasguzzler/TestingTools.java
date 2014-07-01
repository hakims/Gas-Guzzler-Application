package com.app.gasguzzler;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import android.content.Context;

/**
 * 
 * 
 * @author Hakims
 * Class used for testing purposes. Mainly it is used to generate random data values.
 */

public class TestingTools {

	DatabaseHelper dbHelper;
	Context c = null;
	
	DecimalFormat twoDform = new DecimalFormat("#########.##");
	
	
	public TestingTools(Context c)
	{
		this.c = c;
	}
	
	public TestingTools()
	{
		
	}
	
	/**
	 * Was used to prepopulate the DB. This is now done in DBHelper.java
	 * 
	 * @return void
	 * @deprecated
	 */
	
	void initializeDB()
	{
		dbHelper = new DatabaseHelper(c);
		
		dbHelper.eraseDBContents();
		
		double price;
		double vol;
		double odo = 0;
		String date;
		
		/*
		for(int i = 0; i<150; i++)
		{
			price = Double.valueOf(twoDform.format(randPrice()));
			vol = Double.valueOf(twoDform.format(randVol()));
			odo = Double.valueOf(twoDform.format(randOdo(odo)));
			date = randDate();
			dbHelper.insertData(price, vol, odo, date);
			
		
		}
		*/
		//dbHelper.close();
		return;
	}
	
	/**
	 * 
	 * @return random price between $0.01 and $159.99. Ensures values are limited to 2 decimal places.
	 */
	
	double randPrice()
	{
		Random r = new Random();
		double min = 0.01;
		double max = 159.99;
		double rand = min + (max - min) * r.nextDouble();
		
		rand = Double.valueOf(twoDform.format(rand));
		
		return rand;
	}
	
	/**
	 * 
	 * @return random volume from 0.01 to 100 gallons. Ensures values are limited to 2 decimal places
	 */
	
	double randVol()
	{
		Random r = new Random();
		double min = 0.01;
		double max = 100.00;
		double rand = min + (max - min) * r.nextDouble();
		
		rand = Double.valueOf(twoDform.format(rand));
		
		return rand;
	}
	/**
	 * 
	 * @param min: minimum odometer reading
	 * @return a random odometer reading that is larger than min. Tries to scale the random numbers from min to min+1000
	 * unless that value is greater than 999999.99
	 */
	
	double randOdo(double min)
	{
		Random r = new Random();
		double max;
		if(min + 10000 > 999999.99)
			max = 999999.99;
		else
			max = min + 10000;
		
		double rand = min + (max - min) * r.nextDouble();
		rand = Double.valueOf(twoDform.format(rand));
		
		return rand;
	}
	
	/**
	 * 
	 * @return random date from 1900-2025
	 */
	
	String randDate()
	{
		GregorianCalendar gc = new GregorianCalendar();

		String rand = "  ";
		
        int year = randBetween(1900, 2025);

        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        
        rand += gc.get(Calendar.MONTH) + "-" + gc.get(Calendar.DAY_OF_MONTH) +  "-" + gc.get(Calendar.YEAR) + " " ;

        return rand;
    
	}
	
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
}

package com.example.gasguzzler;

import android.content.Context;

public class DataProcessor {

	public DataProcessor()
	{	
	}
	
	/*
	 * Helper function to process user inputs. Return false
	 * if the input is empty 
	 * */
	public boolean isInValidInputEmpty(String input)
	{
		return input.matches("");
	}
	
	/*
	 * Helper function to process user inputs. Return false
	 * if the input is Zero 
	 * */
	public boolean isInValidInputZero(String input)
	{
		return input.matches(String.valueOf(0.0)) || input.matches(String.valueOf(0));
	}
	
}

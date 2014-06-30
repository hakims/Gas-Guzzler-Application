package com.app.gasguzzler;


/**
 * 
 * @author Hakims
 *
 * The purpose of this class is to handle user inputs and verify they are valid nonzero, nonempty entries.	
 */

public class DataProcessor {

	public DataProcessor()
	{	
	}
	
	/**
	 * Helper function to process user inputs
	 * @param input: input to be processed
	 * @return true if input is empty
	 * */
	public boolean isInValidInputEmpty(String input)
	{
		return input.matches("");
	}
	
	/**
	 * Helper function to process user inputs
	 * @param input: input to be processed
	 * @return true if input is 0
	 * */
	public boolean isInValidInputZero(String input)
	{
		return input.matches(String.valueOf(0.0)) || input.matches(String.valueOf(0)) || Double.parseDouble(input) == 0.00;
	}
	
}

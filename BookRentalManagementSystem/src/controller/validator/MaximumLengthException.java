package controller.validator;

public class MaximumLengthException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public MaximumLengthException(String field, int maximum) 
	{
		super(field  + " must be less than or equals to " + maximum + " characters.");
	}

}

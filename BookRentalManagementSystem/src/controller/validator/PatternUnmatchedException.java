package controller.validator;

public class PatternUnmatchedException extends Exception {

	private static final long serialVersionUID = 1L;

	public PatternUnmatchedException(String field) 
	{
		super(field + " is not in the correct format.");
	}

}

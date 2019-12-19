package controller.validator;

public class Validator {

	public static String validate(String field,String value,boolean required,int maximum)
	throws RequiredFieldException,MaximumLengthException, PatternUnmatchedException
	{
		if(required &&(value==null||value.trim().isEmpty()))
			throw new RequiredFieldException(field);
		
		value=value.trim();
		
		if(value.length()>maximum)
			throw new MaximumLengthException(field,maximum);
		
		if(field=="Matric No" && !value.matches("[\bB0-9|\bD0-9|\bb0-9|\bd0-9]{10}"))
			throw new PatternUnmatchedException(field);
		
		if(field=="Name" && value.matches("[!#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]"))
			throw new PatternUnmatchedException(field);
		
		if(field=="ISBN" && !value.matches("[0-9]{13}"))
			throw new PatternUnmatchedException(field);
		
		
		
		return value;
	}
}

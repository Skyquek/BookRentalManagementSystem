package controller.validator;

public class Validator {

	public static String validate(String field,String value,boolean required,int maximum)
	throws RequiredFieldException,MaximumLengthException
	{
		if(required &&(value==null||value.trim().isEmpty()))
			throw new RequiredFieldException(field);
		
		value=value.trim();
		
		if(value.length()>maximum)
			throw new MaximumLengthException(field,maximum);
		
		return value;
	}
}

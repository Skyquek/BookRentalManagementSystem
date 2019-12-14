package model;

public class Student extends Model{
	private String matricNo;
	private String name ;
	
	public String getMatricNo() {
		return matricNo;
	}
	
	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

package model;

public class Student extends Model{
	private String matricNo;
	private String name ;
	private String course;
	
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
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
}

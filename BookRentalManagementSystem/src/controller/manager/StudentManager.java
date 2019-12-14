package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Student;

public class StudentManager {
	private static Vector<Student> students = new Vector<>();
	
	public static int addStudent(Student student) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("INSERT INTO student(matricNo, name) VALUES (?, ?)");
				
		ps.setString(1,  student.getMatricNo());
		ps.setString(2, student.getName());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	
	public static Vector<Student> getStudents() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM student");
		ResultSet rs = ps.executeQuery();
		Vector<Student> students = new Vector<>();
		
		while(rs.next()) {
			Student student = new Student();
			
			student.setMatricNo(rs.getString(1));
			student.setName(rs.getString(2));
			
			students.add(student);
			connection.close();
			return students;
		}
		return new Vector<>(students);
	}
	
	
	public static int deleteStudent(String matricNo) throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE matricNo=?");
				
		ps.setString(1,  matricNo);
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	
	public int updateStudent(Student student)throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("UPDATE student SET(name) VALUES (?, ?) WHERE matricNo = ?");
				
		ps.setString(1, student.getName());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
		
	}
}
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
		//return students.add(student) ? 1:0;
		return new Vector<>(students);
	}
	
	
	public void viewStudent()
	{
		
	}
	
	public static int deleteStudent(String matricNo)
	{
		int index = -1;

		for (int i = 0; i < students.size(); i++){
			Student temp = students.get(i);

			if(temp != null && (temp.getMatricNo() == matricNo)){
				
				index = i;
				break;
			}
		}

		return students.remove(index) != null ? 1 : 0;
	}
	
	public int updateStudent(Student student)
	{
		int index = -1;

		for (int i = 0; i < students.size(); i++)
		{
			Student temp = students.get(i);

			if(temp != null && (temp.getMatricNo() == student.getMatricNo()))
			{
				students.set(index, student);
				index = i;

				break;
			}
		}

		return index;
	}
}

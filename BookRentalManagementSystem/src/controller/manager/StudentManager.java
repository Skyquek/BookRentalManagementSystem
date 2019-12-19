package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import model.Student;

public class StudentManager {
	
	public static int addStudent(Student student) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("INSERT INTO student(matricNo, name) VALUES (?, ?)");
				
		ps.setString(1,  student.getMatricNo());
		ps.setString(2, student.getName());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	
	public static Object[][] getStudents() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");

		PreparedStatement ps = connection.prepareStatement("SELECT * FROM student");
		PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(*) AS rowcount FROM student");

		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = ps2.executeQuery();
		rs2.next();

		int rowsNumber=rs2.getInt("rowcount");
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		// Convert ResultSet to 2D Java Object
		Object[][] resultSet = new Object[rowsNumber][columnsNumber];
        int row = 0;
        while (rs.next())
        {
            for (int i = 0; i < columnsNumber; i++) {
                resultSet[row][i] = rs.getObject(i+1);
            }
            row++;
        }

		return resultSet;
	}
	
	
	public static int deleteStudent(String matricNo) throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE matricNo=?");
				
		ps.setString(1,  matricNo);
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	public static Object[] getStudentsMatric() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");

		PreparedStatement ps = connection.prepareStatement("SELECT matricNo FROM student");
		PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(*) AS rowcount FROM student");

		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = ps2.executeQuery();
		rs2.next();

		int rowsNumber=rs2.getInt("rowcount");

		// Convert ResultSet to 2D Java Object
		Object[] resultSet = new Object[rowsNumber];
        int row = 0;
        while (rs.next())
        {
            resultSet[row] = rs.getObject(1);
            
            row++;
        }
        System.out.println(resultSet);
		return resultSet;
	}
	
	
	public static int updateStudent(Student student)throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("UPDATE student SET name = ? WHERE matricNo = ?");
				
		ps.setString(1, student.getName());
		ps.setString(2, student.getMatricNo());
		
		int status = ps.executeUpdate();
		connection.close();
		return status;
		
	}
}
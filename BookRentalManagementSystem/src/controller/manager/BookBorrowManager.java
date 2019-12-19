package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class BookBorrowManager {

	public static Object[][] getBookBorrows() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");

		PreparedStatement ps = connection.prepareStatement("SELECT * FROM rental");
		PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(*) AS rowcount FROM rental");

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


	public static int borrowBook(String matricNo, String isbn) throws SQLException, ClassNotFoundException
	{
		// Date
		long millis=System.currentTimeMillis();  
		java.sql.Date date = new java.sql.Date(millis);  
		int status = 0;
		
		// Database connection
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM rental WHERE ISBN=?");
		ps.setString(1, isbn);
		
		ResultSet available_book = ps.executeQuery();		
		boolean check_book = available_book.first();
			    
		if(check_book == false)
		{
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement newRental = connection.prepareStatement("INSERT INTO rental(matricNo, ISBN, dateStart) VALUES (?, ?, ?)");
			
			//ps.setInt(1,  bookBorrow.getRentalID());
			newRental.setString(1,  matricNo);
			newRental.setString(2,  isbn);
			newRental.setDate(3, date);
			
			status = newRental.executeUpdate();
			connection.close();
		}
		// Check whether the Query return another value
		return status;
	}
	
	public static Vector<Long> returnBook(String isbn) throws SQLException, ClassNotFoundException
	{
		// First result is status, second is price
		Vector<Long> result = new Vector<Long>();
		result.add((long) 0);
		result.add((long) 0);

		// Date
		long millis=System.currentTimeMillis();  
		java.sql.Date date = new java.sql.Date(millis);
		int status = 0;
		long delayTime = 0; 
		long penaltyPrice = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
				
		// Get Start Date
		PreparedStatement ps_date = connection.prepareStatement("SELECT dateStart FROM rental WHERE ISBN=?");
		ps_date.setString(1,  isbn);
		ResultSet rs_date = ps_date.executeQuery();
		
		while(rs_date.next())
		{
			java.sql.Date old_date = rs_date.getDate("dateStart");
			
			//System.out.println(old_date);
		    //java.sql.Date jsqlD = java.sql.Date.valueOf("2010-01-31");
		    
			long diffInMillies = Math.abs(date.getTime() - old_date.getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			    
			// Get Date Duration
			System.out.println(diff);
			
			if(diff > 7)
			{
				// Penalty
				delayTime = diff - 7;
				penaltyPrice = delayTime * 2;
			}
			
			// Delete from the book
			PreparedStatement ps = connection.prepareStatement("DELETE FROM rental WHERE ISBN = ? ");
			
			ps.setString(1, isbn);
			status = ps.executeUpdate();
			
			
			System.out.println("STATUS "+ status);
			
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
				
			// Prepare result to send 
			result.set(0, (long) 1);
			result.set(1, penaltyPrice);
			
		}
		
		return result;
	}
}
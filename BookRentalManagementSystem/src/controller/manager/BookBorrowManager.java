package controller.manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BookBorrow;

public class BookBorrowManager {
	
private static Vector<BookBorrow> bookBorrows = new Vector<>();
	
	public static int addBookBorrow(BookBorrow bookBorrow) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("INSERT INTO rental(matricNo, ISBN, dateStart, rentalFees) VALUES (?, ?, ?, ?)");
				
		//ps.setInt(1,  bookBorrow.getRentalID());
		ps.setString(2,  bookBorrow.getMatricNo());
		ps.setString(3,  bookBorrow.getISBN());
		ps.setDate(4, (Date)bookBorrow.getDateStart());
		//ps.setDate(5, (Date)bookBorrow.getDateEnd());
		ps.setFloat(6, bookBorrow.getRentalFees());
		
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	
	public static Vector<BookBorrow> getBookBorrows() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM rental");
		ResultSet rs = ps.executeQuery();
		Vector<BookBorrow> bookBorrows = new Vector<>();
		
		while(rs.next()) {
			BookBorrow bookBorrow = new BookBorrow();
			
			bookBorrow.setRentalID(rs.getInt(1));
			bookBorrow.setMatricNo(rs.getString(2));
			bookBorrow.setISBN(rs.getString(3));
			bookBorrow.setDateStart(rs.getDate(4));
			bookBorrow.setDateEnd(rs.getDate(5));
			bookBorrow.setRentalFees(rs.getFloat(6));
			
			
			bookBorrows.add(bookBorrow);
			connection.close();
			return bookBorrows;
		}
		//return students.add(student) ? 1:0;
		return new Vector<>(bookBorrows);
	}


	public static void borrowBook(String matricNo, String isbn) throws SQLException, ClassNotFoundException
	{
		// Date
		long millis=System.currentTimeMillis();  
		java.sql.Date date = new java.sql.Date(millis);  

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM rental WHERE ISBN=?");
		
		ps.setString(1, isbn);
		
		ResultSet rs = ps.executeQuery();
		
		Vector<BookBorrow> bookBorrows = new Vector<>();
		// If book is equal to null, this mean that the book is not rent by other people
		if(rs == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			//PreparedStatement newRental = connection.prepareStatement("INSERT INTO rental(rentalID, matricNo, ISBN, dateStart, dateEnd, rentalFees) VALUES (?, ?, ?, ?)");
			PreparedStatement newRental = connection.prepareStatement("INSERT INTO rental(matricNo, ISBN, dateStart) VALUES (?, ?, ?)");
			
			//ps.setInt(1,  bookBorrow.getRentalID());
			ps.setString(1,  matricNo);
			ps.setString(2,  isbn);
			ps.setDate(3, date);
			
			int status = ps.executeUpdate();
			connection.close();
		}
		// Check whether the Query return another value
	}
	
	public void returnBook(String matricNo, String isbn) throws SQLException, ClassNotFoundException
	{
		// Date
		long millis=System.currentTimeMillis();  
		java.sql.Date date = new java.sql.Date(millis);
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
				
		// Get Start Date
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement ps_date = connection.prepareStatement("SELECT dateStart FROM rental WHERE ISBN=?");
		
		ps_date.setString(1,  isbn);
		
		ResultSet rs_date = ps_date.executeQuery();
		connection.close();
		
		// Haven't implement the duration of book
		
		
		// Delete from the book
		PreparedStatement ps = connection.prepareStatement("DELETE FROM rental WHERE ISBN = ? AND matricNo = ?");
		
		ps.setString(1, isbn);
		ps.setString(2, matricNo);
		
		ResultSet rs = ps.executeQuery();
		
		connection.close();
		
		Vector<BookBorrow> bookBorrows = new Vector<>();
		
		// If book is equal to null, this mean that the book is not rent by other people
		
		
		
	}
}
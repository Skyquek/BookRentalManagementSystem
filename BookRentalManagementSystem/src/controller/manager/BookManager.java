package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Book;

public class BookManager {
	private static Vector<Book> books = new Vector<>();
	
	public static int addBook(Book book) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("INSERT INTO book(ISBN, Title, Author) VALUES (?, ?, ?)");
				
		ps.setString(1,  book.getISBN());
		ps.setString(2, book.getTitle());
		ps.setString(3, book.getAuthor());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	
	public static Vector<Book> getBooks() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM book");
		ResultSet rs = ps.executeQuery();
		Vector<Book> books = new Vector<>();
		
		while(rs.next()) {
			Book book = new Book();
			
			book.setISBN(rs.getString(1));
			book.setTitle(rs.getString(2));
			book.setAuthor(rs.getString(3));
			
			books.add(book);
			connection.close();
			return books;
		}
		//return books.add(book) ? 1:0;
		return new Vector<>(books);
	}
	
	
	public static int deleteBook(String bookISBN)throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE ISBN =?");
				
		ps.setString(1,  bookISBN);
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	public int updateBook(Book book) throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("UPDATE book SET(Title, Author) VALUES (?, ?) WHERE ISBN = ?");
				
		ps.setString(2, book.getTitle());
		ps.setString(3, book.getAuthor());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
		
	}
}
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
	
	
	public Vector<Book> viewBook()
	{
		return new Vector<>(books);
	}
	/*
	public int deleteBook(String bookISBN)
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", "");
		PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE ISBN =?");
				
		ps.setString(1,  book.getISBN());
		ps.setString(2, book.getTitle());
		ps.setString(2, book.getAuthor());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
		
		int index = -1;

		for (int i = 0; i < books.size(); i++){
			Book temp = books.get(i);

			if(temp != null && (temp.getISBN() == bookISBN)){
				// cars[i] = null;
				index = i;

				break;
			}
		}

		return books.remove(index) != null ? 1 : 0;
	}
	*/
	public int updateBook(Book book) 
	{
		int index = -1;

		for (int i = 0; i < books.size(); i++)
		{
			Book temp = books.get(i);

			if(temp != null && (temp.getISBN() == book.getISBN()))
			{
				books.set(index, book);
				index = i;

				break;
			}
		}

		return index;
	}
}

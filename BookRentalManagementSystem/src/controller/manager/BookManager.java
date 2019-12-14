package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import model.Book;

public class BookManager {
	private static Vector<Book> books = new Vector<>();
	
	public static int addBook(Book book) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("INSERT INTO book(ISBN, Title, Author) VALUES (?, ?, ?)");
				
		ps.setString(1,  book.getISBN());
		ps.setString(2, book.getTitle());
		ps.setString(3, book.getAuthor());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	
	public static Object[][] getBooks() throws SQLException, ClassNotFoundException
	{

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");

		PreparedStatement ps = connection.prepareStatement("SELECT * FROM book");
		PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(*) AS rowcount FROM book");

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
	
	
	public static int deleteBook(String bookISBN)throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE ISBN =?");
				
		ps.setString(1,  bookISBN);
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
	}
	
	public int updateBook(Book book) throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brms", "root", " ");
		PreparedStatement ps = connection.prepareStatement("UPDATE book SET(Title, Author) VALUES (?, ?) WHERE ISBN = ?");
				
		ps.setString(2, book.getTitle());
		ps.setString(3, book.getAuthor());
	
		int status = ps.executeUpdate();
		connection.close();
		return status;
		
	}
}
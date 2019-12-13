package controller.manager;

import java.util.Vector;
import model.Book;

public class BookManager {
	private static Vector<Book> books = new Vector<>();
	
	public static int addBook(Book book)
	{
		return books.add(book) ? 1:0;
	}
	
	public Vector<Book> viewBook()
	{
		return new Vector<>(books);
	}
	
	public static int deleteBook(String bookISBN)
	{
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

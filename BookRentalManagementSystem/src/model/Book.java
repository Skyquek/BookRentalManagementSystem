package model;

public class Book extends Model{
	private String ISBN;
	private String title;
	private String bookType;
	private String author;
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBookType() {
		return bookType;
	}
	
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}

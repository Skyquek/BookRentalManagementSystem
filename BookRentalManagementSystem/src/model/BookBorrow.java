package model;

import java.util.Date;

public class BookBorrow extends Model{
	private int rentalID;
	private String matricNo;
	private String ISBN;
	private Date dateStart;
	private Date dateEnd;
	private float rentalFees;
	
	public int getRentalID() {
		return rentalID;
	}
	
	public void setRentalID(int rentalID) {
		this.rentalID = rentalID;
	}
	
	public String getMatricNo() {
		return matricNo;
	}
	
	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public Date getDateStart() {
		return dateStart;
	}
	
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public float getRentalFees() {
		return rentalFees;
	}
	
	public void setRentalFees(float rentalFees) {
		this.rentalFees = rentalFees;
	}
	
	
}

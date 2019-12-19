package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.manager.BookManager;
import controller.manager.StudentManager;

public class ManageRentalsDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnBorrowBook = new JButton("Borrow Book");
	private JButton btnReturnBook = new JButton("Return Book");
	private JButton btnViewBorrowRecords = new JButton("View Borrow Records");

	public ManageRentalsDialog(MainFrame frame) 
	{
		super(frame,"Manage Students",true);
		
		GridLayout layout = new GridLayout(3,1);
		
		btnBorrowBook.addActionListener(this);
		btnReturnBook.addActionListener(this);
		btnViewBorrowRecords.addActionListener(this);
		
		btnBorrowBook.setBackground(Color.WHITE);
		btnReturnBook.setBackground(Color.WHITE);
		btnViewBorrowRecords.setBackground(Color.WHITE);
		
		btnBorrowBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 40)));
		btnReturnBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 40)));
		btnViewBorrowRecords.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 40)));
		
		this.add(btnBorrowBook);
		this.add(btnReturnBook);
		this.add(btnViewBorrowRecords);
		
		this.setLayout(layout);
		this.pack();		//modal dialog= while open cant interact with parent frame
		this.setLocationRelativeTo(frame);//appear in center of parent frame
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		if(source==btnBorrowBook)
		{
			try 
			{
				Object[] bookOptions = BookManager.getAvailableBooksISBN();
				Object[] studentOptions = StudentManager.getStudentsMatric();
				 
				Object selectedStudent = JOptionPane.showInputDialog(this, "Select student: ", "Borrow Book", JOptionPane.QUESTION_MESSAGE, null, studentOptions, studentOptions[0]);
				
				if(selectedStudent!=null)
				{
					Object selectedBook = JOptionPane.showInputDialog(this, "Select book: ", "Borrow Book", JOptionPane.QUESTION_MESSAGE, null, bookOptions, bookOptions[0]);
					
					if(selectedBook!=null)
					{
						new BorrowBookDialog(this,selectedBook.toString(),selectedStudent.toString());
					}
				}
				
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}	
		}
		else if(source==btnReturnBook)
		{
			new ReturnBookDialog(this);
		}
		else if(source==btnViewBorrowRecords)
		{
			new ViewRentalDialog(this);
		}

	}

}

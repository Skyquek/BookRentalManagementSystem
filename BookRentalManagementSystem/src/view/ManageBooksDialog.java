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

public class ManageBooksDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	private JButton btnAddBook = new JButton("Add Book");
	private JButton btnViewBook = new JButton("View Book");
	private JButton btnUpdateBook = new JButton("Update Book");
	private JButton btnDeleteBook = new JButton("Delete Book");
	
	public ManageBooksDialog(MainFrame frame)
	{
		super(frame,"Manage Books",true);
		
		GridLayout layout = new GridLayout(2,2);
		
		btnAddBook.addActionListener(this);
		btnViewBook.addActionListener(this);
		btnUpdateBook.addActionListener(this);
		btnDeleteBook.addActionListener(this);
		
		btnAddBook.setBackground(Color.WHITE);
		btnViewBook.setBackground(Color.WHITE);
		btnUpdateBook.setBackground(Color.WHITE);
		btnDeleteBook.setBackground(Color.WHITE);
		
		btnAddBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		btnViewBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		btnUpdateBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		btnDeleteBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		
		this.add(btnAddBook);
		this.add(btnViewBook);
		this.add(btnUpdateBook);
		this.add(btnDeleteBook);
		
		this.setLayout(layout);
		this.pack();		//modal dialog= while open cant interact with parent frame
		this.setLocationRelativeTo(frame);//appear in center of parent frame
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source=event.getSource();
		
		if(source==btnAddBook)
		{
			new AddBookDialog(this);
		}
		else if(source==btnViewBook)
		{
			new ViewBookDialog(this);
		}
		else if(source==btnUpdateBook)
		{
			Object[] options;
			try 
			{
				options = BookManager.getBooksISBN();
				Object selectedValues = JOptionPane.showInputDialog(this, "Select book ISBN: ", "Update Book", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(selectedValues!=null)
				{
					new UpdateBookDialog(this,selectedValues.toString());
				}	
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}			
		}
		else if(source==btnDeleteBook)
		{
			Object[] options;
			try 
			{
				options = BookManager.getBooksISBN();
				Object selectedValues = JOptionPane.showInputDialog(this, "Select book ISBN: ", "Delete Book", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(selectedValues!=null)
				{
					new DeleteBookDialog(this,selectedValues.toString());
				}
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}		
			
		}
			
	}

}

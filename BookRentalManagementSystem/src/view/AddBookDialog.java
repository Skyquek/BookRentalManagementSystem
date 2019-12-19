package view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.manager.BookManager;
import controller.validator.MaximumLengthException;
import controller.validator.PatternUnmatchedException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Book;

public class AddBookDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtISBN = new JTextField(20);
	private JTextField txtTitle = new JTextField(20);
	private JTextField txtAuthor = new JTextField(20);
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	

	public AddBookDialog(ManageBooksDialog dialog) 
	{
		super(dialog,"Add Book",true);
		
		JPanel pnlCenter = new JPanel(new GridLayout(7,1,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		pnlCenter.add(new JLabel("ISBN: *", JLabel.LEFT));
		pnlCenter.add(txtISBN);
		pnlCenter.add(new JLabel("Title: *", JLabel.LEFT));
		pnlCenter.add(txtTitle);
		pnlCenter.add(new JLabel("Author: *", JLabel.LEFT));
		pnlCenter.add(txtAuthor);
		pnlCenter.add(new JLabel("* Required"));
		
		pnlSouth.add(btnSubmit);
		pnlSouth.add(btnReset);
		
		this.add(pnlCenter);
		this.add(pnlSouth,BorderLayout.SOUTH);
		
		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);
		
		
		
		this.getRootPane().setDefaultButton(btnSubmit);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(dialog);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source=event.getSource();
		
		if(source==btnSubmit)
		{
			Vector<Exception> exceptions= new Vector<>();
			String ISBN=null,Title=null,Author=null;
			
			try
			{
				ISBN=Validator.validate("ISBN", txtISBN.getText(), true, 13);
			}
			catch (RequiredFieldException | MaximumLengthException | PatternUnmatchedException e) 
			{
				exceptions.add(e);
			}
			
			try
			{
				Title=Validator.validate("Title", txtTitle.getText(), true, 40);
			}
			catch (RequiredFieldException | MaximumLengthException | PatternUnmatchedException e) 
			{
				exceptions.add(e);
			}
			
			try
			{
				Author=Validator.validate("Author", txtAuthor.getText(), true, 30);
			}
			catch (RequiredFieldException | MaximumLengthException | PatternUnmatchedException e) 
			{
				exceptions.add(e);
			}
			
			int size=exceptions.size();
			
			if(size==0)
			{
				Book book = new Book();
				book.setISBN(ISBN);
				book.setTitle(Title);
				book.setAuthor(Author);
				
				try 
				{
					if(BookManager.addBook(book)!=0)
					{
						JOptionPane.showMessageDialog(this, "Book with ISBN: " + book.getISBN() + 
						" has been successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					else
						JOptionPane.showMessageDialog(this, "Unable to add new book.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
				} 
				catch (HeadlessException | ClassNotFoundException | SQLException e) 
				{
					if(e.getMessage() != null) 
					{
						JOptionPane.showMessageDialog(this, "Book with ISBN: ." + book.getISBN() + " already exists.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
					}			
				}
			}
			else
			{
				String message=null;
				if(size==1)
					message=exceptions.firstElement().getMessage();
				else
				{
					message="PLease fix the following errors: ";
					
					for(int i=0;i<size;i++)
						message+="\n"+(i+1)+"."+exceptions.get(i).getMessage();
				}
				JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(source==btnReset)
		{
			txtISBN.setText("");
			txtTitle.setText("");
			txtAuthor.setText("");
		
		}
	}

}

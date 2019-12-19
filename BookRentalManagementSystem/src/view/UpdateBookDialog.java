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

public class UpdateBookDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtTitle = new JTextField();
	private JTextField txtAuthor = new JTextField();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	private JTextField selectedIc = new JTextField();
	
	public UpdateBookDialog(ManageBooksDialog dialog,String txtIc) throws HeadlessException 
	{
		super(dialog,"Update Book",true);
		
		selectedIc.setEditable(false);
		selectedIc.setText(txtIc);
		
		JPanel pnlCenter = new JPanel(new GridLayout(7,1,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		pnlCenter.add(new JLabel("Selected Book: ", JLabel.LEFT));
		pnlCenter.add(selectedIc);
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
			String Title =null;
			String Author =null;
			
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
				Author=Validator.validate("Author", txtAuthor.getText(), true, 40);
			}
			catch (RequiredFieldException | MaximumLengthException | PatternUnmatchedException e) 
			{
				exceptions.add(e);
			}
			
			int size=exceptions.size();
			
			if(size==0)
			{
				Book book = new Book();
				book.setISBN(selectedIc.getText());
				book.setTitle(txtTitle.getText());
				book.setAuthor(txtAuthor.getText());
				
				try 
				{
					if(BookManager.updateBook(book)!=0)
					{
						JOptionPane.showMessageDialog(this, "Book with ISBN: " + book.getISBN() + 
						" has been successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					else
						JOptionPane.showMessageDialog(this, "Unable to update book " + book.getISBN(),"Unsuccessful",JOptionPane.WARNING_MESSAGE);
				} 
				catch (HeadlessException | ClassNotFoundException | SQLException e) 
				{
					if(e.getMessage() != null) 
					{
						JOptionPane.showMessageDialog(this, "Failed to update book with ISBN: ." + book.getISBN() + ".","Unsuccessful",JOptionPane.WARNING_MESSAGE);
						e.printStackTrace();
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
			txtTitle.setText("");
			txtAuthor.setText("");
		}

	}

}

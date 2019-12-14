package view;
import java.lang.*;
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

import controller.manager.BookBorrowManager;
import controller.validator.MaximumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Book;
import model.Student;

public class ReturnBookDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnSubmit = new JButton ("Submit");
	private JButton btnReset = new JButton ("Reset");
	private JTextField txtISBN = new JTextField();

	public ReturnBookDialog(ManageRentalsDialog dialog) 
	{
		super(dialog,"Return Book",true);
		

		JPanel pnlCenter = new JPanel(new GridLayout(1,2,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		pnlCenter.add(new JLabel("ISBN: ", JLabel.LEFT));
		pnlCenter.add(txtISBN);
		
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
			String ISBN=null;
			
			try
			{
				ISBN=Validator.validate("ISBN", txtISBN.getText(), true, 15);
			}
			catch (RequiredFieldException | MaximumLengthException e) 
			{
				exceptions.add(e);
			}
			
			int size=exceptions.size();
			
			if(size==0)
			{
				Book book = new Book();
				Student student = new Student();
				book.setISBN(ISBN);
				
				try {
					if(BookBorrowManager.returnBook(txtISBN.getText(), txtMatricNo.getText()) == 1)
						JOptionPane.showMessageDialog(this, "Borrow Record added for student: " + txtMatricNo.getText() + 
						"  added.", "Success", JOptionPane.INFORMATION_MESSAGE);
			try {
				
				// Status = 1: Fail to execute db ; Status = 0: Successful
				Vector v = BookBorrowManager.returnBook(txtISBN.getText());
				Object statusObj = v.get(0);	
				String tempStrStatus = Long.toString((long) statusObj);
				long status = Long.parseLong(tempStrStatus);
				
				// price = 0: Pay right in time; price != 0: not pay right in time
				Object priceObj = v.get(1);	
				String tempStrPrice = Long.toString((long) priceObj);
				long price = Long.parseLong(tempStrPrice);
				
				if(status == 1) 
				{		
					// Return book in time
					if(price == 0)
					{
						JOptionPane.showMessageDialog(this, "Thank you for return the book in time." , "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(this, "Unable to add new record.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
				} catch (HeadlessException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
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
		}
	}

}

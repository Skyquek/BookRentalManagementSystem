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

import controller.manager.BookBorrowManager;
import controller.validator.MaximumLengthException;
import controller.validator.PatternUnmatchedException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Book;
import model.Student;



public class BorrowBookDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnSubmit = new JButton ("Submit");
	private JButton btnReset = new JButton ("Cancel");
	private JTextField txtISBN = new JTextField(20);
	private JTextField txtMatricNo = new JTextField(20);

	public BorrowBookDialog(ManageRentalsDialog dialog,String isbn,String matric)
	{
		super(dialog,"Borrow Book",true);
		
		txtISBN.setEditable(false);
		txtMatricNo.setEditable(false);
		txtISBN.setText(isbn);
		txtMatricNo.setText(matric);
		
		JPanel pnlCenter = new JPanel(new GridLayout(4,1,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		
		pnlCenter.add(new JLabel("Matric No: ", JLabel.LEFT));
		pnlCenter.add(txtMatricNo);
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
			String ISBN=null,matricNo=null;
			
			try
			{
				ISBN=Validator.validate("ISBN", txtISBN.getText(), true, 15);
			}
			catch (RequiredFieldException | MaximumLengthException | PatternUnmatchedException e) 
			{
				exceptions.add(e);
			}
			
			try
			{
				matricNo=Validator.validate("Matric No", txtMatricNo.getText(), true, 40);
			}
			catch (RequiredFieldException | MaximumLengthException | PatternUnmatchedException e) 
			{
				exceptions.add(e);
			}
			
			int size=exceptions.size();
			
			if(size==0)
			{
				Book book = new Book();
				Student student = new Student();
				book.setISBN(ISBN);
				student.setMatricNo(matricNo);
				
				try {
					if(BookBorrowManager.borrowBook(txtMatricNo.getText(),txtISBN.getText()) == 1)
					{
						JOptionPane.showMessageDialog(this, "Borrow Record added for student: " + txtMatricNo.getText() + 
						".", "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
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
			dispose();
		}
	}
}

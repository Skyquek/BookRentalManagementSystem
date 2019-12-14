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
		

		JPanel pnlCenter = new JPanel(new GridLayout(3,2,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		pnlCenter.add(new JLabel("ISBN: ", JLabel.RIGHT));
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
			Book book = new Book();
			Student student = new Student();
			
			try {
				
				// Status = 1: Fail to execute db ; Status = 0: Successful
				Vector v = BookBorrowManager.returnBook(txtISBN.getText());
				Object statusObj = v.get(0);	
				String tempStrStatus = Long.toString((long) statusObj);
				long status = Long.parseLong(tempStrStatus);
				
				// price = 0: Pay right in time; price != 0: not pay right in time
				Object priceObj = v.get(1);	
				String tempStrPrice = Long.toString((long) statusObj);
				long price = Long.parseLong(tempStrPrice);
				
				if(status == 1) 
				{		
					// Return book in time
					if(price == 0)
					{
						JOptionPane.showMessageDialog(this, "Thank You for return the book in time." , "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "You do not return books in time, please pay RM" + price + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				else
					JOptionPane.showMessageDialog(this, "Unable to return book.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
			} catch (HeadlessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(source==btnReset)
		{
			txtISBN.setText("");
		}
	}

}

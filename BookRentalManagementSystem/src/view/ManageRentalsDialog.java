package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ManageRentalsDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnBorrowBook = new JButton("Borrow Book");
	private JButton btnReturnBook = new JButton("Return Book");

	public ManageRentalsDialog(MainFrame frame) 
	{
		super(frame,"Manage Students",true);
		
		GridLayout layout = new GridLayout(1,2);
		
		btnBorrowBook.addActionListener(this);
		btnReturnBook.addActionListener(this);
		
		btnBorrowBook.setBackground(Color.WHITE);
		btnReturnBook.setBackground(Color.WHITE);
		
		btnBorrowBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 40)));
		btnReturnBook.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 40)));
		
		this.add(btnBorrowBook);
		this.add(btnReturnBook);
		
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
			new BorrowBookDialog(this);
		}
		else if(source==btnReturnBook)
		{
			new ReturnBookDialog(this);
		}

	}

}

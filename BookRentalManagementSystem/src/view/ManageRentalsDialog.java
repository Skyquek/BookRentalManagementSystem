package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ManageRentalsDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnBorrowBook = new JButton("Borrow Book");
	private JButton btnReturnBook = new JButton("Return Book");

	public ManageRentalsDialog(MainFrame frame) 
	{
		super(frame,"Manage Students",true);
		
		GridLayout layout = new GridLayout(2,1);
		
		btnBorrowBook.addActionListener(this);
		btnReturnBook.addActionListener(this);
		
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

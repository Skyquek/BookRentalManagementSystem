package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			
			if(BookBorrowManager.returnBook(txtISBN.getText())==1)
				JOptionPane.showMessageDialog(this, "Book ISBN: " + txtISBN.getText() + 
				" returned.", "Success", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Unable to return book.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
		}
		else if(source==btnReset)
		{
			txtISBN.setText("");
		}

	}

}

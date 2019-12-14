package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.manager.BookBorrowManager;
import controller.manager.BookManager;

public class ViewRentalDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnClose = new JButton("Close");
	private JButton btnUpdate = new JButton("Update");

	public ViewRentalDialog(ManageRentalsDialog dialog) 
	{
		super(dialog,"View Borrow Records",true);
		
		JPanel pnlCenter = new JPanel();
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		String[] headerNames = {"Rental ID","Matric No","ISBN","Date Start"};
		JTable jtable;
		
		try {
			jtable = new JTable(BookBorrowManager.getBookBorrows(),headerNames);
			jtable.setRowHeight(25);
			JScrollPane scrollPane = new JScrollPane(jtable);
			jtable.setFillsViewportHeight(true);
			pnlCenter.add(scrollPane);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		pnlSouth.add(btnUpdate);
		pnlSouth.add(btnClose);
		
		this.add(pnlCenter);
		this.add(pnlSouth,BorderLayout.SOUTH);
		
		btnClose.addActionListener(this);
		btnUpdate.addActionListener(this);
		
		
		this.getRootPane().setDefaultButton(btnUpdate);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(dialog);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source=event.getSource();
		
		if(source==btnClose)
		{
			dispose();
		}
		else if(source==btnUpdate)
		{
			
		}

	}

}

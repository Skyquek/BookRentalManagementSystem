package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnManageBooks = new JButton("Manage Books");
	private JButton btnManageStudents = new JButton("Manage Students");
	private JButton btnManageRentals = new JButton("Manage Rentals");
	
	public MainFrame() 
	{
		
		super("Book Rental Management System");
		
		GridLayout layout=new GridLayout(3,1);
		
		btnManageStudents.addActionListener(this);
		btnManageBooks.addActionListener(this);
		btnManageRentals.addActionListener(this);
		
		
		btnManageBooks.setBackground(Color.WHITE);
		btnManageStudents.setBackground(Color.WHITE);
		btnManageRentals.setBackground(Color.WHITE);
		
		btnManageBooks.setFont(new Font("Calibri", Font.BOLD, 20));
		btnManageStudents.setFont(new Font("Calibri", Font.BOLD, 20));
		btnManageRentals.setFont(new Font("Calibri", Font.BOLD, 20));
		
		this.add(btnManageBooks);
		this.add(btnManageStudents);
		this.add(btnManageRentals);
		
		this.setSize(500,400);
		this.setLayout(layout);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source=event.getSource();
		if(source==btnManageBooks)
			new ManageBooksDialog(this);//pass MainFrame as parameter
		else if(source==btnManageStudents)
			new ManageStudentsDialog(this);
		else if(source==btnManageRentals)
			new ManageRentalsDialog(this);

	}
	
	public static void main(String[] args) 
	{
		new MainFrame();
	}

}

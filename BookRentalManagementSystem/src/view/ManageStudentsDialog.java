package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.manager.StudentManager;

public class ManageStudentsDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnAddStudent = new JButton("Add Student");
	private JButton btnViewStudent = new JButton("View Student");
	private JButton btnUpdateStudent = new JButton("Update Student");
	private JButton btnDeleteStudent = new JButton("Delete Student");

	public ManageStudentsDialog(MainFrame frame) 
	{
		super(frame,"Manage Students",true);
		
		GridLayout layout = new GridLayout(2,2);
		
		btnAddStudent.addActionListener(this);
		btnViewStudent.addActionListener(this);
		btnUpdateStudent.addActionListener(this);
		btnDeleteStudent.addActionListener(this);
		
		btnAddStudent.setBackground(Color.WHITE);
		btnViewStudent.setBackground(Color.WHITE);
		btnUpdateStudent.setBackground(Color.WHITE);
		btnDeleteStudent.setBackground(Color.WHITE);
		
		btnAddStudent.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		btnViewStudent.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		btnUpdateStudent.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		btnDeleteStudent.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(Color.GRAY, 1),
	               BorderFactory.createLineBorder(Color.WHITE, 50)));
		
		this.add(btnAddStudent);
		this.add(btnViewStudent);
		this.add(btnUpdateStudent);
		this.add(btnDeleteStudent);
		
		this.setLayout(layout);
		this.pack();		//modal dialog= while open cant interact with parent frame
		this.setLocationRelativeTo(frame);//appear in center of parent frame
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source=event.getSource();
		
		if(source==btnAddStudent)
		{
			new AddStudentDialog(this);
		}
		else if(source==btnViewStudent)
		{
			new ViewStudentDialog(this);
		}	
		else if(source==btnUpdateStudent)
		{
			try
			{
				Object[] options = StudentManager.getStudentsMatric();
				
				Object selectedValues = JOptionPane.showInputDialog(this, "Select student: ", "Update Student", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if( selectedValues != null)
				{
					new UpdateStudentDialog(this,selectedValues.toString());
				}
			}
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}
		}	
		else if(source==btnDeleteStudent)
		{
			try
			{
				Object[] options = StudentManager.getStudentsMatric();
				
				Object selectedValues = JOptionPane.showInputDialog(this, "Select student: ", "Delete Student", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(selectedValues!=null)
				{
					new DeleteStudentDialog(this,selectedValues.toString());
				}
			}
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

}

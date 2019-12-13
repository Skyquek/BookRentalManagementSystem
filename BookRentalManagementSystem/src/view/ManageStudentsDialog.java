package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

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
			new UpdateStudentDialog(this);
		}	
		else if(source==btnDeleteStudent)
		{
			new DeleteStudentDialog(this);
		}
		
	}

}

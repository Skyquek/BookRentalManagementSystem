package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.manager.StudentManager;
import model.Student;

public class AddStudentDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtMatricNo = new JTextField();
	private JTextField txtName = new JTextField();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	

	public AddStudentDialog(ManageStudentsDialog dialog) throws HeadlessException 
	{
		super(dialog,"Add Student",true);
		
		JPanel pnlCenter = new JPanel(new GridLayout(3,2,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		pnlCenter.add(new JLabel("Matric No: ", JLabel.RIGHT));
		pnlCenter.add(txtMatricNo);
		pnlCenter.add(new JLabel("Name: ", JLabel.RIGHT));
		pnlCenter.add(txtName);
		
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
			Student student = new Student();
			
			student.setMatricNo(txtMatricNo.getText());
			student.setName(txtName.getText());
			
			try {
				if(StudentManager.addStudent(student) !=0)
					JOptionPane.showMessageDialog(this, "Student with Matric No: " + student.getMatricNo() + 
					" has been successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, "Unable to add new student.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
			} catch (HeadlessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(source==btnReset)
		{
			txtMatricNo.setText("");
			txtName.setText("");
		}

	}

}

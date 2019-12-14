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

import controller.manager.StudentManager;

public class DeleteStudentDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtMatricNo = new JTextField();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public DeleteStudentDialog(ManageStudentsDialog dialog) 
	{
		super(dialog,"Delete Student",true);
		
		JPanel pnlCenter = new JPanel(new GridLayout(1,2,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		pnlCenter.add(new JLabel("Matric No: ", JLabel.RIGHT));
		pnlCenter.add(txtMatricNo);
		
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
			if(StudentManager.deleteStudent(txtMatricNo.getText())==1)
				JOptionPane.showMessageDialog(this, "Student with Matric No: " + txtMatricNo.getText() + 
						" has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
			else if(StudentManager.deleteStudent(txtMatricNo.getText())==0)
				JOptionPane.showMessageDialog(this, "Unable to delete student "+ txtMatricNo.getText()+ ".","Unsuccessful",JOptionPane.WARNING_MESSAGE);	
		}
		else if(source==btnReset)
		{
			txtMatricNo.setText("");
		}
	}

}

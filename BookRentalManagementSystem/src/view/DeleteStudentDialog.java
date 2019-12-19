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

public class DeleteStudentDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtMatricNo = new JTextField(20);
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public DeleteStudentDialog(ManageStudentsDialog dialog,String matric) 
	{
		super(dialog,"Delete Student",true);
		
		JPanel pnlCenter = new JPanel(new GridLayout(2,1,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		
		txtMatricNo.setText(matric);
		txtMatricNo.setEditable(false);
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		
		pnlCenter.add(new JLabel("Matric No: ", JLabel.LEFT));
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
			try {
				if(StudentManager.deleteStudent(txtMatricNo.getText())==1)
				{
					JOptionPane.showMessageDialog(this, "Student with Matric No: " + txtMatricNo.getText() + 
							" has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else if(StudentManager.deleteStudent(txtMatricNo.getText())==0)
					JOptionPane.showMessageDialog(this, "Unable to delete student "+ txtMatricNo.getText()+ ".","Unsuccessful",JOptionPane.WARNING_MESSAGE);
			} catch (HeadlessException | ClassNotFoundException | SQLException e) {
				if(e.getMessage() != null) {
					JOptionPane.showMessageDialog(this, "Unable to delete student ." + txtMatricNo.getText() + ".","Unsuccessful",JOptionPane.WARNING_MESSAGE);
				}
			}	
		}
		else if(source==btnReset)
		{
			txtMatricNo.setText("");
		}
	}

}

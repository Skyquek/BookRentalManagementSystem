package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AddStudentDialog extends JFrame implements ActionListener {

	public AddStudentDialog() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public AddStudentDialog(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public AddStudentDialog(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public AddStudentDialog(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

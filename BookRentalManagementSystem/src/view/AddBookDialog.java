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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBookDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtISBN = new JTextField();
	private JTextField txtTitle = new JTextField();
	private JTextField txtAuthor = new JTextField();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	

	public AddBookDialog(ManageBooksDialog dialog) 
	{
		super(dialog,"Add Book",true);
		
		JPanel pnlCenter = new JPanel(new GridLayout(3,2,10,10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		pnlCenter.add(new JLabel("ISBN: ", JLabel.RIGHT));
		pnlCenter.add(txtISBN);
		pnlCenter.add(new JLabel("Title: ", JLabel.RIGHT));
		pnlCenter.add(txtTitle);
		pnlCenter.add(new JLabel("Author: ", JLabel.RIGHT));
		pnlCenter.add(txtAuthor);
		
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
			
		}
		else if(source==btnReset)
		{
			
		
		}
	}

}

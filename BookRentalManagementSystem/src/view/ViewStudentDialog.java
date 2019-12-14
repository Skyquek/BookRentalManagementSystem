package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewStudentDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnClose = new JButton("Close");
	private JButton btnUpdate = new JButton("Update");

	public ViewStudentDialog(ManageStudentsDialog dialog) 
	{
		super(dialog,"View Students",true);
		
		JPanel pnlCenter = new JPanel();
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		String[] headerNames = {"Matric No","Name"};
		Object [][] data = {{"1","2"}};
//		for(int i =0;i<SIZE;i++) {
//			for(int j=0;j<2;j++) {
//				data[i][j] = value;
//			}
//		}
		JTable jtable = new JTable(data,headerNames);
		jtable.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(jtable);
		jtable.setFillsViewportHeight(true);
		pnlCenter.add(scrollPane);
		
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

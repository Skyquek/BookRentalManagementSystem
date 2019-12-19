package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.manager.BookBorrowManager;

public class ViewRentalDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnClose = new JButton("Close");
	private JTextField searchItem = new JTextField(30);

	public ViewRentalDialog(ManageRentalsDialog dialog) 
	{
		super(dialog,"View Borrow Records",true);
		
		JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
		JPanel pnlCenter = new JPanel();
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		
		pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		
		pnlTop.add(new JLabel("Filter keyword:"));
		pnlTop.add(searchItem);
		
		try {
			String[] headerNames = {"Rental ID","Matric No","ISBN","Date Start"};
			Object[][] data=BookBorrowManager.getBookBorrows();
			
			DefaultTableModel model = new DefaultTableModel(data, headerNames) {
				
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
			        return false;
			    }
				
				
			};
			JTable jTable = new JTable(model);
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
			jTable.setRowSorter(rowSorter);
			
			searchItem.getDocument().addDocumentListener(new DocumentListener(){

		    	 @Override
		         public void insertUpdate(DocumentEvent e) {
		             String text = searchItem.getText();

		             if (text.trim().length() == 0) {
		                 rowSorter.setRowFilter(null);
		             } else {
		                 rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		             }
		         }

		         @Override
		         public void removeUpdate(DocumentEvent e) {
		             String text = searchItem.getText();

		             if (text.trim().length() == 0) {
		                 rowSorter.setRowFilter(null);
		             } else {
		                 rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		             }
		         }
		         
		         

		         @Override
		         public void changedUpdate(DocumentEvent e) {
		             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		         }    

		    }); 
			
			jTable.setRowHeight(25);
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTable.getColumn("ISBN").setPreferredWidth(100);
			jTable.getColumn("Matric No").setPreferredWidth(150);
			jTable.getColumn("Rental ID").setPreferredWidth(100);
			jTable.getColumn("Date Start").setPreferredWidth(100);
			jTable.setSelectionForeground(Color.WHITE);
			jTable.setSelectionBackground(Color.DARK_GRAY);
			JScrollPane scrollPane = new JScrollPane(jTable);
			jTable.setFillsViewportHeight(true);
			pnlCenter.add(scrollPane);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		pnlSouth.add(btnClose);
		
		this.add(pnlTop,BorderLayout.NORTH);
		this.add(pnlCenter);
		this.add(pnlSouth,BorderLayout.SOUTH);
		
		btnClose.addActionListener(this);
		
		this.getRootPane().setDefaultButton(btnClose);
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
	}

}

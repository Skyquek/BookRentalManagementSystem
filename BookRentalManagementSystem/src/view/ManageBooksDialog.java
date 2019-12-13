package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ManageBooksDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	private JButton btnAddBook = new JButton("Add Book");
	private JButton btnViewBook = new JButton("View Book");
	private JButton btnUpdateBook = new JButton("Update Book");
	private JButton btnDeleteBook = new JButton("Delete Book");
	
	public ManageBooksDialog(MainFrame frame)
	{
		super(frame,"Manage Books",true);
		
		GridLayout layout = new GridLayout(2,2);
		
		btnAddBook.addActionListener(this);
		btnViewBook.addActionListener(this);
		btnUpdateBook.addActionListener(this);
		btnDeleteBook.addActionListener(this);
		
		this.add(btnAddBook);
		this.add(btnViewBook);
		this.add(btnUpdateBook);
		this.add(btnDeleteBook);
		
		this.setLayout(layout);
		this.pack();		//modal dialog= while open cant interact with parent frame
		this.setLocationRelativeTo(frame);//appear in center of parent frame
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source=event.getSource();
		
		if(source==btnAddBook)
		{
			new AddBookDialog(this);
		}
		else if(source==btnViewBook)
		{
			new ViewBookDialog(this);
		}
		else if(source==btnUpdateBook)
		{
			new UpdateBookDialog(this);
		}
		else if(source==btnDeleteBook)
		{
			new DeleteBookDialog(this);
		}
			
	}

}

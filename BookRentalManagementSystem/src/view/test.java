package view;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class test {

	public test() {
		


		   
	}
	
	 public static void main(String args[]) throws IOException  {


	        String[] date= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
 String[] month= {"1","2","3","4","5","6","7","8","9","10","11","12"};
 String[] year={"2016","2017","2018","2019","2020"};
 JComboBox jcd = new JComboBox(date);
 JComboBox jcm = new JComboBox(month);
 JComboBox jcy = new JComboBox(year);

 jcd.setEditable(true);
 jcm.setEditable(true);
 jcy.setEditable(true);

 //create a JOptionPane
 Object[] options = new Object[] {};
 JOptionPane jop = new JOptionPane("Please Select",
                                 JOptionPane.QUESTION_MESSAGE,
                                 JOptionPane.DEFAULT_OPTION,
                                 null,options, null);

 //add combos to JOptionPane
 jop.add(jcd);
 jop.add(jcm);
 jop.add(jcy);

 //create a JDialog and add JOptionPane to it 
 JDialog diag = new JDialog();
 diag.getContentPane().add(jop);
 diag.pack();
 diag.setVisible(true);
}
}

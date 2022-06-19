import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Scores extends JFrame{
	public Scores() {

		super("Arkanoid Game - Score Table");
	    JTextArea textarea = new JTextArea();
   	      	      
	    try {
	    	BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("myFile.txt")));
	    	textarea.read(input, "");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    JButton clearButton = new JButton("Return");
	    clearButton.setBounds(350,30,80,40);
	    clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	String[] args = new String[] {};
            	Menu.main(args);
            	setVisible(false);
            }
        });
	    
	    add(clearButton);
	    getContentPane().add(textarea, BorderLayout.CENTER);
	    JScrollPane sp = new JScrollPane(textarea);			// Scroll Bar
	    getContentPane().add(sp);							//
	    textarea.setEditable(false);
	  }
}

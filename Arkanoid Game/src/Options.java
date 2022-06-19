import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Options extends JFrame implements KeyListener {
	
	Icon background = new ImageIcon(getClass().getResource("backgroundOptions.jpg"));
	
	Options() {
		
		JPanel panel = new JPanel();
		FlowLayout layout = (FlowLayout)panel.getLayout();	// Remove top side space
        layout.setVgap(0);									//
		panel.setSize(200,200);
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		
		JRadioButton mouseButton = new JRadioButton("Mouse");	
		mouseButton.setOpaque(false);
		JRadioButton keyboardButton = new JRadioButton("Keyboard");		
		keyboardButton.setOpaque(false);
		
		ButtonGroup group = new ButtonGroup();
        group.add(mouseButton);
        group.add(keyboardButton);
        
        backgroundLabel.setLayout(new FlowLayout());     
        backgroundLabel.add(mouseButton);
        backgroundLabel.add(keyboardButton);

        
        // APPLY
		JButton applyButton = new JButton("Apply");
		applyButton.setBackground(Color.WHITE);
		backgroundLabel.add(applyButton);
		
//      	if(mouseButton != null) {
//    			// use mouseButton
//    		}else {
//    			// use keyboardButton
//    		}
    
		
		
        // RETURN PAGE
		JButton returnPage = new JButton("return");
		returnPage.setBackground(Color.WHITE);
		returnPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	String[] args = new String[] {};
            	Menu.main(args);
            	setVisible(false);
            }
        });
		backgroundLabel.add(returnPage);
		
		
		
		addKeyListener(this);	
		setFocusable(true);	// For CTRL-Q
		panel.add(backgroundLabel);
		add(panel);
	}
	
	
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_Q && !(event.getKeyCode()==KeyEvent.VK_CONTROL))
		{
		    System.exit(0);
		}	
		else {
			
		}
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}

	public void keyReleased(KeyEvent arg0) {

	}

}

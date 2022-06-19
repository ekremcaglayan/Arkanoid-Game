import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewGame extends JFrame implements KeyListener {
	
	JPanel panel = new JPanel();
	Icon background = new ImageIcon(getClass().getResource("backgroundSelectLevel.jpg"));
	
	NewGame() {
		
		super("Arkanoid Game");
		
		JPanel panel = new JPanel();
		FlowLayout layout = (FlowLayout)panel.getLayout();	// Remove top side space
        layout.setVgap(0);									//
		panel.setSize(600,800);
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets = new Insets(10,10,10,10);	// Padding (top, left, bottom, right)
		backgroundLabel.setLayout(grid);
		
		
		
		// LEVEL 1
		gbc.fill = GridBagConstraints.HORIZONTAL;	
		gbc.ipadx = 35;
		gbc.ipady = 30;	
		gbc.gridy = 0;
		JButton level1Button = new JButton("Level 1");
		level1Button.setBackground(Color.WHITE);
		level1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	Level1 level1Area = new Level1(); 
            	level1Area.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	level1Area.setSize(600, 800);
            	level1Area.setLocationRelativeTo(null);
            	level1Area.setResizable(false);
            	level1Area.setVisible(true);
        		setVisible(false);
            }
        });
		backgroundLabel.add(level1Button, gbc);
		

		
		// LEVEL 2
		gbc.gridy = 1;	
		JButton level2Button = new JButton("Level 2");
		level2Button.setBackground(Color.WHITE);
		level2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	Level2 level2Area = new Level2(0); 
            	level2Area.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	level2Area.setSize(600, 800);
            	level2Area.setLocationRelativeTo(null);
            	level2Area.setResizable(false);
            	level2Area.setVisible(true);
        		setVisible(false);
            }
        });
		backgroundLabel.add(level2Button, gbc);
		
		
		
		// LEVEL 3
		gbc.gridy = 2;		
		JButton level3Button = new JButton("Level 3");
		level3Button.setBackground(Color.WHITE);
		level3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	Level3 level3Area = new Level3(0); 
            	level3Area.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	level3Area.setSize(600, 800);
            	level3Area.setLocationRelativeTo(null);
            	level3Area.setResizable(false);
            	level3Area.setVisible(true);
        		setVisible(false);
            }
        });
		backgroundLabel.add(level3Button, gbc);
		
		
		
		// RETURN PAGE
		gbc.gridy = 3;		
		gbc.ipady = 10;	
		JButton returnPage = new JButton("return");
		returnPage.setBackground(Color.WHITE);
		returnPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	String[] args = new String[] {};
            	Menu.main(args);
            	setVisible(false);
            }
        });
		backgroundLabel.add(returnPage, gbc);

		
		
		
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

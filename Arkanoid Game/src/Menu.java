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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu {
	
	public static void main(String[] args) {	
		
		JFrame frame = new JFrame("Arkanoid Game");
		Icon background = new ImageIcon(Menu.class.getResource("backgroundLevels.jpg"));
		
		JPanel panel = new JPanel();
		FlowLayout layout = (FlowLayout)panel.getLayout(); 	// Remove top side space
        layout.setVgap(0); 									//
		panel.setSize(600,800);
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		
		
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets = new Insets(10,10,10,10);	// Padding (top, left, bottom, right)
		backgroundLabel.setLayout(grid);
		
	
		
		// NEW GAME
		gbc.fill = GridBagConstraints.HORIZONTAL;		
		gbc.ipadx = 65;
		gbc.ipady = 70;	
		gbc.gridx = 0;
		JButton newGameButton = new JButton("New Game");
		newGameButton.setBackground(Color.WHITE);
		newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	NewGame selectWindow = new NewGame(); 
            	selectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	selectWindow.setSize(300, 400);
            	selectWindow.setLocationRelativeTo(null);
            	selectWindow.setResizable(false);
            	selectWindow.setVisible(true);
            	frame.setVisible(false);
            }
        });
		backgroundLabel.add(newGameButton, gbc);
		
		
		
		// OPTIONS
		gbc.gridy = 1;
		JButton optionsButton = new JButton("Options");		
		optionsButton.setBackground(Color.WHITE);
		optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	Options optionsWindow = new Options(); 
            	optionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	optionsWindow.setSize(200, 200);
            	optionsWindow.setLocationRelativeTo(null);
            	optionsWindow.setResizable(true);
            	optionsWindow.setVisible(true);
            	frame.setVisible(false);
            }
        });
		backgroundLabel.add(optionsButton, gbc);
		
		
		
		// SCORES
		gbc.gridy = 2;	
		JButton scoresButton = new JButton("Scores");
		scoresButton.setBackground(Color.WHITE);
		scoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	Scores scoresWindow = new Scores(); 
            	scoresWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	scoresWindow.setSize(500, 500);
            	scoresWindow.setLocationRelativeTo(null);
            	scoresWindow.setResizable(false);
            	scoresWindow.setVisible(true);
            	frame.setVisible(false);
            }
        });
		backgroundLabel.add(scoresButton, gbc);
		
		
		
		// HELP
		gbc.gridy = 3;
		JButton helpButton = new JButton("Help");	
		helpButton.setBackground(Color.WHITE);
		helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                JOptionPane.showMessageDialog(backgroundLabel, "In this game you must control a ball with your paddle "
                		+ "\n" +"and attempt to break all of the colored panels on each level. "
                		+ "\n" +"Fire your initial ball using the left click mouse button, "
                		+ "\n" +"and use your mouse to control the movement of your paddle - "
                		+ "\n" +"be sure to move quickly and anticipate the path of the ball.","Help",JOptionPane.INFORMATION_MESSAGE);
            }
        });
		backgroundLabel.add(helpButton, gbc);
		
		
		
		// ABOUT
		gbc.gridy = 4;
		JButton aboutButtonn = new JButton("About");
		aboutButtonn.setBackground(Color.WHITE);
		aboutButtonn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                JOptionPane.showMessageDialog(backgroundLabel, "Name: Ekrem"
                									+ "\n" + "Surname: Caglayan"
                									+ "\n" + "School Number: 20190702086"
                									+ "\n" +"Email: ekremcaglayan1@hotmail.com","About",JOptionPane.QUESTION_MESSAGE);
            }
        });
		backgroundLabel.add(aboutButtonn, gbc);
			
		
		
		// EXIT
		gbc.gridy = 5;
		JButton exitButton = new JButton("Exit");
		exitButton.setBackground(Color.WHITE);
		exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
            	System.exit(0);
            }
        });
		backgroundLabel.add(exitButton, gbc);
		
		
		

		KeyListenerMain k = new Menu().new KeyListenerMain();
		frame.addKeyListener(k);	
		frame.setFocusable(true);	// For CTRL-Q
		
		panel.add(backgroundLabel);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);	
	}
	
	
	public class KeyListenerMain implements KeyListener
	{     
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
}

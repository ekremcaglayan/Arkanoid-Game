import javax.swing.JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Level1 extends JFrame implements KeyListener {
	
	private JLabel[] myLabels;
	
	Icon red = new ImageIcon(getClass().getResource("red.png"));
	Icon iconEmpty = new ImageIcon(getClass().getResource("empty.jpg"));
	Icon background = new ImageIcon(getClass().getResource("backgroundLevels.jpg"));
	Icon ball = new ImageIcon(getClass().getResource("ball.png"));
	Icon stick = new ImageIcon(getClass().getResource("stick.png"));
	
	private int yStick;
	private int xStick;
	private int yBall;
	private int xBall;
	
	private int row = 49;
	private int column = 11;
	
	static int score = 0;
	static int lives = 3;
	
	private int time = 0;
	
	private int count = 0;
	
	public Level1() {
		
		super("Arkanoid Game - Level 1");
		
		JPanel panel = new JPanel();
		FlowLayout layout = (FlowLayout)panel.getLayout();	// Remove top side space
        layout.setVgap(0);									//
		panel.setSize(600,800);
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		backgroundLabel.setLayout(new GridLayout(row,column,4,2));
		
		int i;
		
		yStick = 46; // Location of the stick on the y-axis 
		xStick = 5;	 // Location of the stick on the x-axis 
		
		yBall = 45; // Location of the ball on the y-axis 
		xBall = 5;  // Location of the ball on the x-axis 	
				
		myLabels = new JLabel[row*column]; // Fill The Whole Layout With Labels
		
		for(i=0; i<row*column; i++){
			if(i == 0) {
				myLabels[i] = new JLabel("Score: ", JLabel.RIGHT);
				myLabels[i].setForeground(Color.WHITE);							// SCORE
			}
			else if(i == 1) {
				myLabels[i] = new JLabel(score + "" , JLabel.LEFT);
				myLabels[i].setForeground(Color.WHITE);							// SCORE
			}
			else if(i == 5) {
				myLabels[i] = new JLabel("LEVEL 1", JLabel.CENTER);
				myLabels[i].setForeground(Color.WHITE);							// LEVEL 1
			}	
			else if(i == 10) {
				myLabels[i] = new JLabel("Lives: " + lives, JLabel.LEFT);
				myLabels[i].setForeground(Color.WHITE);							// LIVES
			}
			else if(i == 65 || i == 76 ||  i == 66 || i== 77) {
				myLabels[i] = new JLabel(iconEmpty);  	
			}
			else if(i < 87 && i >= 56 ) {
				myLabels[i] = new JLabel(red);  // Add 27 Red images To Labels		
			}
			else {
				myLabels[i] = new JLabel(iconEmpty); // Add Empty images To Whole Labels except 27 Red image 		
			}
			backgroundLabel.add(myLabels[i]);		
		}				
		myLabels[yStick*column+xStick].setIcon(stick);
		myLabels[yStick*column+xStick+1].setIcon(stick);
		myLabels[yBall*column+xBall].setIcon(ball);
		
		
		
		addKeyListener(this);	
		setFocusable(true);	// For CTRL-Q
		panel.add(backgroundLabel);
		add(panel);
		timer.schedule(task,0,100);
	}
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {		
		public void run() {
			time++;
			if(isAllowedBall(yBall,xBall))
			{
				changeLayoutBall();
			}
			else {
				changeLayoutBall2();
			}			
		}				
	};
	

	private void changeLayoutLeft()
	{	
		myLabels[yStick*column+xStick].setIcon(iconEmpty);
		myLabels[yStick*column+xStick+1].setIcon(iconEmpty);
		xStick--;												
		myLabels[yStick*column+xStick].setIcon(stick);	
		myLabels[yStick*column+xStick+1].setIcon(stick);	
	}
	
	
	private void changeLayoutRight()
	{	
		myLabels[yStick*column+xStick].setIcon(iconEmpty);
		myLabels[yStick*column+xStick+1].setIcon(iconEmpty);
		xStick++;		
		myLabels[yStick*column+xStick].setIcon(stick);	
		myLabels[yStick*column+xStick+1].setIcon(stick);
	}
	
	
	boolean up = true;
	boolean left = true;
	
	private void changeLayoutBall() {
		if(count > 20) {
			timer.cancel();
			setVisible(false);
			Level2 level2Area = new Level2(score);
			level2Area.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			level2Area.setSize(600, 800);
        	level2Area.setLocationRelativeTo(null);
        	level2Area.setResizable(false);
        	level2Area.setVisible(true);
		}
		else {
			if(up) {
				if(left) {
					if(iconEmpty.equals(myLabels[(yBall-1)*column+xBall].getIcon()) && iconEmpty.equals(myLabels[((yBall-1)*column+(xBall-1))].getIcon())) {
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall--;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);			
					}
					else if (!iconEmpty.equals(myLabels[(yBall-1)*column+xBall].getIcon())) {
						myLabels[(yBall-1)*column+xBall].setIcon(iconEmpty);
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);
						score += 100;
						count ++;
						myLabels[1].setText(""+score);
						up = false;
					}
					else if (!iconEmpty.equals(myLabels[yBall*column+(xBall-1)].getIcon())) {		// UP LEFT
						myLabels[yBall*column+(xBall-1)].setIcon(iconEmpty);
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);
						score += 100;
						count ++;
						myLabels[1].setText(""+score);
						left = false;
					}
					else {
						myLabels[(yBall-1)*column+(xBall-1)].setIcon(iconEmpty);
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);
						score += 100;
						count ++;
						myLabels[1].setText(""+score);
						up = false;
						left = false;
					}
				}
				else {
					if(iconEmpty.equals(myLabels[(yBall-1)*column+xBall].getIcon()) && iconEmpty.equals(myLabels[((yBall-1)*column+(xBall+1))].getIcon())) {
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall--;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);			
					}
					else if (!iconEmpty.equals(myLabels[(yBall-1)*column+xBall].getIcon())) {
						myLabels[(yBall-1)*column+xBall].setIcon(iconEmpty);
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);
						score += 100;
						count ++;
						myLabels[1].setText(""+score);
						up = false;
					}
					else if (!iconEmpty.equals(myLabels[yBall*column+(xBall+1)].getIcon())) {		// UP RIGHT
						myLabels[yBall*column+(xBall+1)].setIcon(iconEmpty);
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);
						score += 100;
						count ++;
						myLabels[1].setText(""+score);
						left = true;
					}
					else {
						myLabels[(yBall-1)*column+(xBall+1)].setIcon(iconEmpty);
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);
						score += 100;
						count ++;
						myLabels[1].setText(""+score);
						up = false;
						left = true;
					}
				}
			}
			else {
				if(left) {
					if(iconEmpty.equals(myLabels[(yBall+1)*column+xBall].getIcon()) && iconEmpty.equals(myLabels[((yBall+1)*column+(xBall-1))].getIcon())) {
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);			
					}
					else if (!iconEmpty.equals(myLabels[(yBall+1)*column+xBall].getIcon())) {
						if(!stick.equals(myLabels[(yBall+1)*column+xBall].getIcon())) {
							myLabels[(yBall+1)*column+xBall].setIcon(iconEmpty);	
							score += 100;
							count ++;
						}
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall--;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);
						myLabels[1].setText(""+score);
						up = true;
					}
					else if (!iconEmpty.equals(myLabels[yBall*column+(xBall-1)].getIcon())) {		// DOWN LEFT
						if(!stick.equals(myLabels[yBall*column+(xBall-1)].getIcon())) {
							myLabels[yBall*column+(xBall-1)].setIcon(iconEmpty);	
							score += 100;
							count ++;
						}
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);
						myLabels[1].setText(""+score);
						left = false;
					}
					else {
						if(!stick.equals(myLabels[(yBall+1)*column+(xBall-1)].getIcon())) {
							myLabels[(yBall+1)*column+(xBall-1)].setIcon(iconEmpty);
							score += 100;
							count ++;
						}
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall--;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);
						myLabels[1].setText(""+score);
						up = true;
						left = false;
					}
				}
				else {
					if(iconEmpty.equals(myLabels[(yBall+1)*column+xBall].getIcon()) && iconEmpty.equals(myLabels[((yBall+1)*column+(xBall+1))].getIcon())) {
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);			
					}
					else if (!iconEmpty.equals(myLabels[(yBall+1)*column+xBall].getIcon())) {
						if(!stick.equals(myLabels[(yBall+1)*column+xBall].getIcon())) {
							myLabels[(yBall+1)*column+xBall].setIcon(iconEmpty);	
							score += 100;
							count ++;
						}
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall--;
						xBall++;
						myLabels[yBall*column+xBall].setIcon(ball);
						myLabels[1].setText(""+score);
						up = true;
					}
					else if (!iconEmpty.equals(myLabels[yBall*column+(xBall+1)].getIcon())) {		// DOWN RIGHT
						if(!stick.equals(myLabels[yBall*column+(xBall+1)].getIcon())) {
							myLabels[yBall*column+(xBall+1)].setIcon(iconEmpty);	
							score += 100;
							count ++;
						}
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall++;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);
						myLabels[1].setText(""+score);
						left = true;
					}
					else {
						if(!stick.equals(myLabels[(yBall+1)*column+(xBall+1)].getIcon())) {
							myLabels[(yBall+1)*column+(xBall+1)].setIcon(iconEmpty);
							score += 100;
							count ++;
						}
						myLabels[yBall*column+xBall].setIcon(iconEmpty);
						yBall--;
						xBall--;
						myLabels[yBall*column+xBall].setIcon(ball);
						myLabels[1].setText(""+score);
						up = true;
						left = true;
					}
				}
			}
		}
	}
	
	
	// Edges
	private void changeLayoutBall2() {
		if(up) {
			if(left) {
				if(yBall==2) {
					myLabels[yBall*column+xBall].setIcon(iconEmpty);
					yBall++;
					xBall--;												
					myLabels[yBall*column+xBall].setIcon(ball);
					up = false;
				}
				else {
					myLabels[yBall*column+xBall].setIcon(iconEmpty);
					yBall--;
					xBall++;												// When coming UP LEFT
					myLabels[yBall*column+xBall].setIcon(ball);
					left = false;
				}
			}
			else {
				if(yBall==2) {
					myLabels[yBall*column+xBall].setIcon(iconEmpty);
					yBall++;
					xBall++;												
					myLabels[yBall*column+xBall].setIcon(ball);
					up = false;
				}
				else {
					myLabels[yBall*column+xBall].setIcon(iconEmpty);
					yBall--;
					xBall--;												// When coming UP RIGHT
					myLabels[yBall*column+xBall].setIcon(ball);
					left = true;
				}
			}
		}
		else {
			if(left) {
				if(yBall==row-3) {
					lives -= 1;
					myLabels[10].setText("Lives: "+lives);
					reset();
				}
				else {
					myLabels[yBall*column+xBall].setIcon(iconEmpty);
					yBall++;
					xBall++;												// When coming DOWN LEFT
					myLabels[yBall*column+xBall].setIcon(ball);
					left = false;
				}
			}
			else {
				if(yBall==row-3) {
					lives -= 1;
					myLabels[10].setText("Lives: "+lives);
					reset();
				}
				else {
					myLabels[yBall*column+xBall].setIcon(iconEmpty);
					yBall++;
					xBall--;												// When coming DOWN RIGHT
					myLabels[yBall*column+xBall].setIcon(ball);
					left = true;
				}
			}
		}
	}
	
	public void reset(){
			
		//Resets all variables to original positions or values
		if(!(yStick == 46 && xStick == 5)) {
			myLabels[yStick*column+xStick].setIcon(iconEmpty);
			myLabels[yStick*column+xStick+1].setIcon(iconEmpty);			
			yStick = 46; 
			xStick = 5;  
			myLabels[yStick*column+xStick].setIcon(stick);
			myLabels[yStick*column+xStick+1].setIcon(stick);
		}
		
		if(!(yBall == 45 && xBall == 5)) {
			myLabels[yBall*column+xBall].setIcon(iconEmpty);
			yBall = 45; 
			xBall = 5; 
			myLabels[yBall*column+xBall].setIcon(ball);
		}
  	
	                	
		if(lives < 0)
		{					
			timer.cancel();
			JFrame frame2 = new JFrame("Scores");
			
			frame2.setLayout(new FlowLayout());
			
			JLabel lbl1 = new JLabel("Name: ");
			frame2.add(lbl1);
			JTextField nameField = new JTextField(10);
			frame2.add(nameField);
					
			Date date = new Date();
		
			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					FileWriter writer;
					try {
						writer = new FileWriter("myFile.txt",true);
			    	      
						String name = nameField.getText();		    	      
			    	      
						writer.write("\n" + "Name: " + name + "\n" + "Time: " + time/10 + " s" + "\n"  +"Date : " + date + "\n" + "Score: " + score + "\n");
						writer.close();
						frame2.setVisible(false);
						setVisible(false);
						score = 0;
						lives = 3;
						String[] args = new String[] {};
		            	Menu.main(args);
			    	      
					} catch (IOException e) {
						e. printStackTrace();
					}
				}
			};
			
			JButton button1 = new JButton("Save");
			frame2.add(button1);
			button1.addActionListener(actionListener);
			frame2.setSize(300, 400);
			frame2.setLocationRelativeTo(null);
			frame2.setResizable(false);
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		}       	
	}
	
	
	// Allowed area where the stick can go on the y-axis
	public boolean isAllowedStick(int i,int j)
	{
		if(j == -1 || j == column-1)
		{
			return false;
		}
		else{
			return true;
		}
	}
	
	
	// Allowed area where the ball can go on the x-axis and y-axis
	public boolean isAllowedBall(int a,int b)
	{
		if(a == 2  || a == row-3 || b == 0 || b == column-1)
		{
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public void keyPressed(KeyEvent event) {
		
		String whichKey = KeyEvent.getKeyText(event.getKeyCode());
		
		if(whichKey.compareTo("Left") == 0)
		{
			if(isAllowedStick(yStick,xStick-1))
			{
				changeLayoutLeft();
			}
		}
		else if(whichKey.compareTo("Right") == 0)
		{
			if(isAllowedStick(yStick,xStick+1))
			{
				changeLayoutRight();
			}
		}
		else if(event.getKeyCode()==KeyEvent.VK_Q && !(event.getKeyCode()==KeyEvent.VK_CONTROL))
		{
		    System.exit(0);
		}
		else{		
		}	
	}
	
	
	public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (isAllowedStick(yStick,xStick-1)) {
            	changeLayoutLeft();
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (isAllowedStick(yStick,xStick+1)) {
            	changeLayoutLeft();
            }
        }
    }
	
	
	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

}




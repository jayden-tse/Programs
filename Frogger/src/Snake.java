import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Snake extends JLabel implements ActionListener {

	//Start position of snake
	private int startX = 800;
	private int startY = 385;

	//Properties of the snake
	private static final int SPEED = 100;
	private Timer moveTimer = new Timer(20, this);
	private Timer wiggleTimer = new Timer(SPEED, this);
	private int state = 1;
	private int maxWiggle = 3;
	private boolean moving = true;


	//Constructor
	public Snake() {

		setLocation(startX, startY);
		//Sets the image of the snake
		setIcon(new ImageIcon("images/snake1.gif"));
		setVisible(true);
		//Set the start timers
		moveTimer.start();
		wiggleTimer.start();


	}

	public int getStartX() {

		return startX;

	}

	public int getStartY() {

		return startY;

	}

	public void setMaxDive(int maxDive) {

		this.maxWiggle = maxWiggle;

	}


	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == moveTimer) {

			//If snake goes off the screen
			if (getX() < -getWidth()){
				//Reposition the snake
				setLocation(FroggerGUI.FRAME_WIDTH + 500, startY);}

			else {
				//Continue moving the snake by an increment	
				setLocation(getX() - 1, getY());

				//If frog touches snake
				if (getBounds().intersects(FroggerGUI.frog.getBounds())) {

					FroggerGUI.frog.loseLife();

				}

			}

		}	else if (event.getSource() == wiggleTimer) {

			//States of the snake moving
			setIcon(new ImageIcon("images/snake" + state + ".gif"));
			
			//If the snake is moving
			if (moving)

				state++; //Change picture

			else

				state--; //Reset the picture

			//When state reaches maximum frame
			if (state == maxWiggle) 

				moving = false; //Stop moving

			else if (state == 1) //If the snake is done reseting

				moving = true; //Move again

		}

		//If snake touches frog frog loses life
		if (this.getBounds().intersects(FroggerGUI.frog.getBounds()))

			FroggerGUI.frog.loseLife();

	}

}

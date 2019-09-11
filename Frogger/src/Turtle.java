import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Turtle extends JLabel implements ActionListener {

	//Store the number of turtle and its position
	private int numTurtles;
	private int startX;
	private int startY;

	//Set the turtles speed and create variables to handle diving
	private static final int SPEED = 10;
	private Timer moveTimer = new Timer(SPEED, this);
	private Timer diveTimer = new Timer(500, this);
	private int state = 0;
	private int maxDive;
	private boolean diving = true; //When true it is going down when false it is going back up

	//Constructor
	public Turtle(int numTurtles, int maxDive, int startX, int startY, String fileName, int width, int height) {

		//Sets the location and size of the turtle
		this.startX = startX;
		this.startY = startY;
		setSize(width, height);

		//Sets the image of the turtle
		setIcon(new ImageIcon("images/" + fileName + ".gif"));

		//Sets the turtles that dive
		this.numTurtles = numTurtles;
		this.maxDive = maxDive;

		//Set the start timers
		moveTimer.start();
		diveTimer.start();

	}

	public int getStartX() {

		return startX;

	}

	public int getStartY() {

		return startY;

	}

	public void setMaxDive(int maxDive) {

		this.maxDive = maxDive;

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == moveTimer) {

			//If turtle goes off the screen
			if (getX() < -getWidth())

				//Reposition the turtle
				setLocation(FroggerGUI.FRAME_WIDTH, startY);

			else {

				//Continue moving the turtle by an increment	
				setLocation(getX() - 1, getY());

				//If frog lands ontop of turtle
				if (getBounds().intersects(FroggerGUI.frog.getBounds())) {

					FroggerGUI.frog.setRideDirection(-1); //The frog is the same direction of the turtle
					FroggerGUI.frog.getMoveTimer().setDelay(SPEED); //The frog has the same speed as the turtle
					FroggerGUI.frog.getMoveTimer().start(); //The frog has the same start time as the turtle

				}

			}

		}	else if (event.getSource() == diveTimer) {

			//States of the diving turtles
			setIcon(new ImageIcon("images/turtles" + numTurtles + state + ".gif"));

			//If turtle is diving
			if (diving)

				state++; //Dives into water

			else

				state--; //Rises from the water

			//When diving reaches maximum amount
			if (state == maxDive) {

				diving = false; //Stop diving

				//If the frog is still on the turtles back and it is rising
				if (maxDive == 4 && getBounds().intersects(FroggerGUI.frog.getBounds()))

					FroggerGUI.frog.loseLife(); //Frog loses life

			} else if (state == 0) //If the turtle is done diving

				diving = true; //Dive again
		}

	}

}

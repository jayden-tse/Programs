import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Vehicle extends JLabel implements ActionListener {

	//Vehicle's direction, start position and width
	private int direction;
	private int startX;
	private int startY;
	private int width;

	//Timer for moving
	private Timer moveTimer;		

	//Constructor
	public Vehicle(int direction, int speed, int startX, int startY, String fileName, int width, int height) {

		//Sets the direction, location and size of the vehicle
		this.direction = direction;
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.setSize(width, height);

		//Picture of each type of vehicle
		setIcon(new ImageIcon("images/" + fileName + ".gif"));

		//Set movetimer
		moveTimer = new Timer(speed, this);
		moveTimer.start();

	}

	public int getStartX() {

		return startX;

	}

	public int getStartY() {

		return startY;

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == moveTimer) {

			int x = getX(); //Sets a constant x-value for the vehicles

			//If the direction is going left and off the screen 
			if (direction == 1 && x < -width) 

				setLocation(FroggerGUI.FRAME_WIDTH, startY); //Reset location to the right side of the screen

			//If the vehicle goes off the screen
			else if (direction == -1 && x > FroggerGUI.FRAME_WIDTH) 

				setLocation(-width, startY); //Reset location to the left side of the screen

			else {

				setLocation(x - direction, getY()); //Sets the new location either 1 pixel to the right or left

				if (getBounds().intersects(FroggerGUI.frog.getBounds())) //If the vehicle touches the frog

					FroggerGUI.frog.loseLife(); //Frog loses life
					
			}
			
		}

	}

}

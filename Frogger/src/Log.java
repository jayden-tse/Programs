import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Log extends JLabel implements ActionListener {

	//Start position
	private int startX;
	private int startY;

	//Spped and moving time
	private int speed;
	private Timer moveTimer;

	//Constructor
	public Log(int speed, int startX, int startY, String fileName, int width, int height) {

		//Sets the location and size of the log
		this.startX = startX;
		this.startY = startY;
		this.setSize(width, height);

		//Picture of log
		setIcon(new ImageIcon("images/" + fileName + ".gif"));

		//Speed of the log
		this.speed = speed;

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

			//If the log goes off the screen
			if (getX() > FroggerGUI.FRAME_WIDTH+getWidth()) 

				setLocation(-getWidth()*2, startY); //Reset size smaller because the logs are too big (will overlap)

			//If the log does not go off the screen
			else {

				setLocation(getX() + 1, getY()); //Increment by 1 moves the log

				//If the frog is on the log
				if (getBounds().intersects(FroggerGUI.frog.getBounds())) {

					FroggerGUI.frog.setRideDirection(1); //The frog is the same direction of the log
					FroggerGUI.frog.getMoveTimer().setDelay(speed); //The frog has the same speed as the log
					FroggerGUI.frog.getMoveTimer().start(); //The frog has the same start time as the log

				}

			}

		}

	}

}
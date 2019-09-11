import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class Frog extends JLabel implements ActionListener {

	public static final ImageIcon[][] IMAGE = {

			{ new ImageIcon("images/frog00.gif"), new ImageIcon("images/frog01.gif"), new ImageIcon("images/frog02.gif") },
			{ new ImageIcon("images/frog10.gif"), new ImageIcon("images/frog11.gif"), new ImageIcon("images/frog12.gif") },
			{ new ImageIcon("images/frog20.gif"), new ImageIcon("images/frog21.gif"), new ImageIcon("images/frog22.gif") },
			{ new ImageIcon("images/frog30.gif"), new ImageIcon("images/frog31.gif"), new ImageIcon("images/frog32.gif") },

	};

	//Constants for frog's move amount and start position
	private static final int MOVE_AMOUNT = 50;
	private static final int START_X = 325;
	private static final int START_Y = 685;

	private int direction; //0 - left, 1 - up, 2 - right, 3 - down
	private int dx; //Amount frog's change in the x-direction
	private int dy; //Amount frog's change in the y-direction

	private int jumpState = 0; //0 - sitting, 1 - flexing, 2 - flying, 3 - done; referring to different jump states
	private Timer jumpTimer = new Timer(40, this); //Used to move the frog through its jump states
	private Timer moveTimer = new Timer(0, this); //Interval set to same amount as the log or turtle the frog lands on

	Timer DeathTimer = new Timer(500, this);
	private int state = 1;
	private int maxState = 4;
	private boolean death = true;

	private int rideDirection = 0; //0 or 1 or -1 depending on whether frog lands on a log or a turtle

	private int lives = 3; //Amount of lives frog has left
	public static int homeCount = 0; //Count number of homes frog has reached

	//Music and sound effects of frog 
	AudioClip clipDeath = Applet.newAudioClip(playMusic.class.getResource("sounds/deathsound.wav"));
	AudioClip clipOver = Applet.newAudioClip(playMusic.class.getResource("sounds/gameover.wav"));
	AudioClip clipCoin = Applet.newAudioClip(playMusic.class.getResource("sounds/coinsound.wav"));
	AudioClip clipMusic = Applet.newAudioClip(playMusic.class.getResource("sounds/stagemusic.wav"));
	AudioClip clipEat = Applet.newAudioClip(playMusic.class.getResource("sounds/eatnoise.wav"));

	//Constructor Method
	public Frog() {

		//Starting position of the frog
		setLocation(START_X, START_Y);
		setIcon(IMAGE[1][0]); //Starting frog image (upwards and sitting 10)

		clipMusic.loop();

	}

	//Getters and setters
	public Timer getMoveTimer() {

		return moveTimer;

	}

	public void setMoveTimer(Timer moveTimer) {

		this.moveTimer = moveTimer;

	}

	public int getRideDirection() {

		return rideDirection;

	}

	public void setRideDirection(int rideDirection) {

		this.rideDirection = rideDirection;

	}

	public int getLives() {

		return lives;

	}

	public void setLives(int lives) {

		this.lives = lives;

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		//Movement and picture of the frog when it moves
		if (event.getSource() == jumpTimer) {

			//Changes the frog animation state
			jumpState++;

			//If the frog is in the done state return to normal state
			if (jumpState == 3) {

				jumpTimer.stop();
				jumpState = 0;

			} else if (jumpState == 2) //If the frog is in flying state move the frog

				this.setLocation(getX() + dx, getY() + dy); //Find location and move the frog 

			if (getY() < 125 && direction == 1) {

				//For loop for the frog homes
				for(int x = 0; x < FroggerGUI.home.length; x++) {

					//Detect if the frog reaches the home and if there is no frog there
					if (this.getBounds().intersects(FroggerGUI.home[x].getBounds()) && FroggerGUI.home[x].getIcon() == null) {

						//Show the fly
						FroggerGUI.fly.Start();

						//Make the score value
						Score.counter--;
						FroggerGUI.score.scoreCheckUp();

						FroggerGUI.home[x].setIcon(new ImageIcon("images/frogHome.gif")); //When the frog reaches the home create the image
						homeCount++; //Increase frog reaching home count
						FroggerGUI.lilypads[x].setVisible(false); //Show lilypads

						//Play coin sound when home is reached
						try {

							FroggerGUI.playMusic.Play(clipCoin);

						} catch (Exception error) {

							System.out.println("error");

						}

						//When the all frogs reaches the home
						if (homeCount == 5 && lives == 3) {

							FroggerGUI.score.scoreCheckUp();
							
							reset(); 
							FroggerGUI.frog.setVisible(false);

							JOptionPane.showMessageDialog(null, "PERFECT!!! You've won the game with all your lives!");
							System.exit(0);

						} else if (homeCount == 5) {

							FroggerGUI.score.scoreCheckUp();
							
							reset(); 
							FroggerGUI.frog.setVisible(false);

							JOptionPane.showMessageDialog(null, "Congratulations - you've won the game");
							System.exit(0);

						} 

						else

							reset(); //Places frog below and reset location

					}

				}

			} else if (getY() == 385) //If the frog reaches the land in the middle

				moveTimer.stop(); //Stops the frog from changing states when on an object

			this.setIcon(IMAGE[direction][jumpState]);

		} else if (event.getSource() == moveTimer)

			this.setLocation(getX() + rideDirection, getY()); //Ridedirection only moves 1 pixel for smooth animation

		if (getX() < -25 || getX() > FroggerGUI.FRAME_WIDTH) //If the frog goes past the vertical screen resolution (the sides)

			loseLife(); //Lose a life

		if (getY() < 0 || getY() > 760) //If the frog goes past the horizontal screen resolution (the bottom)

			loseLife(); //Lose a life

		//In the river
		else if (getY() < 375) {

			boolean isDead = true; //Assume frog is dead if it does not land on an object

			//If it lands on a log in river you are not dead
			for (int x = 0; x < FroggerGUI.log.length; x++)
				if (getBounds().intersects(FroggerGUI.log[x].getBounds()))
					isDead = false; //If it lands it is not dead

			//If it lands on a turtle in river you are not dead
			for (int x = 0; x < FroggerGUI.turtle.length; x++)
				if (getBounds().intersects(FroggerGUI.turtle[x].getBounds()))
					isDead = false; //If it lands it is not dead

			//If it lands on a home in river you are not dead
			for (int x = 0; x < FroggerGUI.home.length; x++)
				if (getBounds().intersects(FroggerGUI.home[x].getBounds()))
					isDead = false; //If it lands it is not dead

			if (isDead)

				loseLife(); //If the frog does not land on object lose life

		}

		//If frog jumps on a home that is already filled
		for(int x = 0; x < FroggerGUI.home.length; x++) {

			if (this.getBounds().intersects(FroggerGUI.home[x].getBounds()) && FroggerGUI.home[x].getIcon() != null) {

				loseLife();

				reset();

			}
			
		}

		if (FroggerGUI.fly.getBounds().intersects(FroggerGUI.frog.getBounds())) {

			//If frog touches fly gain score
			FroggerGUI.score.scoreCheckUp();
			
			FroggerGUI.fly.setVisible(false);

			//Play an eat effect when fly is touched
			try {

				FroggerGUI.playMusic.Play(clipEat);

			} catch (Exception error) {

				System.out.println("error");

			}

		}

	}

	public void jump(int arrowDirection) {

		direction = arrowDirection - 37; //Find the direction depending on which arrow key is pressed

		dx = 0;
		dy = 0;

		//Virtual keys
		if (arrowDirection == KeyEvent.VK_UP)

			dy = -MOVE_AMOUNT;

		else if (arrowDirection == KeyEvent.VK_RIGHT)

			dx = MOVE_AMOUNT;

		else if (arrowDirection == KeyEvent.VK_DOWN)

			dy = MOVE_AMOUNT;

		else if (arrowDirection == KeyEvent.VK_LEFT)

			dx = -MOVE_AMOUNT;

		jumpTimer.start(); //After a button is pressed start changing frog animation states

	}

	public void loseLife() { 

		FroggerGUI.frog.setLives(FroggerGUI.frog.getLives()-1); //Decrement by 1

		FroggerGUI.lives[FroggerGUI.frog.getLives()].setVisible(false); //Decreasing heart images by 1 when life is lost

		//If game is still not over play death sound
		if(FroggerGUI.frog.getLives() >= 1) {

			try {

				FroggerGUI.playMusic.Play(clipDeath);

			} catch (Exception error) {

				System.out.println("error");

			}

		}

		//If no lives are remaining
		if (FroggerGUI.frog.getLives() == 0) {

			//Play gameover sound
			try {

				FroggerGUI.playMusic.Stop(clipMusic);

			} catch (Exception error) {

				System.out.println("error");
			}

			try {
				
				FroggerGUI.playMusic.Play(clipOver);

			} 

			catch (Exception error) {
				System.out.println("error");

			}

			FroggerGUI.death.Start();
			
			reset();
			
//			new Save();

			//Lose game
			JOptionPane.showMessageDialog(null, "GAME OVER");
			System.exit(0);

		}

		reset(); //Reset the frog location at the bottom

	}


	public void reset() {

		//Stop both move and jump timers
		moveTimer.stop();
		jumpTimer.stop();

		//Set to normal sitting state
		jumpState = 0;

		//Starting direction, image and location
		setLocation(START_X, START_Y);
		setIcon(IMAGE[1][0]);

	}

}
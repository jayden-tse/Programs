/*
 * Make a score timer
 * starts at 120 and decrease
 * score timer is the point multiplier when the game is finished
 * when game is finish multiply the remaining time with 100 and add to points
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Score extends JLabel implements ActionListener, KeyListener {


	Timer Timer = new Timer(1000, this);
	static int time = 120;

	static int points = 0;
	static int counter = 0;
	static int highest = 0;

	public Score() {

		setText(String.valueOf(points));
		setBounds(90, 740, 100, 25);
		setForeground(Color.WHITE);
		setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		Timer.start();

	}
	
	public void scoreCheckUp() {

		counter++;

		//If frog goes up 1 unit
		if (counter > highest) {
			highest = counter;
			points += 10;

		}
		//If frog reaches max distance reset
		else if (highest == 12) {
			counter = 0;
			highest = 0;
		}

		//If frog touches the home
		for(int x = 0; x < FroggerGUI.home.length; x++) {

			if (FroggerGUI.frog.getBounds().intersects(FroggerGUI.home[x].getBounds()) && FroggerGUI.home[x].getIcon() == null)

				points += 100;
			
		}

		//If frog touches the fly
		if (FroggerGUI.fly.getBounds().intersects(FroggerGUI.frog.getBounds())) {

			Score.points += 100;
			
		}

		//If frog finishes game
		if (Frog.homeCount == 5) {

			points += 100 * (time);
			points += FroggerGUI.frog.getLives() * 50;

		}

		setText(String.valueOf(points));
	}

	
	public static int getPoints() {
		return points;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == Timer) {

			if(time > 0) {
				time--;

			}

		}

	}
	

	@Override
	public void keyPressed(KeyEvent event) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.*;

public class Time extends JLabel implements ActionListener {

	//Set timer and time value
	Timer ScoreTimer = new Timer(1000, this);
	static int time = 120;

	public Time() {

		//Set properties of the timer
		setText(String.valueOf(time));
		setBounds(336, 10, 100, 25);
		setForeground(Color.WHITE);
		setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		ScoreTimer.start();

	}

	@Override	
	public void actionPerformed(ActionEvent event) {

		//Decrement to the timer
		if(event.getSource() == ScoreTimer) {

			if (time > 0) {

				time--;
				setText(String.valueOf(time));

			}

		}

	}

}
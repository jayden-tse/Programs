import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;

public class Fly extends JLabel implements ActionListener {

	//Set duration of fly being visible
	Timer FlyTimer = new Timer(1000, this);
	int FlyTime = 20;

	public Fly() {

		//Create properties of fly
		//setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
		setIcon(new ImageIcon("images/fly.gif"));
		setVisible(false);
	}

	public void Start() {

		//Random positioning
		setBounds(10 + (int) (650 * Math.random()), 120 + (int) (560 * Math.random()), 40, 40);

		//Show the fly
		FlyTimer.stop();
		FlyTimer.start();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		//Decrement to flytime
		if (event.getSource() == FlyTimer) {
			if (FlyTime > 0) {
				FlyTime--;

			}

			else {

				//If time is done make fly invisible
				FlyTime = 20;
				FlyTimer.stop();
				setVisible(false);
				
			}

			//Make fly invisible if game is done 
			if (FroggerGUI.frog.homeCount == 5) {
				
				FlyTimer.stop();
				setVisible(false);
				
			}

		}

	}

}

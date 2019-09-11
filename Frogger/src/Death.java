import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.*;

public class Death extends JLabel implements ActionListener {

	//Properties of death animation
	Timer DeathTimer = new Timer(50, this);
	private int state = 1;
	private int maxState = 12;
	private boolean death = true;

	public Death() {

	}

	public void Start() {

		//Start the death animation
		DeathTimer.start();

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == DeathTimer) {

			//Change the death states
			FroggerGUI.frog.setIcon(new ImageIcon("images/death" + state + ".gif"));

			//When death is set change pictures
			if (death)

				state++;

			//When death is done changing stop
			if (state == maxState) {

				state = 1;

				death = false;

				DeathTimer.stop();

				FroggerGUI.frog.setVisible(false);

			}

		}

	}

}

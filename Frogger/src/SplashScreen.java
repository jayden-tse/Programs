import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {//makes loading screen

	//Values and timer for jprogressbar
	private static JProgressBar progressBar;
	private static int counter = 1;
	private static int TimerSpeed = 20;
	private static int maximum = 100;
	private static Timer progressBarTimer;
	static int done = 0;

	public SplashScreen(String image) {

		//Create the image
		createSplash(image);

	}

	private void createSplash(String image) {

		//Create panel and add image to panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel splashImage = new JLabel(new ImageIcon((image)));
		panel.add(splashImage);

		//Properties of the progressbar
		progressBar = new JProgressBar();
		progressBar.setMaximum(maximum);
		progressBar.setForeground(new Color(0, 0, 0));
		progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(progressBar, BorderLayout.SOUTH);

		//Properties on window
		this.setContentPane(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		//Start the timer
		startProgressBar();

	}

	private void startProgressBar() {

		//Set timer speed
		progressBarTimer = new Timer(TimerSpeed, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//Input the variables set
				progressBar.setValue(counter);

				//When counter is done stop the progressbar timer
				if (maximum == counter){

					SplashScreen.this.dispose();
					progressBarTimer.stop();
					done = 1;

				}

				counter++;
			}

		});

		//Start timer
		progressBarTimer.start();

	}

	public static int done(){
		
		return counter;

	}

}
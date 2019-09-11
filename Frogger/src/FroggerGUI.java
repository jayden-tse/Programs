import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class FroggerGUI extends JFrame implements KeyListener {

	//Constant variables for the frame size
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 800;

	//Classes
	public static Frog frog = new Frog();
	public static Vehicle[] vehicle = new Vehicle[12];
	public static Log[] log = new Log[9];
	public static Turtle[] turtle = new Turtle[8];
	public static Snake snake = new Snake();
	public static Time time = new Time();
	public static Score score = new Score();
	public static Death death = new Death();
	public static Fly fly = new Fly();

	//JLabels
	public static JLabel[] lilypads = new JLabel[5];
	public static JLabel[] home = new JLabel[5];
	public static JLabel[] lives = new JLabel[3];

	//Music
	AudioClip clipMusic = Applet.newAudioClip(playMusic.class.getResource("sounds/stagemusic.wav"));
	AudioClip clipHop = Applet.newAudioClip(playMusic.class.getResource("sounds/hopsound.wav"));

	static playMusic playMusic = new playMusic();

	public FroggerGUI() {

		//Frame setup
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); 
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/background.gif").getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, 0)))); //Rescale the background
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this); 

		//Add the frog
		frog.setBounds(frog.getX(),frog.getY(),45,50);
		//frog.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
		add(frog);

		//How many cars there are and selecting which type of car
		for(int x = 0; x < vehicle.length; x++) {

			//For all the types of cars
			//1 number = direction 
			//2 number = speed 
			//3 number = x position (spread apart) 
			//4 number = y position 
			//5 and 6 number = size

			if (x < 3)

				vehicle[x] = new Vehicle(1, 5, 150 + 220 * (3 - x), 635, "yellowcar", 100, 40); // Position and movement of the yellowcar

			else if (x < 6)

				vehicle[x] = new Vehicle(-1, 14, -300 + 225 * (6 - x), 585, "bulldozer", 100, 35); // Position and movement of the dozer

			else if (x < 9)

				vehicle[x] = new Vehicle(1, 6, 100 + 220 * (9 - x), 535, "purplecar", 100, 38); // Position and movement of the purplecar

			else if (x < 10)

				vehicle[x] = new Vehicle(-1, 2, 50 + 150 * (10 - x), 490, "bluecar", 100, 35); // Position and movement of the greencar

			else if (x < 12)	

				vehicle[x] = new Vehicle(1, 8, 100 + 300 * (12 - x), 435, "truck", 100, 48); // Position and movement of the truck

			vehicle[x].setBounds(vehicle[x].getStartX(), vehicle[x].getStartY(), vehicle[x].getWidth(), vehicle[x].getHeight());
			//vehicle[x].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
			add(vehicle[x]);

			//For the turtle/flipping turtle
			//1 number = number of turtles 
			//2 number = maxdive
			//3 number = x position (spread apart) 
			//4 number = y position
			//5 number = file name
			//6 and 7 number = size
		}

		for(int x = 0; x < 8; x++) {

			if (x < 4) {

				turtle[x] = new Turtle(3, 1, 200 * (4 - x), 335, "turtles30", 150, 50);

				if (x == 2)

					turtle[x].setMaxDive(4);

			} else if (x < 8) {

				turtle[x] = new Turtle(2, 1, 175 * (8 - x), 185, "turtles20", 100, 50);

				if (x == 7) 

					turtle[x].setMaxDive(4);

			}

			turtle[x].setBounds(turtle[x].getStartX(), turtle[x].getStartY(), turtle[x].getWidth(),turtle[x].getHeight());
			//turtle[x].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
			add(turtle[x]);

		}

		//For the logs
		//1 number = speed 
		//2 number = x position (spread apart) 
		//3 number = y position 
		//4 and 5 number = size

		for(int x = 0; x < log.length; x++) {

			if (x < 3)

				log[x] = new Log(8, -200 + 300 * (3 - x), 285, "logShort", 125, 50);

			else if (x < 6)

				log[x] = new Log(4, -300 + 400 * (6 - x), 235, "logLong", 285, 50);

			else if (x < 9)

				log[x] = new Log(9, -200 + 300 * (9 - x), 135, "logMedium", 175, 50);

			log[x].setBounds(log[x].getStartX(), log[x].getStartY(), log[x].getWidth(), log[x].getHeight());
			//log[x].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
			add(log[x]);

		}

		//Adding the snake
		snake.setBounds(snake.getStartX(), snake.getStartY(), 128 , 49);
		//snake.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
		add(snake);

		//Adding the fly, score, and time
		add(fly);
		add(score);
		add(time);


		//Lilypads
		for(int x = 0; x < home.length; x++) {

			lilypads[x] = new JLabel();
			lilypads[x].setBounds(23 + x * 150, 80, 50, 50);
			lilypads[x].setIcon(new ImageIcon("images/lilypads.gif"));
			add(lilypads[x]);

		}

		//For the homes of the frog
		//1 number = x position
		//2 number = y position
		//3 and 4 number = size

		for(int x = 0; x < home.length; x++) {

			home[x] = new JLabel();
			home[x].setBounds(27 + x * 150, 80, 50, 50);
			add(home[x]);

		}

		//Creating the live images
		for(int x = 0; x < lives.length; x++) {

			lives[x] = new JLabel();
			lives[x].setIcon(new ImageIcon("images/heart.gif"));
			lives[x].setBounds(25 * x + 5, 25, 20, 20);
			add(lives[x]);

		}

		//Text for lives
		JLabel lives = new JLabel();

		lives.setText("lives");
		lives.setBounds(24, 5, 50, 20);
		lives.setForeground(Color.WHITE);
		lives.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		add(lives);

		//Text for score
		JLabel scoreLabel = new JLabel();

		scoreLabel.setText("score:");
		scoreLabel.setBounds(10, 738, 100, 30);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(scoreLabel);

		//Text for highscore
		JLabel highScoreLabel = new JLabel();

		highScoreLabel.setText("highscore:");
		highScoreLabel.setBounds(500, 738, 200, 30);
		highScoreLabel.setForeground(Color.WHITE);
		highScoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(highScoreLabel);

		//Value for highscore
		JLabel highScore = new JLabel();

		//highScore.setText(String.valueOf(Load.currentline));
		highScore.setBounds(550, 738, 100, 100);
		highScore.setForeground(Color.WHITE);
		highScore.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(highScore);

		setVisible(true);
		
		new Load();
		new Save();

	}

	@Override
	public void keyPressed(KeyEvent event) {

		//The 4 arrow keys (37 - left, 38 - up, 39 - right, 40 - down)
		if (event.getKeyCode() >= 37 && event.getKeyCode() <= 40) {
			frog.jump(event.getKeyCode());

			try {

				playMusic.Play(clipHop);

			} catch (Exception error) {

				System.out.println("error");
			}

			if(event.getKeyCode() == 38) {
				score.scoreCheckUp(); 

			}

			if(event.getKeyCode() == 40) {
				score.counter--;

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameMenu extends JFrame implements ActionListener{

	//Set images
	JButton playbutton = new JButton(new ImageIcon("images/playbutton.png"));
	JButton quitbutton = new JButton(new ImageIcon("images/quitbutton.png"));
	JLabel titlelabel = new JLabel(new ImageIcon("images/froggerlogo.png"));

	public GameMenu(){

		//Properties of the frame
		setSize(FroggerGUI.FRAME_WIDTH, FroggerGUI.FRAME_HEIGHT);
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/menubackground.gif").getImage().getScaledInstance(FroggerGUI.FRAME_WIDTH, FroggerGUI.FRAME_HEIGHT, 0))));
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Set title picture properties
		titlelabel.setBounds(40, 50, 600, 200);//title
		add(titlelabel);
		titlelabel.setVisible(true);

		//Set play button properties
		playbutton.setBounds(190, 300, 304, 175);//start button, starts game
		add(playbutton);
		playbutton.setOpaque(false);
		playbutton.setContentAreaFilled(false);
		playbutton.setBorderPainted(false);
		playbutton.setVisible(true);
		playbutton.addActionListener(this);

		//Set quit button properties
		quitbutton.setBounds(190, 500, 283, 177);//quit button
		add(quitbutton);
		quitbutton.setVisible(true);
		quitbutton.setContentAreaFilled(false);
		quitbutton.setBorderPainted(false);
		quitbutton.setVisible(true);
		quitbutton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//When play button is pressed continue to frogger
		if(e.getSource() == playbutton){

			new FroggerGUI();
			dispose();
		}

		//When quit button is pressed quit
		if(e.getSource() == quitbutton){
			dispose();
		}

	}

}
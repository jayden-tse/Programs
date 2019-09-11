import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class playMusic {
	
	public playMusic(){
		
	}

	public void Play(AudioClip clip) {
		
		//Plays the music
		clip.play();
	}
	
	public void Stop(AudioClip clip) {
		
		//Stops the music
		clip.stop();
	}
	
}

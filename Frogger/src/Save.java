import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

	public Save() {

		try {
			// creating a text files
			BufferedWriter output = new BufferedWriter(new FileWriter("highscorefile.txt"));
			//write to file

			output.write(Integer.toString(Score.getPoints()));

			output.close();

		} catch (IOException e) {
			
			System.out.println("error saving");
			
		}

	}

}
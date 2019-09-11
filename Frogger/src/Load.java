import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Load {

	public static int highest;

	public Load() {

		try {

			Scanner input = new Scanner(new File("highscorefile.txt"));

			while (input.hasNextLine()) {

				String line = input.nextLine();

				System.out.println(line);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
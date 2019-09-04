import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

	//Reads the csv file to obtain the information and then making an instance of the data class

public class FileReader {
	
	public FileReader (ArrayList<Data> data) {

		int index = 0;
		
		try {
			
			Scanner input = new Scanner(new File("data.csv"));

			input.useDelimiter(",");

			while (input.hasNextLine()) {

				data.add(new Data());
				
				data.get(index).setDate(input.next());
				
				data.get(index).setOpen(input.nextDouble());
				
				data.get(index).setHigh(input.nextDouble());
				
				data.get(index).setLow(input.nextDouble());
								
				data.get(index).setClose(input.nextDouble());
				
				data.get(index).setVolume(input.nextLong());
				
				System.out.println(data.get(index).toString());
				
				input.nextLine();
				
				index ++;
				
			} input.close();
			
		}	catch(FileNotFoundException e) {
			
			System.out.println("File not found");
		}
	}
}

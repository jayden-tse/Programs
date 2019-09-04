import java.util.ArrayList;

/*
 * Jayden Tse - Author
 * June 12, 2018
 * ICS3U1-03 Mr.Fernandes
 * Stock Analyzing Application
 * 
 * This application will analyze the prices of stocks in the past and attempt to predict the possible price in the future by using a CSV file
 * to create trends with the past stock prices. It will analyze the values of the open, high, low, close, and volume data within a regular
 * stock price data set.
 * 
 * Some features would be allowing the user to input a fixed amount of days to see how the price may see in the future. The main feature would be
 * the visual line chart graph when the application is ran.
 * 
 * Major skills used within this project would be to hold and utilize data obtained from a csv file. Another skill would be to anaylze trends that
 * exist within large data sets.
 * 
 * One large error would be the methods of my trends. I attempted to analyze and create algorithms to create trends, however it would simply cancel
 * each other out overtime. Another factor would be my lack of knowledge within stock prices, of how they truly work as my knowledge is very limited
 * when it comes to stock prices.
 * 
 */

public class DataMiningTest {
	
	public static ArrayList<Data> data = new ArrayList<Data>();
	
	public static void main(String[] args) {

		new AnalyizationGUI();	
		
		new FileReader(data);
	
	}
}

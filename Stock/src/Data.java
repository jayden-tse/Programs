
public class Data {

	private int year;
	private int month;
	private int day;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	
	//Takes the year, month, and day in the string format of the date
	public void setDate(String date) {
		this.year = Integer.parseInt(date.substring(0, 4));
		this.month = Integer.parseInt(date.substring(5, 7));
		this.day = Integer.parseInt(date.substring(8, 10));
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public double getOpen() {
		return open;
	}
	
	public void setOpen(double open) {
		this.open = open;
	}
	
	public double getHigh() {
		return high;
	}
	
	public void setHigh(double high) {
		this.high = high;
	}
	
	public double getLow() {
		return low;
	}
	
	public void setLow(double low) {
		this.low = low;
	}
	
	public double getClose() {
		return close;
	}
	
	public void setClose(double close) {
		this.close = close;
	}
	
	public long getVolume() {
		return volume;
	}
	
	public void setVolume(long volume) {
		this.volume = volume;
	}


	@Override
	public String toString() {
		return "year = " + year + ", month = " + month + ", day = " + day + ", open = " + open + ", high = "
					   + high + ", low = " + low + ", close = " + close + ", volume = " + volume;
	}
	
	
}
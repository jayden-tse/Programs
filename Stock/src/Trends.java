import java.util.ArrayList;

public class Trends {

	public static ArrayList<Data> data = DataMiningTest.data;

	public double open;
	public double high;
	public double low;
	public double close;
	public int volume;
	public double price;

	public double hmean;
	public double hSD;

	public double rhmean;
	public double rhSD;

	public double lmean;
	public double lSD;

	public double rlmean; 
	public double rlSD;

	public int pratio;
	public int ratio;

	public double rchange;
	public double achange;
	public int recent;

	public double vSD;
	public double vMean;
	public double ovolume;
	public double rovolume;

	public Trends(double[] parray, int index) {

		//Adds the new data set made to make future trends more accurate
		System.out.printf("%d\t%f\t%f\t%f\t%f\n", getVolume(volume, ovolume, rovolume, vSD), getHigh(high, rhSD, hSD), getLow(low, rlSD, lSD), getOpen(open, rchange), getClose(close, achange));
		
		DataMiningTest.data.add(0, new Data());
		gethighSD(hSD, hmean);
		getrecenthighSD(rhSD, rhmean, high, hSD);
		getlowSD(lSD, lmean);
		getrecentlowSD(rlSD, rlmean, low, lSD);
		getrChange(recent, rchange, open, close);
		getaChange(achange);
		//recentChange(recent, rchange, achange, open, close);
		getoverallVolume(ovolume, vSD, vMean);
		getrecentVolume(rovolume, ovolume, vSD, volume);
		output(price, high, low, parray, index, open, close);


	}

	/*if the standard deviation is higher than 0.5 it is more volatile to changes in stock prices dependent on other factors such as volume
	 *if the standard deviation is lower than 0.5 it is less volatile to changes and will steadily increase/decrease dependent on other factors
	 *
	 *volume is the most evident factor for the stock price to increase/decrease as it will grow in demand within the market
	 * 
	 *if the open value is greater than the high value of 
	 */


	public void sethighSD(double hSD) {
		this.hSD = hSD;
	}

	public double gethighSD(double hSD, double hmean) {
		//SD constant of the entire high prices

		/*if standard deviation is higher than 0.5 and the data.get(0).getHigh() is bigger than mean, it is more likely to drop unless
		 *the volume of the stock is greater than previous days as the standard deviation represents an unstable data set
		 *
		 *if standard deviation is higher than 0.5 and the data.get(0).getHigh() is smaller than mean, it is more likely to increase unless
		 *the volume of the stock is less than previous days
		 *
		 */

		double a = 0;
		double diff = 0;

		for (int x = 0; x < data.size(); x++) 
			a += data.get(x).getHigh();

		hmean = a/data.size();

		for (int x = 0; x < data.size(); x++)
			diff += Math.pow(data.get(x).getHigh() - hmean, 2);

		hSD = Math.sqrt(diff/(hmean-1));

		return hSD;
	}

	public void setrecenthighSD(double rhSD) {
		this.rhSD = rhSD;
	}

	public double getrecenthighSD(double rhSD, double rhmean, double high, double hSD) {
		//SD constant of the recent high prices

		/*if the recent standard deviation is higher than the highSD and the data.get(0).getHigh() is bigger than the high mean and the
		 *recent high mean there is a higher difference comparatively and will most likely drop unless the volume of stock is greater
		 *than previous days as the standard deviation represents an unstable data set
		 *
		 */

		double a = 0;
		double diff = 0;

		for (int x = 0; x < 5; x++) 
			a += data.get(x).getHigh();

		rhmean = a/5;

		for (int x = 0; x < 5; x++) 
			diff += Math.pow(data.get(x).getHigh() - rhmean, 2);

		rhSD = Math.sqrt(diff/(rhmean-1));

		return rhSD;
	}

	public void setlowSD(double lSD) {
		this.lSD = lSD;
	}

	public double getlowSD(double lSD, double lmean) {
		//SD constant of the entire low prices

		double a = 0;
		double diff = 0;

		for (int x = 0; x < data.size(); x++)
			a += data.get(x).getLow();

		lmean = a/data.size();

		for (int x = 0; x < data.size(); x++) 
			diff += Math.pow(data.get(x).getLow() - lmean, 2);

		lSD = Math.sqrt(diff/(lmean-1));

		return lSD;
	}

	public void setrecentlowSD(double rlSD) {
		this.rlSD = rlSD;
	}

	public double getrecentlowSD(double rlSD, double rlmean, double low, double lSD) {
		//SD constant of the recent low prices

		/*if the recent standard deviation is higher than the lowSD and the data.get(0).getLow() is bigger than the low mean and the
		 *recent low mean there is a higher difference comparatively and will most likely drop unless the volume of stock is greater
		 *than previous days as the standard deviation represents an unstable data set
		 *
		 *if volume > past volume
		 *low = past low + rlSD
		 *else
		 *low = past low - rlSD
		 * 
		 */

		double a = 0;
		double diff = 0;

		for (int x = 0; x < 5; x++) 
			a += data.get(x).getLow();

		rlmean = a/5;

		for (int x = 0; x < 5; x++) 
			diff += Math.pow(data.get(x).getLow() - rlmean, 2);

		rlSD = Math.sqrt(diff/(rlmean-1));

		return rlSD;
	}

	public void setrChange(double rchange) {
		this.rchange = rchange;
	}

	public double getrChange(int recent, double rchange, double open, double close) {
		//find the overall increase/decrease of recent data (1 week/5 days)

		recent = 0;
		rchange = 0;
		double o = 0;
		double c = 0;

		for (int x = 0; x < data.size(); x++) {

			if (x == data.size() - 1)
				break;
			if (data.get(x).getOpen() >= data.get(x + 1).getClose())
				o++;
			else
				c++;
		}

		if (o >= c)
			recent ++;
		else
			recent --;
		
		for (int x = 0; x < 5; x++) {
			o = data.get(x).getOpen();
			c = data.get(x + 1).getClose();
			rchange += o - c;
		}

		rchange /= 5;

		//if rchange is positive, it is more likely for the prices to increase
		//open = past close + rchange
		//if rchange is negative, it is more likely for the prices to decrease
		//open = past close - rchange
		
		return rchange;

	}
	
	public void setaChange(double achange) {
		this.achange = achange;
	}
	
	public double getaChange(double achange) {
		
		double c;
		double o;
		
		for (int x = 0; x < 5; x++) {
			c = data.get(x).getOpen();
			o = data.get(x).getClose();
			achange += c - o;
		}

		achange /= 5;
		

		//if achange is positive, it is more likely for the prices to increase
		//close = open + achange
		//if achange is negative, it is more likely for the prices to decrease
		//lose = open - achange
		
		return achange;

	}
	
	public void setoverallVolume(double vSD) {
		this.vSD = vSD;
	}

	public double getoverallVolume(double ovolume, double vSD, double vMean) {
		//overall popularity of the stock

		int i = 0;
		int diff = 0;
		ovolume = 0;

		for (int x = 0; x < data.size(); x++) 
			i += data.get(x).getVolume();

		vMean /= data.size();

		for (int x = 0; x < data.size(); x++)
			diff += Math.pow((i - vMean), 2);

		vSD = Math.sqrt(diff/(data.size() - 1));

		return vSD;
	}

	public void setrecentVolume(double rovolume) {
		this.rovolume = rovolume;
	}

	public double getrecentVolume(double rovolume, double ovolume, double vSD, int volume) {
		//recent popularity of the stock

		rovolume = 0;

		for (int x = 0; x < 5; x++) 
			rovolume += data.get(x).getVolume();

		rovolume = rovolume/5;

		return rovolume;
	}

	public double getOpen(double open, double rchange) {
		if(rchange > 0)
			open = data.get(1).getOpen() + getrChange(recent, rchange, open, close);
		else
			open = data.get(1).getOpen() - getrChange(recent, rchange, open, close);
	
		DataMiningTest.data.get(0).setOpen(open);
		
		return open;

	}

	public void setOpen(double open) {
		this.open = open;

	}

	public double getHigh(double high, double rhSD, double hSD) {
		if (rhSD > hSD && data.get(0).getVolume() > data.get(1).getVolume() && data.get(0).getOpen() > data.get(1).getClose())
			high = data.get(1).getHigh() + getrecenthighSD(rhSD, rhmean, high, hSD);
		else
			high = data.get(1).getHigh() - getrecenthighSD(rhSD, rhmean, high, hSD);
	
		DataMiningTest.data.get(0).setHigh(high);

		return high;
	}

	public void setHigh(double high) {
		this.high = high;

	}

	public double getLow(double low, double rlSD, double lSD) {
		if (rlSD > lSD && data.get(0).getVolume() > data.get(1).getVolume() && data.get(0).getOpen() > data.get(1).getClose())
			low = data.get(1).getLow() + getlowSD(lSD, lmean);
		else
			low = data.get(1).getLow() - getlowSD(lSD, lmean);

		DataMiningTest.data.get(0).setLow(low);
		
		return low;
	}

	public void setLow(double low) {
		this.low = low;

	}

	public double getClose(double close, double achange) {
		if(achange > 0)
			close = data.get(1).getOpen() + getaChange(achange);
		else
			close = data.get(1).getOpen() - getaChange(achange);
		
		DataMiningTest.data.get(0).setClose(close);
		return close;
	}

	public void setClose(double close) {
		this.close = close;

	}

	public int getVolume(int volume, double ovolume, double rovolume, double vSD) {
		if (ovolume < data.get(0).getVolume() && rovolume > ovolume) 
			volume = (int) (data.get(0).getVolume() + Math.random() * getoverallVolume(ovolume, vSD, vMean));
		else
			volume = (int) (data.get(0).getVolume() - Math.random() * getoverallVolume(ovolume, vSD, vMean));
		
		DataMiningTest.data.get(0).setVolume(volume);
		
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void output(double price, double high, double low, double[] parray, int index, double open, double close) {

		parray[index] = ((getHigh(high, rhSD, hSD) + getLow(low, rlSD, lSD))/2 + (getOpen(open, rchange) + getClose(close, achange))/2)/2;

		System.out.printf("%d\t%f\t%f\t%f\t%f\t%f\n", getVolume(volume, ovolume, rovolume, vSD), getHigh(high, rhSD, hSD), getLow(low, rlSD, lSD), getOpen(open, rchange), getClose(close, achange), parray[index]);


	}


}

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.swing.*;

public class AnalyizationGUI extends JFrame implements ActionListener {

	private double[] parray;

	private int numS;
	private int period = 0;

	private String aday;
	private String astock;

	public static JPanel panel = new JPanel();

	private JButton start = new JButton("Start");

	private JLabel stocks = new JLabel("Amount of Stocks");
	private	JLabel day = new JLabel("Days from Today");
	public static JLabel profit = new JLabel("Expected Profit");

	private JTextField amount = new JTextField(1);
	private JTextField days = new JTextField(365);
	public static JTextField aprofit = new JTextField();


	public AnalyizationGUI () {

		super("Stock Price Application");
		setSize(1370, 768);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		panel.setVisible(true);;
		panel.setSize(1370, 768);
		panel.setLayout(null);
		add(panel);

		start.setBounds(1100, 590, 175, 50);
		start.addActionListener(this);
		start.setVisible(true);
		panel.add(start);

		stocks.setBounds(75, 600, 500, 30);
		stocks.setFont(new Font("Arial", Font.BOLD, 20));
		stocks.setVisible(true);
		panel.add(stocks);

		day.setBounds(805, 600, 500, 30);
		day.setFont(new Font("Arial", Font.BOLD, 20));
		day.setVisible(true);
		panel.add(day);

		profit.setBounds(380, 600, 500, 30);
		profit.setFont(new Font("Arial", Font.BOLD, 20));
		profit.setVisible(true);
		panel.add(profit);

		amount.setBounds(250, 600, 100, 30);
		amount.addActionListener(this);
		amount.setVisible(true);
		panel.add(amount);

		days.setBounds(970, 600, 100, 30);
		days.addActionListener(this);
		days.setVisible(true);
		panel.add(days);

		aprofit.setBounds(530, 600, 100, 30);
		aprofit.setEditable(false);
		aprofit.setVisible(true);
		panel.add(aprofit);

		new XYLineChart("Stock Price Application", "Stock Price Overtime", period, parray, false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == days) {

			aday = days.getText();
			period = Integer.parseInt(aday);

			parray = new double[period];

			days.disable();
			days.repaint();

		}

		if (e.getSource() == amount) {
			astock = amount.getText();
			numS = Integer.parseInt(astock);

			amount.disable();
			amount.repaint();
		}

		if (e.getSource() == start && numS > 0 && period > 0 ) {

			int index = 0;


			for(int x = 0; x < period; x++) {

				new Trends(parray, index);

				index++;

			}
			//Creates the linechart
			new XYLineChart("Stock Price Application", "Stock Price Overtime", period, parray, true);

			//Calculate the profit
			double fprofit = (numS * parray[period - 1]);
			DecimalFormat df = new DecimalFormat("#.00");
			String fprofitFormated = df.format(fprofit);
			
			aprofit.setText("$" + fprofitFormated); 
		}

		if (e.getSource() == start && (numS == 0 || period == 0))
			JOptionPane.showMessageDialog(new JFrame(), "Please enter an amount of stocks and number of days");

	}


}

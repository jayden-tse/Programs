import org.jfree.chart.ChartPanel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//Creates the linechart

public class XYLineChart extends ApplicationFrame {

	public static ArrayList<Data> data = DataMiningTest.data;

	public XYLineChart(String applicationTitle, String chartTitle, int period, double[] parray, boolean print) {
		super(applicationTitle);

		if (print) {
			AnalyizationGUI.panel.removeAll();
			AnalyizationGUI.panel.add(AnalyizationGUI.profit);
			AnalyizationGUI.panel.add(AnalyizationGUI.aprofit);
			AnalyizationGUI.panel.revalidate();
		}
		
		JFreeChart XYlineChart = ChartFactory.createXYLineChart(
				chartTitle,
				"Day","Price of Stock ($)",
				createDataset(period, parray, print),
				PlotOrientation.VERTICAL,
				true,true,false);
		
		ChartPanel chartPanel = new ChartPanel(XYlineChart);
		chartPanel.setBounds(200, 50, 1000, 500);
		chartPanel.setVisible(true);
		AnalyizationGUI.panel.add(chartPanel);
		AnalyizationGUI.panel.repaint();
		XYPlot xyPlot = (XYPlot) XYlineChart.getPlot();
		NumberFormat yAxisFormat = DecimalFormat.getInstance();
		NumberAxis xAxis = (NumberAxis) xyPlot.getDomainAxis();
		NumberAxis yAxis = (NumberAxis) xyPlot.getRangeAxis();

		xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		yAxisFormat.setMinimumFractionDigits(2);
		yAxis.setNumberFormatOverride(yAxisFormat);

	}

	private XYSeriesCollection createDataset(int period, double[] parray, boolean print) {
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		final XYSeries series = new XYSeries("Prices");
		
		if (print) {
			for(int x = 0; x < period; x++) {
				series.add(x + 1, parray[x]);
			
			}
		dataset.addSeries(series);

		}

		return dataset;

	}

}

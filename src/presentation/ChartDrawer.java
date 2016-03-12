package presentation;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import domini.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

/**
 * Drawer of the statistics chart uses JfreeChart library
 * @author Ruben Marias
 */

public class ChartDrawer extends ApplicationFrame {

    //en aquest ordre clique, louvain, girvan
    private static ArrayList<Pair<Integer, Double>> p1 = new ArrayList<>();
    private static ArrayList<Pair<Integer, Double>> p2 = new ArrayList<>();
    private static ArrayList<Pair<Integer, Double>> p3 = new ArrayList<>();
    private static boolean clique = false;
    private static boolean louvain = false;
    private static boolean girvan = false;
    private static JPanel panel = new JPanel();
    private static ChartPanel CP;
    /**
     * Creates a new chart.
     *
     * @param title  the frame title.
     */
    private ChartDrawer(final String title) {

        super(title);

        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        chartPanel.setMouseZoomable(false, false);
        setContentPane(chartPanel);
        CP = new ChartPanel(chart);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private XYDataset createDataset() {

        final XYSeries series1 = new XYSeries("Clique");
        for (Pair<Integer, Double> p : p1) {
            series1.add((double) p.getFirst(), p.getSecond()/1E9);
        }

        final XYSeries series2 = new XYSeries("Louvain");
        for (Pair<Integer, Double> p : p2) {
            series2.add((double) p.getFirst(), p.getSecond()/1E9);
        }

        final XYSeries series3 = new XYSeries("Girvan-Newman");
        for (Pair<Integer, Double> p : p3) {
            series3.add((double) p.getFirst(), p.getSecond()/1E9);
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        if (clique) dataset.addSeries(series1);
        if (louvain) dataset.addSeries(series2);
        if (girvan) dataset.addSeries(series3);

        return dataset;
    }

    /**
     *
     * @param A Set the first array of points to be representated
     */
    public static void setP1(ArrayList<Pair<Integer, Double>> A) {
        p1 = A;
    }

    /**
     *
     * @param A Set the second array of points to be representated
     */
    public static void setP2(ArrayList<Pair<Integer, Double>> A) {
        p2 = A;
    }

    /**
     *
     * @param A Set the third array of points to be representated
     */
    public static void setP3(ArrayList<Pair<Integer, Double>> A) {
         p3 = A;
    }

    /**
     *
     * @param clique Set wether clique points are going to be represented
     */
    public static void setClique(boolean clique) {
        ChartDrawer.clique = clique;
    }

    /**
     *
     * @param louvain Set wether Louvain points are going to be represented
     */
    public static void setLouvain(boolean louvain) {
        ChartDrawer.louvain = louvain;
    }

    /**
     *
     * @param girvan Set wether Girvan-Newman points are going to be represented
     */
    public static void setGirvan(boolean girvan) {
        ChartDrawer.girvan = girvan;
    }

    /**
     *
     * @param panel Panel in which the chart will be put
     */
    public static void setPanel(JPanel panel) {
        ChartDrawer.panel = panel;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Temps emprat en l'obtencio de solucions",      // chart title
                "Nº de categories",                      // x axis text
                "Temps (s)",                      // y axis text
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        //final StandardLegend legend = (StandardLegend) chart.getLegend();
        //legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        //plot.setBackgroundPaint(Color.lightGray);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //true to see the shapes in the dots
        if (clique) {
            renderer.setSeriesLinesVisible(0, true);
            renderer.setSeriesShapesVisible(0, true);
            renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        }
        if (louvain) {
            renderer.setSeriesLinesVisible(1, true);
            renderer.setSeriesShapesVisible(1, true);
            renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        }
        if (girvan) {
            renderer.setSeriesLinesVisible(2, true);
            renderer.setSeriesShapesVisible(2, true);
            renderer.setSeriesStroke(2, new BasicStroke(3.0f));
       }

        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(3.0f));

        //SETTING lines colors
        if (clique) {
            renderer.setSeriesPaint(0, Color.green);
            if (louvain) {
                renderer.setSeriesPaint(1, Color.red);
                if (girvan) renderer.setSeriesPaint(2, Color.blue);
            }
            else if (girvan) renderer.setSeriesPaint(1, Color.blue);
        }
        else {
            if (louvain) {
                renderer.setSeriesPaint(0, Color.red);
                if (girvan) renderer.setSeriesPaint(1, Color.blue);
            }
            else if (girvan) renderer.setSeriesPaint(0, Color.blue);
        }

        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }
    /**
     * @param args ignored
     */
    public static void main(final String[] args) {
        final ChartDrawer demo = new ChartDrawer("Visualitzacio d'Estadistiques");
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(CP, BorderLayout.CENTER);
        panel.validate();
        //demo.setVisible(true);
    }
}
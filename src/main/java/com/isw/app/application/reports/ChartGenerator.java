package com.isw.app.application.reports;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChartGenerator {

  private static final int CHART_WIDTH = 500;
  private static final int CHART_HEIGHT = 400;

  public String createPreyPredatorChart(int preyCount, int predatorCount) throws IOException {
    DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
    dataset.setValue("Presas", preyCount);
    dataset.setValue("Depredadores", predatorCount);

    JFreeChart chart = ChartFactory.createPieChart(
        "Distribución Final: Presas vs Depredadores",
        dataset,
        true,
        true,
        false
    );

    customizeChart(chart, new Color(76, 175, 80), new Color(244, 67, 54));

    return saveChartAsImage(chart, "prey_predator_chart");
  }

  public String createOccupancyChart(int occupiedCells, int emptyCells) throws IOException {
    DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
    dataset.setValue("Celdas Ocupadas", occupiedCells);
    dataset.setValue("Celdas Vacías", emptyCells);

    JFreeChart chart = ChartFactory.createPieChart(
        "Porcentaje de Ocupación del Ecosistema",
        dataset,
        true,
        true,
        false
    );

    customizeChart(chart, new Color(33, 150, 243), new Color(224, 224, 224));

    return saveChartAsImage(chart, "occupancy_chart");
  }

  private void customizeChart(JFreeChart chart, Color color1, Color color2) {
    @SuppressWarnings("unchecked")
    PiePlot<String> plot = (PiePlot<String>) chart.getPlot();
    plot.setSectionPaint(0, color1);
    plot.setSectionPaint(1, color2);
    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
    plot.setLabelBackgroundPaint(Color.WHITE);
    plot.setLabelOutlinePaint(Color.BLACK);
    plot.setLabelShadowPaint(null);
    plot.setBackgroundPaint(Color.WHITE);
    
    chart.setBackgroundPaint(Color.WHITE);
    chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 14));
  }

  private String saveChartAsImage(JFreeChart chart, String filename) throws IOException {
    BufferedImage image = chart.createBufferedImage(CHART_WIDTH, CHART_HEIGHT);
    String projectRoot = System.getProperty("user.dir");
    String imagePath = projectRoot + "/" + filename + "_" + System.currentTimeMillis() + ".png";
    
    File imageFile = new File(imagePath);
    ImageIO.write(image, "png", imageFile);
    
    return imagePath;
  }
}

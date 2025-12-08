package com.isw.app.application.reports;

import java.io.FileNotFoundException;

public class Report {

  private final DataCollector collector;
  private final PdfGenerator generator;

  public Report() {
    this.collector = new DataCollector();
    this.generator = new PdfGenerator();
  }

  public String generatePdf(String outputPath) throws FileNotFoundException {
    ReportData data = collector.collect();
    generator.generate(data, outputPath);
    return outputPath;
  }

  public String generatePdf() throws FileNotFoundException {
    String projectRoot = System.getProperty("user.dir");
    String defaultPath = projectRoot + "/simulation_report_" + System.currentTimeMillis() + ".pdf";
    return generatePdf(defaultPath);
  }

  public ReportData getData() {
    return collector.collect();
  }
}

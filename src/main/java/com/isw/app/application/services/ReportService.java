package com.isw.app.application.services;

import com.isw.app.application.mails.ReportMail;
import com.isw.app.application.reports.Report;
import com.isw.app.application.reports.ReportData;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public class ReportService {

  private final Report report;
  private final MailingService mailingService;

  public ReportService() {
    this.report = new Report();
    this.mailingService = MailingService.getInstance();
  }

  public void generateAndSend(String email) throws FileNotFoundException, MessagingException {
    String pdfPath = report.generatePdf();

    java.io.File pdfFile = new java.io.File(pdfPath);
    if (!pdfFile.exists()) {
      throw new FileNotFoundException("PDF file not found at: " + pdfPath);
    }

    ReportData data = report.getData();

    ReportMail mail = new ReportMail(
        email,
        data.getTotalTurns(),
        data.hasExtinctions(),
        pdfPath);

    mailingService.send(mail);
  }

  public void generateAndSendAsync(String userEmail) {
    new Thread(() -> {
      try {
        generateAndSend(userEmail);
        System.out.println("Report generated and sent successfully to: " + userEmail);
      } catch (FileNotFoundException e) {
        System.err.println("Error generating PDF report: " + e.getMessage());
        e.printStackTrace();
      } catch (MessagingException e) {
        System.err.println("Error sending email report: " + e.getMessage());
        e.printStackTrace();
      }
    }).start();
  }
}

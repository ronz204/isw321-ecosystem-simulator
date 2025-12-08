package com.isw.app.application.mails;

import com.isw.app.application.helpers.MailingHelper;

public class ReportMail extends Mail {

  private final int totalTurns;
  private final String pdfPath;
  private final boolean hasExtinctions;

  public ReportMail(String to, int totalTurns, boolean hasExtinctions, String pdfPath) {
    super(to);
    this.pdfPath = pdfPath;
    this.totalTurns = totalTurns;
    this.hasExtinctions = hasExtinctions;
    build();
  }

  @Override
  public void build() {
    setSubject("Ecosystem Simulation Complete - Final Report");
    setHtml(true);

    String extinctionMessage = hasExtinctions 
        ? "<p style='color: #d32f2f;'><strong>⚠️ Some species became extinct during the simulation.</strong></p>"
        : "<p style='color: #4CAF50;'><strong>✓ All species survived the simulation!</strong></p>";

    String content = """
        <h2>Simulation Complete!</h2>
        <p>Your ecosystem simulation has finished successfully.</p>
        
        <h3>Summary:</h3>
        <ul>
            <li><strong>Total Turns:</strong> %d</li>
            <li><strong>Status:</strong> %s</li>
        </ul>
        
        %s
        
        <p>Please find the detailed PDF report attached to this email.</p>
        
        <h3>Report Contents:</h3>
        <ul>
            <li>Complete turn-by-turn analysis</li>
            <li>Species extinction timeline (if applicable)</li>
            <li>Final ecosystem state</li>
        </ul>
        
        <p>Thank you for using Ecosystem Simulator!</p>
        """.formatted(
            totalTurns, 
            hasExtinctions ? "Completed with extinctions" : "Completed successfully",
            extinctionMessage
        );

    setBody(MailingHelper.buildHtmlTemplate("Simulation Report", content));
    setAttachment(pdfPath);
  }

  public String getPdfPath() {
    return pdfPath;
  }
}

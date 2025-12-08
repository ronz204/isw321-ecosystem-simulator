package com.isw.app.application.helpers;

public class MailingHelper {

  public static String buildHtmlTemplate(String title, String content) {
    return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <style>
                body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                .header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }
                .content { padding: 20px; background-color: #f9f9f9; }
                .footer { padding: 10px; text-align: center; font-size: 12px; color: #666; }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <h1>%s</h1>
                </div>
                <div class="content">
                    %s
                </div>
                <div class="footer">
                    <p>© 2025 Ecosystem Simulator. All rights reserved.</p>
                </div>
            </div>
        </body>
        </html>
        """.formatted(title, content);
  }

  public static String buildPlainText(String... paragraphs) {
    return String.join("\n\n", paragraphs);
  }
}

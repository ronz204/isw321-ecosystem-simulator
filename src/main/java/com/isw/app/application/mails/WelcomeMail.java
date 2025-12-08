package com.isw.app.application.mails;

import com.isw.app.application.helpers.MailingHelper;

public class WelcomeMail extends Mail {

  public WelcomeMail(String to) {
    super(to);
    build();
  }

  @Override
  public void build() {
    setSubject("Welcome to Ecosystem Simulator!");
    setHtml(true);

    String content = """
        <h2>Hello!</h2>
        <p>Thank you for registering with Ecosystem Simulator.</p>
        <p>You're now ready to start creating and managing your own ecosystem simulations.</p>
        <h3>Getting Started:</h3>
        <ol>
            <li>Log in to your account</li>
            <li>Create your first simulation</li>
            <li>Explore different species interactions</li>
        </ol>
        <p>If you have any questions, feel free to reach out to our support team.</p>
        """;

    setBody(MailingHelper.buildHtmlTemplate("Welcome!", content));
  }
}

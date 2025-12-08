package com.isw.app.application.services;

import jakarta.mail.*;
import java.util.Properties;
import jakarta.mail.internet.*;
import java.io.UnsupportedEncodingException;
import com.isw.app.application.helpers.PropertiesHelper;
import com.isw.app.application.mails.Mail;

public class MailingService {
  private final Session session;
  private static MailingService instance;
  private final PropertiesHelper mailProperties;

  private MailingService() {
    this.mailProperties = PropertiesHelper.getInstance("mail.properties");
    this.session = createSession();
  }

  public static MailingService getInstance() {
    if (instance == null) {
      instance = new MailingService();
    }
    return instance;
  }

  private Session createSession() {
    Properties props = new Properties();
    props.put("mail.smtp.host", mailProperties.getProperty("mail.smtp.host"));
    props.put("mail.smtp.port", mailProperties.getProperty("mail.smtp.port"));
    props.put("mail.smtp.auth", mailProperties.getProperty("mail.smtp.auth"));
    props.put("mail.smtp.starttls.enable", mailProperties.getProperty("mail.smtp.starttls.enable"));

    return Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
            mailProperties.getProperty("mail.username"),
            mailProperties.getProperty("mail.password"));
      }
    });
  }

  public void send(Mail mail) throws MessagingException {
    if (mail.isHtml()) {
      sendHtmlEmail(mail.getTo(), mail.getSubject(), mail.getBody());
    } else {
      sendEmail(mail.getTo(), mail.getSubject(), mail.getBody());
    }
  }

  public void sendEmail(String to, String subject, String body) throws MessagingException {
    Message message = createBaseMessage(to, subject);
    message.setText(body);
    Transport.send(message);
  }

  public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
    Message message = createBaseMessage(to, subject);
    message.setContent(htmlBody, "text/html; charset=utf-8");
    Transport.send(message);
  }

  public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath)
      throws MessagingException {
    Message message = createBaseMessage(to, subject);

    MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText(body);

    MimeBodyPart attachmentPart = new MimeBodyPart();
    try {
      attachmentPart.attachFile(attachmentPath);
    } catch (Exception e) {
      throw new MessagingException("Error attaching file", e);
    }

    MimeMultipart multipart = new MimeMultipart();
    multipart.addBodyPart(textPart);
    multipart.addBodyPart(attachmentPart);

    message.setContent(multipart);
    Transport.send(message);
  }

  private Message createBaseMessage(String to, String subject) throws MessagingException {
    Message message = new MimeMessage(session);
    try {
      message.setFrom(new InternetAddress(
          mailProperties.getProperty("mail.from"),
          mailProperties.getProperty("mail.from.name")));
    } catch (UnsupportedEncodingException e) {
      throw new MessagingException("Error setting from address", e);
    }
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    message.setSubject(subject);
    return message;
  }
}

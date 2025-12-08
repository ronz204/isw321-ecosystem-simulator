package com.isw.app.application.mails;

public abstract class Mail {
  
  private String to;
  private String subject;

  private String body;
  private boolean isHtml;
  private String attachment;

  public Mail(String to) {
    this.to = to;
    this.isHtml = false;
  }

  public abstract void build();

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public boolean isHtml() {
    return isHtml;
  }

  public void setHtml(boolean html) {
    isHtml = html;
  }

  public String getAttachment() {
    return attachment;
  }

  public void setAttachment(String attachment) {
    this.attachment = attachment;
  }

  public boolean hasAttachment() {
    return attachment != null && !attachment.isEmpty();
  }
}

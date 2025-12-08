package com.isw.app.application.mails;

public abstract class Mail {
  protected String to;
  protected String subject;
  protected String body;
  protected boolean isHtml;

  public Mail(String to) {
    this.to = to;
    this.isHtml = false;
  }

  public abstract void build();

  public String getTo() {
    return to;
  }

  public String getSubject() {
    return subject;
  }

  public String getBody() {
    return body;
  }

  public boolean isHtml() {
    return isHtml;
  }

  protected void setSubject(String subject) {
    this.subject = subject;
  }

  protected void setBody(String body) {
    this.body = body;
  }

  protected void setHtml(boolean isHtml) {
    this.isHtml = isHtml;
  }
}

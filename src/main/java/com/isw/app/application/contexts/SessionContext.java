package com.isw.app.application.contexts;

public class SessionContext {
  private static SessionContext instance;

  private SessionContext() {}

  public static SessionContext getInstance() {
    if (instance == null) {
      instance = new SessionContext();
    }
    return instance;
  }

  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

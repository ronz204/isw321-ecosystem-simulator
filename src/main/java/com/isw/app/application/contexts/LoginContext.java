package com.isw.app.application.contexts;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginContext {
  private static LoginContext instance;

  private LoginContext() {}

  public static LoginContext getInstance() {
    if (instance == null) {
      instance = new LoginContext();
    }
    return instance;
  }

  private final StringProperty email = new SimpleStringProperty();
  private final StringProperty cedula = new SimpleStringProperty();
  private final StringProperty message = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();
  private final BooleanProperty success = new SimpleBooleanProperty();

  public String getEmail() {
    return email.get();
  }

  public void setEmail(String value) {
    email.set(value);
  }

  public StringProperty emailProperty() {
    return email;
  }

  public String getCedula() {
    return cedula.get();
  }

  public void setCedula(String value) {
    cedula.set(value);
  }

  public StringProperty cedulaProperty() {
    return cedula;
  }

  public String getPassword() {
    return password.get();
  }

  public void setPassword(String value) {
    password.set(value);
  }

  public StringProperty passwordProperty() {
    return password;
  }

  public String getMessage() {
    return message.get();
  }

  public StringProperty messageProperty() {
    return message;
  }

  public boolean isSuccess() {
    return success.get();
  }

  public void setSuccess(boolean value, String message) {
    this.message.set(message);
    success.set(value);
  }

  public BooleanProperty successProperty() {
    return success;
  }
}

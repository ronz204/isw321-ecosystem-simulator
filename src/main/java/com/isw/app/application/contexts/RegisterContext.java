package com.isw.app.application.contexts;

import java.time.LocalDate;
import com.isw.app.domain.enums.Gender;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class RegisterContext {
  private static RegisterContext instance;

  private RegisterContext() {
  }

  public static RegisterContext getInstance() {
    if (instance == null) {
      instance = new RegisterContext();
    }
    return instance;
  }

  private final StringProperty name = new SimpleStringProperty();
  private final StringProperty email = new SimpleStringProperty();
  private final StringProperty cedula = new SimpleStringProperty();
  private final StringProperty message = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();
  private final BooleanProperty success = new SimpleBooleanProperty();

  private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
  private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();

  public String getCedula() {
    return cedula.get();
  }

  public void setCedula(String value) {
    cedula.set(value);
  }

  public StringProperty cedulaProperty() {
    return cedula;
  }

  public String getName() {
    return name.get();
  }

  public void setName(String value) {
    name.set(value);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public String getEmail() {
    return email.get();
  }

  public void setEmail(String value) {
    email.set(value);
  }

  public StringProperty emailProperty() {
    return email;
  }

  public Gender getGender() {
    return gender.get();
  }

  public void setGender(Gender value) {
    gender.set(value);
  }

  public ObjectProperty<Gender> genderProperty() {
    return gender;
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

  public LocalDate getBirthday() {
    return birthday.get();
  }

  public void setBirthday(LocalDate value) {
    birthday.set(value);
  }

  public ObjectProperty<LocalDate> birthdayProperty() {
    return birthday;
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

package com.isw.app.properties;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class LoginProperties {
  private final String ERROR_STYLE = "login-form__message--error";
  private final String SUCCESS_STYLE = "login-form__message--success";

  private final StringProperty cedula = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();
  private final StringProperty message = new SimpleStringProperty("");

  public String getMessage() {
    return message.get();
  }

  public void setMessage(String value) {
    message.set(value);
  }

  public String getCedula() {
    return cedula.get();
  }

  public void setCedula(String value) {
    cedula.set(value);
  }

  public String getPassword() {
    return password.get();
  }

  public void setPassword(String value) {
    password.set(value);
  }

  public void bindLblMessage(Label label) {
    label.textProperty().bind(this.message);
  }

  public void bindFldCedula(TextField cedula) {
    cedula.textProperty().bindBidirectional(this.cedula);
  }

  public void bindFldPassword(PasswordField password) {
    password.textProperty().bindBidirectional(this.password);
  }

  public void listenLblMessage(Label label) {
    this.message.addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);
      
      if (next != null && !next.isEmpty()) {
        label.getStyleClass().add(next.contains("exitoso") ? SUCCESS_STYLE : ERROR_STYLE);
      }
    });
  }
}

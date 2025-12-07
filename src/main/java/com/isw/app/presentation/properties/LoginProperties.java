package com.isw.app.presentation.properties;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class LoginProperties {

  private final String ERROR_STYLE = "login-form__message--error";
  private final String SUCCESS_STYLE = "login-form__message--success";

  private final StringProperty cedula = new SimpleStringProperty();
  private final StringProperty message = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();
  private final BooleanProperty success = new SimpleBooleanProperty();


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

  public boolean isSuccess() {
    return success.get();
  }

  public void setSuccess(boolean value, String message) {
    this.message.set(message);
    success.set(value);
  }

  public void bindFldCedula(TextField cedula) {
    cedula.textProperty().bindBidirectional(this.cedula);
  }

  public void bindFldPassword(PasswordField password) {
    password.textProperty().bindBidirectional(this.password);
  }

  public void bindIsSuccess(Label label) {
    this.success.addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next) {
        label.setText(this.message.get());
        label.getStyleClass().add(SUCCESS_STYLE);
      } else {
        label.setText(this.message.get());
        label.getStyleClass().add(ERROR_STYLE);
      }
    });
  }
}

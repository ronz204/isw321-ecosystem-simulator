package com.isw.app.presentation.properties;

import java.time.LocalDate;
import com.isw.app.enums.Gender;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class RegisterProperties {

  private final String ERROR_STYLE = "register-form__message--error";
  private final String SUCCESS_STYLE = "register-form__message--success";

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

  public String getName() {
    return name.get();
  }

  public void setName(String value) {
    name.set(value);
  }

  public String getEmail() {
    return email.get();
  }

  public void setEmail(String value) {
    email.set(value);
  }

  public Gender getGender() {
    return gender.get();
  }

  public void setGender(Gender value) {
    gender.set(value);
  }

  public String getPassword() {
    return password.get();
  }

  public void setPassword(String value) {
    password.set(value);
  }

  public LocalDate getBirthday() {
    return birthday.get();
  }

  public void setBirthday(LocalDate value) {
    birthday.set(value);
  }

  public boolean isSuccess() {
    return success.get();
  }

  public void setSuccess(boolean value, String message) {
    this.message.set(message);
    success.set(value);
  }

  public void bindFldName(TextField name) {
    name.textProperty().bindBidirectional(this.name);
  }

  public void bindFldCedula(TextField cedula) {
    cedula.textProperty().bindBidirectional(this.cedula);
  }

  public void bindFldEmail(TextField email) {
    email.textProperty().bindBidirectional(this.email);
  }

  public void bindFldPassword(PasswordField password) {
    password.textProperty().bindBidirectional(this.password);
  }

  public void bindFldBirthday(DatePicker birthday) {
    birthday.valueProperty().bindBidirectional(this.birthday);
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

  public void bindRdoGender(ToggleGroup grpGender) {
    grpGender.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (next != null && next.getUserData() instanceof Gender) {
        this.gender.set((Gender) next.getUserData());
      }
    });

    this.gender.addListener((obs, prev, next) -> {
      if (next != null) {
        grpGender.getToggles().stream()
            .filter(toggle -> toggle.getUserData() != null && next.equals(toggle.getUserData()))
            .findFirst()
            .ifPresent(toggle -> grpGender.selectToggle(toggle));
      }
    });
  }
}

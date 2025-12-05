package com.isw.app.properties;

import java.time.LocalDate;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.PasswordField;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RegisterProperties {

  private final String ERROR_STYLE = "register-form__message--error";
  private final String SUCCESS_STYLE = "register-form__message--success";

  private final StringProperty name = new SimpleStringProperty();
  private final StringProperty email = new SimpleStringProperty();
  private final StringProperty cedula = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();
  private final StringProperty gender = new SimpleStringProperty();
  private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
  private final StringProperty message = new SimpleStringProperty("");

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

  public String getGender() {
    return gender.get();
  }

  public void setGender(String value) {
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

  public String getMessage() {
    return message.get();
  }

  public void setMessage(String value) {
    message.set(value);
  }

  public void bindLblMessage(Label label) {
    label.textProperty().bind(this.message);
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

  public void bindRdoGender(ToggleGroup grpGender) {
    grpGender.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (next != null && next.getUserData() instanceof String) {
        this.gender.set((String) next.getUserData());
      }
    });

    this.gender.addListener((obs, prev, next) -> {
      if (next != null) {
        grpGender.getToggles().stream()
            .filter(toggle -> next.equals(toggle.getUserData()))
            .findFirst()
            .ifPresent(toggle -> grpGender.selectToggle(toggle));
      }
    });
  }

  public void listenLblMessage(Label label) {
    this.message.addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next != null && !next.isEmpty()) {
        label.getStyleClass().add(next.contains("exitosamente") ? SUCCESS_STYLE : ERROR_STYLE);
      }
    });
  }
}

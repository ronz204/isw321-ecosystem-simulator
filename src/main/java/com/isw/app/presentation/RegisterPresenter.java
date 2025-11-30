package com.isw.app.presentation;

import javafx.fxml.FXML;
import java.time.LocalDate;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import com.isw.app.enums.FXMLPath;
import com.isw.app.helpers.SwitcherHelper;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.repositories.customer.*;
import com.isw.app.handlers.customer.register.*;

public class RegisterPresenter {

  private final CustomerRepository repository = new CustomerTLQRepository();
  private final RegisterCustomerHandler handler = new RegisterCustomerHandler(repository);

  @FXML
  private TextField nameField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField cedulaField;

  @FXML
  private DatePicker birthdayField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label messageLabel;

  @FXML
  private RadioButton maleRadio;

  @FXML
  private RadioButton femaleRadio;

  @FXML
  private ToggleGroup genderGroup;

  @FXML
  public void onRegisterClick() {
    String name = nameField.getText();
    String email = emailField.getText();
    String cedula = cedulaField.getText();
    String gender = null;
    if (maleRadio.isSelected())
      gender = "Masculino";
    else if (femaleRadio.isSelected())
      gender = "Femenino";
    String password = passwordField.getText();
    LocalDate birthday = birthdayField.getValue();

    RegisterCustomerSchema schema = new RegisterCustomerSchema(
        cedula, name, email, gender, password, birthday);

    var violations = ValidatorHelper.validate(schema);

    if (!violations.isEmpty()) {
      messageLabel.setText(ValidatorHelper.getMessages(violations));
      messageLabel.setStyle("-fx-text-fill: red;");
      return;
    }

    RegisterCustomerCommand command = schema.toCommand();
    RegisterCustomerResponse response = handler.handle(command);

    messageLabel.setText(response.message());
    if (response.message().startsWith("Cliente registrado")) {
      messageLabel.setStyle("-fx-text-fill: #388e3c;");

      clearFields();
      switchToLogin();
    } else {
      messageLabel.setStyle("-fx-text-fill: red;");
    }
  }

  private void switchToLogin() {
    try {
      SwitcherHelper.switchTo(FXMLPath.LOGIN.getPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void clearFields() {
    nameField.clear();
    emailField.clear();
    cedulaField.clear();
    birthdayField.setValue(null);
    passwordField.clear();
    genderGroup.selectToggle(null);
  }
}

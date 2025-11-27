package com.isw.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class RegisterController {

  @FXML
  private TextField usernameField;

  @FXML
  private TextField emailField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label messageLabel;

  private final Validator validator;

  public RegisterController() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @FXML
  public void onRegisterButtonClick() {
    String username = usernameField.getText();
    String email = emailField.getText();
    String password = passwordField.getText();

    RegisterSchema schema = new RegisterSchema(username, email, password);
    Set<ConstraintViolation<RegisterSchema>> violations = validator.validate(schema);

    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<RegisterSchema> violation : violations) {
        sb.append(violation.getMessage()).append("\n");
      }
      messageLabel.setText(sb.toString().trim());
      messageLabel.setStyle("-fx-text-fill: red;");
    } else {
      messageLabel.setText("¡Registro exitoso!");
      messageLabel.setStyle("-fx-text-fill: green;");
    }
  }
}

package com.isw.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterController {

  @FXML
  private TextField usernameField;

  @FXML
  private TextField emailField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label messageLabel;

  @FXML
  public void onRegisterButtonClick() {
    String username = usernameField.getText();
    String email = emailField.getText();
    String password = passwordField.getText();

    if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
      messageLabel.setText("Todos los campos son obligatorios.");
    } else {
      // Aquí puedes agregar la lógica de registro real
      messageLabel.setText("¡Registro exitoso!");
      messageLabel.setStyle("-fx-text-fill: green;");
    }
  }
}

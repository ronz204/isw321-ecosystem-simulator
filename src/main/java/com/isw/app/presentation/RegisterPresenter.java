package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.handlers.register.RegisterSchema;
import com.isw.app.handlers.register.RegisterHandler;
import com.isw.app.handlers.register.RegisterResponse;

public class RegisterPresenter {
  private final RegisterHandler registerHandler = new RegisterHandler();

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

    RegisterSchema schema = new RegisterSchema(username, email, password);
    var violations = ValidatorHelper.validate(schema);

    if (!violations.isEmpty()) {
      messageLabel.setText(ValidatorHelper.getMessages(violations));
      messageLabel.setStyle("-fx-text-fill: red;");
      return;
    }

    RegisterResponse response = registerHandler.handler(schema.toCommand());
    
    messageLabel.setText(response.message());
    messageLabel.setStyle("-fx-text-fill: green;");
  }
}

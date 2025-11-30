package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.repositories.customer.*;
import com.isw.app.handlers.customer.login.*;

public class LoginPresenter {

  private final CustomerRepository repository = new CustomerTLQRepository();
  private final LoginCustomerHandler handler = new LoginCustomerHandler(repository);

  @FXML
  private TextField cedulaField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label messageLabel;

  @FXML
  public void onLoginClick() {
    String cedula = cedulaField.getText();
    String password = passwordField.getText();

    LoginCustomerSchema schema = new LoginCustomerSchema(cedula, password);

    var violations = ValidatorHelper.validate(schema);

    if (!violations.isEmpty()) {
      messageLabel.setText(ValidatorHelper.getMessages(violations));
      messageLabel.setStyle("-fx-text-fill: red;");
      return;
    }

    LoginCustomerCommand command = new LoginCustomerCommand(cedula, password);
    LoginCustomerResponse response = handler.handle(command);

    messageLabel.setText(response.message());
    if (response.message().startsWith("Inicio de sesión exitoso")) {
      messageLabel.setStyle("-fx-text-fill: #388e3c;");
      cedulaField.clear();
      passwordField.clear();
    } else {
      messageLabel.setStyle("-fx-text-fill: red;");
    }
  }
}

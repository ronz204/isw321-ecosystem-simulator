package com.isw.app.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.handlers.login.LoginCustomerSchema;
import com.isw.app.handlers.login.LoginCustomerHandler;
import com.isw.app.presentation.properties.LoginProperties;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.repositories.customer.CustomerTLQRepository;

public class LoginController {

  private final LoginProperties properties = new LoginProperties();
  private final CustomerRepository repository = new CustomerTLQRepository();
  private final LoginCustomerHandler handler = new LoginCustomerHandler(repository, properties);

  @FXML
  private Label lblMessage;

  @FXML
  private TextField fldCedula;

  @FXML
  private PasswordField fldPassword;

  @FXML
  private void initialize() {
    properties.bindFldCedula(fldCedula);
    properties.bindIsSuccess(lblMessage);
    properties.bindFldPassword(fldPassword);
  }

  @FXML
  public void onLoginClick() {
    LoginCustomerSchema schema = new LoginCustomerSchema(
        properties.getCedula(),
        properties.getPassword());

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      String message = violations.iterator().next().getMessage();
      properties.setSuccess(false, message);
      return;
    }

    handler.handle(schema);
  }
}

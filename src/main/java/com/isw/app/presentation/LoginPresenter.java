package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.properties.LoginProperties;
import com.isw.app.handlers.login.LoginCustomerSchema;
import com.isw.app.handlers.login.LoginCustomerHandler;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.repositories.customer.CustomerTLQRepository;

public class LoginPresenter {

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
    properties.bindLblMessage(lblMessage);
    properties.bindFldPassword(fldPassword);
    properties.listenLblMessage(lblMessage);
  }

  @FXML
  public void onLoginClick() {
    LoginCustomerSchema schema = new LoginCustomerSchema(
      properties.getCedula(),
      properties.getPassword()
    );

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      properties.setMessage(ValidatorHelper.getMessages(violations));
      return;
    }

    handler.handle(schema);
  }
}

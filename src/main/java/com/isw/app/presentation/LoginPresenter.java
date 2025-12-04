package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import com.isw.app.handlers.login.LoginCustomerHandler;
import com.isw.app.handlers.login.LoginCustomerResponse;
import com.isw.app.handlers.login.LoginCustomerSchema;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.repositories.customer.CustomerTLQRepository;

public class LoginPresenter {

  private static final String ERROR_STYLE = "login-form__message--error";
  private static final String SUCCESS_STYLE = "login-form__message--success";

  private final CustomerRepository repository = new CustomerTLQRepository();
  private final LoginCustomerHandler handler = new LoginCustomerHandler(repository);

  @FXML
  private Label lblMessage;

  @FXML
  private TextField fldCedula;

  @FXML
  private PasswordField fldPassword;

  @FXML
  public void onLoginClick() {
    LoginCustomerSchema schema = getFormFields();

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      showMessage(ValidatorHelper.getMessages(violations), true);
      return;
    }

    LoginCustomerResponse response = handler.handle(schema.toCommand());

    if (response.isSuccess()) {
      showMessage(response.message(), false);
      clearFormFields();
    } else {
      showMessage(response.message(), true);
    }
  }

  private void showMessage(String message, boolean isError) {
    lblMessage.setText(message);
    lblMessage.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);
    lblMessage.getStyleClass().add(isError ? ERROR_STYLE : SUCCESS_STYLE);
  }

  private LoginCustomerSchema getFormFields() {
    return new LoginCustomerSchema(
        fldCedula.getText(),
        fldPassword.getText());
  }

  private void clearFormFields() {
    fldCedula.clear();
    fldPassword.clear();
  }
}

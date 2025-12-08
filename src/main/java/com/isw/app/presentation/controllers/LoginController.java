package com.isw.app.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.isw.app.domain.enums.FXMLPath;
import javafx.scene.control.PasswordField;
import com.isw.app.application.contexts.LoginContext;
import com.isw.app.application.services.SceneService;
import com.isw.app.application.contexts.SessionContext;
import com.isw.app.application.helpers.ValidatorHelper;
import com.isw.app.application.handlers.login.LoginCustomerSchema;
import com.isw.app.application.handlers.login.LoginCustomerHandler;
import com.isw.app.infrastructure.repositories.customer.CustomerRepository;
import com.isw.app.infrastructure.repositories.customer.CustomerTLQRepository;

public class LoginController {

  private static final String ERROR_STYLE = "login-form__message--error";
  private static final String SUCCESS_STYLE = "login-form__message--success";

  private final LoginContext context = LoginContext.getInstance();
  private final SessionContext session = SessionContext.getInstance();
  private final CustomerRepository repository = new CustomerTLQRepository();
  private final LoginCustomerHandler handler = new LoginCustomerHandler(repository);

  @FXML
  private Label lblMessage;

  @FXML
  private TextField fldEmail;

  @FXML
  private TextField fldCedula;

  @FXML
  private PasswordField fldPassword;

  @FXML
  private void initialize() {
    bindFldEmail(fldEmail);
    bindFldCedula(fldCedula);
    bindFldPassword(fldPassword);
    bindLblMessage(lblMessage);
  }

  @FXML
  public void onLoginClick() {
    LoginCustomerSchema schema = new LoginCustomerSchema(
        context.getEmail(),
        context.getCedula(),
        context.getPassword());

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      String message = violations.iterator().next().getMessage();
      context.setSuccess(false, message);
      return;
    }

    handler.handle(schema);
  }

  @FXML
  public void onGoToRegisterClick() {
    SceneService.switchTo(FXMLPath.REGISTER.getPath());
  }

  private void bindFldEmail(TextField email) {
    email.textProperty().bindBidirectional(context.emailProperty());
  }

  private void bindFldCedula(TextField cedula) {
    cedula.textProperty().bindBidirectional(context.cedulaProperty());
  }

  private void bindFldPassword(PasswordField password) {
    password.textProperty().bindBidirectional(context.passwordProperty());
  }

  private void bindLblMessage(Label label) {
    context.successProperty().addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next) {
        label.setText(context.getMessage());
        label.getStyleClass().add(SUCCESS_STYLE);

        session.setEmail(context.getEmail());
        SceneService.switchTo(FXMLPath.SIMULATION.getPath());
      } else {
        label.setText(context.getMessage());
        label.getStyleClass().add(ERROR_STYLE);
      }
    });
  }
}

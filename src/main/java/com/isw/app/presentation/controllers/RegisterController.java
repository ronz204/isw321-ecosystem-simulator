package com.isw.app.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.isw.app.domain.enums.Gender;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import com.isw.app.domain.enums.FXMLPath;
import javafx.scene.control.PasswordField;
import com.isw.app.application.services.SceneService;
import com.isw.app.application.helpers.ValidatorHelper;
import com.isw.app.application.contexts.SessionContext;
import com.isw.app.application.contexts.RegisterContext;
import com.isw.app.application.handlers.register.RegisterCustomerSchema;
import com.isw.app.application.handlers.register.RegisterCustomerHandler;
import com.isw.app.infrastructure.repositories.customer.CustomerRepository;
import com.isw.app.infrastructure.repositories.customer.CustomerTLQRepository;

public class RegisterController {

  private static final String ERROR_STYLE = "register-form__message--error";
  private static final String SUCCESS_STYLE = "register-form__message--success";

  private final SessionContext session = SessionContext.getInstance();
  private final RegisterContext context = RegisterContext.getInstance();
  private final CustomerRepository repository = new CustomerTLQRepository();
  private final RegisterCustomerHandler handler = new RegisterCustomerHandler(repository);

  private ToggleGroup grpGender;

  @FXML
  private Label lblMessage;

  @FXML
  private DatePicker fldBirthday;

  @FXML
  private PasswordField fldPassword;

  @FXML
  private RadioButton rdoMale, rdoFemale;

  @FXML
  private TextField fldName, fldEmail, fldCedula;

  @FXML
  private void initialize() {
    grpGender = new ToggleGroup();
    rdoMale.setUserData(Gender.MALE);
    rdoMale.setToggleGroup(grpGender);
    rdoFemale.setToggleGroup(grpGender);
    rdoFemale.setUserData(Gender.FEMALE);

    bindFldName(fldName);
    bindFldEmail(fldEmail);
    bindFldCedula(fldCedula);
    bindRdoGender(grpGender);
    bindLblMessage(lblMessage);
    bindFldPassword(fldPassword);
    bindFldBirthday(fldBirthday);
  }

  @FXML
  public void onRegisterClick() {
    RegisterCustomerSchema schema = new RegisterCustomerSchema(
        context.getCedula(),
        context.getName(),
        context.getEmail(),
        context.getGender(),
        context.getPassword(),
        context.getBirthday());
    
    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      String message = violations.iterator().next().getMessage();
      context.setSuccess(false, message);
      return;
    }
    
    handler.handle(schema);
  }

  @FXML
  public void onGoToLoginClick() {
    SceneService.switchTo(FXMLPath.LOGIN.getPath());
  }

  private void bindFldName(TextField name) {
    name.textProperty().bindBidirectional(context.nameProperty());
  }

  private void bindFldCedula(TextField cedula) {
    cedula.textProperty().bindBidirectional(context.cedulaProperty());
  }

  private void bindFldEmail(TextField email) {
    email.textProperty().bindBidirectional(context.emailProperty());
  }

  private void bindFldPassword(PasswordField password) {
    password.textProperty().bindBidirectional(context.passwordProperty());
  }

  private void bindFldBirthday(DatePicker birthday) {
    birthday.valueProperty().bindBidirectional(context.birthdayProperty());
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

  private void bindRdoGender(ToggleGroup grpGender) {
    grpGender.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (next != null && next.getUserData() instanceof Gender) {
        context.setGender((Gender) next.getUserData());
      }
    });

    context.genderProperty().addListener((obs, prev, next) -> {
      if (next != null) {
        grpGender.getToggles().stream()
            .filter(toggle -> toggle.getUserData() != null && next.equals(toggle.getUserData()))
            .findFirst()
            .ifPresent(toggle -> grpGender.selectToggle(toggle));
      }
    });
  }
}

package com.isw.app.presentation.controllers;

import javafx.fxml.FXML;
import com.isw.app.enums.Gender;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.handlers.register.RegisterCustomerSchema;
import com.isw.app.handlers.register.RegisterCustomerHandler;
import com.isw.app.presentation.properties.RegisterProperties;
import com.isw.app.repositories.customer.CustomerTLQRepository;

public class RegisterController {

  private final RegisterProperties properties = new RegisterProperties();
  private final CustomerRepository repository = new CustomerTLQRepository();
  private final RegisterCustomerHandler handler = new RegisterCustomerHandler(repository, properties);

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

    properties.bindFldName(fldName);
    properties.bindFldEmail(fldEmail);
    properties.bindFldCedula(fldCedula);
    properties.bindRdoGender(grpGender);
    properties.bindIsSuccess(lblMessage);
    properties.bindFldPassword(fldPassword);
    properties.bindFldBirthday(fldBirthday);
  }

  @FXML
  public void onRegisterClick() {
    RegisterCustomerSchema schema = new RegisterCustomerSchema(
        properties.getCedula(),
        properties.getName(),
        properties.getEmail(),
        properties.getGender(),
        properties.getPassword(),
        properties.getBirthday());
    
    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      String message = violations.iterator().next().getMessage();
      properties.setSuccess(false, message);
      return;
    }
    
    handler.handle(schema);
  }
}

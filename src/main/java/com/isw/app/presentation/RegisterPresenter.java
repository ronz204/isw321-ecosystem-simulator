package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.properties.RegisterProperties;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.handlers.register.RegisterCustomerSchema;
import com.isw.app.handlers.register.RegisterCustomerHandler;
import com.isw.app.repositories.customer.CustomerTLQRepository;

public class RegisterPresenter {

  private final RegisterProperties properties = new RegisterProperties();
  private final CustomerRepository repository = new CustomerTLQRepository();
  private final RegisterCustomerHandler handler = new RegisterCustomerHandler(repository, properties);

  @FXML
  private Label lblMessage;

  private ToggleGroup grpGender;

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
    rdoMale.setToggleGroup(grpGender);
    rdoFemale.setToggleGroup(grpGender);

    properties.bindFldName(fldName);
    properties.bindFldEmail(fldEmail);
    properties.bindFldCedula(fldCedula);
    properties.bindLblMessage(lblMessage);
    properties.bindFldPassword(fldPassword);
    properties.bindFldBirthday(fldBirthday);
    
    properties.listenLblMessage(lblMessage);
    properties.listenRdoGender(rdoMale, rdoFemale, grpGender);
  }

  @FXML
  public void onRegisterClick() {
    RegisterCustomerSchema schema = new RegisterCustomerSchema(
      properties.getCedula(),
      properties.getName(),
      properties.getEmail(),
      properties.getGender(),
      properties.getPassword(),
      properties.getBirthday()
    );

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      properties.setMessage(ValidatorHelper.getMessages(violations));
      return;
    }

    handler.handle(schema);
  }
}

package com.isw.app.presentation;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.isw.app.enums.FXMLPath;
import com.isw.app.helpers.SwitcherHelper;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.handlers.RegisterCustomerSchema;
import com.isw.app.handlers.RegisterCustomerHandler;
import com.isw.app.handlers.RegisterCustomerResponse;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.repositories.customer.CustomerTLQRepository;

public class RegisterPresenter implements Initializable {

  private static final String ERROR_STYLE = "register-form__message--error";
  private static final String SUCCESS_STYLE = "register-form__message--success";

  private final CustomerRepository repository = new CustomerTLQRepository();
  private final RegisterCustomerHandler handler = new RegisterCustomerHandler(repository);

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    grpGender = new ToggleGroup();
    rdoMale.setToggleGroup(grpGender);
    rdoFemale.setToggleGroup(grpGender);
  }

  @FXML
  public void onRegisterClick() {
    RegisterCustomerSchema schema = getFormFields();

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      showMessage(ValidatorHelper.getMessages(violations), true);
      return;
    }

    RegisterCustomerResponse response = handler.handle(schema.toCommand());

    if (response.isSuccess()) {
      showMessage(response.message(), false);
      clearFormFields();
      SwitcherHelper.switchTo(FXMLPath.LOGIN.getPath());
    } else {
      showMessage(response.message(), true);
    }
  }

  private void showMessage(String message, boolean isError) {
    lblMessage.setText(message);
    lblMessage.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);
    lblMessage.getStyleClass().add(isError ? ERROR_STYLE : SUCCESS_STYLE);
  }

  private RegisterCustomerSchema getFormFields() {
    return new RegisterCustomerSchema(
        fldCedula.getText(),
        fldName.getText(),
        fldEmail.getText(),
        getSelectedGender(),
        fldPassword.getText(),
        fldBirthday.getValue());
  }

  private String getSelectedGender() {
    if (rdoMale.isSelected())
      return "Masculino";
    if (rdoFemale.isSelected())
      return "Femenino";
    return null;
  }

  private void clearFormFields() {
    fldName.clear();
    fldEmail.clear();
    fldCedula.clear();
    fldBirthday.setValue(null);
    fldPassword.clear();
    grpGender.selectToggle(null);
  }
}

package com.isw.app.presentation;

import javafx.fxml.FXML;
import java.time.LocalDate;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;

import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.repositories.customer.CustomerRepository;
import com.isw.app.repositories.customer.CustomerTLQRepository;
import com.isw.app.handlers.customer.register.RegisterCustomerSchema;
import com.isw.app.handlers.customer.register.RegisterCustomerHandler;
import com.isw.app.handlers.customer.register.RegisterCustomerCommand;
import com.isw.app.handlers.customer.register.RegisterCustomerResponse;

public class RegisterPresenter {
  private final CustomerRepository repository = new CustomerTLQRepository();
  private final RegisterCustomerHandler handler = new RegisterCustomerHandler(repository);

  @FXML
  private TextField nameField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField cedulaField;

  @FXML
  private ComboBox<String> genderField;

  @FXML
  private DatePicker birthdayField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label messageLabel;

  @FXML
  public void onRegisterClick() {
    String name = nameField.getText();
    String email = emailField.getText();
    String cedula = cedulaField.getText();
    String gender = genderField.getValue();
    String password = passwordField.getText();
    LocalDate birthday = birthdayField.getValue();

    RegisterCustomerSchema schema = new RegisterCustomerSchema(
        cedula, name, email, gender, password, birthday);

    var violations = ValidatorHelper.validate(schema);

    if (!violations.isEmpty()) {
      messageLabel.setText(ValidatorHelper.getMessages(violations));
      messageLabel.setStyle("-fx-text-fill: red;");
      return;
    }

    RegisterCustomerCommand command = schema.toCommand();
    RegisterCustomerResponse response = handler.handle(command);

    messageLabel.setText(response.message());
    messageLabel.setStyle(
        response.message().startsWith("Cliente registrado") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
  }
}

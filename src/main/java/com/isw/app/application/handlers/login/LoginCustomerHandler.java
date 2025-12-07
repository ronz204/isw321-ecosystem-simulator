package com.isw.app.application.handlers.login;

import com.isw.app.application.helpers.HashingHelper;
import com.isw.app.presentation.properties.LoginProperties;
import com.isw.app.infrastructure.repositories.customer.CustomerRepository;


public class LoginCustomerHandler {

  private final LoginProperties properties;
  private final CustomerRepository repository;

  public LoginCustomerHandler(CustomerRepository repository, LoginProperties properties) {
    this.repository = repository;
    this.properties = properties;
  }

  public void handle(LoginCustomerSchema schema) {
    var customer = repository.findByCedula(schema.getCedula());

    if (customer.isEmpty()) {
      properties.setSuccess(false, "Cédula no registrada.");
      return;
    }

    if (!HashingHelper.verify(schema.getPassword(), customer.get().getPassword())) {
      properties.setSuccess(false, "Contraseña incorrecta.");
      return;
    }

    properties.setCedula("");
    properties.setPassword("");
    properties.setSuccess(true, "Inicio de sesion exitoso.");
  }
}

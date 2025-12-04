package com.isw.app.handlers.login;

import com.isw.app.models.Customer;
import com.isw.app.helpers.HashingHelper;
import com.isw.app.properties.LoginProperties;
import com.isw.app.repositories.customer.CustomerRepository;

public class LoginCustomerHandler {

  private final LoginProperties properties;
  private final CustomerRepository repository;

  public LoginCustomerHandler(CustomerRepository repository, LoginProperties properties) {
    this.repository = repository;
    this.properties = properties;
  }

  public void handle(LoginCustomerSchema schema) {
    var customerOpt = repository.findByCedula(schema.getCedula());

    if (customerOpt.isEmpty()) {
      properties.setMessage("Cédula o contraseña incorrecta.");
      return;
    }

    Customer customer = customerOpt.get();
    if (!HashingHelper.verify(schema.getPassword(), customer.getPassword())) {
      properties.setMessage("Cédula o contraseña incorrecta.");
      return;
    }

    properties.setMessage("Inicio de sesion exitoso.");
    properties.setCedula("");
    properties.setPassword("");
  }
}

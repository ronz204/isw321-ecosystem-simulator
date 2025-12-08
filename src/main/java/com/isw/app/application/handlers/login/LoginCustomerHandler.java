package com.isw.app.application.handlers.login;

import com.isw.app.application.contexts.LoginContext;
import com.isw.app.application.helpers.HashingHelper;
import com.isw.app.infrastructure.repositories.customer.CustomerRepository;

public class LoginCustomerHandler {

  private final LoginContext context;
  private final CustomerRepository repository;

  public LoginCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
    this.context = LoginContext.getInstance();
  }

  public void handle(LoginCustomerSchema schema) {
    var customer = repository.findByCedula(schema.getCedula());

    if (customer.isEmpty()) {
      context.setSuccess(false, "Cédula no registrada.");
      return;
    }

    if (!HashingHelper.verify(schema.getPassword(), customer.get().getPassword())) {
      context.setSuccess(false, "Contraseña incorrecta.");
      return;
    }

    context.setEmail("");
    context.setCedula("");
    context.setPassword("");
    context.setEmail(customer.get().getEmail());
    context.setSuccess(true, "Inicio de sesion exitoso.");
  }
}

package com.isw.app.handlers;

import com.isw.app.models.Customer;
import com.isw.app.contexts.AuthContext;
import com.isw.app.helpers.HashingHelper;
import com.isw.app.repositories.customer.CustomerRepository;

public class LoginCustomerHandler {
  private final CustomerRepository repository;

  public LoginCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
  }

  public LoginCustomerResponse handle(LoginCustomerCommand command) {
    var customerOpt = repository.findByCedula(command.cedula());

    if (customerOpt.isEmpty()) {
      return new LoginCustomerResponse("Cédula o contraseña incorrecta.", false);
    }

    Customer customer = customerOpt.get();
    if (!HashingHelper.verify(command.password(), customer.getPassword())) {
      return new LoginCustomerResponse("Cédula o contraseña incorrecta.", false);
    }

    AuthContext.setUser(customer);
    return new LoginCustomerResponse("Inicio de sesion exitoso.", true);
  }
}

package com.isw.app.handlers;

import com.isw.app.models.Customer;
import com.isw.app.contexts.AuthContext;
import com.isw.app.helpers.HashingHelper;
import com.isw.app.helpers.LogBackHelper;
import com.isw.app.repositories.customer.CustomerRepository;

public class LoginCustomerHandler {
  private final CustomerRepository repository;
  private static final LogBackHelper log = LogBackHelper.getLogger(LoginCustomerHandler.class);

  public LoginCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
  }

  public LoginCustomerResponse handle(LoginCustomerCommand command) {
    log.info("Intento de inicio de sesion para cedula: {}", command.cedula());

    var customerOpt = repository.findByCedula(command.cedula());

    if (customerOpt.isEmpty()) {
      log.warn("Inicio de sesion fallido: cedula {} no encontrada", command.cedula());
      return new LoginCustomerResponse("Cédula o contraseña incorrecta.");
    }

    Customer customer = customerOpt.get();
    if (!HashingHelper.verify(command.password(), customer.getPassword())) {
      log.warn("Inicio de sesion fallido: contrasena incorrecta para cedula {}", command.cedula());
      return new LoginCustomerResponse("Cédula o contraseña incorrecta.");
    }

    AuthContext.setUser(customer);
    log.info("Inicio de sesion exitoso para cedula: {}", command.cedula());
    return new LoginCustomerResponse("Inicio de sesion exitoso.");
  }
}

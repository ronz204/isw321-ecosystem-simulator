package com.isw.app.handlers;

import com.isw.app.contexts.AuthContext;
import com.isw.app.helpers.LocalDateHelper;
import com.isw.app.repositories.customer.CustomerRepository;

public class RegisterCustomerHandler {
  public final CustomerRepository repository;

  public RegisterCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
  }

  public RegisterCustomerResponse handle(RegisterCustomerCommand command) {
    if (repository.findByEmail(command.email()).isPresent()) {
      return new RegisterCustomerResponse("Ya existe un cliente con ese email.", false);
    }

    if (repository.findByCedula(command.cedula()).isPresent()) {
      return new RegisterCustomerResponse("Ya existe un cliente con esa cedula.", false);
    }

    if (!LocalDateHelper.hasRequiredAge(command.birthday(), 18)) {
      return new RegisterCustomerResponse("El cliente debe ser mayor de edad.", false);
    }

    repository.save(command.toEntity());
    AuthContext.setUser(command.toEntity());
    return new RegisterCustomerResponse("Cliente registrado exitosamente", true);
  }
}

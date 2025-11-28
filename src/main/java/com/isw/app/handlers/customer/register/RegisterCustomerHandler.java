package com.isw.app.handlers.customer.register;

import com.isw.app.repositories.customer.CustomerRepository;

public class RegisterCustomerHandler {
  public final CustomerRepository repository;

  public RegisterCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
  }

  public RegisterCustomerResponse handle(RegisterCustomerCommand command) {
    if (repository.findByEmail(command.email()).isPresent()) {
      return new RegisterCustomerResponse("Ya existe un cliente con ese email.");
    }

    if (repository.findByCedula(command.cedula()).isPresent()) {
      return new RegisterCustomerResponse("Ya existe un cliente con esa cédula.");
    }

    repository.save(command.toEntity());
    return new RegisterCustomerResponse("Cliente registrado exitosamente");
  }
}

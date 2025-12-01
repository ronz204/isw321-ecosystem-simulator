package com.isw.app.handlers;

import com.isw.app.models.Customer;
import com.isw.app.contexts.AuthContext;
import com.isw.app.helpers.LocalDateHelper;
import com.isw.app.helpers.LogBackHelper;
import com.isw.app.repositories.customer.CustomerRepository;

public class RegisterCustomerHandler {
  public final CustomerRepository repository;
  private final LogBackHelper log = LogBackHelper.getLogger(RegisterCustomerHandler.class);

  public RegisterCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
  }

  public RegisterCustomerResponse handle(RegisterCustomerCommand command) {
    log.info("Intentando registrar cliente con email: {} y cedula: {}", command.email(), command.cedula());

    if (repository.findByEmail(command.email()).isPresent()) {
      log.warn("Registro fallido: ya existe un cliente con el email {}", command.email());
      return new RegisterCustomerResponse("Ya existe un cliente con ese email.");
    }

    if (repository.findByCedula(command.cedula()).isPresent()) {
      log.warn("Registro fallido: ya existe un cliente con la cedula {}", command.cedula());
      return new RegisterCustomerResponse("Ya existe un cliente con esa cedula.");
    }

    if (!LocalDateHelper.hasRequiredAge(command.birthday(), 18)) {
      log.warn("Registro fallido: el cliente con email {} no es mayor de edad", command.email());
      return new RegisterCustomerResponse("El cliente debe ser mayor de edad.");
    }

    Customer customer = command.toEntity();
    repository.save(customer);
    AuthContext.setUser(customer);
    log.info("Cliente registrado exitosamente: {}", customer.getEmail());
    return new RegisterCustomerResponse("Cliente registrado exitosamente");
  }
}

package com.isw.app.application.handlers.register;

import com.isw.app.domain.models.Customer;
import com.isw.app.application.helpers.HashingHelper;
import com.isw.app.application.helpers.LocalDateHelper;
import com.isw.app.application.contexts.RegisterContext;
import com.isw.app.infrastructure.repositories.customer.CustomerRepository;

public class RegisterCustomerHandler {
  private final RegisterContext context;
  private final CustomerRepository repository;

  public RegisterCustomerHandler(CustomerRepository repository) {
    this.repository = repository;
    this.context = RegisterContext.getInstance();
  }

  public void handle(RegisterCustomerSchema schema) {
    if (repository.findByEmail(schema.getEmail()).isPresent()) {
      context.setSuccess(false, "Ya existe un cliente con ese email.");
      return;
    }

    if (repository.findByCedula(schema.getCedula()).isPresent()) {
      context.setSuccess(false, "Ya existe un cliente con esa cedula.");
      return;
    }

    if (!LocalDateHelper.hasRequiredAge(schema.getBirthday(), 18)) {
      context.setSuccess(false, "El cliente debe ser mayor de edad.");
      return;
    }

    Customer customer = new Customer(schema.getCedula());
    customer.setName(schema.getName());
    customer.setEmail(schema.getEmail());
    customer.setGender(schema.getGender());
    customer.setPassword(HashingHelper.hash(schema.getPassword()));
    customer.setBirthday(schema.getBirthday());
    repository.save(customer);

    context.setSuccess(true, "Cliente registrado exitosamente");
    context.setCedula("");
    context.setName("");
    context.setEmail("");
    context.setGender(null);
    context.setPassword("");
    context.setBirthday(null);
  }
}

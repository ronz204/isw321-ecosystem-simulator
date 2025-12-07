package com.isw.app.application.handlers.register;

import com.isw.app.domain.models.Customer;
import com.isw.app.application.helpers.HashingHelper;
import com.isw.app.application.helpers.LocalDateHelper;
import com.isw.app.presentation.properties.RegisterProperties;
import com.isw.app.infrastructure.repositories.customer.CustomerRepository;

public class RegisterCustomerHandler {
  private final CustomerRepository repository;
  private final RegisterProperties properties;

  public RegisterCustomerHandler(CustomerRepository repository, RegisterProperties properties) {
    this.repository = repository;
    this.properties = properties;
  }

  public void handle(RegisterCustomerSchema schema) {
    if (repository.findByEmail(schema.getEmail()).isPresent()) {
      properties.setSuccess(false, "Ya existe un cliente con ese email.");
      return;
    }

    if (repository.findByCedula(schema.getCedula()).isPresent()) {
      properties.setSuccess(false, "Ya existe un cliente con esa cedula.");
      return;
    }

    if (!LocalDateHelper.hasRequiredAge(schema.getBirthday(), 18)) {
      properties.setSuccess(false, "El cliente debe ser mayor de edad.");
      return;
    }

    Customer customer = new Customer(schema.getCedula());
    customer.setName(schema.getName());
    customer.setEmail(schema.getEmail());
    customer.setGender(schema.getGender());
    customer.setPassword(HashingHelper.hash(schema.getPassword()));
    customer.setBirthday(schema.getBirthday());
    repository.save(customer);

    properties.setSuccess(true, "Cliente registrado exitosamente");
    properties.setCedula("");
    properties.setName("");
    properties.setEmail("");
    properties.setGender(null);
    properties.setPassword("");
    properties.setBirthday(null);
  }
}

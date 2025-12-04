package com.isw.app.handlers.register;

import com.isw.app.models.Customer;
import com.isw.app.helpers.LocalDateHelper;
import com.isw.app.properties.RegisterProperties;
import com.isw.app.repositories.customer.CustomerRepository;

public class RegisterCustomerHandler {
  private final CustomerRepository repository;
  private final RegisterProperties properties;

  public RegisterCustomerHandler(CustomerRepository repository, RegisterProperties properties) {
    this.repository = repository;
    this.properties = properties;
  }

  public void handle(RegisterCustomerSchema schema) {
    if (repository.findByEmail(schema.getEmail()).isPresent()) {
      properties.setMessage("Ya existe un cliente con ese email.");
      return;
    }

    if (repository.findByCedula(schema.getCedula()).isPresent()) {
      properties.setMessage("Ya existe un cliente con esa cedula.");
      return;
    }

    if (!LocalDateHelper.hasRequiredAge(schema.getBirthday(), 18)) {
      properties.setMessage("El cliente debe ser mayor de edad.");
      return;
    }

    Customer customer = new Customer(schema.getCedula());
    customer.setName(schema.getName());
    customer.setEmail(schema.getEmail());
    customer.setGender(schema.getGender());
    customer.setPassword(schema.getPassword());
    customer.setBirthday(schema.getBirthday());
    repository.save(customer);

    properties.setMessage("Cliente registrado exitosamente");
    properties.setCedula("");
    properties.setName("");
    properties.setEmail("");
    properties.setGender("");
    properties.setPassword("");
    properties.setBirthday(null);
  }
}

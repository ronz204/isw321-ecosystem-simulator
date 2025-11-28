package com.isw.app.handlers.customer.register;

import java.time.LocalDate;
import com.isw.app.models.Customer;

public record RegisterCustomerCommand(
    String name,
    String email,
    String gender,
    String cedula,
    String password,
    LocalDate birthday) {

  public Customer toEntity() {
    Customer customer = new Customer(cedula());
    customer.setName(name());
    customer.setEmail(email());
    customer.setGender(gender());
    customer.setPassword(password());
    customer.setBirthday(birthday());
    return customer;
  }
}

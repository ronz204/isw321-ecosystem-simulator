package com.isw.app.handlers;

import java.time.LocalDate;
import com.isw.app.models.Customer;
import com.isw.app.helpers.HashingHelper;

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
    customer.setPassword(HashingHelper.hash(password()));
    customer.setBirthday(birthday());
    return customer;
  }
}

package com.isw.app.repositories.customer;

import java.util.Optional;
import com.isw.app.models.Customer;

public interface CustomerRepository {
  void save(Customer customer);
  Optional<Customer> findById(String id);
  Optional<Customer> findByEmail(String email);
  Optional<Customer> findByCedula(String cedula);
}

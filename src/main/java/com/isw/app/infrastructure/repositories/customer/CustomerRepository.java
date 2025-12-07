package com.isw.app.infrastructure.repositories.customer;

import java.util.Optional;
import com.isw.app.domain.models.Customer;

public interface CustomerRepository {
  void save(Customer customer);
  Optional<Customer> findById(String id);
  Optional<Customer> findByEmail(String email);
  Optional<Customer> findByCedula(String cedula);
}

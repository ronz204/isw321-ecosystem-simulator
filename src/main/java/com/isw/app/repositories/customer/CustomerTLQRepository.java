package com.isw.app.repositories.customer;

import java.util.Optional;
import java.util.Map;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import com.isw.app.enums.DataPath;
import com.isw.app.models.Customer;
import com.isw.app.helpers.BufferedHelper;
import com.isw.app.repositories.BaseTLQRepository;

public class CustomerTLQRepository extends BaseTLQRepository implements CustomerRepository {

  @Override
  public void save(Customer customer) {
    try (BufferedWriter writer = BufferedHelper.getWriter(DataPath.CUSTOMERS)) {
      writeDelimiter(writer);
      writeField(writer, "id", customer.getId());
      writeField(writer, "cedula", customer.getCedula());
      writeField(writer, "name", customer.getName());
      writeField(writer, "email", customer.getEmail());
      writeField(writer, "gender", customer.getGender());
      writeField(writer, "password", customer.getPassword());
      writeField(writer, "birthday", customer.getBirthday().toString());
      writeDelimiter(writer);
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Customer buildCustomer(Map<String, String> fields) {
    Customer customer = new Customer(fields.get("cedula"));
    customer.setId(fields.get("id"));
    customer.setName(fields.get("name"));
    customer.setEmail(fields.get("email"));
    customer.setGender(fields.get("gender"));
    customer.setPassword(fields.get("password"));
    customer.setBirthday(java.time.LocalDate.parse(fields.get("birthday")));
    return customer;
  }

  private Optional<Customer> findByField(String field, String value) {
    try (BufferedReader reader = BufferedHelper.getReader(DataPath.CUSTOMERS)) {
      for (var block : readBlocks(reader)) {
        Map<String, String> fields = parseFields(block);
        
        if (value.equals(fields.get(field))) {
          return Optional.of(buildCustomer(fields));
        }
      }
      return Optional.empty();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<Customer> findById(String id) {
    return findByField("id", id);
  }

  @Override
  public Optional<Customer> findByEmail(String email) {
    return findByField("email", email);
  }

  @Override
  public Optional<Customer> findByCedula(String cedula) {
    return findByField("cedula", cedula);
  }
}

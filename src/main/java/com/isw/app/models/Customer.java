package com.isw.app.models;

import java.time.LocalDate;
import com.isw.app.helpers.IdentifierHelper;

public class Customer {
  private final String PREFIX = "CUST";

  private final String id;
  private final String cedula;

  private String name;
  private String email;
  private String gender;
  private String password;
  private LocalDate birthday;

  public Customer(String cedula) {
    this.id = IdentifierHelper.generate(PREFIX);
    this.cedula = cedula;
  }

  public String getId() {
    return id;
  }

  public String getCedula() {
    return cedula;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
}

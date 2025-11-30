package com.isw.app.models;

import java.time.LocalDate;

public class Customer {

  private String name;
  private String cedula;
  private String email;
  private String password;
  private String gender;
  private LocalDate birthday;

  public Customer(String cedula) {
    this.cedula = cedula;
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

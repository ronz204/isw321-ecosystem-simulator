package com.isw.app.domain.models;

import java.time.LocalDate;
import com.isw.app.domain.enums.Gender;

public class Customer {

  private String name;
  private String cedula;
  private String email;
  private String password;
  private Gender gender;
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

  public Gender getGender() {
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

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
}

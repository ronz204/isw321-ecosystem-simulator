package com.isw.app.handlers.customer.login;

import jakarta.validation.constraints.NotBlank;

public class LoginCustomerSchema {
  @NotBlank(message = "La cédula es obligatoria.")
  private String cedula;

  @NotBlank(message = "La contraseña es obligatoria.")
  private String password;

  public LoginCustomerSchema(String cedula, String password) {
    this.cedula = cedula;
    this.password = password;
  }

  public String getCedula() {
    return cedula;
  }

  public String getPassword() {
    return password;
  }
}

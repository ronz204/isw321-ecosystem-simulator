package com.isw.app.application.handlers.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginCustomerSchema {
  @NotBlank(message = "El email es obligatorio.")
  @Email(message = "El email no es válido.")
  private String email;

  @NotBlank(message = "La cédula es obligatoria.")
  private String cedula;

  @NotBlank(message = "La contraseña es obligatoria.")
  private String password;

  public LoginCustomerSchema(String email, String cedula, String password) {
    this.email = email;
    this.cedula = cedula;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getCedula() {
    return cedula;
  }

  public String getPassword() {
    return password;
  }
}

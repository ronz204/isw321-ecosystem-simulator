package com.isw.app.handlers.login;

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

  public LoginCustomerCommand toCommand() {
    return new LoginCustomerCommand(cedula, password);
  }
}

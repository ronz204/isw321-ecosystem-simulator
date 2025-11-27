package com.isw.app.handlers.register;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterSchema {
  @NotBlank(message = "El nombre de usuario es obligatorio.")
  private String username;

  @NotBlank(message = "El email es obligatorio.")
  @Email(message = "El email no es válido.")
  private String email;

  @NotBlank(message = "La contraseña es obligatoria.")
  @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
  private String password;

  public RegisterSchema(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public RegisterCommand toCommand() {
    return new RegisterCommand(username, email, password);
  }
}

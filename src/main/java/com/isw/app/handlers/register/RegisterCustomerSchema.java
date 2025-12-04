package com.isw.app.handlers.register;

import java.time.LocalDate;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class RegisterCustomerSchema {
  @NotBlank(message = "La cédula es obligatoria.")
  private String cedula;

  @NotBlank(message = "El nombre es obligatorio.")
  private String name;

  @NotBlank(message = "El email es obligatorio.")
  @Email(message = "El email no es válido.")
  private String email;

  @NotBlank(message = "El género es obligatorio.")
  private String gender;

  @NotBlank(message = "La contraseña es obligatoria.")
  @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
  private String password;

  @NotNull(message = "La fecha de nacimiento es obligatoria.")
  private LocalDate birthday;

  public RegisterCustomerSchema(String cedula, String name, String email, String gender, String password, LocalDate birthday) {
    this.name = name;
    this.email = email;
    this.gender = gender;
    this.cedula = cedula;
    this.password = password;
    this.birthday = birthday;
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
}

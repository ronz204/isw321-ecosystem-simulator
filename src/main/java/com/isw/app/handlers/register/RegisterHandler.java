package com.isw.app.handlers.register;

public class RegisterHandler {
  public RegisterResponse handler(RegisterCommand command) {
    String message = "Usuario " + command.username() + " registrado con éxito.";
    return new RegisterResponse(message);
  }
}

package com.isw.app.handlers.login;

public record LoginCustomerCommand(
    String cedula,
    String password) {
}

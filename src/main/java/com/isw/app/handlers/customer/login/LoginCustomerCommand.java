package com.isw.app.handlers.customer.login;

public record LoginCustomerCommand(
    String cedula,
    String password) {
}

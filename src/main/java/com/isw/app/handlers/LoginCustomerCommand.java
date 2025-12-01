package com.isw.app.handlers;

public record LoginCustomerCommand(
    String cedula,
    String password) {
}

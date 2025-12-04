package com.isw.app.enums;

public enum Balance {
  BALANCED("BALANCED"),
  PREY_DOMINANT("PREY_DOMINANT"),
  PREDATOR_DOMINANT("PREDATOR_DOMINANT");

  private final String value;

  Balance(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Balance fromValue(String value) {
    for (Balance balance : Balance.values()) {
      if (balance.value.equals(value)) return balance;
    }
    throw new IllegalArgumentException("Balance inválido: " + value);
  }
}

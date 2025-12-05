package com.isw.app.enums;

public enum Balance {
  BALANCED("Balanceado", 10, 10),
  PREY_DOMINANT("Presas Dominantes", 20, 10),
  PREDATOR_DOMINANT("Depredadores Dominantes", 10, 20);

  private final String name;
  private final int preyAmount;
  private final int predatorAmount;

  Balance(String name, int prey, int predator) {
    this.name = name;
    this.preyAmount = prey;
    this.predatorAmount = predator;
  }

  public String getName() {
    return name;
  }

  public int getPreyAmount() {
    return preyAmount;
  }

  public int getPredatorAmount() {
    return predatorAmount;
  }

  public static Balance fromName(String name) {
    for (Balance balance : Balance.values()) {
      if (balance.getName().equals(name)) return balance;
    }
    return BALANCED;
  }
}

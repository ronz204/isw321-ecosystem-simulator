package com.isw.app.domain.models;

import com.isw.app.domain.enums.Balance;

public class Ecosystem {
  private int turns;
  private Balance balance;
  private boolean flagZombieMutation;
  private boolean flagOmnivoreExpansion;

  public Ecosystem(int turns, Balance balance, boolean flagZombie, boolean flagOmnivore) {
    this.turns = turns;
    this.balance = balance;
    this.flagZombieMutation = flagZombie;
    this.flagOmnivoreExpansion = flagOmnivore;
  }

  public int getTurns() {
    return turns;
  }

  public Balance getBalance() {
    return balance;
  }

  public boolean getFlagZombieMutation() {
    return flagZombieMutation;
  }

  public boolean getFlagOmnivoreExpansion() {
    return flagOmnivoreExpansion;
  }
}

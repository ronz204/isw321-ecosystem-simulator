package com.isw.app.core.expansions.species;

import com.isw.app.enums.Balance;
import com.isw.app.core.expansions.Expansion;

public abstract class SpeciesExpansion extends Expansion {
  protected Balance balance;

  public SpeciesExpansion(Balance balance) {
    this.balance = balance;
  }
}

package com.isw.app.domain.core.artifacts.expansions;

import com.isw.app.domain.enums.Balance;
import com.isw.app.domain.core.artifacts.Artifact;

public abstract class BaseExpansion extends Artifact {
  protected Balance balance;

  public BaseExpansion(Balance balance) {
    this.balance = balance;
  }
}

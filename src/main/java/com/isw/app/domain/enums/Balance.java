package com.isw.app.domain.enums;

import com.isw.app.domain.core.objects.Detail;

public enum Balance {
  BALANCED("Balanceado") {
    @Override
    public int getPopulation(Detail detail) {
      switch (detail) {
        case HERBIVORE: return 5;
        case CARNIVORE: return 5;
        default: return 5;
      }
    }
  },
  HERBIVORE_DOMINANT("Herbívoros Dominantes") {
    @Override
    public int getPopulation(Detail detail) {
      switch (detail) {
        case HERBIVORE: return 10;
        case CARNIVORE: return 2;
        default: return 5;
      }
    }
  },
  CARNIVORE_DOMINANT("Carnívoros Dominantes") {
    @Override
    public int getPopulation(Detail detail) {
      switch (detail) {
        case HERBIVORE: return 6;
        case CARNIVORE: return 8;
        default: return 5;
      }
    }
  },;

  private final String display;
  
  public abstract int getPopulation(Detail detail);

  Balance(String display) {
    this.display = display;
  }

  public String getDisplay() {
    return display;
  }

  public static Balance fromDisplay(String display) {
    for (Balance balance : Balance.values()) {
      if (balance.getDisplay().equals(display)) return balance;
    }
    return BALANCED;
  }
}

package com.isw.app.enums;

import com.isw.app.core.objects.Detail;

public enum Balance {
  BALANCED("Balanceado") {
    @Override
    public int getPopulation(Detail detail) {
      switch (detail) {
        case HERBIVORE: return 5;
        case CARNIVORE: return 5;
        case OMNIVORE: return 5;
        case SCAVENGER: return 5;
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
        case OMNIVORE: return 5;
        case SCAVENGER: return 3;
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
        case OMNIVORE: return 5;
        case SCAVENGER: return 3;
        default: return 5;
      }
    }
  },
  OMNIVORE_DOMINANT("Omnívoros Dominantes") {
    @Override
    public int getPopulation(Detail detail) {
      switch (detail) {
        case HERBIVORE: return 4;
        case CARNIVORE: return 4;
        case OMNIVORE: return 12;
        case SCAVENGER: return 2;
        default: return 5;
      }
    }
  };

  private final String label;

  Balance(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public abstract int getPopulation(Detail detail);

  public static Balance fromLabel(String label) {
    for (Balance balance : Balance.values()) {
      if (balance.getLabel().equals(label)) return balance;
    }
    return BALANCED;
  }
}

package com.isw.app.enums;

public enum EcosystemType {
  BALANCED("Balanceado"),
  DOMINANT_PREY("Presas Dominantes"),
  DOMINANT_PREDATORS("Depredadores Dominantes");

  public final String label;

  EcosystemType(String label) {
    this.label = label;
  }
}

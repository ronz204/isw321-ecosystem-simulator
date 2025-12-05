package com.isw.app.core.objects;

public enum Detail {
  OMNIVORE("Omnivoro", "🐻"),
  CARNIVORE("Carnivoro", "🦊"),
  HERBIVORE("Herbivoro", "🐰"),
  SCAVENGER("Carroñero", "🦅");

  private final String label;
  private final String icon;

  Detail(String label, String icon) {
    this.label = label;
    this.icon = icon;
  }

  public String getLabel() {
    return label;
  }

  public String getIcon() {
    return icon;
  }
}

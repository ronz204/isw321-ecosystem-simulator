package com.isw.app.core.objects;

public enum Detail {
  CARNIVORE("Carnivoro", "🦊"),
  HERBIVORE("Herbivoro", "🐰");

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

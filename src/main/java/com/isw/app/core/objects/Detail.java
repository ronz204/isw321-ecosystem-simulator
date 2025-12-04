package com.isw.app.core.objects;

public enum Detail {
  CARNIVORE("Carnivoro", "🦊"),
  HERBIVORE("Herbivoro", "🐰");

  private final String type;
  private final String icon;

  Detail(String type, String icon) {
    this.type = type;
    this.icon = icon;
  }

  public String getType() {
    return type;
  }

  public String getIcon() {
    return icon;
  }
}

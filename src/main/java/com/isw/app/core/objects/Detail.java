package com.isw.app.core.objects;

public enum Detail {
  OMNIVORE("Omnivoro", "🐻", 3),
  CARNIVORE("Carnivoro", "🦊", 3),
  HERBIVORE("Herbivoro", "🐰", 1),
  SCAVENGER("Carroñero", "🦅", 2);

  private final String label;
  private final String icon;
  private final int tier;

  Detail(String label, String icon, int tier) {
    this.label = label;
    this.icon = icon;
    this.tier = tier;
  }

  public String getLabel() {
    return label;
  }

  public String getIcon() {
    return icon;
  }

  public int getTier() {
    return tier;
  }
}

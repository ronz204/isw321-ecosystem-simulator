package com.isw.app.domain.core.objects;

public enum Detail {
  OMNIVORE("Omnivoro", "🐻", 3),
  CARNIVORE("Carnivoro", "🦊", 3),
  HERBIVORE("Herbivoro", "🐰", 1),
  SCAVENGER("Carroñero", "🦅", 2);

  private String label;
  private String icon;
  private int tier;

  Detail(String label, String icon, int tier) {
    this.label = label;
    this.icon = icon;
    this.tier = tier;
  }

  public int getTier() {
    return tier;
  }

  public void setTier(int tier) {
    this.tier = tier;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}

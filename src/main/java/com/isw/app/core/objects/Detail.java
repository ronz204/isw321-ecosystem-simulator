package com.isw.app.core.objects;

public enum Detail {
  CARNIVORE("Carnivoro"),
  HERBIVORE("Herbivoro");

  private final String name;

  Detail(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

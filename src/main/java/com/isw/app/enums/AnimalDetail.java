package com.isw.app.enums;

public enum AnimalDetail {
  CARNIVORE("Carnivoro"),
  HERBIVORE("Herbivoro");

  private final String name;

  AnimalDetail(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

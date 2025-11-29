package com.isw.app.models;

import com.isw.app.enums.AnimalType;

public class Animal {
  private AnimalType type;

  public Animal(AnimalType type) {
    this.type = type;
  }

  public AnimalType getType() {
    return type;
  }
}

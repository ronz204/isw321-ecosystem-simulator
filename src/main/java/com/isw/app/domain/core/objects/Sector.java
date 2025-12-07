package com.isw.app.domain.core.objects;

public class Sector {
  private Coord coord;
  private Animal animal;

  public Sector(Coord coord) {
    this.coord = coord;
    this.animal = null;
  }

  public boolean isEmpty() {
    return animal == null;
  }

  public Coord getCoord() {
    return coord;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public String getIcon() {
    return animal != null ? animal.getIcon() : "🌿";
  }
}

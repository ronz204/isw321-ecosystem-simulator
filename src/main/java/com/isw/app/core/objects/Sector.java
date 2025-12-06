package com.isw.app.core.objects;

public class Sector {
  private Coord coord;
  private Animal animal;

  public Sector(Coord coord) {
    this.coord = coord;
    this.animal = null;
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

  public boolean isEmpty() {
    return animal == null;
  }

  public boolean hasAnimalOfType(Detail detail) {
    return animal != null && animal.getDetail() == detail;
  }
}

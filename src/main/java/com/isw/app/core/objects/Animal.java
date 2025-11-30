package com.isw.app.core.objects;

public class Animal {
  
  private String name;
  private Coord coord;

  public Animal(String name, Coord coord) {
    this.name = name;
    this.coord = coord;
  }

  public String getName() {
    return name;
  }

  public Coord getCoord() {
    return coord;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }
}

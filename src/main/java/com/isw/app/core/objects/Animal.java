package com.isw.app.core.objects;

import com.isw.app.enums.AnimalDetail;

public class Animal {
  
  private Coord coord;
  private AnimalDetail detail;

  public Animal(AnimalDetail detail, Coord coord) {
    this.detail = detail;
    this.coord = coord;
  }

  public Coord getCoord() {
    return coord;
  }

  public AnimalDetail getDetail() {
    return detail;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }
}

package com.isw.app.core.objects;

import com.isw.app.enums.AnimalDetail;
import com.isw.app.helpers.IdentifierHelper;

public class Animal {
  private final String PREFIX = "ANI";
  
  private String uuid;
  private Coord coord;
  private AnimalDetail detail;

  public Animal(AnimalDetail detail, Coord coord) {
    this.uuid = IdentifierHelper.generate(PREFIX);
    this.detail = detail;
    this.coord = coord;
  }

  public String getUuid() {
    return uuid;
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

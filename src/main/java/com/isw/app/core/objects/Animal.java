package com.isw.app.core.objects;

import com.isw.app.core.behavior.Behavior;
import com.isw.app.helpers.IdentifierHelper;

public class Animal {
  private final String PREFIX = "ANM";

  private String uuid;
  private Coord coord;
  private Detail detail;
  private Behavior behavior;

  public Animal(Detail detail, Coord coord, Behavior behavior) {
    this.uuid = IdentifierHelper.generate(PREFIX);
    this.behavior = behavior;
    this.detail = detail;
    this.coord = coord;
  }

  public String getUuid() {
    return uuid;
  }

  public Coord getCoord() {
    return coord;
  }
  public String getType() {
    return detail.getType();
  }

  public Detail getDetail() {
    return detail;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }
}

package com.isw.app.core.objects;

import com.isw.app.core.behavior.Behavior;
import com.isw.app.helpers.IdentifierHelper;

public class Animal {
  private final String PREFIX = "ANM";

  private String uuid;
  private Coord coord;
  private Detail detail;
  private Behavior behavior;
  private boolean isDead = false;

  public Animal(Detail detail, Coord coord, Behavior behavior) {
    this.uuid = IdentifierHelper.generate(PREFIX);
    this.behavior = behavior;
    this.detail = detail;
    this.coord = coord;
  }

  public String getUuid() {
    return uuid;
  }

  public boolean isDead() {
    return isDead;
  }

  public Coord getCoord() {
    return coord;
  }

  public int getRow() {
    return coord.getRow();
  }

  public int getCol() {
    return coord.getCol();
  }

  public String getLabel() {
    return detail.getLabel();
  }

  public String getIcon() {
    return detail.getIcon();
  }

  public Detail getDetail() {
    return detail;
  }

  public Behavior getBehavior() {
    return behavior;
  }

  public void makeAsDead() {
    this.isDead = true;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }
}

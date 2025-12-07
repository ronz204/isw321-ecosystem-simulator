package com.isw.app.domain.core.objects;

import com.isw.app.domain.core.behaviors.Behavior;

public class Animal {
  
  private Coord coord;
  private Detail detail;
  private Behavior behavior;

  public Animal(Detail detail, Coord coord, Behavior behavior) {
    this.behavior = behavior;
    this.detail = detail;
    this.coord = coord;
  }

  public void act() {
    behavior.act(this);
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

  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  public Detail getDetail() {
    return detail;
  }

  public int getTier() {
    return detail.getTier();
  }

  public String getIcon() {
    return detail.getIcon();
  }

  public String getLabel() {
    return detail.getLabel();
  }
}

package com.isw.app.domain.core.objects;

import com.isw.app.domain.core.behaviors.Behavior;

public class Animal {
  
  private Coord coord;
  private Detail detail;
  private Behavior behavior;
  private boolean isDead = false;

  public Animal(Detail detail, Coord coord, Behavior behavior) {
    this.behavior = behavior;
    this.detail = detail;
    this.coord = coord;
  }

  public boolean isDead() {
    return isDead;
  }

  public void markAsDead() {
    this.detail = Detail.CORPSE;
    isDead = true;
  }

  public Result act(Matrix matrix) {
    return behavior.act(this, matrix);
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

  public Behavior getBehavior() {
    return behavior;
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }
}

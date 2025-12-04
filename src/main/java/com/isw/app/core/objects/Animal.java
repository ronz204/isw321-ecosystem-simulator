package com.isw.app.core.objects;

import com.isw.app.core.behavior.Behavior;
import com.isw.app.helpers.IdentifierHelper;

public class Animal {
  private final String PREFIX = "ANM";

  private String uuid;
  private Coord coord;
  private Detail detail;
  private Behavior behavior;

  private int meals;
  private int aliveTurns;
  private int hungryTurns;

  public Animal(Detail detail, Coord coord, Behavior behavior) {
    this.uuid = IdentifierHelper.generate(PREFIX);
    this.behavior = behavior;
    this.detail = detail;
    this.coord = coord;

    this.meals = 0;
    this.aliveTurns = 0;
    this.hungryTurns = 0;
  }

  public void act(Matrix matrix) {
    Coord next = behavior.nextMove(this, matrix);
    boolean ate = false;
    if (next != null) {
      ate = behavior.canEat(next, matrix);
      coord = next;
    }
    behavior.update(this, ate);
  }

  public boolean isDead() {
    return detail == Detail.CARNIVORE && hungryTurns >= 3;
  }

  public boolean canReproduce() {
    return behavior.canReproduce(this);
  }

  public Animal reproduce() {
    return new Animal(this.detail, this.coord, this.behavior);
  }

  public boolean isPrey() {
    return detail == Detail.HERBIVORE;
  }

  public boolean isPredator() {
    return detail == Detail.CARNIVORE;
  }

  public String toFileFormat() {
    return String.format("%s,%s,%d,%d,%d,%d,%d",
        uuid, detail, coord.getRow(), coord.getCol(),
        aliveTurns, hungryTurns, meals);
  }

  public String getUuid() {
    return uuid;
  }

  public Coord getCoord() {
    return coord;
  }

  public Detail getDetail() {
    return detail;
  }

  public int getAliveTurns() {
    return aliveTurns;
  }

  public int getHungryTurns() {
    return hungryTurns;
  }

  public int getMeals() {
    return meals;
  }

  public void setCoord(Coord c) {
    coord = c;
  }

  public void incAliveTurns() {
    aliveTurns++;
  }

  public void incHungryTurns() {
    hungryTurns++;
  }

  public void resetHungryTurns() {
    hungryTurns = 0;
  }

  public void incMeals() {
    meals++;
  }

  public void resetMeals() {
    meals = 0;
  }
}

package com.isw.app.domain.core.behaviors;

public class CarnivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 3;
  private final int STARVATION_THRESHOLD = 3;

  @Override
  public boolean canEat() {
    return true;
  }

  @Override
  public boolean canDie() {
    return turnsSinceLastMeal >= STARVATION_THRESHOLD;
  }

  @Override
  public boolean canSex() {
    return turnsSinceLastMeal == REPRODUCTION_THRESHOLD;
  }
}

package com.isw.app.domain.core.behaviors;

public class OmnivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 2;
  private final int STARVATION_THRESHOLD = 4;

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
    return turnsSinceLastMeal < REPRODUCTION_THRESHOLD;
  }
}

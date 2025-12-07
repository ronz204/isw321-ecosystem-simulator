package com.isw.app.domain.core.behaviors;

public class HerbivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 2;

  @Override
  public boolean canEat() {
    return false;
  }

  @Override
  public boolean canDie() {
    return false;
  }

  @Override
  public boolean canSex() {
    return turnsSinceLastSex >= REPRODUCTION_THRESHOLD;
  }
}

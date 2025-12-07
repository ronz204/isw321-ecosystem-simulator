package com.isw.app.domain.core.behaviors;

import com.isw.app.domain.core.objects.Animal;

public class HerbivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 2;

  @Override
  public void act(Animal animal) {
    attemptMove.execute(animal, matrix);
  }

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

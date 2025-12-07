package com.isw.app.domain.core.behaviors;

import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;

public class CarnivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 2;

  @Override
  public void act(Animal animal, Matrix matrix) {
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

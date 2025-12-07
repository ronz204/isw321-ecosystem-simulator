package com.isw.app.domain.core.behaviors;

import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;

public class CarnivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 3;
  private final int STARVATION_THRESHOLD = 3;

  @Override
  public void act(Animal animal, Matrix matrix) {
    attemptMove.execute(animal, matrix);
  }

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

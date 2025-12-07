package com.isw.app.domain.core.behaviors;

import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;

public class OmnivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 2;
  private final int STARVATION_THRESHOLD = 4;

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

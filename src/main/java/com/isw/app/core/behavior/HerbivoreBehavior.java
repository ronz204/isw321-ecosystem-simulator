package com.isw.app.core.behavior;

import com.isw.app.core.objects.Action;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Result;
import com.isw.app.core.attempts.AttemptMove;
import com.isw.app.core.attempts.AttemptReproduce;

public class HerbivoreBehavior extends Behavior {
  private final int REPRODUCTION_THRESHOLD = 2;

  @Override
  public Result act(Animal animal, Matrix matrix) {

    turnsAlive++;
    turnsSinceLastSex++;

    if (canReproduce()) {
      Result reproduction = AttemptReproduce.execute(animal, matrix);

      if (reproduction.getAction() == Action.REPRODUCE) {
        turnsSinceLastSex = 0;
        return reproduction;
      }
    }

    return AttemptMove.execute(animal, matrix);
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
  public boolean canReproduce() {
    return turnsSinceLastSex >= REPRODUCTION_THRESHOLD;
  }
}

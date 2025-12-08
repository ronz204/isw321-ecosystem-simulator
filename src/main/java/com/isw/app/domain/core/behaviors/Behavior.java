package com.isw.app.domain.core.behaviors;

import com.isw.app.domain.core.objects.Action;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.domain.core.attempts.Attempt;
import com.isw.app.domain.core.attempts.AttemptSex;
import com.isw.app.domain.core.attempts.AttemptMove;
import com.isw.app.domain.core.attempts.AttemptHunt;
import com.isw.app.domain.core.attempts.AttemptDeath;

public abstract class Behavior {
  protected int turnsAlive = 0;
  protected int turnsSinceLastSex = 0;
  protected int turnsSinceLastMeal = 0;
  protected int consecutiveSurvivalTurns = 0;

  protected Attempt attemptSex = new AttemptSex();
  protected Attempt attemptMove = new AttemptMove();
  protected Attempt attemptHunt = new AttemptHunt();
  protected Attempt attemptDeath = new AttemptDeath();

  public abstract boolean canEat();
  public abstract boolean canDie();
  public abstract boolean canSex();

  public Result act(Animal animal, Matrix matrix) {
    if (animal.isDead()) {
      String description = "";
      return Result.build(animal, Action.IDLE, description, false);
    }
    incrementTurns();

    if (canDie())
      return attemptDeath.execute(animal, matrix);

    if (canSex()) {
      Result result = attemptSex.execute(animal, matrix);
      if (result.isSuccess())
        return result;
    }

    if (canEat()) {
      Result result = attemptHunt.execute(animal, matrix);
      if (result != null && result.isSuccess())
        return result;
    }

    return attemptMove.execute(animal, matrix);
  };

  protected void incrementTurns() {
    turnsAlive++;
    turnsSinceLastSex++;
    turnsSinceLastMeal++;
  }

  public void resetMealTurns() {
    turnsSinceLastMeal = 0;
  }

  public void resetSexTurns() {
    turnsSinceLastSex = 0;
  }
}

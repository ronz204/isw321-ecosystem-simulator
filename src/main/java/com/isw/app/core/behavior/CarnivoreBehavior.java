package com.isw.app.core.behavior;

import com.isw.app.core.objects.Action;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Result;
import com.isw.app.core.attempts.AttemptHunt;
import com.isw.app.core.attempts.AttemptMove;
import com.isw.app.core.attempts.AttemptReproduce;

public class CarnivoreBehavior extends Behavior {
  private final int STARVATION_THRESHOLD = 3;
  private final int REPRODUCTION_WINDOW = 3;

  private int lastMealTurn = 0;

  @Override
  public Result act(Animal animal, Matrix matrix) {

    turnsAlive++;
    turnsSinceLastMeal++;

    if (canDie())
      return Result.death(animal);

    if (canEat()) {
      Result hunt = AttemptHunt.execute(animal, matrix);
      
      if (hunt.getAction() == Action.EAT) {
        lastMealTurn = turnsAlive;
        turnsSinceLastMeal = 0;
        return hunt;
      }
    }

    if (canReproduce())
      return AttemptReproduce.execute(animal, matrix);

    return AttemptMove.execute(animal, matrix);
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
  public boolean canReproduce() {
    int turnsSinceLastMeal = turnsAlive - lastMealTurn;
    return turnsSinceLastMeal <= REPRODUCTION_WINDOW && lastMealTurn > 0;
  }
}

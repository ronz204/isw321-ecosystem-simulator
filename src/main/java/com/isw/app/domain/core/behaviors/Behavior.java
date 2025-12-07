package com.isw.app.domain.core.behaviors;

import java.util.Map;
import java.util.List;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.attempts.Attempt;
import com.isw.app.domain.core.attempts.AttemptMove;
import com.isw.app.domain.core.setup.SimulatorContext;

public abstract class Behavior {
  protected int turnsAlive = 0;
  protected int turnsSinceLastSex = 0;
  protected int turnsSinceLastMeal = 0;
  protected int consecutiveSurvivalTurns = 0;
  
  protected SimulatorContext context = SimulatorContext.getInstance();
  protected Map<Detail, List<Animal>> animals = context.getAnimals();
  protected Matrix matrix = context.getMatrix();

  protected Attempt attemptMove = new AttemptMove();

  public abstract boolean canEat();
  public abstract boolean canDie();
  public abstract boolean canSex();
  public abstract void act(Animal animal);
}

package com.isw.app.core.behavior;

import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Result;
import com.isw.app.core.artifacts.ContextArtifact;

public abstract class Behavior {
  protected int turnsAlive = 0;
  protected int turnsSinceLastSex = 0;
  protected int turnsSinceLastMeal = 0;
  protected int consecutiveSurvivalTurns = 0;

  protected ContextArtifact context = ContextArtifact.getInstance();

  public abstract boolean canEat();
  public abstract boolean canDie();
  public abstract boolean canReproduce();
  public abstract Result act(Animal animal, Matrix matrix);
}

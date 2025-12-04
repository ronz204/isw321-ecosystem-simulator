package com.isw.app.core.behavior;

import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;

public class HerbivoreBehavior extends Behavior {

  @Override
  public Coord nextMove(Animal animal, Matrix matrix) {
    return findAdjacent(animal.getCoord(), matrix, sector -> sector.isEmpty());
  }

  @Override
  public boolean canEat(Coord pos, Matrix matrix) {
    return false;
  }

  @Override
  public boolean canReproduce(Animal animal) {
    return animal.getAliveTurns() >= 2;
  }

  @Override
  public void update(Animal animal, boolean ate) {
    animal.incAliveTurns();
  }
}

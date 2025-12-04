package com.isw.app.core.behavior;

import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Detail;

public class CarnivoreBehavior extends Behavior {

  @Override
  public Coord nextMove(Animal animal, Matrix matrix) {
    Coord prey = findAdjacent(animal.getCoord(), matrix,
        sector -> sector.hasAnimalOfType(Detail.HERBIVORE));

    if (prey != null)
      return prey;

    return findAdjacent(animal.getCoord(), matrix, sector -> sector.isEmpty());
  }

  @Override
  public boolean canEat(Coord pos, Matrix matrix) {
    return matrix.getSectorAt(pos.getRow(), pos.getCol()).hasAnimalOfType(Detail.HERBIVORE);
  }

  @Override
  public boolean canReproduce(Animal animal) {
    return animal.getMeals() >= 1;
  }

  @Override
  public void update(Animal animal, boolean ate) {
    animal.incAliveTurns();
    if (ate) {
      animal.resetHungryTurns();
      animal.incMeals();
    } else {
      animal.incHungryTurns();
    }

    if (animal.getAliveTurns() % 3 == 0) {
      animal.resetMeals();
    }
  }
}

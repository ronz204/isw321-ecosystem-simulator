package com.isw.app.domain.core.attempts;

import java.util.List;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Action;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.domain.core.objects.Sector;
import com.isw.app.application.helpers.RandomHelper;

public class AttemptMove extends Attempt {

  @Override
  public Result execute(Animal animal, Matrix matrix) {
    List<Coord> adjacent = matrix.getAdjacentEmptySectors(animal.getCoord());

    if (adjacent.isEmpty()) {
      String description = String.format("%s no pudo moverse, no hay sectores adyacentes vacíos", 
          animal.getDetail().getLabel());
      return Result.build(animal, Action.IDLE, description);
    }

    Sector prev = matrix.getSectorAt(animal.getCoord());
    prev.setAnimal(null);

    int index = RandomHelper.getChooseInt(adjacent.size());
    animal.setCoord(adjacent.get(index));

    Sector next = matrix.getSectorAt(animal.getCoord());
    next.setAnimal(animal);

    String description = String.format("%s se movió de %s a %s", 
        animal.getDetail().getLabel(), prev.getCoord(), animal.getCoord());
    return Result.build(animal, Action.MOVE, description);
  }
}

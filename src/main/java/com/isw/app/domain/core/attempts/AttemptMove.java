package com.isw.app.domain.core.attempts;

import java.util.List;
import com.isw.app.domain.core.objects.Coord;
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
      return Result.idle(animal);
    }

    Sector prev = matrix.getSectorAt(animal.getCoord());
    prev.setAnimal(null);

    int index = RandomHelper.getChooseInt(adjacent.size());
    animal.setCoord(adjacent.get(index));

    Sector next = matrix.getSectorAt(animal.getCoord());
    next.setAnimal(animal);

    return Result.move(animal);
  }
}

package com.isw.app.core.attempts;

import java.util.List;
import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Result;
import com.isw.app.helpers.RandomHelper;
import com.isw.app.core.factories.AnimalFactory;

public class AttemptReproduce {
  
  public static Result execute(Animal animal, Matrix matrix) {
    List<Coord> adjacent = matrix.getAdjacentEmptySectors(animal.getCoord());

    if (adjacent.isEmpty()) {
      return Result.idle();
    }

    int choose = RandomHelper.getNextInt(adjacent.size());
    Coord coord = adjacent.get(choose);
    Animal child = AnimalFactory.build(animal.getDetail(), coord);

    return Result.reproduce(animal, child);
  }
}

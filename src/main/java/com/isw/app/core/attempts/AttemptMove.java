package com.isw.app.core.attempts;

import java.util.List;
import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Result;
import com.isw.app.helpers.RandomHelper;

public class AttemptMove {
  
  public static Result execute(Animal animal, Matrix matrix) {
    List<Coord> adjacent = matrix.getAdjacentEmptySectors(animal.getCoord());

    if (adjacent.isEmpty()) {
      return Result.idle();
    }

    int choose = RandomHelper.getRandomInt(0, adjacent.size() - 1);
    Coord next = adjacent.get(choose);
    return Result.move(animal, next);
  }
}

package com.isw.app.core.behavior;

import com.isw.app.enums.Direction;
import java.util.function.Predicate;
import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Sector;

public abstract class Behavior {
  public abstract Coord nextMove(Animal animal, Matrix matrix);

  public abstract boolean canEat(Coord pos, Matrix matrix);

  public abstract void update(Animal animal, boolean ate);

  public abstract boolean canReproduce(Animal animal);

  protected Coord[] adjacentCoords(Coord coord) {
    Coord[] result = new Coord[Direction.values().length];
    int i = 0;
    for (Direction dir : Direction.values()) {
      int[] p = dir.move(coord.getRow(), coord.getCol());
      result[i++] = new Coord(p[0], p[1]);
    }
    return result;
  }

  protected Coord findAdjacent(Coord current, Matrix matrix, Predicate<Sector> condition) {
    for (Coord pos : adjacentCoords(current)) {
      try {
        if (condition.test(matrix.getSectorAt(pos.getRow(), pos.getCol()))) {
          return pos;
        }
      } catch (IndexOutOfBoundsException ignored) {
      }
    }
    return null;
  }
}

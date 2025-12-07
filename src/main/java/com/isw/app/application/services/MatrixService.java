package com.isw.app.application.services;

import java.util.List;
import java.util.ArrayList;
import com.isw.app.domain.enums.Lineal;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Sector;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;

public class MatrixService {
  private Matrix matrix;

  public MatrixService(Matrix matrix) {
    this.matrix = matrix;
  }

  public List<Coord> getAdjacentCoords(Coord coord) {
    List<Coord> adjacent = new ArrayList<>();

    for (Lineal dir : Lineal.values()) {
      Coord move = coord.move(dir);

      if (matrix.isValidCoord(move)) {
        adjacent.add(move);
      }
    }

    return adjacent;
  }

  public List<Coord> getAdjacentEmptySectors(Coord coord) {
    List<Coord> empty = new ArrayList<>();

    for (Coord adjacent : getAdjacentCoords(coord)) {
      if (matrix.getSectorAt(adjacent).isEmpty()) {
        empty.add(adjacent);
      }
    }

    return empty;
  }

  public List<Animal> getAdjacentAnimals(Coord coord) {
    List<Animal> animals = new ArrayList<>();

    for (Coord adjacent : getAdjacentCoords(coord)) {
      Sector sector = matrix.getSectorAt(adjacent);

      if (!sector.isEmpty()) {
        animals.add(sector.getAnimal());
      }
    }

    return animals;
  }
}

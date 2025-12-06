package com.isw.app.core.objects;

import java.util.ArrayList;
import java.util.List;

import com.isw.app.enums.Direction;
import com.isw.app.helpers.RandomHelper;

public class Matrix {
  public static final int ROWS = 10;
  public static final int COLS = 10;
  private Sector[][] sectors;

  public Matrix() {
    sectors = new Sector[ROWS][COLS];
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Coord coord = new Coord(row, col);
        sectors[row][col] = new Sector(coord);
      }
    }
  }

  private boolean isValidCoord(Coord coord) {
    return coord.getRow() >= 0 && coord.getRow() < ROWS && coord.getCol() >= 0 && coord.getCol() < COLS;
  }

  public Sector getSectorAt(Coord coord) {
    if (isValidCoord(coord))
      return sectors[coord.getRow()][coord.getCol()];
    throw new IndexOutOfBoundsException("Invalid sector coordinates");
  }

  public Coord getRandomCoord() {
    int row = RandomHelper.getRandomInt(0, ROWS - 1);
    int col = RandomHelper.getRandomInt(0, COLS - 1);

    Coord coord = new Coord(row, col);
    if (getSectorAt(coord).isEmpty())
      return coord;
    return getRandomCoord();
  }

  public List<Coord> getAdjacentCoords(Coord coord) {
    List<Coord> adjacent = new ArrayList<>();

    for (Direction dir : Direction.values()) {
      Coord move = coord.move(dir);

      if (isValidCoord(move)) {
        adjacent.add(move);
      }
    }

    return adjacent;
  }

  public List<Coord> getAdjacentEmptySectors(Coord coord) {
    List<Coord> empty = new ArrayList<>();

    for (Coord adjacent : getAdjacentCoords(coord)) {
      if (getSectorAt(adjacent).isEmpty()) {
        empty.add(adjacent);
      }
    }

    return empty;
  }

  public List<Animal> getAdjacentAnimalsOfType(Coord coord, Detail detail) {
    List<Animal> animals = new ArrayList<>();

    for (Coord adjacent : getAdjacentCoords(coord)) {
      Sector sector = getSectorAt(adjacent);

      if (sector.hasAnimalOfType(detail)) {
        animals.add(sector.getAnimal());
      }  
    }

    return animals;
  }
}

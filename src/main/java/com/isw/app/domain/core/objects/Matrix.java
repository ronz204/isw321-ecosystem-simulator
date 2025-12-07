package com.isw.app.domain.core.objects;

import java.util.List;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.application.services.MatrixService;

public class Matrix {
  public static final int ROWS = 10;
  public static final int COLS = 10;
  private MatrixService service;
  private Sector[][] sectors;

  public Matrix() {
    this.service = new MatrixService(this);

    sectors = new Sector[ROWS][COLS];
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Coord coord = new Coord(row, col);
        sectors[row][col] = new Sector(coord);
      }
    }
  }

  public boolean isValidCoord(Coord coord) {
    int row = coord.getRow();
    int col = coord.getCol();
    return row >= 0 && row < ROWS && col >= 0 && col < COLS;
  }

  public Sector getSectorAt(Coord coord) {
    if (isValidCoord(coord))
      return sectors[coord.getRow()][coord.getCol()];
    throw new IndexOutOfBoundsException("Invalid sector coordinates");
  }

  public Coord getRandomCoord() {
    int row = RandomHelper.getRandomBetween(0, ROWS - 1);
    int col = RandomHelper.getRandomBetween(0, COLS - 1);

    Coord coord = new Coord(row, col);
    if (getSectorAt(coord).isEmpty())
      return coord;
    return getRandomCoord();
  }

  public List<Coord> getAdjacentCoords(Coord coord) {
    return service.getAdjacentCoords(coord);
  }

  public List<Coord> getAdjacentEmptySectors(Coord coord) {
    return service.getAdjacentEmptySectors(coord);
  }

  public List<Animal> getAdjacentAnimals(Coord coord) {
    return service.getAdjacentAnimals(coord);
  }
}

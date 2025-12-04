package com.isw.app.core.objects;

import java.util.List;
import java.util.ArrayList;
import com.isw.app.helpers.RandomHelper;

public class Matrix {
  private static final int ROWS = 10;
  private static final int COLS = 10;
  private Sector[][] sectors;

  public Matrix() {
    setupSectors();
  }

  private void setupSectors() {
    sectors = new Sector[ROWS][COLS];
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Coord coord = new Coord(row, col);
        sectors[row][col] = new Sector(coord);
      }
    }
  }

  private boolean isValidCoord(int row, int col) {
    return row >= 0 && row < ROWS && col >= 0 && col < COLS;
  }

  public Sector getSectorAt(int row, int col) {
    if (isValidCoord(row, col))
      return sectors[row][col];
    throw new IndexOutOfBoundsException("Invalid sector coordinates");
  }

  public static Coord getRandomCoord() {
    int row = RandomHelper.getRandomInt(0, ROWS - 1);
    int col = RandomHelper.getRandomInt(0, COLS - 1);
    return new Coord(row, col);
  }

  public List<Animal> getAllAnimals() {
    List<Animal> animals = new ArrayList<>();
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (!sectors[row][col].isEmpty()) {
          animals.add(sectors[row][col].getAnimal());
        }
      }
    }
    return animals;
  }

  public int countByType(Detail type) {
    int count = 0;
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (sectors[row][col].hasAnimalOfType(type)) {
          count++;
        }
      }
    }
    return count;
  }

  public int countEmpty() {
    int count = 0;
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (sectors[row][col].isEmpty()) {
          count++;
        }
      }
    }
    return count;
  }

  public double getOccupancyPercentage() {
    int total = ROWS * COLS;
    int occupied = total - countEmpty();
    return (occupied * 100.0) / total;
  }

  public List<Coord> getEmptySectors() {
    List<Coord> empty = new ArrayList<>();
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (sectors[row][col].isEmpty()) {
          empty.add(new Coord(row, col));
        }
      }
    }
    return empty;
  }

  public String toFileFormat() {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Animal animal = sectors[row][col].getAnimal();
        sb.append(animal != null ? animal.getDetail() : "EMPTY");
        if (col < COLS - 1)
          sb.append(",");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public static int getRows() {
    return ROWS;
  }

  public static int getCols() {
    return COLS;
  }
}

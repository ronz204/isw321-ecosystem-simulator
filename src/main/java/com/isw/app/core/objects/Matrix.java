package com.isw.app.core.objects;

public class Matrix {
  private final int ROWS = 10;
  private final int COLS = 10;
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
    if (isValidCoord(row, col)) return sectors[row][col];
    throw new IndexOutOfBoundsException("Invalid sector coordinates");
  }
}

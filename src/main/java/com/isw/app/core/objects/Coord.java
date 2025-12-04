package com.isw.app.core.objects;

public class Coord {
  private int row, col;

  public Coord(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Coord coord = (Coord) obj;
    return row == coord.row && col == coord.col;
  }

  @Override
  public int hashCode() {
    return 31 * row + col;
  }
}

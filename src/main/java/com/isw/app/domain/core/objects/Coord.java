package com.isw.app.domain.core.objects;

import com.isw.app.domain.enums.Lineal;

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

  public Coord move(Lineal direction) {
    return new Coord(
        this.row + direction.getDy(),
        this.col + direction.getDx());
  }
}

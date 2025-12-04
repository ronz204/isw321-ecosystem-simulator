package com.isw.app.core.objects;

import com.isw.app.enums.Direction;

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
  
  public void move(Direction direction) {
    this.row += direction.getDy();
    this.col += direction.getDx();
  }

  public boolean isEqual(Coord other) {
    return this.row == other.row && this.col == other.col;
  }
}

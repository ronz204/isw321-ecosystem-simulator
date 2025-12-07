package com.isw.app.domain.core.setup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Sector;
import com.isw.app.application.contexts.SimulationContext;

public class SimulatorRenderer {

  private SimulatorRenderer() {}

  public static void render() {
    SimulatorContext context = SimulatorContext.getInstance();
    SimulationContext simulation = SimulationContext.getInstance();

    simulation.getPane().getChildren().clear();
    GridPane pane = simulation.getPane();
    Matrix matrix = context.getMatrix();

    for (int row = 0; row < Matrix.ROWS; row++) {
      for (int col = 0; col < Matrix.COLS; col++) {
        Coord coord = new Coord(row, col);
        Sector sector = matrix.getSectorAt(coord);

        StackPane cell = buildCell(sector);
        pane.add(cell, col, row);
      }
    }
  }

  private static StackPane buildCell(Sector sector) {
    Label icon = new Label(sector.getIcon());
    icon.getStyleClass().add("matrix-cell__icon");
    icon.setAlignment(Pos.CENTER);
    icon.setMaxWidth(Double.MAX_VALUE);
    icon.setMaxHeight(Double.MAX_VALUE);

    StackPane stack = new StackPane(icon);
    stack.getStyleClass().add("matrix-cell");

    if (!sector.isEmpty()) {
      stack.getStyleClass().add("matrix-cell--occupied");
    }

    return stack;
  }
}

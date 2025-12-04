package com.isw.app.properties;

import javafx.beans.property.*;

import com.isw.app.models.Ecosystem;

public class SimulationProperties {
  public final StringProperty message = new SimpleStringProperty("");

  public final IntegerProperty maxTurns = new SimpleIntegerProperty(0);
  public final IntegerProperty preyCount = new SimpleIntegerProperty(0);
  public final IntegerProperty currentTurn = new SimpleIntegerProperty(0);
  public final IntegerProperty predatorCount = new SimpleIntegerProperty(0);

  public final BooleanProperty isRunning = new SimpleBooleanProperty(false);
  public final BooleanProperty isFinished = new SimpleBooleanProperty(false);

  public final ObjectProperty<Ecosystem> ecosystem = new SimpleObjectProperty<>();
}

package com.isw.app.core.objects;

public enum Action {
  IDLE("Sin accion"),
  MOVE("Movimiento"),
  EAT("Alimentacion"),
  DEATH("Fallecimiento"),
  REPRODUCE("Reproduccion");

  private final String display;

  Action(String display) {
    this.display = display;
  }

  public String getDisplay() {
    return display;
  }
}

package com.isw.app.domain.core.objects;

public enum Action {
  IDLE("Sin accion"),
  MOVE("Movimiento"),
  EAT("Alimentacion"),
  SEX("Reproduccion"),
  DEATH("Fallecimiento");

  private final String display;

  Action(String display) {
    this.display = display;
  }

  public String getDisplay() {
    return display;
  }
}

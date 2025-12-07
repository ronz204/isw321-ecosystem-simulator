package com.isw.app.domain.enums;

public enum FXMLPath {
  LOGIN("/presentation/login-presenter.fxml"),
  REGISTER("/presentation/register-presenter.fxml"),
  SIMULATION("/presentation/simulation-presenter.fxml");

  private final String path;

  FXMLPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}

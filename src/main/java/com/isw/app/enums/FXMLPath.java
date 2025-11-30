package com.isw.app.enums;

public enum FXMLPath {
  LOGIN("/presentation/login-presenter.fxml"),
  REGISTER("/presentation/register-presenter.fxml"),;

  private final String path;

  FXMLPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}

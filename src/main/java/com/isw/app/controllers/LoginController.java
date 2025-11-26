package com.isw.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

  @FXML
  private Label welcomeText;

  public void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }
}

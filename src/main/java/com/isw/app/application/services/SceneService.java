package com.isw.app.application.services;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class SceneService {
  private static Stage primary;

  public static void setStage(Stage stage) {
    primary = stage;
  }

  public static void switchTo(String path) {
    try {
      Parent root = FXMLLoader.load(SceneService.class.getResource(path));
      Scene scene = new Scene(root, 1000, 700);

      primary.setTitle("Ecosystem Simulator");
      primary.setScene(scene);
      primary.show();
    } catch (Exception e) {
      System.err.println("No se pudo cambiar la escena a: " + path);
      e.printStackTrace();
    }
  }
}

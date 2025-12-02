package com.isw.app.helpers;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class SwitcherHelper {
  private static Stage primary;

  public static void setStage(Stage stage) {
    primary = stage;
  }

  public static void switchTo(String path) {
    try {
      Parent root = FXMLLoader.load(SwitcherHelper.class.getResource(path));
      Scene scene = new Scene(root, 1000, 600);

      primary.setTitle("Ecosystem Simulator");
      primary.setScene(scene);
      primary.show();
    } catch (Exception e) {
      System.err.println("No se pudo cambiar la escena a: " + path);
      e.printStackTrace();
    }
  }
}

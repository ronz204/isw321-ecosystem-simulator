package com.isw.app;

import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Application;
import com.isw.app.domain.enums.FXMLPath;
import com.isw.app.application.services.SceneService;

public class Entrypoint extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    SceneService.setStage(stage);
    SceneService.switchTo(FXMLPath.LOGIN.getPath());
  }
}

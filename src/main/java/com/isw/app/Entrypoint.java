package com.isw.app;

import javafx.stage.Stage;
import java.io.IOException;
import com.isw.app.enums.FXMLPath;
import javafx.application.Application;
import com.isw.app.helpers.SwitcherHelper;

public class Entrypoint extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    SwitcherHelper.setStage(stage);
    SwitcherHelper.switchTo(FXMLPath.REGISTER.getPath());
  }
}

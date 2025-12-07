package com.isw.app.domain.core.setup;

import java.util.Map;
import java.util.List;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.application.contexts.SimulationContext;

public class SimulatorEngine {
  private Timeline timeline;

  public SimulatorEngine() {}

  public void start() {
    SimulationContext simulation = SimulationContext.getInstance();
    
    int maxTurns = simulation.getTurns();
    simulation.setCurrent(0);
    simulation.setRunning(true);

    timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
      execute();
      
      int currentTurn = simulation.getCurrent();
      simulation.setCurrent(currentTurn + 1);
      
      SimulatorRenderer.render();
      if (currentTurn >= maxTurns - 1) {
        stop();
      }
    }));

    timeline.setCycleCount(maxTurns);
    timeline.play();
  }

  public void stop() {
    if (timeline != null) {
      timeline.stop();
    }
    SimulationContext.getInstance().setRunning(false);
  }

  private void execute() {
    SimulatorContext context = SimulatorContext.getInstance();
    Map<Detail, List<Animal>> animals = context.getAnimals();
    Matrix matrix = context.getMatrix();

    for (Detail detail : Detail.values()) {
      List<Animal> list = animals.get(detail);
      
      if (list != null && !list.isEmpty()) {
        int index = RandomHelper.getChooseInt(list.size());
        Animal selected = list.get(index);
        selected.act(matrix);
      }
    }
  }
}

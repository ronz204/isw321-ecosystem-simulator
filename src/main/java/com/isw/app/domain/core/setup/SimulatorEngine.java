package com.isw.app.domain.core.setup;

import java.util.Map;
import java.util.List;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.application.contexts.SimulationContext;

public class SimulatorEngine {
  private Timeline timeline;

  private final SimulatorContext context;
  private final SimulationContext simulation;

  public SimulatorEngine() {
    this.context = SimulatorContext.getInstance();
    this.simulation = SimulationContext.getInstance();
  }

  public void start() {
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
    Map<Detail, List<Animal>> animals = context.getAnimals();

    for (Detail specie : Detail.values()) {
      List<Animal> species = animals.get(specie);

      if (species != null && !species.isEmpty()) {
        int index = RandomHelper.getChooseInt(species.size());
        Animal selected = species.get(index);
        selected.act(context.getMatrix());
      }
    }
  }
}

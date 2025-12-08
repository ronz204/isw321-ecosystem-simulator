package com.isw.app.domain.core.setup;

import java.util.Map;
import java.util.List;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.application.contexts.SimulationContext;
import com.isw.app.infrastructure.repositories.actions.ActionRepository;
import com.isw.app.infrastructure.repositories.actions.ActionTLQRepository;

public class SimulatorEngine {
  private Timeline timeline;
  private int currentSpeciesIndex = 0;

  private final SimulatorContext context;
  private final ActionRepository repository;
  private final SimulationContext simulation;

  public SimulatorEngine() {
    this.repository = new ActionTLQRepository();
    this.context = SimulatorContext.getInstance();
    this.simulation = SimulationContext.getInstance();
  }

  public void start() {
    int maxTurns = simulation.getTurns();

    simulation.setCurrent(0);
    simulation.setRunning(true);
    currentSpeciesIndex = 0;

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
    Detail[] allSpecies = Detail.values();
    
    List<Detail> availableSpecies = new java.util.ArrayList<>();
    for (Detail specie : allSpecies) {
      List<Animal> speciesList = animals.get(specie);
      if (speciesList != null && !speciesList.isEmpty()) {
        availableSpecies.add(specie);
      }
    }

    if (availableSpecies.isEmpty()) {
      return;
    }

    if (currentSpeciesIndex >= availableSpecies.size()) {
      currentSpeciesIndex = 0;
    }

    Detail currentSpecie = availableSpecies.get(currentSpeciesIndex);
    List<Animal> speciesAnimals = animals.get(currentSpecie);

    if (currentSpecie == Detail.CORPSE) {
      currentSpeciesIndex++;
      if (currentSpeciesIndex >= availableSpecies.size()) {
        currentSpeciesIndex = 0;
      }
      return;
    }

    int animalIndex = RandomHelper.getChooseInt(speciesAnimals.size());
    Animal selected = speciesAnimals.get(animalIndex);
    
    Result resultt = selected.act(context.getMatrix());
    repository.save(resultt);

    currentSpeciesIndex++;
    if (currentSpeciesIndex >= availableSpecies.size()) {
      currentSpeciesIndex = 0;
    }
  }
}

package com.isw.app.handlers.simulate;

import com.isw.app.core.artifacts.ContextArtifact;
import com.isw.app.properties.SimulationProperties;
import com.isw.app.core.expansions.species.OmnivoreExpansion;
import com.isw.app.core.expansions.species.CarnivoreExpansion;
import com.isw.app.core.expansions.species.HerbivoreExpansion;

public class SimulateEcosystemHandler {
  private final ContextArtifact context;
  private final SimulationProperties properties;

  public SimulateEcosystemHandler(SimulationProperties properties) {
    this.properties = properties;
    this.context = ContextArtifact.getInstance();
  }

  public ContextArtifact getContext() {
    return context;
  }

  public void handle(SimulateEcosystemSchema schema) {
    context.initialize();

    context.addSpecies(new OmnivoreExpansion(schema.getBalance()));
    context.addSpecies(new HerbivoreExpansion(schema.getBalance()));
    context.addSpecies(new CarnivoreExpansion(schema.getBalance()));

    context.applyExpansions();
    context.populateEcosystem();

    properties.setTurns(schema.getMaxTurns());
  }
}

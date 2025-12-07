package com.isw.app.application.handlers.simulate;

import com.isw.app.domain.core.setup.SimulatorConfig;
import com.isw.app.domain.core.setup.SimulatorInitializer;
import com.isw.app.presentation.properties.SimulationProperties;
import com.isw.app.domain.core.artifacts.expansions.HerbivoreExpansion;

public class SimulateEcosystemHandler {

  private final SimulatorInitializer initializer;
  private final SimulationProperties properties;

  public SimulateEcosystemHandler(SimulationProperties properties) {
    this.properties = properties;
    this.initializer = SimulatorInitializer.getInstance();
  }

  public void handle(SimulateEcosystemSchema schema) {
    SimulatorConfig config = new SimulatorConfig();
    config.addExpansion(new HerbivoreExpansion(schema.getBalance()));

    initializer.setNewConfig(config);
    initializer.initialize();

    properties.setSuccess(true, "Simulación completada exitosamente.");
  }
}

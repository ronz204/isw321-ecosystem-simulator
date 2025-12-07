package com.isw.app.handlers.simulate;

import com.isw.app.presentation.properties.SimulationProperties;

public class SimulateEcosystemHandler {

  private final SimulationProperties properties;

  public SimulateEcosystemHandler(SimulationProperties properties) {
    this.properties = properties;
  }

  public void handle(SimulateEcosystemSchema schema) {
    properties.setSuccess(true, "Simulación completada exitosamente.");
  }
}

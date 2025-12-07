package com.isw.app.application.handlers.simulate;


import com.isw.app.application.contexts.SimulationContext;

public class SimulateEcosystemHandler {

  private final SimulationContext simulation = SimulationContext.getInstance();

  public void handle(SimulateEcosystemSchema schema) {
    simulation.setSuccess(true, "Simulación completada exitosamente.");
  }
}

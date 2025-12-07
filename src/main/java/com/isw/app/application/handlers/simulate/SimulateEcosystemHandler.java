package com.isw.app.application.handlers.simulate;


import com.isw.app.application.contexts.SimulationContext;

public class SimulateEcosystemHandler {

  private final SimulationContext context;

  public SimulateEcosystemHandler() {
    this.context = SimulationContext.getInstance();
  }

  public void handle(SimulateEcosystemSchema schema) {
    context.setSuccess(true, "Simulación completada exitosamente.");
  }
}

package com.isw.app.domain.core.setup;

public class SimulatorInitializer {

  private SimulatorInitializer() {}

  public static void simulate() {
    SimulatorConfig config = SimulatorConfig.getInstance();
    config.apply();
    
    SimulatorPopulator.populate();
    SimulatorRenderer.render();
    
    SimulatorEngine engine = new SimulatorEngine();
    engine.start();
  }

  public static void render() {
    SimulatorRenderer.render();
  }
}

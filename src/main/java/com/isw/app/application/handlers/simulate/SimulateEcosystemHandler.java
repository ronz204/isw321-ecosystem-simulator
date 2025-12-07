package com.isw.app.application.handlers.simulate;

import com.isw.app.domain.core.setup.SimulatorConfig;
import com.isw.app.domain.core.setup.SimulatorContext;
import com.isw.app.application.contexts.SimulationContext;
import com.isw.app.domain.core.setup.SimulatorInitializer;
import com.isw.app.domain.core.artifacts.mutations.ZombieMutation;
import com.isw.app.domain.core.artifacts.expansions.OmnivoreExpansion;
import com.isw.app.domain.core.artifacts.expansions.HerbivoreExpansion;
import com.isw.app.domain.core.artifacts.expansions.CarnivoreExpansion;

public class SimulateEcosystemHandler {

  public void handle(SimulateEcosystemSchema schema) {
    SimulatorConfig.reset();
    SimulatorContext.reset();

    SimulatorConfig config = SimulatorConfig.getInstance();
    SimulationContext simulation = SimulationContext.getInstance();

    config.addExpansion(new HerbivoreExpansion(schema.getBalance()));
    config.addExpansion(new CarnivoreExpansion(schema.getBalance()));

    if (schema.getFlagZombieMutation()) {
      config.addMutation(new ZombieMutation());
    }

    if (schema.getFlagOmnivoreExpansion()) {
      config.addExpansion(new OmnivoreExpansion(schema.getBalance()));
    }

    SimulatorInitializer.simulate();
    simulation.setSuccess(true, "Simulación completada exitosamente.");
  }
}

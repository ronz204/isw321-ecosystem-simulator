package com.isw.app.application.handlers.simulate;

import com.isw.app.domain.models.Ecosystem;
import com.isw.app.domain.core.setup.SimulatorConfig;
import com.isw.app.domain.core.setup.SimulatorContext;
import com.isw.app.application.contexts.SimulationContext;
import com.isw.app.domain.core.setup.SimulatorInitializer;
import com.isw.app.domain.core.artifacts.mutations.ZombieMutation;
import com.isw.app.domain.core.artifacts.expansions.OmnivoreExpansion;
import com.isw.app.domain.core.artifacts.expansions.HerbivoreExpansion;
import com.isw.app.domain.core.artifacts.expansions.CarnivoreExpansion;
import com.isw.app.infrastructure.repositories.ecosystem.EcosystemRepository;

public class SimulateEcosystemHandler {

  private final EcosystemRepository repository;

  public SimulateEcosystemHandler(EcosystemRepository repository) {
    this.repository = repository;
  }

  public void handle(SimulateEcosystemSchema schema) {
    Ecosystem ecosystem = new Ecosystem(schema.getMaxTurns(), schema.getBalance(),
        schema.getFlagZombieMutation(), schema.getFlagOmnivoreExpansion());
    repository.save(ecosystem);

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

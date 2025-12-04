package com.isw.app.handlers.simulate;

import com.isw.app.enums.Balance;
import com.isw.app.properties.SimulationProperties;

public class SimulateEcosystemHandler {
  private final SimulationProperties properties;

  public SimulateEcosystemHandler() {
    this.properties = new SimulationProperties();
  }

  public SimulationProperties getProperties() {
    return properties;
  }

  public SimulateEcosystemResponse handle(SimulateEcosystemCommand command) {
    Balance.fromValue(command.balanceType());

    if (command.maxTurns() < 1) {
      throw new IllegalArgumentException("El número de turnos debe ser mayor a 0.");
    }

    properties.maxTurns.set(command.maxTurns());
    properties.message.set("Simulación configurada exitosamente");
    
    return new SimulateEcosystemResponse("Simulación iniciada correctamente", true);
  }
}

package com.isw.app.handlers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class SimulateEcosystemSchema {
  @NotNull(message = "El número de turnos es obligatorio.")
  @Min(value = 1, message = "El número de turnos debe ser al menos 1.")
  private Integer maxTurns;

  @NotBlank(message = "Debe seleccionar un escenario de balance.")
  private String balanceType;

  private boolean thirdSpeciesEnabled;
  private boolean geneticMutationEnabled;

  public SimulateEcosystemSchema(Integer maxTurns, String balanceType,
      boolean thirdSpeciesEnabled, boolean geneticMutationEnabled) {
    this.maxTurns = maxTurns;
    this.balanceType = balanceType;
    this.thirdSpeciesEnabled = thirdSpeciesEnabled;
    this.geneticMutationEnabled = geneticMutationEnabled;
  }

  public SimulateEcosystemCommand toCommand() {
    return new SimulateEcosystemCommand(maxTurns, balanceType, thirdSpeciesEnabled, geneticMutationEnabled);
  }
}

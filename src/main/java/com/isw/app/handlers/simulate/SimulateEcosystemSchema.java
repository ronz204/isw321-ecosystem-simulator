package com.isw.app.handlers.simulate;

import com.isw.app.enums.Balance;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class SimulateEcosystemSchema {
  @NotNull(message = "El número de turnos es obligatorio.")
  @Min(value = 1, message = "El número de turnos debe ser al menos 1.")
  private Integer maxTurns;

  @NotBlank(message = "Debe seleccionar un escenario de balance.")
  private String balance;

  public SimulateEcosystemSchema(Integer maxTurns, String balance) {
    this.balance = balance;
    this.maxTurns = maxTurns;
  }

  public Balance getBalance() {
    return Balance.fromLabel(balance);
  }

  public Integer getMaxTurns() {
    return maxTurns;
  }
}

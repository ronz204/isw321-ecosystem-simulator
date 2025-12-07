package com.isw.app.application.handlers.simulate;

import com.isw.app.domain.enums.Balance;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SimulateEcosystemSchema {
  @NotNull(message = "El número de turnos es obligatorio.")
  @Min(value = 1, message = "El número de turnos debe ser al menos 1.")
  private Integer turns;

  @NotNull(message = "Debe seleccionar un escenario de balance.")
  private Balance balance;

  private Boolean flagZombieMutation;
  private Boolean flagOmnivoreExpansion;

  public SimulateEcosystemSchema(Integer turns, Balance balance) {
    this.balance = balance;
    this.turns = turns;
  }

  public Balance getBalance() {
    return balance;
  }

  public Integer getMaxTurns() {
    return turns;
  }

  public Boolean getFlagZombieMutation() {
    return flagZombieMutation;
  }

  public void setFlagZombieMutation(Boolean flag) {
    this.flagZombieMutation = flag;
  }

  public Boolean getFlagOmnivoreExpansion() {
    return flagOmnivoreExpansion;
  }

  public void setFlagOmnivoreExpansion(Boolean flag) {
    this.flagOmnivoreExpansion = flag;
  }
}

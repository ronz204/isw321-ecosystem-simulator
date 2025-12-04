package com.isw.app.handlers.simulate;

public record SimulateEcosystemCommand(
    Integer maxTurns,
    String balanceType,
    boolean thirdSpeciesEnabled,
    boolean geneticMutationEnabled) {
}

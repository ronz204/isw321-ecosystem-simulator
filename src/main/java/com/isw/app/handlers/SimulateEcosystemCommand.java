package com.isw.app.handlers;

public record SimulateEcosystemCommand(
    Integer maxTurns,
    String balanceType,
    boolean thirdSpeciesEnabled,
    boolean geneticMutationEnabled) {
}

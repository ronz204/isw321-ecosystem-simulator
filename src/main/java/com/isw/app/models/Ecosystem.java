package com.isw.app.models;

import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Detail;

public class Ecosystem {
  private int currentTurn;
  private final int maxTurns;
  private final Matrix matrix;
  private final String balanceType;

  private final boolean thirdSpeciesEnabled;
  private final boolean geneticMutationEnabled;

  public Ecosystem(Matrix matrix, int maxTurns, String balanceType,
      boolean thirdSpeciesEnabled, boolean geneticMutationEnabled) {
    this.matrix = matrix;
    this.maxTurns = maxTurns;
    this.currentTurn = 0;
    this.balanceType = balanceType;
    this.thirdSpeciesEnabled = thirdSpeciesEnabled;
    this.geneticMutationEnabled = geneticMutationEnabled;
  }

  public Matrix getMatrix() {
    return matrix;
  }

  public int getMaxTurns() {
    return maxTurns;
  }

  public int getCurrentTurn() {
    return currentTurn;
  }

  public void incrementTurn() {
    currentTurn++;
  }

  public String getBalanceType() {
    return balanceType;
  }

  public boolean isThirdSpeciesEnabled() {
    return thirdSpeciesEnabled;
  }

  public boolean isGeneticMutationEnabled() {
    return geneticMutationEnabled;
  }

  public int getPreyCount() {
    return matrix.countByType(Detail.HERBIVORE);
  }

  public int getPredatorCount() {
    return matrix.countByType(Detail.CARNIVORE);
  }

  public boolean isFinished() {
    return currentTurn >= maxTurns || getPreyCount() == 0 || getPredatorCount() == 0;
  }
}

package com.isw.app.application.reports;

import java.util.Map;
import java.util.HashMap;
import com.isw.app.domain.core.objects.Detail;

public class ReportData {
  private int totalTurns;
  private Map<Detail, Integer> extinctionData;
  private int preyCount;
  private int predatorCount;
  private int occupiedCells;
  private int emptyCells;

  public ReportData() {
    this.extinctionData = new HashMap<>();
  }

  public int getTotalTurns() {
    return totalTurns;
  }

  public void setTotalTurns(int totalTurns) {
    this.totalTurns = totalTurns;
  }

  public Map<Detail, Integer> getExtinctionData() {
    return extinctionData;
  }

  public void setExtinctionData(Map<Detail, Integer> extinctionData) {
    this.extinctionData = extinctionData;
  }

  public boolean hasExtinctions() {
    return extinctionData.values().stream().anyMatch(turn -> turn > 0);
  }

  public int getPreyCount() {
    return preyCount;
  }

  public void setPreyCount(int preyCount) {
    this.preyCount = preyCount;
  }

  public int getPredatorCount() {
    return predatorCount;
  }

  public void setPredatorCount(int predatorCount) {
    this.predatorCount = predatorCount;
  }

  public int getOccupiedCells() {
    return occupiedCells;
  }

  public void setOccupiedCells(int occupiedCells) {
    this.occupiedCells = occupiedCells;
  }

  public int getEmptyCells() {
    return emptyCells;
  }

  public void setEmptyCells(int emptyCells) {
    this.emptyCells = emptyCells;
  }
}

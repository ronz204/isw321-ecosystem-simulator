package com.isw.app.application.reports;

import java.util.Map;
import java.util.HashMap;
import com.isw.app.domain.core.objects.Detail;

public class ReportData {
  private int totalTurns;
  private Map<Detail, Integer> extinctionData;

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
}

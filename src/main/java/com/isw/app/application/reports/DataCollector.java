package com.isw.app.application.reports;

import com.isw.app.application.contexts.SimulationContext;
import com.isw.app.domain.core.setup.SimulatorContext;

public class DataCollector {

  public ReportData collect() {
    SimulationContext simContext = SimulationContext.getInstance();
    SimulatorContext simSetup = SimulatorContext.getInstance();

    ReportData data = new ReportData();
    data.setTotalTurns(simContext.getCurrent());
    data.setExtinctionData(simSetup.getExtinctions());

    return data;
  }
}

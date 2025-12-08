package com.isw.app.application.reports;

import com.isw.app.application.contexts.SimulationContext;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.setup.SimulatorContext;

import java.util.List;
import java.util.Map;

public class DataCollector {

  public ReportData collect() {
    SimulationContext simContext = SimulationContext.getInstance();
    SimulatorContext simSetup = SimulatorContext.getInstance();

    ReportData data = new ReportData();
    data.setTotalTurns(simContext.getCurrent());
    data.setExtinctionData(simSetup.getExtinctions());

    Map<Detail, List<Animal>> animals = simSetup.getAnimals();
    int preyCount = animals.get(Detail.HERBIVORE).size();
    int predatorCount = animals.get(Detail.CARNIVORE).size() + 
                        animals.get(Detail.OMNIVORE).size() + 
                        animals.get(Detail.ZOMBIE).size();
    
    data.setPreyCount(preyCount);
    data.setPredatorCount(predatorCount);

    Matrix matrix = simSetup.getMatrix();
    int totalCells = Matrix.ROWS * Matrix.COLS;
    int occupiedCells = 0;

    for (int row = 0; row < Matrix.ROWS; row++) {
      for (int col = 0; col < Matrix.COLS; col++) {
        if (!matrix.getSectorAt(new com.isw.app.domain.core.objects.Coord(row, col)).isEmpty()) {
          occupiedCells++;
        }
      }
    }

    data.setOccupiedCells(occupiedCells);
    data.setEmptyCells(totalCells - occupiedCells);

    return data;
  }
}
